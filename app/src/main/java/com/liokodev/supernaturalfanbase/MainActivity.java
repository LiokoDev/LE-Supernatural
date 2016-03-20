package com.liokodev.supernaturalfanbase;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.SlideInLeftAnimationAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<String> showTitle = new ArrayList<>();
    public static ArrayList<String> showUrl = new ArrayList<>();
    public static ArrayList<String> showPhoto = new ArrayList<>();
    public static ArrayList<String> showDesc = new ArrayList<>();
    static ArrayList<showData> showFeed;
    static RecyclerView rv;

    static int dontLoad = 10;
    static String selectedPerson;
    static Context thisContext;
    int nope = 0;

    static public void initializeAdapter() {

        showAdapter adapter = new showAdapter(showFeed);
        SlideInLeftAnimationAdapter alphaAdapter = new SlideInLeftAnimationAdapter(adapter);
        alphaAdapter.setDuration(250);
        alphaAdapter.setFirstOnly(true);
        rv.setAdapter(alphaAdapter);
    }

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
        //showFeed.clear();

        if (nope == 0) {


            showTitle.add("Sam Winchester");
            showUrl.add("http://www.imdb.com/name/nm0655585/bio?ref_=nm_ov_bio_sm");
            showPhoto.add("http://images6.fanpop.com/image/photos/37100000/Sam-sam-winchester-37167329-1920-1080.jpg"); // Sam Winchester
            showDesc.add("Whiney");
            nope = 1;
        }
        if (nope == 1) {
            showTitle.add("Dean Winchester");
            showUrl.add("http://www.imdb.com/name/nm0010075/bio?ref_=nm_ov_bio_sm");
            showPhoto.add("http://thumbs2.modthesims2.com/img/7/0/4/4/5/2/3/MTS_piggypeach-1402387-DEAN3.jpg"); // Dean Winchester
            showDesc.add("Baller");
            nope = 2;
        }
        if (nope == 2) {
            showTitle.add("Castiel");
            showUrl.add("http://www.imdb.com/name/nm0172557/bio?ref_=nm_ov_bio_sm");
            showPhoto.add("http://cdn.playbuzz.com/cdn/38f1afba-f2a9-438a-8fd7-c2d296789251/56214f0e-2bb3-498d-b449-b63b149002d0.jpg"); // Castiel
            showDesc.add("Our Lord and Savior");
            nope = 3;
        }
        if (nope == 3) {
            showTitle.add("Crowley");
            showUrl.add("http://www.imdb.com/name/nm0791968/bio?ref_=nm_ov_bio_sm");
            showPhoto.add("https://tribzap2it.files.wordpress.com/2014/12/crowley-mark-sheppard-supernatural.jpg"); // Crowley
            showDesc.add("The King of Hell");
            nope = 4;
        }
        if (nope == 4) {
            showTitle.add("Kevin Tran");
            showUrl.add("http://www.imdb.com/name/nm1859543/bio?ref_=nm_ov_bio_sm");
            showPhoto.add("http://www.sitcomsonline.com/photopost/data/2410/Sup14TaxiDriver.jpg"); // Kevin Tran
            showDesc.add("Prophet");
            nope = 5;
        }
        if (nope == 5) {
            showTitle.add("Bobby");
            showUrl.add("http://www.imdb.com/name/nm0064769/bio?ref_=nm_ov_bio_sm");
            showPhoto.add("http://www.supernaturalwiki.com/images/thumb/e/e6/Bobbydt.jpg/350px-Bobbydt.jpg"); // Bobby
            showDesc.add("Master of trades Mista Bobby");
            nope = 6;
        }
        if (nope == 6) {
            showTitle.add("John Winchester");
            showUrl.add("http://www.imdb.com/name/nm0604742/bio?ref_=nm_ov_bio_sm");
            showPhoto.add("https://upload.wikimedia.org/wikipedia/en/6/63/John_winchester.jpg"); // John Winchester
            showDesc.add("Babby Daddy");
            nope = 7;
        }
        if (nope == 7) {
            showTitle.add("Lucifer");
            showUrl.add("http://www.imdb.com/name/nm0671032/bio?ref_=nm_ov_bio_sm");
            showPhoto.add("http://www.supernaturalwiki.com/images/6/66/Supernatural-5x19-Hammer-of-the-gods-mark-pellegrino-16732531-1280-720.jpg"); // Lucifer
            showDesc.add("God damn Satan up in here");
            nope = 8;
        }
        if (nope == 8) {
            showTitle.add(" Metatron");
            showUrl.add("http://www.imdb.com/name/nm0035664/bio?ref_=nm_ov_bio_sm");
            showPhoto.add("http://www.thewinchesterfamilybusiness.com/images/SeasonNine/MetaFiction/SPN_0023.jpg"); //  Metatron
            showDesc.add("Asshole of Angels");
            nope = 9;
        }

        showFeed = new ArrayList<>();
        showFeed.clear();
        for (int i = 0; i < showTitle.size(); i++) {
            //                        Should probably change these names -->>>>> Probs Faggot
            showFeed.add(new showData(showTitle.get(i), showUrl.get(i), showPhoto.get(i), showDesc.get(i)));

            if (i == showTitle.size() - 1) {

                //Log.d("Run", "Adapter Time");
                initializeAdapter();
            }
        }
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
            AddTheData(); // Move this later.

            //The History of the Show Tab
        } else if (id == R.id.History) {
            //Pictures of the Show

        } else if (id == R.id.Pics) {
            //The plot Lines of Each Episode

        } else if (id == R.id.Plots) {


        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
