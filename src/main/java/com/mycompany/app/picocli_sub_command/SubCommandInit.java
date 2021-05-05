package com.mycompany.app.picocli_sub_command;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "init")
public class SubCommandInit implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "the root pathname")
    private String rootPathname;

    @Override
    public Integer call() throws IOException {
        if (!(rootPathname.charAt(0) == '/')){
            System.out.println("The parameters must begin with /");
            return 0;
        }
        StringBuilder path = new StringBuilder(System.getProperty("user.dir") + rootPathname);

        new File(path.toString()).mkdirs();
        Writer indexWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(path.toString() + "/index.md"),
                        StandardCharsets.UTF_8
                )
        );

        final String baseIndexText = "titre: Mon premier article\n" +
                "auteur: Joe Doe\n" +
                "date: 2021-03-10"  +
                "---\n" +
                "# Mon premier article\n" +
                "## Mon sous-titre\n" +
                "Le contenu de mon article.";

        indexWriter.write(baseIndexText);

        indexWriter.close();

        Writer configWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(path.toString() + "/config.json"),
                        StandardCharsets.UTF_8
                )
        );

        final String baseConfigText = "{\n" +
                "\t\"domaine\" : \"www.mon-site.com\",\n" +
                "\t\"titre\" : \"mon Site\"\n" +
                "}";

        configWriter.write(baseConfigText);

        configWriter.close();

        new File(path.toString() + "/contents").mkdir();

        path.append("/template");
        new File(path.toString()).mkdir();

        Writer menuHtml = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(path.toString() + "/menu.html"),
                        StandardCharsets.UTF_8
                )
        );
        final String baseMenuText = "<ul>\n" +
                "\t<li><a href=\"/index.html\">home</a></li>\n" +
                "\t<li><a href=\"/content/page.html\">page</a></li>\n" +
                "</ul>";

        menuHtml.write(baseMenuText);
        menuHtml.close();

        Writer layoutHtml = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(path.toString() + "/layout.html"),
                        StandardCharsets.UTF_8
                )
        );
        final String baseLayoutMenu = "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<title>{{ site.titre }} | {{ page.titre }}</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t{% include menu.html }\n" +
                "\t{{ content }}\n" +
                "</body>\n" +
                "</html>";

        layoutHtml.write(baseLayoutMenu);
        layoutHtml.close();

        return 0;
    }
}
