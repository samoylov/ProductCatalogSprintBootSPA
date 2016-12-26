package com.mystore;

import com.mystore.dao.GenderDao;
import com.mystore.dao.ProductDao;
import com.mystore.dao.ProductTypeDao;
import com.mystore.model.*;
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
    @Autowired
    private static GenderDao genderDao = null;
    @Autowired
    private static ProductTypeDao productTypeDao = null;

    public Application(ProductDao productDao, GenderDao genderDao, ProductTypeDao productTypeDao) {
        this.productDao = productDao;
        this.genderDao = genderDao;
        this.productTypeDao = productTypeDao;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

//        parse("http://www.adidas.com/us/men-originals-shoes");
//        parse("http://www.adidas.com/us/men-soccer-shoes");
//        parse("http://www.adidas.com/us/men-jackets");
//        parse("http://www.adidas.com/us/men-hoodies");
//        parse("http://www.adidas.com/us/women-jackets");
//        parse("http://www.adidas.com/us/women-hoodies");
//        parse("http://www.adidas.com/us/women-sleeveless_tops");
//        parse("http://www.adidas.com/us/women-bras");
    }

    public static void parse(String url) throws IOException {

        Document document =
                Jsoup.connect(url).get();

        Product product;

        Elements filterNames = document.select("span.filtername");
        Elements filterValues = document.select("span.filtervalue");

        Gender gender = new Gender(filterValues.get(0).text());
        System.out.println("found:" + genderDao.findOneByName(gender.getName()));
        if (genderDao.findOneByName(gender.getName()) == null)
            genderDao.save(gender);
        gender = genderDao.findOneByName(gender.getName());

        ProductType productType = new ProductType(filterValues.get(1).text());
        System.out.println("found:" + productTypeDao.findOneByName(productType.getName()));
        if (productTypeDao.findOneByName(productType.getName()) == null)
            productTypeDao.save(productType);
        productType = productTypeDao.findOneByName(productType.getName());

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
            product.setGender(gender);
            product.setProductType(productType);

            productDao.save(product);
        }


    }

}