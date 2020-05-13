package com.damso.speedreadingtest;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    Chronometer chrono;
    Button start,pause,reset;
    private long pauseOffset;
    long calc,time;
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        // TODO KAYEN 104 WORDS
        chrono = findViewById(R.id.chrono);
        pause = findViewById(R.id.btpause);

        chrono.setBase(SystemClock.elapsedRealtime() - pauseOffset);
        chrono.start();
        running = true;


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // HNA MIN YECKLICKER YAHBES CHRONO W YGET LA VALEUR LI RAHI F CHRONO
                chrono.stop();
                pauseOffset = SystemClock.elapsedRealtime() - chrono.getBase();
                time = pauseOffset / 1000   ;

                calc = (104 * 60) / time;

                running = false;
                // LA VALEUR RAHI F PAUSEOFFSET B MILLISECOND LAZEM NBEDELHA L Second
                AlertDialog.Builder dialog = new AlertDialog.Builder(TestActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Result ! ");
                dialog.setMessage("Time : "+time+"\n You Can Read "+calc+" Words Per Minute");
                dialog.setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // TODO AFTER 500 DOWNLOADS ADD ADS
                        Intent intent = new Intent(TestActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                        .setNegativeButton("Note The App", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //HNA TERSLEK LEL APP F PLAYSTORE
                                Uri uri = Uri.parse("market://details?id=" +getApplicationContext().getPackageName());
                                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                // To count with Play market backstack, After pressing back button,
                                // to taken back to our application, we need to add following flags to intent.
                                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                try {
                                    startActivity(goToMarket);
                                } catch (ActivityNotFoundException e) {
                                    startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                                }
                            }
                        });
                final AlertDialog alert = dialog.create();
                alert.show();
            }

        });

    }

    @Override
    public void onBackPressed() {
        chrono.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;

    }
}
