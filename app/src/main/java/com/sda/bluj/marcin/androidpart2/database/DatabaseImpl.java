package com.sda.bluj.marcin.androidpart2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sda.bluj.marcin.androidpart2.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-03-06.
 */

public class DatabaseImpl extends SQLiteOpenHelper implements Database {

    private final static String NAME = "database.db";
    private final static int VERSION = 1;

    private static final String DB_CREATE_TODO_TABLE =
            "CREATE TABLE products(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL UNIQUE," +
                    "price INTEGER DEFAULT 0," +
                    "image_name TEXT DEFAULT roslina," +
                    "description TEXT" +
                    ");";

    private static final String ADD_COLUMN = "ALTER TABLE products " + "ADD test TEXT";

    private static final String REMOVE_COLUMN = "ALTER TABLE products " + "DROP COLUMN test";

    private static final String DROP_TODO_TABLE = "DROP TABLE IF EXISTS products";


    public DatabaseImpl(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        List<Product> products = getProducts(db); //
        db.execSQL(DROP_TODO_TABLE);

        onCreate(db);
        saveProducts(products, db);
    }

    public void saveProducts(List<Product> products, SQLiteDatabase db) {
        db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        try {
            db.beginTransaction();
            for (Product product : products) {
                contentValues.put("name", product.getName());
                contentValues.put("price", product.getPrice());
                contentValues.put("image_name", product.getImageName());
                contentValues.put("description", product.getDescription());
                long id = db.insertOrThrow("products", null, contentValues);

                Log.i("database", "" + id);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void saveProducts(List<Product> products) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        try {
            db.beginTransaction();
            for (Product product : products) {
                contentValues.put("name", product.getName());
                contentValues.put("price", product.getPrice());
                contentValues.put("image_name", product.getImageName());
                contentValues.put("description", product.getDescription());
                long id = db.insertOrThrow("products", null, contentValues);

                Log.i("database", "" + id);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void saveProduct(String name, int price, String description) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        try {
            db.beginTransaction();
            contentValues.put("name", name);
            contentValues.put("price", price);
//            contentValues.put("image_name", product.getImageName());
            contentValues.put("description", description);
            long id = db.insertOrThrow("products", null, contentValues);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void updateProduct(Product product, String name, int price, String description) {
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        try {
            db.beginTransaction();
            contentValues.put("name", name);
            contentValues.put("price", price);
            contentValues.put("description", description);
            long id = db.update("products", contentValues, "_id="+product.getId(), null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }


    }

    private List<Product> getProducts(SQLiteDatabase db) {
        List<Product> products = new ArrayList<>();

        db = getReadableDatabase();
        Cursor cursor = db.query("products", null, null, null, null, null, null);
        cursor.moveToFirst();
        do {
            int idColumnIndex = cursor.getColumnIndex("id");
            int id = cursor.getInt(idColumnIndex);

            int nameColumnIndex = cursor.getColumnIndex("name");
            String name = cursor.getString(nameColumnIndex);

            int priceColumnIndex = cursor.getColumnIndex("price");
            int price = cursor.getInt(priceColumnIndex);

            int imageNameColumnIndex = cursor.getColumnIndex("image_name");
            String imageName = cursor.getString(imageNameColumnIndex);

            int descriptionColumnIndex = cursor.getColumnIndex("description");
            String description = cursor.getString(descriptionColumnIndex);

            Product product = new Product(id, name, price, imageName);
            product.setDescription(description);

            products.add(product);

        } while (cursor.moveToNext());
        cursor.close();

        return products;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("products", null, null, null, null, null, null);
        cursor.moveToFirst();
        do {
            int id = cursor.getInt(0);
            int nameColumnIndex = cursor.getColumnIndex("name");
            String name = cursor.getString(nameColumnIndex);
            int price = cursor.getInt(2);
            String imageName = cursor.getString(3);
            String description = cursor.getString(4);

            Product product = new Product(id, name, price, imageName);
            product.setDescription(description);

            products.add(product);

        } while (cursor.moveToNext());
        cursor.close();

        return products;
    }

    public Product getProduct(int productId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("products",
                new String[]{"id", "name"}, "id = ?",
                new String[]{String.valueOf(productId)},
                null, null, null);
        cursor.moveToFirst();

        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        int price = cursor.getInt(2);
        String imageName = cursor.getString(3);
        String description = cursor.getString(4);

        Product product = new Product(id, name, price, imageName, description);

        cursor.close();

        return product;
    }
}
