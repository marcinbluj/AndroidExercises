package com.sda.bluj.marcin.androidpart2.model;

/**
 * Created by RENT on 2017-02-18.
 */

public class Product {

    private final int mId;
    private String mName;
    private int mPrice;
    private int mImageResId; //ogolnie zla praktyka (uzywac odwolania do widoku w czesci do tego nie przeznaczonej)

    public Product(final int mId, String mName, int mPrice, int mImageResId) {
        this.mId = mId;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mImageResId = mImageResId;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public int getmPrice() {
        return mPrice;
    }

    public int getmImageResId() {
        return mImageResId;
    }
}
