package com.boolong.hangrywaits.dataprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by dennizhu on 4/11/15.
 */
public class HangryContentProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.boolong.hangrywaits.hangryprovider";
    static final String URL = "content://" + PROVIDER_NAME + "/businesses";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String ID = "_id";
    static final String NAME = "name";
    static final String WAIT_TIME = "waitTime";
    static final String PHONE = "phone";
    static final String ADDRESS = "address";

    static final int BUSINESSES = 1;
    static final int BUSINESSES_ID = 2;
    private MainDatabaseHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "businesses", BUSINESSES);
        uriMatcher.addURI(PROVIDER_NAME, "businesses/#", BUSINESSES_ID);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mDbHelper = new MainDatabaseHelper(context);
        mDatabase = mDbHelper.getWritableDatabase();
        if (mDatabase == null)
            return false;
        else
            return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables("favorites");
        switch (uriMatcher.match(uri)) {
            // maps all database column names
            case BUSINESSES:
                break;
            case BUSINESSES_ID:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        Cursor cursor = queryBuilder.query(mDatabase, projection, selection,
                selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long row = mDatabase.insert(BusinessDataContract.BusinessEntry.TABLE_NAME, "", values);
        if (row > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Fail to add a new record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
