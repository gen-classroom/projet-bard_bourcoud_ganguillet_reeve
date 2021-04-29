package com.mycompany.app.Template;

import com.samskivert.mustache.Template;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.samskivert.mustache.Mustache;

public class PageDataTest {

    @Test
    public void shouldPrintHello()
    {
        String tmpl = "{{content}}";
        Template t = Mustache.compiler().compile(tmpl);

        tmpl = (t.execute(new PageData("hello", new PageMetaData("title","author", "12.02.2012"))));
        assertEquals( "hello",tmpl );
    }

    @Test
    public void shouldPrintMetaData()
    {
        String tmpl = "{{metaData}}";
        Template t = Mustache.compiler().compile(tmpl);

        tmpl = (t.execute(new PageData("", new PageMetaData("title","author", "12.02.2012"))));
        assertEquals( "hello",tmpl );
    }
}