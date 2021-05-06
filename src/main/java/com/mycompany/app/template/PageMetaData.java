package com.mycompany.app.template;

/**
 * Classe représentant les métadonnées associées à une page.
 */
public class PageMetaData {
    /**
     * Crée un objet contenant les métadonnées.
     *
     * @param title  Le titre de la page.
     * @param author Le nom de l'auteur de la page.
     * @param date   Le date de publication de la page.
     */
    public PageMetaData(String title, String author, String date) {
        this.title = title;
        this.author = author;
        this.date = date;
    }

    /**
     * Le titre de la page.
     */
    private final String title;

    /**
     * Le nom de l'auteur de la page.
     */
    private final String author;

    /**
     * La date de publication de la page.
     */
    private final String date;

    /**
     * @return titre de la page
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Le nom de l'auteur de la page.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return Le date de publication de la page.
     */
    public String getDate() {
        return date;
    }
}
