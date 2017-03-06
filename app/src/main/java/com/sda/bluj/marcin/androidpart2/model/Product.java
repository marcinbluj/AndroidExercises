package com.sda.bluj.marcin.androidpart2.model;

/**
 * Created by RENT on 2017-02-18.
 */

public class Product {

    private final int mId;
    private String mName;
    private int mPrice;
    private String mImageName;
    private String mDescription;

    public Product(final int mId, String mName, int mPrice, String mImageName) {
        this.mId = mId;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mImageName = mImageName;
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
}
