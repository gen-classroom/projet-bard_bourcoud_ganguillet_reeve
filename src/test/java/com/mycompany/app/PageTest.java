package com.mycompany.app;

import java.io.*;

import com.mycompany.app.template.MDPageParser;
import com.mycompany.app.template.PageData;
import com.mycompany.app.template.PageMetaData;
import com.mycompany.app.template.SiteConfig;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Les tests unitaires pour la classe Page.
 *
 */
public class PageTest {

    /**
     * Exemple tiré du cahier des charges.
     */
    @Test
    public void shouldWorkWithExampleFromBacklog() {
        StringReader exampleFromBacklog = new StringReader("titre: Mon premier article\n" + "auteur: Bertil Chapuis\n"
                + "date: 2021-03-10\n" + "---\n" + "# Mon premier article\n" + "\n" + "## Mon sous-titre\n" + "\n"
                + "Le contenu de mon article.\n" + "\n" + "![Une image](./image.png)");
        MDPageParser page = new MDPageParser(new SiteConfig("./mySite/config.json"));

        try {
            File f = new File("./mySite/file.md");
            PageData pageData = page.parse(f);
            PageMetaData metaData = pageData.getPage();
            assertEquals(metaData.getTitle(), "Mon premier article");
            assertEquals(metaData.getAuthor(), "Bertil Chapuis");
            assertEquals(metaData.getDate(), "2021-03-10");
            assertEquals(pageData.getContent(), "<h1>Mon premier article</h1>\n" + "<h2>Mon sous-titre</h2>\n"
                    + "<p>Le contenu de mon article.</p>\n" + "<p><img src=\"./image.png\" alt=\"Une image\" /></p>\n");

        } catch(IOException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
