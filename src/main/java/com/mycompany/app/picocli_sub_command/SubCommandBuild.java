package com.mycompany.app.picocli_sub_command;

import com.mycompany.app.template.MDPageParser;
import com.mycompany.app.template.PageData;
import com.mycompany.app.template.SiteConfig;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.Callable;

/**
 * Commande générant un site statique à partir de fichiers Markdown.
 */
@Command(name = "build", description = "build command")
public class SubCommandBuild implements Callable<Integer> {

    /**
     * Chemin du dossier contenant le site à construire.
     */
    @CommandLine.Parameters(index = "0", description = "the root pathname")
    private String rootPathname;

    /**
     * @return  0 si construction réussie
     *          1 si dossier du site non trouvé depuis le répertoire courant
     * @throws IOException si erreur dans la lecture des fichiers du site
     */
    @Override
    public Integer call() throws IOException {
        if (!(rootPathname.charAt(0) == '/')) {
            System.out.println("The parameters must begin with /");
            return 1;
        }

        String currentDirectory = System.getProperty("user.dir") + rootPathname;
        final Path mySite = Paths.get(currentDirectory);
        Path build = Paths.get(currentDirectory + "/build");
        build.toFile().mkdirs();

        SiteConfig config = new SiteConfig(mySite.toString() + "/config.json");
        MDPageParser parser = new MDPageParser(config);
        FileReader layout = new FileReader(currentDirectory + "/template/layout.html");
        Template template = Mustache
                .compiler()
                .withLoader((String name) -> new FileReader(new File(currentDirectory + "/template/", name)))
                .compile(layout);

        Files.walkFileTree(mySite, new BuildVisitor(mySite, build, parser, template));

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
        // Parser MD -> Html
        MDPageParser parser;
        // Le fichier template de base pour une page
        private Template layout;

        /**
         * @param mySite Le chemin source du site.
         * @param build  Le chemin où les fichiers seront convertis ou copiés.
         */
        public BuildVisitor(Path mySite, Path build, MDPageParser parser, Template layout) {
            this.mySite = mySite;
            this.build = build;
            this.parser = parser;
            this.layout = layout;
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

                PageData pageData = parser.parse(file.toFile());
                String out = layout.execute(pageData);

                String filename = newFile.toAbsolutePath().toString();
                String prefix = filename.substring(0, filename.length() - 3);
                FileWriter writer = new FileWriter(prefix + ".html");
                writer.write(out);
                writer.close();
            } else {
                Files.copy(file, newFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return FileVisitResult.CONTINUE;
        }
    }
}


