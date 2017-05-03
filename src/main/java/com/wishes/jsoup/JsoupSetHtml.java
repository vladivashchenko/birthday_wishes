package com.wishes.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by stazhor on 03.05.17.
 */
public class JsoupSetHtml {
    public static void setContent(String path,String content) throws IOException {
        File addwish = new File(path);
        Document document = Jsoup.parse(addwish, "UTF-8");
        Element div = document.select("div").first();
        div.html(content);
    }
}
