package com.mycompany.app.picocli_sub_command;

import com.mycompany.app.Page;
import picocli.CommandLine.Command;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.Callable;

@Command(name = "build", description = "build command")
public class SubCommandBuild implements Callable<Integer> {

    @Override
    public Integer call() throws IOException {
        //créer sous-dossier build
        String repertoireCourant = System.getProperty("user.dir") + "/mySite";
        //TODO : enlever le mySite quand on fait
        // le .jar
        final File mySite = new File(repertoireCourant);
        File build = new File(repertoireCourant + "/build");
        System.out.println(repertoireCourant);
        build.mkdirs();

        Files.walkFileTree(mySite.toPath(), new BuildVisitor(mySite.toPath(), build.toPath()));
        //parcourirEtCopier(main);

        return 0;
    }

    public static void parcourirEtCopier(File node) {

        System.out.println(node);

        if (node.isDirectory()) {
            if (node.getName().equals("build")) return;
            String[] subNote = node.list();
            for (String filename : subNote) {

                parcourirEtCopier(new File(node, filename));
            }
        }
    }
}

class BuildVisitor extends SimpleFileVisitor<Path> {

    private final Path mySite;
    private final Path build;

    public BuildVisitor(Path mySite, Path build) {
        this.mySite = mySite;
        this.build = build;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (dir.toAbsolutePath().equals(build.toAbsolutePath())) {
            return FileVisitResult.SKIP_SUBTREE;
        }
        Path relative = mySite.relativize(dir);
        Path newDir = build.resolve(relative);
        newDir.toFile().mkdirs();
        super.preVisitDirectory(dir, attrs);
        return FileVisitResult.CONTINUE;
}

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.print(file + " -> ");
        Path relative = mySite.relativize(file);
        Path htmlFile = build.resolve(relative);
        if (file.toString().toLowerCase().endsWith(".md")) {
            Page page = new Page(file.toFile());
            String filename = htmlFile.toAbsolutePath().toString();
            String prefix = filename.substring(0, filename.length() - 3);
            FileOutputStream writer = new FileOutputStream(prefix + ".html");
            writer.write(page.getContentAsHtml().getBytes());

            writer.close();

            System.out.println(htmlFile);
        } else {
            // TODO: Copie fichier
            System.out.println(htmlFile);
        }

        super.visitFile(file, attrs);

        return FileVisitResult.CONTINUE;
    }
}
