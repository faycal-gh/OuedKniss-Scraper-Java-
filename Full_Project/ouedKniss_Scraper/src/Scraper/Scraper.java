package Scraper;

import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {

    public static void get_phones_details(int number_of_phones) throws IOException {
        int counter = 0;
        outer:
        for (int i = 1; i <= 203; i++) {
            String url = "https://www.ouedkniss.com/telephones/" + String.valueOf(i);
            Document page = Jsoup.connect(url).get();
            Elements elements = page.select("ul.annonce_left");
            try {
                if (number_of_phones != 0) {
                    System.out.println("---------------Page " + i + " ------------------");
                    for (Element element : elements) {
                        String PhoneName = element.select("h2").text();
                        String PhonePrice = element.select("span[itemprop = price]").text();
                        System.out.println("Phone Name: " + PhoneName + " *** Price: " + PhonePrice);
                        counter++;
                        if (counter == number_of_phones) {
                            break outer;
                        }
                    }
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args){
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Akteb ch7al rak hab Tajbed mn tlf: ");
            int num = s.nextInt();
            get_phones_details(num);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
