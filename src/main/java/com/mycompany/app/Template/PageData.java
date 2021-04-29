package com.mycompany.app.Template;

 public class PageData {
    public PageData(String content, PageMetaData metaData) {
        this.content = content;
        this.metaData = metaData;
    }

    public String getContent() {
        return content;
    }

    private final String content;

    private final PageMetaData metaData;

     public PageMetaData getMetaData() {
         return metaData;
     }
 }
