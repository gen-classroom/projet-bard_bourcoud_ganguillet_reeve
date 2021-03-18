package com.mycompany.app.picocli_sub_command;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "-version", description = "Give the version of the site.")
public class SubCommandVersion implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        System.out.println();
        return 0;
    }
}
