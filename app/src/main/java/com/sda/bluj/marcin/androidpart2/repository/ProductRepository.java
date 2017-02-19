package com.sda.bluj.marcin.androidpart2.repository;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RENT on 2017-02-18.
 */

public class ProductRepository implements ProductRepositoryInterface {

    private static ProductRepository mInstance = new ProductRepository();

    private final Map<Integer, Product> mProducts = new HashMap<>();

    private ProductRepository() {
        Product product1 = new Product(1, "Alstremeria", 25, R.drawable.alstremeria);
        Product product2 = new Product(2, "Aster chiński", 10, R.drawable.asterchinski);
        Product product3 = new Product(3, "Celozja pierzasta", 30, R.drawable.celozjapierzasta);
        Product product4 = new Product(4, "Abelia", 50, R.drawable.abelia);
        Product product5 = new Product(5, "Bluszczpospolity", 12, R.drawable.bluszczpospolity);
        Product product6 = new Product(6, "Ciemiernik", 41, R.drawable.ciemiernik);
        Product product7 = new Product(7, "Jałowiec płożący", 34, R.drawable.jalowiecplozacy);
        Product product8 = new Product(8, "Lobelia", 49, R.drawable.lobelia);

        mProducts.put(1,product1);
        mProducts.put(2,product2);
        mProducts.put(3,product3);
        mProducts.put(4,product4);
        mProducts.put(5,product5);
        mProducts.put(6,product6);
        mProducts.put(7,product7);
        mProducts.put(8,product8);
    }

    public static ProductRepositoryInterface getInstance() {
        return mInstance;
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(mProducts.values());
    }
}
