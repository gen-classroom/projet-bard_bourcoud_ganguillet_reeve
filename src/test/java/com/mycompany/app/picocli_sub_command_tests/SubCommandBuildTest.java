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

        String currentDirectory = System.getProperty("user.dir");

        File indexHtml = new File(currentDirectory, "mySite/build/index.html");
        assertTrue(indexHtml.exists());

        BufferedReader indexReader = new BufferedReader(new FileReader(indexHtml));
        String indexContent = indexReader.lines().reduce("", (String acc, String line) -> acc + "\n" + line).trim();
        assertEquals(indexContent,
                "<html lang=\"en\">\n" + "<head>\n" + "\t<meta charset=\"utf-8\">\n"
                        + "\t<title>mon Site | Mon premier article</title>\n" + "</head>\n" + "<body>\n" + "\t<ul>\n"
                        + "\t<li><a href=\"/index.html\">home</a></li>\n"
                        + "\t<li><a href=\"/content/page.html\">page</a></li>\n" + "</ul>\n"
                        + "\t<h1>Mon premier article</h1>\n" + "<h2>Mon sous-titre</h2>\n"
                        + "<p>Le contenu de mon article.</p>\n" + "\n" + "</body>\n" + "</html>");
    }
}
