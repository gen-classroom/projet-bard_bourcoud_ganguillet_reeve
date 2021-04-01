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
