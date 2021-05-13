package com.mycompany.app.picocli_sub_command;

import picocli.CommandLine.Command;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

import com.mycompany.app.serve.StaticFileHandler;
import com.sun.net.httpserver.HttpServer;

@Command(name = "serve", description = "serve command")
public class SubCommandServe implements Callable<Integer> {
    private static int HTTP_PORT = 8080;
    private static String URL_PREFIX = "/";

    @Override
    public Integer call() throws Exception {
        System.out.println("Serving static website at http://localhost:" + HTTP_PORT + URL_PREFIX);

        String buildDirectory = System.getProperty("user.dir") + File.separator + "build";

        HttpServer server = HttpServer.create(new InetSocketAddress(HTTP_PORT), 0);
        server.createContext("/", new StaticFileHandler(buildDirectory, URL_PREFIX));
        server.start();

        while (true) {
        }
    }
}
