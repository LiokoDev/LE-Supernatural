package com.liokodev.supernaturalfanbase;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.SlideInLeftAnimationAdapter;

public class MainTestActivity extends AppCompatActivity {

    //private BottomBar mBottomBar;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SuperNatural FanBase");

        ///////////// Navigation /////////////////

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_menu_camera, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_menu_gallery, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_menu_manage, R.color.color_tab_3);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.ic_menu_share, R.color.color_tab_4);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        // Force the titles to be displayed (against Material Design guidelines!)
        bottomNavigation.setForceTitlesDisplay(true);

        // Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);

        // Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        // Add or remove notification for each item
        bottomNavigation.setNotification(4, 1);
        bottomNavigation.setNotification(0, 1);

        // Set listener
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                Toast.makeText(MainTestActivity.this, "Position: " + position + " wasSelected: " + wasSelected, Toast.LENGTH_SHORT).show();
            }
        });

        ///////////// Setting up cards ///////////////////////

        rv = (RecyclerView) findViewById(R.id.ShowRV);
        LinearLayoutManager llm = new LinearLayoutManager(this);

        // Wee
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        AddTheData(); // Move this later.
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

    public void thefinalcountdown() {
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


    public void initializeAdapter() {

        showAdapter adapter = new showAdapter(showFeed);
        SlideInLeftAnimationAdapter alphaAdapter = new SlideInLeftAnimationAdapter(adapter);
        alphaAdapter.setDuration(250);
        alphaAdapter.setFirstOnly(true);
        rv.setAdapter(alphaAdapter);
    }

}
