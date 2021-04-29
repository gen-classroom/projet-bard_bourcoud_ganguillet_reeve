package com.mycompany.app.template;

import com.samskivert.mustache.Template;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import com.samskivert.mustache.Mustache;

public class PageDataTest {

    @Test
    public void shouldPrintHello()
    {
        String tmpl = "{{content}}";
        Template t = Mustache.compiler().compile(tmpl);

        tmpl = t.execute(new PageData("hello", new PageMetaData("title","author", "12.02.2012")));
        assertEquals( "hello",tmpl );
    }

    @Test
    public void shouldPrintMetaData()
    {
        String tmpl = "{{metaData.title}} {{metaData.author}} {{metaData.date}}";
        Template t = Mustache.compiler().compile(tmpl);

        tmpl = t.execute(new PageData("content", new PageMetaData("title","author", "12.02.2012")));

        assertEquals( "title author 12.02.2012",tmpl );
    }

    @Test
    public void shouldPrintAll()
    {
        String tmpl = "{{metaData.title}} {{metaData.author}} {{metaData.date}}\n" +
                "{{content}}";
        Template t = Mustache.compiler().compile(tmpl);

        tmpl = t.execute(new PageData("content", new PageMetaData("title","author", "12.02.2012")));

        assertEquals( "title author 12.02.2012\ncontent",tmpl );
    }
}