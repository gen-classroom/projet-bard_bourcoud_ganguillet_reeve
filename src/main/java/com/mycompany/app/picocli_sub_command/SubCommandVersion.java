package com.mycompany.app.picocli_sub_command;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "-version", description = "Give the version of the programm.")
public class SubCommandVersion implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("version : 0.0.1");
        return 0;
    }
}
