package com.mystore.controller;

import com.mystore.dao.GenderDao;
import com.mystore.dao.ProductDao;
import com.mystore.dao.ProductTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductListController {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductTypeDao productTypeDao;
    @Autowired
    private GenderDao genderDao;

    @RequestMapping(value = "/getProductList", method = RequestMethod.GET)
    public ProductListRepresentation getProductList(@RequestParam(value = "genderId", required = false) String genderId, @RequestParam(value = "productTypeId", required = false) String productTypeId) {

        ProductListRepresentation productList = new ProductListRepresentation(productDao.findAll());
        return productList;

    }


}
