package com.sda.bluj.marcin.androidpart2.database;

import com.sda.bluj.marcin.androidpart2.model.Product;

import java.util.List;

/**
 * Created by RENT on 2017-03-06.
 */

public interface Database {

    void saveProducts(List<Product> products);

    List<Product> getProducts();

    void saveProduct(String name, int price, String description);

    void updateProduct(Product product, String name, int price, String description);

    Product getProduct(int productId);
}
