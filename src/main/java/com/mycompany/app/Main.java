package com.mycompany.app;

import java.util.concurrent.Callable;
import com.mycompany.app.picocli_sub_command.*;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "gen", subcommands = { SubCommandClean.class, SubCommandBuild.class, SubCommandInit.class,
        SubCommandServe.class, SubCommandVersion.class})
public class Main implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("Please use one of the subcommands: 'new', 'clean', 'build' or 'serve'");
        return 0;
    }

    public static void main(String[] args) {
        int rc = new CommandLine(new Main()).execute(args);
        System.exit(rc);
    }
}
