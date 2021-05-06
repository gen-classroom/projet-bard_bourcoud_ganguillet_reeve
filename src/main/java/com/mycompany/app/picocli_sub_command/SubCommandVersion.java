package com.mycompany.app.picocli_sub_command;

import com.mycompany.app.Main;
import picocli.CommandLine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "-version", description = "Give the version of the programm.")
public class SubCommandVersion implements Callable<Integer> {

    @Override
    public Integer call() {
        /*
        Package mainPackage =Main.class.getPackage();
        String version = mainPackage.getImplementationVersion();
        System.out.println("version : " + version);
        */

        try {
            Properties appProps = new Properties();
            appProps.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            System.out.println("version : " + appProps.getProperty("version"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
