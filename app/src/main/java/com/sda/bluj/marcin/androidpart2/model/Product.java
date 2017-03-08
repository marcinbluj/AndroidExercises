package com.sda.bluj.marcin.androidpart2.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by RENT on 2017-02-18.
 */

@DatabaseTable(tableName = Product.TABLE_NAME)
public class Product {

    static final String TABLE_NAME = "products";

    @DatabaseField(columnName = "id", generatedId = true)
    private int mId;

    @DatabaseField(columnName = "name", canBeNull = false, unique = true)
    private String mName;

    @DatabaseField(columnName = "price", canBeNull = false)
    private int mPrice;

    @DatabaseField(columnName = "imageName", defaultValue = "roslina")
    private String mImageName;

    @DatabaseField(columnName = "description", canBeNull = false)
    private String mDescription;

    public Product() {

    }

    public Product(final int mId, String mName, int mPrice, String mImageName) {
        this.mId = mId;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mImageName = mImageName;
    }

    public Product(final int mId, String mName, int mPrice, String mImageName, String mDescription) {
        this.mId = mId;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mImageName = mImageName;
        this.mDescription = mDescription;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getPrice() {
        return mPrice;
    }

    public String getImageName() {
        return mImageName;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }
}
