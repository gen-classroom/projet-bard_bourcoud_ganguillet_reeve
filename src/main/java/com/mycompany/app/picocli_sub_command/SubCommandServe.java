package com.mycompany.app.picocli_sub_command;

import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "serve", description = "serve command")
public class SubCommandServe implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("Serve static website");
        return 0;
    }
}
