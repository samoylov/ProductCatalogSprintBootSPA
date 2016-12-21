package com.mystore;

import com.mystore.model.Product;
import com.mystore.model.ProductDao;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {


    @Autowired
    private static ProductDao productDao = null;

    public Application(ProductDao productDao) {
        this.productDao = productDao;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

        //parse();
    }

    public static void parse() throws IOException {

        Document document =
                Jsoup.connect("http://www.adidas.com/us/women-shoes").get();

        Product product;

        Elements names = document.select("span.title");
        Elements descriptions = document.select("span.subtitle");
        Elements photos = document.select("img.show");
        Elements prices = document.select("span.salesprice");

        for (int i = 0; i < names.size(); i++) {

//            System.out.println(names.get(i).text());
//            System.out.println(descriptions.get(i).text());
//            System.out.println(photos.get(i).attr("data-original"));
//            System.out.println(prices.get(i).text());
//            System.out.println("-------");

            product = new Product(
                    names.get(i).text(),
                    descriptions.get(i).text(),
                    photos.get(i).attr("data-original"),
                    Double.parseDouble(prices.get(i).text()));

            productDao.save(product);
        }


    }

}