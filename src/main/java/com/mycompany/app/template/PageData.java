package com.mycompany.app.template;

/**
 * Classe contenant le contenu et les métadonnées associées à une page.
 */
public class PageData {

    /**
     * @param content  contenu de la page
     * @param metaData méta-données de la page
     * @param site     configuration de la page
     */
    public PageData(String content, PageMetaData metaData, SiteConfig site) {
        this.content = content;
        this.page = metaData;
        this.site = site;
    }

    private final String content;
    private final PageMetaData page;
    private final SiteConfig site;

    /**
     * @return Le contenu de la page.
     */
    public String getContent() {
        return content;
    }

    /**
     * @return les méta données de la page
     */
    public PageMetaData getPage() {
        return page;
    }

    /**
     * @return la configuration du site
     */
    public SiteConfig getSite() {
        return site;
    }


}
