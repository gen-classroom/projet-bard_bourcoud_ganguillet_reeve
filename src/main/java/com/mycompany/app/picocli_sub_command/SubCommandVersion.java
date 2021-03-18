package com.mycompany.app.picocli_sub_command;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import java.io.FileReader;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "-version", description = "Give the version of the programm.")
public class SubCommandVersion implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        System.out.println("version : " + model.getVersion());
        return 0;
    }
}
