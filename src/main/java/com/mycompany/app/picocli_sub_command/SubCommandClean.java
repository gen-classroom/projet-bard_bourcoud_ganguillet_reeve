package com.mycompany.app.picocli_sub_command;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(name = "clean")
public class SubCommandClean implements Callable<Integer> {

    @Override
    public Integer call() {
            String currentPath = System.getProperty("user.dir");
            File buildDirectory = new File(currentPath + "/build");
            deleteDirectory(buildDirectory);
            return 0;
    }

    private void deleteDirectory(File directory)
    {
        if(directory == null) return;

        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directory.delete();
    }
}