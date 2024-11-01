package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://ru.wikipedia.org/wiki/Всемирная_история").get();
        System.out.println(doc.title());

        String title = doc.title();
        System.out.println(title);

        Elements p = doc.select("p");
        System.out.println(p);;
    }
}
