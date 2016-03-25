package com.liokodev.supernaturalfanbase;

/**
 * Created by Jake on 3/25/16.
 */

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarerApplication extends Application {

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

        ParseObject gameScore = new ParseObject("GameScore");
        gameScore.put("score", 7331);
        gameScore.put("playerName", "Jake");
        gameScore.put("cheatMode", true);
        gameScore.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("Parse", "Save Succeeded");
                } else {
                    Log.i("Parse", "Save Failed");
                }
            }
        });


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}

