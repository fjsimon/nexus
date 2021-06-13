package com.fjsimon.nexus.store.delegate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Component
public class JSoupDelegate {


    public void display(String url) throws IOException {

        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        System.out.println(title);

        Map<String, String> meta = getMeta(doc);
        meta.forEach((k,v) -> System.out.println(k + ": " + v));
//        findLinksByClassName(doc, "categoriesWrapper");
//        findLinksByClassName(doc, "tagsWrapper");
    }

    private void findLinksByClassName(Document doc, String className) {

        doc.getElementsByClass(className).forEach( e -> {
            Elements links = e.getElementsByTag("a");
            for (Element link : links) {
                String href = link.attr("href");
                String text = link.text();
                System.out.println(text +": "+href);
            }
        });

    }

    public Map<String, String> getMeta(Document doc) {
        Map<String, String> result = new TreeMap<>();
        Elements metaTags = doc.getElementsByTag("meta");

        for (Element metaTag : metaTags) {

            String name = metaTag.attr("name");
            String content = metaTag.attr("content");
            if(StringUtils.hasText(name)) {
                result.put(name, content);
            }
        }
        return result;
    }

}
