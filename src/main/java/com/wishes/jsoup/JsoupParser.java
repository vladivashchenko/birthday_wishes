package com.wishes.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupParser {
    public static String parsePageHeaderInfo(String urlStr) throws IOException {
        StringBuilder sb = new StringBuilder();
        Connection con = Jsoup.connect(urlStr);
        Document doc = con.get();
        /*Get meta og:url from link*/
        /*if meta og:url exists*/
        String Url = getMetaTag(doc, "og:url");
        if (Url!=null) {
            sb.append("<a href='");
            sb.append(Url);
            sb.append("'>");
        }
        /* else use initial url*/
        else{
            sb.append("<a href='");
            sb.append(urlStr);
            sb.append("'>");
        }
        /*Get meta og:title from link if meta og:title exists*/
        String title = getMetaTag(doc, "og:title");
        /*if not exist use meta title*/
        if (title == null) {
            title = getMetaTag(doc, "title");
        }
        if (title != null) {
            sb.append(title);
        }
        String imageUrl = getMetaTag(doc, "og:image");
        if (imageUrl != null) {
            sb.append("<img src='");
            sb.append(imageUrl);
            sb.append("' align='left' hspace='12' vspace='12' width='150px'/>");
        }
        sb.append("</a>");
        return sb.toString();
    }
   public static String getMetaTag(Document document, String attr) throws IOException {
        Elements elements = document.select("meta[name=" + attr + "]");
        for (Element element : elements) {
            final String s = element.attr("content");
            if (s != null) return s;
        }
        elements = document.select("meta[property=" + attr + "]");
        for (Element element : elements) {
            final String s = element.attr("content");
            if (s != null) return s;
        }
        return null;
    }

}