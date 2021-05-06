package com.mycompany.app.picocli_sub_command_tests;

import com.mycompany.app.Main;
import org.junit.Test;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SubCommandBuildTest {

    @Test
    public void shouldCreateSubDirectoryBuild() throws FileNotFoundException {
        new SubCommandInitTest().fileAreCreatedWithInitCommand();

        int returnValue = new CommandLine(new Main()).execute("build", "/mySite");

        assertEquals(returnValue, 0);
    }
}
