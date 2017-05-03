package com.wishes.jsoup;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by stazhor on 03.05.17.
 */
public class JsoupParser {
    public static String parsePageHeaderInfo(String urlStr) throws IOException {

        StringBuilder sb = new StringBuilder();
        Connection con = Jsoup.connect(urlStr);


        Document doc = con.get();

        String text = null;
        Elements metaOgTitle = doc.select("meta[property=og:title]");
        if (metaOgTitle != null) {
            text = metaOgTitle.attr("content");
        } else {
            text = doc.title();
        }
        String Url = null;
        Elements metaOgUrl = doc.select("meta[property=og:url]");
        if (metaOgUrl != null) {
            Url = metaOgUrl.attr("content");
        }
        if (Url != null) {
            sb.append("<a href='");
            sb.append(Url);
            sb.append("'>");
        }
        if (text != null) {
            sb.append(text);

        }
        String imageUrl = null;
        Elements metaOgImage = doc.select("meta[property=og:image]");
        if (metaOgImage != null) {
            imageUrl = metaOgImage.attr("content");
        }

        if (imageUrl != null) {
            sb.append("<img src='");
            sb.append(imageUrl);
            sb.append("' align='left' hspace='12' vspace='12' width='150px'/>");
            sb.append("</a>");
        }



        return sb.toString();
    }
}