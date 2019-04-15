package pers.adlered;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpamReader {
    public static void main(String[] args) {
        String words = "HelloWorld你好世界sb";
        System.out.println(spamer(words, "*", "General_SpamWords_V1.0.0_CN.min.txt",true,true));
    }

    public static Map spamer(String words, String replaceTo, String file, boolean replaceSpam, boolean outputSpam) {
        long startTime = System.nanoTime();
        //if (replaceSpam) words = words.toLowerCase();
        List<String> arrayList = toArrayByFileReader(file);
        Map map = new HashMap();
        List<String> spamHits = new ArrayList();
        for (String i:arrayList) {
            if (words.indexOf(i) != -1) {
                if (outputSpam) {
                    spamHits.add(i);
                }
                if (replaceSpam) {
                    String build = "";
                    for (int pointer = 0; pointer < i.length(); pointer++) {
                        build += replaceTo;
                    }
                    words = words.replaceAll(i, build);
                }
            }
        }
        map.put("Result", words);
        if (outputSpam) map.put("SpamHits", spamHits);
        map.put("SpamWordsVersion", file);
        long endTime = System.nanoTime();
        map.put("Runtime", (endTime - startTime) + "ns");
        return map;
    }

    public static List<String> toArrayByFileReader(String name) {
        List<String> arrayList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(name);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
