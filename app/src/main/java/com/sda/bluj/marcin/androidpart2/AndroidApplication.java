package com.sda.bluj.marcin.androidpart2;

import android.app.Application;

import com.sda.bluj.marcin.androidpart2.database.Database;
import com.sda.bluj.marcin.androidpart2.database.DatabaseImpl;

/**
 * Created by RENT on 2017-03-06.
 */

public class AndroidApplication extends Application {

    private static Database mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = new DatabaseImpl(this);
        ((DatabaseImpl) mDatabase).getWritableDatabase(); //tymczasowo
    }

    public static Database getDatabase() {
        return mDatabase;
    }
}
