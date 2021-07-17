package com.cea.covidshield;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class splashactivity extends AppCompatActivity {

        ProgressBar progressBar;
        int i = 0;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);

            getSupportActionBar().hide();
            final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
            progressBar.setProgress(i);
            new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    i++;
                    progressBar.setProgress((int)i*100/(5000/1000));

                }

                @Override
                public void onFinish() {
                    Intent secondActivity = new Intent (getApplicationContext(), MainActivity.class);

                    startActivity(secondActivity);
                    i++;
                    progressBar.setProgress(100);
                }
            }.start();

        }
}