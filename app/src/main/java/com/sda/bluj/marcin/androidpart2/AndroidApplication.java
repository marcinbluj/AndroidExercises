package com.sda.bluj.marcin.androidpart2;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.sda.bluj.marcin.androidpart2.database.Database;
import com.sda.bluj.marcin.androidpart2.database.DatabaseImpl;
import com.sda.bluj.marcin.androidpart2.database.DatabaseOrmImpl;

import java.sql.SQLException;

/**
 * Created by RENT on 2017-03-06.
 */

public class AndroidApplication extends Application {

    private static Database mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = OpenHelperManager.getHelper(this, DatabaseOrmImpl.class);
//        ((DatabaseImpl) mDatabase).getWritableDatabase(); //tymczasowo //TODO

        try {
            ((DatabaseOrmImpl) mDatabase)  //tymczasowo
                    .getConnectionSource()
                    .getReadWriteConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getDatabase() {
        return mDatabase;
    }
}
