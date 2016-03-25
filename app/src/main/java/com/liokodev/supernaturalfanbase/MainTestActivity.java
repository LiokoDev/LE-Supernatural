package com.liokodev.supernaturalfanbase;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.SlideInLeftAnimationAdapter;

public class MainTestActivity extends Activity {
    private BottomBar mBottomBar;

    static ArrayList<showData> showFeed;

    public static ArrayList<String> showTitle = new ArrayList<>();
    public static ArrayList<String> showUrl = new ArrayList<>();
    public static ArrayList<String> showPhoto = new ArrayList<>();
    public static ArrayList<String> showDesc = new ArrayList<>();

    static RecyclerView rv;

    static int dontLoad = 10;

    static String selectedPerson;

    static Context thisContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        ///////////// Navigation /////////////////

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                // Button 1
                if (menuItemId == R.id.barCast) {
                    Toast.makeText(MainTestActivity.this, "barCast - First Time", Toast.LENGTH_SHORT).show();
                }
                // Button 2
                if (menuItemId == R.id.barHistory) {
                    Toast.makeText(MainTestActivity.this, "barHistory - First Time", Toast.LENGTH_SHORT).show();
                }
                // Button 3
                if (menuItemId == R.id.barPics) {
                    Toast.makeText(MainTestActivity.this, "barPics - First Time", Toast.LENGTH_SHORT).show();
                }
                // Button 4
                if (menuItemId == R.id.barPlots) {
                    Toast.makeText(MainTestActivity.this, "barPlots - First Time", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                // Button 1
                if (menuItemId == R.id.barCast) {
                    Toast.makeText(MainTestActivity.this, "barCast - Second Time", Toast.LENGTH_SHORT).show();
                }
                // Button 2
                if (menuItemId == R.id.barHistory) {
                    Toast.makeText(MainTestActivity.this, "barHistory - Second Time", Toast.LENGTH_SHORT).show();
                }
                // Button 3
                if (menuItemId == R.id.barPics) {
                    Toast.makeText(MainTestActivity.this, "barPics - Second Time", Toast.LENGTH_SHORT).show();
                }
                // Button 4
                if (menuItemId == R.id.barPlots) {
                    Toast.makeText(MainTestActivity.this, "barPlots - Second Time", Toast.LENGTH_SHORT).show();
                }
            }

        });

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(1, 0xFF5D4037);
        mBottomBar.mapColorForTab(2, "#7B1FA2");
        mBottomBar.mapColorForTab(3, "#FF5252");
        //mBottomBar.mapColorForTab(4, "#FF9800");

        ///////////// Setting up cards ///////////////////////

        rv = (RecyclerView) findViewById(R.id.ShowRV);
        LinearLayoutManager llm = new LinearLayoutManager(this);

        // Wee
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        AddTheData(); // Move this later.
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void AddTheData() {

        // This will push the names and such through to the adapter for the recyclerView.

        ParseQuery<ParseObject> queryCast = ParseQuery.getQuery("Supernatural");
        queryCast.orderByAscending("Name");
        queryCast.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> object, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < object.size(); i++) {
                        ParseObject objectT = object.get(i);
                        // You can get a value of a Parse Object column with get:

                        String myName = (String) objectT.get("Name");
                        showTitle.add(myName);
                        //Log.d("AddTheData", myName);

                        String myIMDB = (String) objectT.get("IMDB");
                        showUrl.add(myIMDB);
                        //Log.d("AddTheData", myIMDB);

                        String myPhoto = (String) objectT.get("PhotoUrl");
                        showPhoto.add(myPhoto);
                        //Log.d("AddTheData", myPhoto);

                        String myDesc = (String) objectT.get("Desc");
                        showDesc.add(myDesc);
                        //Log.d("AddTheData", myDesc);

                        //Log.d("INFO", String.valueOf(i) + " - " + object.size());
                        Integer l = 0;
                        l = i + 2;

                        if (l > object.size()) {
                            thefinalcountdown();
                            Log.d("AddTheData", showTitle.size() + " " + showUrl.size() + " " +
                                    showPhoto.size() + " " + showDesc.size());
                        }
                    }

                } else {
                    Log.d("AddTheData", "Error: " + e.getMessage());
                }
            }
        });

    }

    static public void thefinalcountdown() {
        showFeed = new ArrayList<>();
        showFeed.clear();
        for (int i = 0; i < showTitle.size(); i++) {
            //
            showFeed.add(new showData(showTitle.get(i), showUrl.get(i), showPhoto.get(i), showDesc.get(i)));

            if (i == showTitle.size() - 1) {

                //Log.d("Run", "Adapter Time");
                initializeAdapter();
            }
        }
    }


    static public void initializeAdapter() {

        showAdapter adapter = new showAdapter(showFeed);
        SlideInLeftAnimationAdapter alphaAdapter = new SlideInLeftAnimationAdapter(adapter);
        alphaAdapter.setDuration(250);
        alphaAdapter.setFirstOnly(true);
        rv.setAdapter(alphaAdapter);
    }

}
