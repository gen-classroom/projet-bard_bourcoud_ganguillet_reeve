package com.mycompany.app.picocli_sub_command_tests;

import java.io.ByteArrayOutputStream;
import java.io.File;

import com.mycompany.app.Main;
import org.junit.Test;

import picocli.CommandLine;
import static org.junit.Assert.assertTrue;

public class SubCommandInitTest {

    @Test
    public void fileAreCreatedWithInitCommand() throws Exception {
        new CommandLine(new Main()).execute("init", "/mySite");
        StringBuilder path = new StringBuilder("./mySite");

        assertTrue( new File(path.toString()).exists() );
        assertTrue( new File(path.toString() + "/index.md").exists() );
        assertTrue( new File(path.toString() + "/config.json").exists() );
        assertTrue( new File(path.toString() + "/contents").exists() );

        path.append("/template");

        assertTrue( new File(path.toString()).exists() );
        assertTrue( new File(path.toString() + "/menu.html").exists() );
        assertTrue( new File(path.toString() + "/layout.html").exists() );
    }
}
