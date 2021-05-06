package com.mycompany.app.template;

public class PageMetaData {
    public PageMetaData(String title, String author, String date) {
        this.title = title;
        this.author = author;
        this.date = date;
    }

    private final String title;
    private final String author;
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
