package com.boolong.hangrywaits.dataprovider;

import android.provider.BaseColumns;

/**
 * Created by dennizhu on 4/4/15.
 */
public final class BusinessDataContract {
    public BusinessDataContract() {}

    /* Inner class that defines the table contents */
    public static abstract class BusinessEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_WAIT_TIME = "waitTime";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_ADDRESS = "address";

    }
}


