package com.mycompany.app.picocli_sub_command_tests;

import com.mycompany.app.Main;
import org.junit.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class SubCommandCleanTest {
    @Test
    public void shouldDeleteBuildDirectory() throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        File build = new File(currentDirectory + "/build");
        build.mkdir();
        File test1 = new File(build.getPath() + "/test1.txt");
        test1.createNewFile();
        File directoryTest = new File(build.getPath() + "/directoryTest");
        directoryTest.mkdir();
        File test2 = new File(directoryTest.getPath()+"/test2.txt");
        test2.createNewFile();

        assertTrue(build.exists());
        assertTrue(test1.exists());
        assertTrue(directoryTest.exists());
        assertTrue(test2.exists());

        int returnValue = new CommandLine(new Main()).execute("clean");

        assertEquals(0, returnValue);
        assertFalse(build.exists());
        assertFalse(test1.exists());
        assertFalse(directoryTest.exists());
        assertFalse(test2.exists());
    }

}
