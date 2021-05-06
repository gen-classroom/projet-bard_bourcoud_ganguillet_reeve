package com.mycompany.app.template;

 public class PageData {
    public PageData(String content, PageMetaData metaData, SiteConfig site) {
        this.content = content;
        this.page = metaData;
        this.site = site;
    }
    private final String content;
    private final PageMetaData page;

     /**
      * @return Le contenu de la page.
      *
      */
     public String getContent() {
         return content;
     }

     public PageMetaData getPage() {
         return page;
     }

     public SiteConfig getSite() {
         return site;
     }

     private final SiteConfig site;
 }
