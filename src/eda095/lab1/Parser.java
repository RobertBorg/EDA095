package eda095.lab1;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Parser {
    public static ArrayList<String> getHrefs(String urlIn) {
        ArrayList<String> hrefs = new ArrayList<String>();
        URL url;
        try {
            url = new URL(urlIn);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return hrefs;
        }
        char[] troll = "<a href=\"".toCharArray();
        InputStream hip;
        try {
            hip = (InputStream) url.getContent();
        } catch (IOException e) {
            return hrefs;
        }
        int state = 0;
        int c = 0;
        for (; c != -1;) {
            try {
                c = hip.read();
            } catch (IOException e) {
                return hrefs;
            }
            if (c == -1)
                break;
            if (troll[state] == c) {
                state = ++state % troll.length;
                if (state == 0) {
                    StringBuilder sb = new StringBuilder();
                    try {
                        c = hip.read();
                    } catch (IOException e) {
                        return hrefs;
                    }
                    while (c != -1 && c != '"') {
                        sb.append((char) c);
                        try {
                            c = hip.read();
                        } catch (IOException e) {
                            return hrefs;
                        }
                    }
                    String newUrl = sb.toString();
                    if (newUrl.startsWith("http") || newUrl.startsWith("https") || newUrl.startsWith("www")) {
//                        System.out.println("Absolute");
                    } else
                        newUrl = url.getProtocol() + "://" + url.getHost() + "/" + newUrl;
                    hrefs.add(newUrl);
                    state = 0;
                }
            } else if (Character.isWhitespace(c)) {
                continue;
            } else {
                if (state > 3)
                    state = 3;
                else if (state > 0)
                    state = 0;
            }

        }
        return hrefs;
    }
}
