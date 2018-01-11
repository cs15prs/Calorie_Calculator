package com.caloriecalculator.calorie_calculator;

/**
 * Created by Parth Shah on 11/01/2018.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class web_Scraping
{
    public static String getCalories(String queryy) {
        String calories="",recipe="";
    	try{
        Document doc=Jsoup.connect("http://www.sparkpeople.com/calories-in.asp?food="+""+queryy).userAgent("Mozilla/17.0").get();
        Elements temp=doc.select("div.card_w");

        int i=0;
        for(Element calorie:temp)
        {
            i++;
            //System.out.println( i + " " + calorie.getAllElements().text());
            if(i>=0)
            {
                //System.out.println("Here");
                recipe=calorie.getAllElements().text();
                Scanner in=new Scanner(recipe);
                while(in.hasNext()==true)
                {
                    //Amount Per Serving Calories
                    String G=in.next();

                    if(G.equalsIgnoreCase("Calories")==true)
                    {
                        String tem=in.next();
                        if(tem.equalsIgnoreCase("in")==false)
                        {
                            calories=tem;
                            break;
                        }
                    }
                }
            }
        }
    }catch(Exception sdf){}
    return calories;
}
}
