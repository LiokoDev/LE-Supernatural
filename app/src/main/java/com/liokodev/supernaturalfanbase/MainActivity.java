package com.liokodev.supernaturalfanbase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.SlideInLeftAnimationAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        thisContext = this;

        /*CircularImageView fab = (CircularImageView) findViewById(R.id.fabtwo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "\"Oh, now he's got a boner\"  - Dean Winchester", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                Toast.makeText(MainActivity.this, "\"Oh, now he's got a boner\"\n - Dean Winchester", Toast.LENGTH_SHORT).show();
            }
        });*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "\"Oh, now he's got a boner\"  - Dean Winchester", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle The Following Nav Buttons
        int id = item.getItemId();

        // The Cast Menu
        if (id == R.id.Cast) {

            //The History of the Show Tab
        } else if (id == R.id.History) {
            //Pictures of the Show

        } else if (id == R.id.Pics) {
            //The plot Lines of Each Episode

        } else if (id == R.id.Plots) {
            Intent i = new Intent(getApplicationContext(), MainTestActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
