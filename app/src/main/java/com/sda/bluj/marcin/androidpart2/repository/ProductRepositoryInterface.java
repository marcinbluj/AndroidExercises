package com.sda.bluj.marcin.androidpart2.repository;

import com.sda.bluj.marcin.androidpart2.model.Product;

import java.util.List;

/**
 * Created by RENT on 2017-02-18.
 */

public interface ProductRepositoryInterface {

    List<Product> getProducts();

    Product getProduct(int productId);

}
