package com.boolong.hangrywaits.dataprovider;

import android.content.ContentProvider;
import android.content.Context;

import com.boolong.hangrywaits.Business;

import java.util.List;

/**
 * Created by dennizhu on 4/3/15.
 */
public abstract class DataProvider{
    public static DataProvider getProvider(Context context){
        return new MockDataProvider(context);
    }
    public abstract List<Business> getFavorites();
}
