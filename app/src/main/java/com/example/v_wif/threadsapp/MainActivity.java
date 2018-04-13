package com.example.v_wif.threadsapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    //instance variables
    ProgressBar progressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ref to widgets
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);

        //handle the click event
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create an instance of the Downloader class and execute it
                new Downloader().execute();
            }
        });
    }

    //inner class Downloader
    //AsyncTask runs in its own thread in the background

    class Downloader extends AsyncTask<Void, Integer, Integer>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //set the max value of the progress bar to 100
            progressBar.setMax(100);
        }

        @Override
        protected void onProgressUpdate(Integer[] values){
            super.onProgressUpdate();

            //update the progress bar
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Integer doInBackground(Void... voids) {

            //simulate time consuming task
            for(int i = 0; i < 100; i++){
                publishProgress(i);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute(Integer result){
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "Task Completed", Toast.LENGTH_LONG).show();
        }
    }
}
