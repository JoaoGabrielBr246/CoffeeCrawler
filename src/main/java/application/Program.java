package application;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    private static final int MAX_DEPTH = 3;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        System.out.print("┏━╸┏━┓┏━╸┏━╸┏━╸┏━╸┏━╸┏━┓┏━┓╻ ╻╻  ┏━╸┏━┓\n" +
                "┃  ┃ ┃┣╸ ┣╸ ┣╸ ┣╸ ┃  ┣┳┛┣━┫┃╻┃┃  ┣╸ ┣┳┛\n" +
                "┗━╸┗━┛╹  ╹  ┗━╸┗━╸┗━╸╹┗╸╹ ╹┗┻┛┗━╸┗━╸╹┗╸\n");
        System.out.println("This is a program made for studies, I am not responsible for any misuse");


        System.out.print("Enter the url - Ex.: (https://www.example.com): ");
        String url = sc.nextLine();

        if (url.matches("^https?://.*$")) {
            crawl(url, url, 1); // Inicia a varredura com a profundidade 1
        } else {
            System.out.println("Invalid URL. Please enter a valid URL!");
            System.out.println("Remember, it must contain https in the URL.");
        }
    }

    private static void crawl(String originalUrl, String url, int depth) {
        try {
            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                    .get();
            Elements inputElements = document.select("input[type=text]");

            for (Element inputElement : inputElements) {
                System.out.println("Checking URL: " + url);
                System.out.println("Tag: " + inputElement.tagName());
                System.out.println("Attributes: " + inputElement.attributes());
                System.out.println("-----------------------");
            }

            if (depth < MAX_DEPTH) {
                Elements links = document.select("a[href]");
                for (Element link : links) {
                    String href = link.absUrl("href");
                    crawl(originalUrl, href, depth + 1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
