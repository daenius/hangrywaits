package com.boolong.hangrywaits.dataprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.UserDictionary;

import com.boolong.hangrywaits.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dennizhu on 4/3/15.
 */
public class MockDataProvider extends DataProvider {

    private SQLiteDatabase db;
    private MainDatabaseHelper mOpenHelper;


    public MockDataProvider(Context context) {
        super();
        mOpenHelper = new MainDatabaseHelper(context);
        db = mOpenHelper.getWritableDatabase();
        db.delete(BusinessDataContract.BusinessEntry.TABLE_NAME, null, null);
        ContentValues values = new ContentValues();
        for (int i = 0; i < 80; i++) {
            values.put(BusinessDataContract.BusinessEntry.COLUMN_NAME_NAME, "Restaurant " + i);
            values.put(BusinessDataContract.BusinessEntry.COLUMN_NAME_WAIT_TIME, (int) (Math.random() * 60));
            values.put(BusinessDataContract.BusinessEntry.COLUMN_NAME_PHONE, "(666)666-6666");
            values.put(BusinessDataContract.BusinessEntry.COLUMN_NAME_ADDRESS, (int) (Math.random() * 1000) + " " + UUID.randomUUID().toString());
            db.insert(BusinessDataContract.BusinessEntry.TABLE_NAME, null, values);
        }
    }

    @Override
    public List<Business> getFavorites() {
        String[] projection =
                {
                        UserDictionary.Words._ID,
                        "name",
                        "waitTime",
                        BusinessDataContract.BusinessEntry.COLUMN_NAME_PHONE,
                        BusinessDataContract.BusinessEntry.COLUMN_NAME_ADDRESS
                };

        // Queries the user dictionary and returns results
        Cursor cursor = db.query(
                "favorites",
                projection, null, null, null, null, null);

        ArrayList<Business> results = new ArrayList<Business>();
        while (cursor.moveToNext()) {
            results.add(new Business(cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                    true, cursor.getString(3), cursor.getString(4)
            ));
        }
        return results;
    }
}