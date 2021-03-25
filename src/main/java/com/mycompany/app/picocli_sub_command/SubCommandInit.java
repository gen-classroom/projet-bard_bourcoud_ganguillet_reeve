package com.mycompany.app.picocli_sub_command;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "init")
public class SubCommandInit implements Callable<Integer> {

    @CommandLine.Parameters(index = "0..*", description = "the root pathname")
    private String rootPathname;

    @Override
    public Integer call() throws IOException {
        if (!(rootPathname.charAt(0) == '/')){
            System.out.println("The parameters must begin with /");
            return 0;
        }
        StringBuilder path = new StringBuilder(System.getProperty("user.dir") + rootPathname);

        new File(path.toString()).mkdirs();
        Writer indexWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(path.toString() + "/index.md"),
                        StandardCharsets.UTF_8
                )
        );

        indexWriter.write("something");

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
        new File(path.toString()).mkdir();



        return 0;
    }
}
