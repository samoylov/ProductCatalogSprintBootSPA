package com.mystore;

import com.mystore.dao.FacetDao;
import com.mystore.dao.FacetTypeDao;
import com.mystore.dao.ProductDao;
import com.mystore.model.Product;
import com.mystore.model.Facet;
import com.mystore.model.FacetType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class Application {


    @Autowired
    private static ProductDao productDao = null;
    @Autowired
    private static FacetDao facetDao;
    @Autowired
    private static FacetTypeDao facetTypeDao;


    public Application(ProductDao productDao, FacetTypeDao facetTypeDao, FacetDao facetDao) {
        this.facetDao = facetDao;
        this.facetTypeDao = facetTypeDao;
        this.productDao = productDao;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

        parse("http://www.adidas.com/us/men-originals-shoes");
        parse("http://www.adidas.com/us/men-soccer-shoes");
        parse("http://www.adidas.com/us/men-jackets");
        parse("http://www.adidas.com/us/men-hoodies");
        parse("http://www.adidas.com/us/women-jackets");
        parse("http://www.adidas.com/us/women-hoodies");
        parse("http://www.adidas.com/us/women-sleeveless_tops");
        parse("http://www.adidas.com/us/women-bras");
    }

    public static void parse(String url) throws IOException {

        Document document =
                Jsoup.connect(url).get();

        Product product;

        Elements facetTypeNames = document.select("span.filtername");
        Elements facetNames = document.select("span.filtervalue");
        ArrayList<Facet> facets = new ArrayList<>();
        String facetTypeName;
        String facetName;

        for (int i = 0; i < facetTypeNames.size(); i++) {

            facetTypeName = facetTypeNames.get(i).text();
            facetName = facetNames.get(i).text();

            FacetType facetType = facetTypeDao.findOneByName(facetTypeName);
            if (facetType == null) {
                facetType = new FacetType(facetTypeName);
                facetTypeDao.save(facetType);
            }

            Facet facet = facetDao.findOneByNameAndFacetTypeId(facetName, facetType.getId());
            if (facet == null) {
                facet = new Facet(facetName, facetType);
                facetDao.save(facet);
                facets.add(facet);
            }

        }

        Elements names = document.select("span.title");
        Elements descriptions = document.select("span.subtitle");
        Elements photos = document.select("img.show");
        Elements prices = document.select("span.salesprice");

        for (int i = 0; i < names.size(); i++) {

            product = new Product(
                    names.get(i).text(),
                    descriptions.get(i).text(),
                    photos.get(i).attr("data-original"),
                    Double.parseDouble(prices.get(i).text()));

            product.setFacets(facets);

            productDao.save(product);
        }


    }

}