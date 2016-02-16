package com.liokodev.supernaturalfanbase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.SlideInLeftAnimationAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static ArrayList<showData> showFeed;

    public static ArrayList<String> showTitle = new ArrayList<>();
    public static ArrayList<String> showUrl = new ArrayList<>();
    public static ArrayList<String> showPhoto = new ArrayList<>();
    public static ArrayList<String> showDesc = new ArrayList<>();

    static RecyclerView rv;

    static int dontLoad = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        showTitle.add("Working Title 1");
        showUrl.add("http://www.google.com");
        showPhoto.add("http://images6.fanpop.com/image/photos/37100000/Sam-sam-winchester-37167329-1920-1080.jpg"); // Sam Winchester
        showDesc.add("Sam Winchester");

        showTitle.add("Test Title 2");
        showUrl.add("http://www.google.com");
        showPhoto.add("http://thumbs2.modthesims2.com/img/7/0/4/4/5/2/3/MTS_piggypeach-1402387-DEAN3.jpg"); // Dean Winchester
        showDesc.add("Dean Winchester");

        showTitle.add("Test Title 2");
        showUrl.add("http://www.google.com");
        showPhoto.add("http://cdn.playbuzz.com/cdn/38f1afba-f2a9-438a-8fd7-c2d296789251/56214f0e-2bb3-498d-b449-b63b149002d0.jpg"); // Castiel
        showDesc.add("Castiel");


        showFeed = new ArrayList<>();
        showFeed.clear();
        for (int i = 0; i < showTitle.size(); i++) {
            //                        Should probably change these names
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
