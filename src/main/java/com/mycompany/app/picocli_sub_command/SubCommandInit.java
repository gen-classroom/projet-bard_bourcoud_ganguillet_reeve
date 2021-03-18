package com.mycompany.app.picocli_sub_command;


import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(name = "init")
public class SubCommandInit implements Callable<Integer> {

    @Override
    // pull request
    public Integer call() {
        System.out.println("Init.");
        return 0;
    }
}
