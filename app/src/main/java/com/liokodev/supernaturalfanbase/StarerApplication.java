package com.liokodev.supernaturalfanbase;

/**
 * Created by Jake on 3/25/16.
 */

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;


public class StarerApplication extends Application {

    static Context thisContext;

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("parseDataOnFireTonight1239012309")
                .clientKey("FirstComeLastThird32109123891u24")
                .server("https://parseisonfire.herokuapp.com/parse/")
                .build()
        );


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}

