package com.mycompany.app.picocli_sub_command;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "init")
public class SubCommandInit implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "the root pathname")
    private String rootPathname;

    @Override
    public Integer call() throws IOException {
        if (!(rootPathname.charAt(0) == '/')){
            System.out.println("The parameters must begin with /");
            return 0;
        }
        StringBuilder path = new StringBuilder(System.getProperty("user.dir") + rootPathname);

        if(new File(path.toString()).mkdirs()){
            System.out.println("error :directory can't be created");
        }
        Writer indexWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(path.toString() + "/index.md"),
                        StandardCharsets.UTF_8
                )
        );

        final String baseIndexText = "titre: Mon premier article\n" +
                "auteur: Joe Doe\n" +
                "date: 2021-03-10"  +
                "---\n" +
                "# Mon premier article\n" +
                "## Mon sous-titre\n" +
                "Le contenu de mon article.";

        indexWriter.write(baseIndexText);

        indexWriter.close();

        Writer configWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(path.toString() + "/config.json"),
                        StandardCharsets.UTF_8
                )
        );

        configWriter.write("something");

        configWriter.close();
        path.append("/contents");
        if(new File(path.toString()).  mkdir()){
            System.out.println("error :directory can't be created");
        }

        return 0;
    }
}
