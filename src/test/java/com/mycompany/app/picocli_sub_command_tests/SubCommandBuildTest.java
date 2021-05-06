package com.mycompany.app.picocli_sub_command_tests;

import com.mycompany.app.Main;
import org.junit.Test;
import picocli.CommandLine;

import static org.junit.Assert.assertEquals;

public class SubCommandBuildTest {

    @Test
    public void shouldCreateSubDirectoryBuild() {
        int returnValue = new CommandLine(new Main()).execute("build", "/mySite");

        assertEquals(returnValue, 0);
    }
}
