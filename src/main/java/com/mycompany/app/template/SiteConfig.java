package com.mycompany.app.template;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class SiteConfig {
    private String domain;
    private String title;

    public SiteConfig(String configFile){
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader(configFile)){
            JSONObject o = (JSONObject) parser.parse(fileReader);
            domain = (String) o.get("domaine");
            title = (String) o.get("titre");
        } catch(IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
