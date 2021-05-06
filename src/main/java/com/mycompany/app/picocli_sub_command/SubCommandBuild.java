package com.mycompany.app.picocli_sub_command;

import com.mycompany.app.template.MDPageParser;
import com.mycompany.app.template.PageData;
import com.mycompany.app.template.SiteConfig;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import picocli.CommandLine.Command;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.Callable;

@Command(name = "build", description = "build command")
public class SubCommandBuild implements Callable<Integer> {

    @Override
    public Integer call() throws IOException {
        String currentDirectory = System.getProperty("user.dir"); // + "/mySite";
        final Path mySite = Paths.get(currentDirectory);
        Path build = Paths.get(currentDirectory + "/build");
        build.toFile().mkdirs();
        SiteConfig config = new SiteConfig(mySite.toString() + "/config.json");
        Files.walkFileTree(mySite, new BuildVisitor(mySite, build, config));

        return 0;
    }

    /**
     * Visiteur convertissant les fichiers Html et copiant les autres fichiers du chemin source au chemin de destination.
     */
    private static class BuildVisitor extends SimpleFileVisitor<Path> {

        // Le chemin source du site.
        private final Path mySite;
        // Le chemin de destination où les fichiers seront convertis ou copiés.
        private final Path build;
        // La config globale du site
        private final SiteConfig config;

        /**
         * @param mySite Le chemin source du site.
         * @param build  Le chemin où les fichiers seront convertis ou copiés.
         */
        public BuildVisitor(Path mySite, Path build, SiteConfig config) {
            this.mySite = mySite;
            this.build = build;
            this.config = config;
        }

        /**
         * @param dir   Le chemin du dossier qui sera visité.
         * @param attrs attributs du dossier
         * @return le résultat de la visite
         */
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            if (dir.toAbsolutePath().equals(build.toAbsolutePath())) {
                return FileVisitResult.SKIP_SUBTREE;
            }

            Path relative = mySite.relativize(dir);
            Path newDir = build.resolve(relative);
            newDir.toFile().mkdirs();

            return FileVisitResult.CONTINUE;
        }

        /**
         * @param file  chemin du fichier à visiter
         * @param attrs attributs du fichier à visiter
         * @return résultat de la visite
         * @throws IOException à cause de l'utilisation de FileOutputStream
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Path relative = mySite.relativize(file);
            Path newFile = build.resolve(relative);
            if (file.toString().toLowerCase().endsWith(".md")) {



                MDPageParser parser = new MDPageParser(config);
                FileReader layout = new FileReader(build+"/layout.html");
                Template template = Mustache.compiler().compile(layout.toString());
                PageData pageData = parser.parse(new FileReader(file.toFile()));

                /*String filename = newFile.toAbsolutePath().toString();
                String prefix = filename.substring(0, filename.length() - 3);
                FileOutputStream writer = new FileOutputStream(prefix + ".html");
                writer.write(page.getContentAsHtml().getBytes());

                writer.close();*/
            } else {
                Files.copy(file, newFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return FileVisitResult.CONTINUE;
        }
    }
}


