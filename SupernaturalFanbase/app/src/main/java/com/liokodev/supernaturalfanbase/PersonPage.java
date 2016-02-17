package com.liokodev.supernaturalfanbase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonPage extends AppCompatActivity {

    ProgressBar loadingP;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadingP = (ProgressBar) findViewById(R.id.loadingPage);



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

                String[] splitResult = result.split("(.*?)</html>"); // We can change this to cut up the web page.

                Pattern p = Pattern.compile("<title>(.*?) - Biography - IMDb</title>", Pattern.MULTILINE); // Title
                Matcher m = p.matcher(splitResult[0]);

                while (m.find()) {

                    Log.d("Downloaded", "Title: " + m.group(1));
                    getSupportActionBar().setTitle(m.group(1));

                }

                /*

                    I might be doing something stupid for the part below.
                    It isn't working and I am blanking as to why...

                    Time for raid. 

                 */

                p = Pattern.compile("<h4 class="li_group">Mini Bio (1)</h4>(.*?)</em></p>");
                m = p.matcher(splitResult[0]);

                while (m.find()) {

                    Log.d("Downloaded","Bio: " +  m.group(1));

                }


            } catch (Exception e) {
                e.printStackTrace();
                Log.i("INFO", e.getMessage());

            }

            loadingP.setVisibility(View.GONE);



        }
    }

}

