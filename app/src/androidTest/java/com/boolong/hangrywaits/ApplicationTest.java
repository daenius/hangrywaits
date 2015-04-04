package com.boolong.hangrywaits;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.boolong.hangrywaits.dataprovider.DataProvider;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testContentProvider(){
        DataProvider dp = DataProvider.getProvider(getContext());
        List<Business> results = dp.getFavorites();
        int i = 0;
        for(Business b : results){
            System.out.println(b.toString());
        }
    }
}