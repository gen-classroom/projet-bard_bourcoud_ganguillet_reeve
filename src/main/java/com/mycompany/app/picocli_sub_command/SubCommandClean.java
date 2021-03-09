package com.mycompany.app.picocli_sub_command;

import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(name = "clean")
public class SubCommandClean implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("Clean.");
        return 0;
    }
}