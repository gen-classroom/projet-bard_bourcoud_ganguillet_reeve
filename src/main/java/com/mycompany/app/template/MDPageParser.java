package com.mycompany.app.template;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.*;

/**
 * Classe permettant de récupérer les métadonnées d'une page en Markdown ainsi
 * que son contenu sous forme de Html.
 */
public class MDPageParser {

    /**
     * La configuration du site.
     */
    private final SiteConfig config;

    /**
     * Crée une page à partir d'un reader. Ne nécessite pas de fichier, ce qui est
     * bien pratique pour des tests unitaires notamment.
     *
     * @param config La configuration du site.
     */
    public MDPageParser(SiteConfig config) {
        this.config = config;
    }


    /**
     * @param reader reader dont le contenu doit être parsé
     * @return page parsée prête à être convertie en html
     * @throws IOException si problème pour lecture du fichier
     */
    public PageData parse(Reader reader) throws IOException {
        BufferedReader bufReader = new BufferedReader(reader);
        String title = null;
        String author = null;
        String date = null;

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

        PageMetaData metaData = new PageMetaData(title, author, date);

        Parser parser = Parser.builder().build();
        Node document = parser.parseReader(bufReader);
        HtmlRenderer renderer = HtmlRenderer.builder().escapeHtml(true).build();
        String content = renderer.render(document);

        return new PageData(content, metaData, config);
    }

    /**
     * @param file fichier à parser
     * @return page parsée prête à être convertie en html
     * @throws IOException si problème pour lecture du fichier
     */
    public PageData parse(File file) throws IOException {
        return parse(new FileReader(file));
    }
}
