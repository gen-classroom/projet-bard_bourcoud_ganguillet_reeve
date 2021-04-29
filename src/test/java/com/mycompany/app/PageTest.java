package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;

import com.mycompany.app.Template.Page;
import org.junit.Test;

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
        Page page;
        try {
            page = new Page(exampleFromBacklog);

            assertEquals(page.getTitle(), "Mon premier article");
            assertEquals(page.getAuthor(), "Bertil Chapuis");
            assertEquals(page.getDate(), "2021-03-10");
            assertEquals(page.getContentAsHtml(), "<h1>Mon premier article</h1>\n" + "<h2>Mon sous-titre</h2>\n"
                    + "<p>Le contenu de mon article.</p>\n" + "<p><img src=\"./image.png\" alt=\"Une image\" /></p>\n");
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
