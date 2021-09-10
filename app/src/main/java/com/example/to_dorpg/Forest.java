package com.example.to_dorpg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class Forest extends Activity  {

    private VideoView video;
    private TextView forest;
    private AlertDialog aldg;
    private MediaController mediaController;
    private Timer timerl;
    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forest);
        forest=(TextView) findViewById(R.id.forest_click);
        video = (VideoView) findViewById(R.id.forest_view);

        playVideoRaw();

        timerl=new Timer();
        handler=new MyHandler();

        AlertDialog.Builder adBd = new AlertDialog.Builder(Forest.this);
        adBd.setTitle("Oh no!!");

        adBd.setMessage("Dangerous! Watch out!");
        adBd.setPositiveButton("Stay!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Forest.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        });
        adBd.setNegativeButton("Run!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        aldg = adBd.create();


        showAlertDialog();

    }


    private void playVideoRaw() {
        mediaController = new MediaController(this);

        mediaController.setVisibility(View.INVISIBLE);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.forest;
        video.setVideoURI(Uri.parse(uri));

        video.setMediaController(mediaController);
        mediaController.setMediaPlayer(video);

        video.requestFocus();

        video.start();
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                video.start();
            }
        });
    }

    private void showAlertDialog() {


        timerl.schedule(new ShowDialogTask(), 3000);
    }

        private class ShowDialogTask extends TimerTask
    {
        @Override
        public void run(){
            handler.sendEmptyMessage(0);
        }
    }


    private class MyHandler extends Handler {
        public void handleMessage(Message msg) {
            aldg.show();
        }

    }
}
