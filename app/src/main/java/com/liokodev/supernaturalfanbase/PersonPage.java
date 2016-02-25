package com.liokodev.supernaturalfanbase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonPage extends AppCompatActivity {

    ProgressBar loadingP;
    ScrollView scrollView;
    TextView tempText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadingP = (ProgressBar) findViewById(R.id.loadingPage);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        tempText = (TextView) findViewById(R.id.tempText);

        loadingP.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        // This creates the back arrow ( R.drawable...)
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        // This sets the action when it is pressed.
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonPage.this, "Kevin is a butt.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE); // hiding it for now
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        new DownloadTask().execute();
    }

    public class DownloadTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result;
            try {
                Document doc = Jsoup.connect(MainActivity.selectedPerson).get();

                //Log.d("Hmm1", String.valueOf(doc));
                result = String.valueOf(doc);
                return result;

            } catch (IOException io) {
                io.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("INFO", "Almost done!");

            try {

                // Might want to just save the name from the onClick so the Title doesn't have to load.
                String[] splitResult = result.split("<html(.*?)/html>"); // We can change this to cut up the web page.

                Pattern p = Pattern.compile("<title>(.*?) - Biography - IMDb</title>", Pattern.MULTILINE); // Title
                Matcher m = p.matcher(splitResult[0]);

                while (m.find()) {

                    Log.d("Downloaded", "Title: " + m.group(1));
                    getSupportActionBar().setTitle(m.group(1));

                }

                //Log.d("INFO", String.valueOf(result.contains("<h4 class=\"li_group\">Mini Bio (1)</h4>"))); // TRUE!
                Integer test1 = result.indexOf("<h4 class=\"li_group\">Mini Bio (1)</h4>"), // Find index location of string
                        test2 = result.indexOf("</a> </em></p>");
                Log.d("INFO", String.valueOf(test1) + " " + String.valueOf(test2));

                String message = result.substring(test1,test2);
                tempText.setText(Html.fromHtml(message));

                /*
                // And this is how it shouldn't be done.
                for(int i = test1; i < test2; i++) {
                    message+=result.charAt(i); // Add each char one at a time to message.
                    if (test2 - 1 == i) {
                        //Log.d("INFO", message);
                        tempText.setText(Html.fromHtml(message));
                    }
                }
                */


            } catch (Exception e) {
                e.printStackTrace();
                Log.i("INFO", e.getMessage());

            }

            loadingP.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);


        }
    }

}

