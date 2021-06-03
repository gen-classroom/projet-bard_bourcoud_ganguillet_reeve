package com.mycompany.app.picocli_sub_command;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.Callable;

import com.mycompany.app.serve.StaticFileHandler;
import com.sun.net.httpserver.HttpServer;

@Command(name = "serve", description = "serve command")
public class SubCommandServe implements Callable<Integer> {
    private static int HTTP_PORT = 8080;
    private static String URL_PREFIX = "/";

    @CommandLine.Option(names = { "--watch" }, description = "run build every time a file has changed")
    private boolean watch;

    @Override
    public Integer call() throws Exception {
        System.out.println("Serving static website at http://localhost:" + HTTP_PORT + URL_PREFIX);

        String currentDirectory = System.getProperty("user.dir");
        final Path mySite = Paths.get(currentDirectory);
        Path destination = Paths.get(currentDirectory + File.separator + "build");

        SubCommandBuild.build(mySite, destination);

        HttpServer server = HttpServer.create(new InetSocketAddress(HTTP_PORT), 0);
        server.createContext("/", new StaticFileHandler(destination.toString(), URL_PREFIX));
        server.start();

        if (watch) {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            mySite.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            boolean rebuild = false;
            WatchKey key = watchService.take();
            while (key != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    Path changedFile = mySite.resolve(event.context().toString()).toAbsolutePath();
                    if (!changedFile.startsWith(destination.toAbsolutePath())) {
                        rebuild = true;
                    }
                    System.out.println("Event : " + event.context());
                }

                if (rebuild) {
                    System.out.println("Changed detected : rebuilding");
                    SubCommandBuild.build(mySite, destination);
                    rebuild = false;
                }

                key.reset();
                key = watchService.take();
            }
        } else {
            while (true) {
            }
        }

        return 0;
    }
}
