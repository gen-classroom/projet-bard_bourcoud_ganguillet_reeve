package com.mycompany.app.picocli_sub_command;

import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "build", description = "build command")
public class SubCommandBuild implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("Buid static website");
        return 0;
    }
}
