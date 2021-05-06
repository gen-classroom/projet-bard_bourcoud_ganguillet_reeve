package com.mycompany.app.template;

 public class PageData {
    public PageData(String content, PageMetaData metaData, SiteConfig site) {
        this.content = content;
        this.page = metaData;
        this.site = site;
    }
    private final String content;
    private final PageMetaData page;
    private final SiteConfig site;
 }
