package com.mycompany.app.serve;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class StaticFileHandler implements HttpHandler {
    private static final Map<String, String> MIME_TYPES = new HashMap<>();

    static {
        MIME_TYPES.put("css", "text/css");
        MIME_TYPES.put("html", "text/html");
        MIME_TYPES.put("js", "application/javascript");
        MIME_TYPES.put("jpg", "image/jpeg");
        MIME_TYPES.put("jpeg", "image/jpeg");
        MIME_TYPES.put("png", "image/png");
        MIME_TYPES.put("txt", "text/plain");
    }

    private static String getMimeType(String url) {
        String defaultMimeType = "text/plain";

        String[] parts = url.split("\\.");
        if (parts.length > 0) {
            return MIME_TYPES.getOrDefault(parts[parts.length - 1], defaultMimeType);
        } else {
            return defaultMimeType;
        }
    }

    private String prefixUrl;
    private String servedDirectory;

    public StaticFileHandler(String servedDirectory, String prefixUrl) {
        this.prefixUrl = prefixUrl;
        this.servedDirectory = servedDirectory;
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String method = he.getRequestMethod();
        if (method.equals("HEAD") && method.equals("GET")) {
            sendError(he, 405, "Method Not Allowed");
            return;
        }

        String requestedPath = he.getRequestURI().getPath();
        if (requestedPath.endsWith("/")) {
            requestedPath += "index.html";
        }

        if (!requestedPath.startsWith(prefixUrl)) {
            sendError(he, 404, "File Not Found");
            return;
        }

        String relativePath = requestedPath.substring(prefixUrl.length());
        sendFile(he, relativePath);
        return;
    }

    private void sendError(HttpExchange he, int status, String message) throws IOException {
        byte[] body = (status + " " + message).getBytes("UTF-8");

        he.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
        he.sendResponseHeaders(status, body.length);

        OutputStream out = he.getResponseBody();
        out.write(body);
        out.close();
    }

    private void sendFile(HttpExchange he, String relativePath) throws IOException {
        File requestedFile;
        try {
            requestedFile = new File(servedDirectory, relativePath).getCanonicalFile();
        } catch (IOException ex) {
            sendError(he, 404, "File Not Found");
            return;
        }

        if (!requestedFile.getPath().startsWith(servedDirectory)) {
            sendError(he, 404, "File Not Found");
            return;
        }

        try (FileInputStream fileStream = new FileInputStream(requestedFile)) {
            String mimeType = getMimeType(relativePath);
            he.getResponseHeaders().set("Content-Type", mimeType);
            he.sendResponseHeaders(200, requestedFile.length());

            OutputStream out = he.getResponseBody();
            writeStream(out, fileStream);
            out.close();
        } catch (IOException ex) {
            sendError(he, 404, "File Not Found");
            return;
        }
    }

    private void writeStream(OutputStream out, InputStream in) throws IOException {
        byte[] buffer = new byte[4096];

        int bytesRead = in.read(buffer);
        while (bytesRead >= 0) {
            out.write(buffer, 0, bytesRead);

            bytesRead = in.read(buffer);
        }
    }
}
