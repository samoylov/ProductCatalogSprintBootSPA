package com.mystore;

import com.mystore.domain.Product;
import com.mystore.repository.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {


    private static ProductRepository productRepository = null;

    @Autowired
    public Application(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

//        parse();
    }

    public static void parse() throws IOException {

        Document document =
                Jsoup.connect("http://www.adidas.co.uk/men-shoes").get();

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

            productRepository.save(product);
        }


    }

}