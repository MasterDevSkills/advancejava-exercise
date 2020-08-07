package com.masterdevskills.cha2.ext1;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MovieDbMain {
    public static void main(String[] args) throws IOException {
        Connection connect = Jsoup.connect("https://www.imdb.com/search/title/?count=100&groups=oscar_best_picture_winners&sort=year%2Cdesc&ref_=nv_ch_osc");

        Document document = connect.get();
        Element body = document.body();

        Elements select = document.body().select("a[href]");
        List<String> collect = select.stream().map(element -> element.attr("abs:href"))
                                     .filter(link -> link.startsWith("https://www.imdb.com/title/"))
                                     .map(link -> link.split("/")[4])
                                     .collect(Collectors.toList());
    }
}
