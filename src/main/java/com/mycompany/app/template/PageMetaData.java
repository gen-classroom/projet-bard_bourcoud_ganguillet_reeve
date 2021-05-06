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

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }
}
