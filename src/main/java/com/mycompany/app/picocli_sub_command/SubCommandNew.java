package com.mycompany.app.picocli_sub_command;


import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(name = "new")
public class SubCommandNew implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("New.");
        return 0;
    }
}
