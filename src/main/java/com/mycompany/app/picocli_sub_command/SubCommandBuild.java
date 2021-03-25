package com.mycompany.app.picocli_sub_command;

import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "build", description = "build command")
public class SubCommandBuild implements Callable<Integer> {

    @Override
    public Integer call() {
        //créer sous-dossier build
        return 0;
    }
}
