package com.mycompany.app.template;

import com.samskivert.mustache.Template;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import com.samskivert.mustache.Mustache;

public class PageDataTest {


    SiteConfig config = new SiteConfig("./mySite/config.json");

    @Test
    public void shouldPrintHello()
    {
        String tmpl = "{{content}}";
        Template t = Mustache.compiler().compile(tmpl);

        tmpl = t.execute(new PageData("hello", new PageMetaData("title","author", "12.02.2012"), config));
        assertEquals( "hello",tmpl );
    }

    @Test
    public void shouldPrintMetaData()
    {
        String tmpl = "{{page.title}} {{page.author}} {{page.date}}";
        Template t = Mustache.compiler().compile(tmpl);

        tmpl = t.execute(new PageData("content", new PageMetaData("title","author", "12.02.2012"), config));

        assertEquals( "title author 12.02.2012",tmpl );
    }

    @Test
    public void shouldPrintAll()
    {
        String tmpl = "{{page.title}} {{page.author}} {{page.date}}\n" +
                "{{content}}";
        Template t = Mustache.compiler().compile(tmpl);

        tmpl = t.execute(new PageData("content", new PageMetaData("title","author", "12.02.2012"), config));

        assertEquals( "title author 12.02.2012\ncontent",tmpl );
    }
}