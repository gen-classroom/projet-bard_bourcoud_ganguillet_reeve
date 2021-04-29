package com.mycompany.app.Template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * Classe permettant de récupérer les métadonnées d'une page en Markdown ainsi
 * que son contenu sous forme de Html.
 *
 */
public class Page {

    /**
     * Le titre de la page.
     *
     */
    private String title;

    /**
     * L'auteur de la page.
     *
     */
    private String author;

    /**
     * La date de la page.
     *
     */
    private String date;

    /**
     * Le contenu de la page, en format Html.
     *
     */
    private String content;

    /**
     * Crée une page à partir d'un reader. Ne nécessite pas de fichier, ce qui est
     * bien pratique pour des tests unitaires notamment.
     * 
     * @param reader Le reader à partir duquel lire la page.
     * @throws IOException
     */
    public Page(Reader reader) throws IOException {
        BufferedReader bufReader = new BufferedReader(reader);

        for (String line = bufReader.readLine(); line != null && !line.startsWith("---"); line = bufReader.readLine()) {
            String[] parts = line.split(":");

            switch (parts[0]) {
            case "titre":
                title = parts[1].trim();
                break;
            case "auteur":
                author = parts[1].trim();
                break;
            case "date":
                date = parts[1].trim();
                break;
            }
        }

        if (title == null) {
            throw new Error("La page doit avoir un titre.");
        }
        if (author == null) {
            throw new Error("La page doit avoir un auteur.");
        }
        if (date == null) {
            throw new Error("La page doit avoir une date.");
        }

        Parser parser = Parser.builder().build();
        Node document = parser.parseReader(bufReader);
        HtmlRenderer renderer = HtmlRenderer.builder().escapeHtml(true).build();
        content = renderer.render(document);
    }

    /**
     * Crée une page à partir d'un fichier.
     * 
     * @param file Le fichier à partir duquel lire la page.
     * @throws IOException
     */
    public Page(File file) throws IOException {
        this(new FileReader(file));
    }

    /**
     * @return Le titre de la page.
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return L'auteur de la page.
     *
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return La date de la page.
     *
     */
    public String getDate() {
        return date;
    }

    /**
     * @return Le contenu de la page, au format Html.
     *
     */
    public String getContentAsHtml() {
        return content;
    }
}
