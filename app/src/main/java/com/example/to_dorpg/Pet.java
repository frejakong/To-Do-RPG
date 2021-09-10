package com.example.to_dorpg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class Pet extends Activity {
    private static ProgressBar mHungerProgress;
    private static ProgressBar mCleanProgress;
    private static ProgressBar mHappinessProgress;

    public static int mHunger;
    public static int mCleanness;
    public static int mHappiness;

    private TextView mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet);

        mHungerProgress = (ProgressBar) findViewById(R.id.progressHunger);
        mCleanProgress = (ProgressBar) findViewById(R.id.progressClean);
        mHappinessProgress = (ProgressBar) findViewById(R.id.progressPlay);
        mDialog = (TextView) findViewById(R.id.petDialog);

        Intent intent = new Intent(getApplicationContext(), myService.class);
        startService(intent);
    }

    @SuppressLint("WrongConstant")
    public void onFeed(View view) { 
        double random = Math.random();
        mHunger = mHunger + (int) (random * 25);
        if ((random * 25) > 20) {  //20%
            showRandomDialog_Hunger();
        }
        if (mHunger > 100) {
            mHunger = 100;
        }
        setState();
        mHungerProgress.setProgress(mHunger);
        Toast.makeText(getApplicationContext(), "Feed Successfully", 0).show();
    }


    @SuppressLint("WrongConstant")
    public void onClean(View view) {
        double random = Math.random();
        mCleanness = mCleanness + (int) (random * 15);
        if ((random * 15) > 10) {   //33.3%
            showRandomDialog_Cleaning();
        }
        if (mCleanness > 100) {
            mCleanness = 100;
        }
        setState();
        mCleanProgress.setProgress(mCleanness);
        Toast.makeText(getApplicationContext(), "Clean Successfully", 0).show();
    }


    public void onPlay(View view) {
        double random = Math.random();
        mHappiness = mHappiness + (int) (random * 20);
        if ((random * 20) > 15) {   //25%
            showRandomDialog_Happiness();
        } else {
            showRandomDialog_Notification();
        }
        if (mHappiness > 100) {
            mHappiness = 100;
        }
        setState();
        mHappinessProgress.setProgress(mHappiness);
    }


    public static void setProgress(int mHunger, int mCleanness, int mHappiness) {
        mHungerProgress.setProgress(mHunger);
        mCleanProgress.setProgress(mCleanness);
        mHappinessProgress.setProgress(mHappiness);
    }


    public static void getState() {
        mHunger = petState.getHunger();
        mCleanness = petState.getCleaning();
        mHappiness = petState.getHappiness();
    }


    public static void setState() {
        petState.setHunger(mHunger);
        petState.setCleaning(mCleanness);
        petState.setHappiness(mHappiness);
    }

    int Random = (int) (Math.random() * 1.99);

    private void showRandomDialog_Hunger() {
        mDialog.setText(petState.getHungerDialog(Random));
    }


    private void showRandomDialog_Cleaning() {
        mDialog.setText(petState.getCleanDialog(Random));
    }

    private void showRandomDialog_Happiness() {
        mDialog.setText(petState.getPlayDialog(Random));
    }

    private void showRandomDialog_Notification() {
        mDialog.setText("You still have some tasks to be done!");
    }

}


