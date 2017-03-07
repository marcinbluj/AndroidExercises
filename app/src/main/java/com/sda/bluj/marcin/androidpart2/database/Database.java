package com.sda.bluj.marcin.androidpart2.database;

import com.sda.bluj.marcin.androidpart2.model.Product;

import java.util.List;

/**
 * Created by RENT on 2017-03-06.
 */

public interface Database {

    void saveProducts(List<Product> products);

    List<Product> getProducts();

    void addProduct(Product product);
}
