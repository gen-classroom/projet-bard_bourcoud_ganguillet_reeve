package com.mycompany.app;

import com.mycompany.app.picocli_sub_command.SubCommandBuild;
import com.mycompany.app.picocli_sub_command.SubCommandInit;
import com.mycompany.app.template.MDPageParser;
import com.mycompany.app.template.PageData;
import com.mycompany.app.template.SiteConfig;
import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class benchmark {
    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 5, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testMethod(MyState state) throws IOException {

        MDPageParser parser = new MDPageParser(new SiteConfig(state.siteConfig));
        parser.parse(state.md);
        return;
    }

    @State(Scope.Thread)
    public static class MyState {
        String dir = "/test";
        File md = new File("./test/index.md");
        String siteConfig = "./test/config.json";

        @Setup (Level.Trial)
        public void doSetup() throws IOException {
            SubCommandInit init = new SubCommandInit();
            init.setRootPathname(dir);
            init.call();
        }
    }
}
