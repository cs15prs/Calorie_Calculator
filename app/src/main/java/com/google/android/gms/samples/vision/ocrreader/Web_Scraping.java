package com.google.android.gms.samples.vision.ocrreader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Scanner;

public class Web_Scraping
{
    public static String getCalories(String queryy)throws IOException {
        String r = "0";
        try {
            int i = 0;
            Document doc = Jsoup.connect("https://www.google.co.uk/search?q=calories+in+" + queryy).userAgent("Mozilla/17.0").get();
            Elements temp = doc.select("div");
            for (Element calorie : temp) {
                i++;
                String outt = calorie.getAllElements().text();
                System.out.println(i + " " + outt);
                if (i == 39 || i == 38 || i == 37) {
                    Scanner inn = new Scanner(outt);
                    int count = 1;
                    String c = "", cal = "";
                    String last = "";
                    while (inn.hasNext() == true) {
                        count++;
                        String G = inn.next();
                        if (count % 2 == 0) {
                            last = G;
                        }
                        if (G.equalsIgnoreCase("calories") == true) {
                            r = last;
                            break;
                        }
                    }
                }
                if (i == 41) {
                    if (calorie.getAllElements().text().equalsIgnoreCase(null) == false) {
                        Scanner inn = new Scanner(outt);
                        int count = 1;
                        String c = "", cal = "";
                        String last = "";
                        while (inn.hasNext() == true) {
                            count++;
                            String G = inn.next();
                            if (count % 2 == 0) {
                                last = G;
                            }
                            if (G.equalsIgnoreCase("calories") == true) {
                                r = last;
                                break;
                            }
                        }
                    }
                }
            }
            if (r.equalsIgnoreCase(null) == true || Character.isDigit(r.charAt(0)) == false) {
                r = "0";
            }
            System.out.println(r);
        }catch (Exception ee){
            }
        return r;
    }
    /*public static void main(String args[]){
        Web_Scraping G=new Web_Scraping();
        try {
            G.getCalories("potato");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}