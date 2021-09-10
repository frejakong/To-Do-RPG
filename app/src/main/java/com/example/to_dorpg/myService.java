package com.example.to_dorpg;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class myService extends Service {

            @Override
            public IBinder onBind(Intent arg0) {
                return null;
            }

            @Override
            public boolean onUnbind(Intent intent) {
                return super.onUnbind(intent);
            }

            @Override
            public void onCreate() {
                System.out.println("onCreate");
                super.onCreate();
            }

            @Override
            public int onStartCommand(Intent intent, int flags, int startId) {
                Timer mTimer = new Timer();
                TimerTask mTimerTask = new TimerTask() {
                    private int mHunger;
                    private int mClean;
                    private int mHappiness;

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        double random = Math.random();
                        mHunger = petState.getHunger() - (int) (20 * random);
                        if (mHunger > 100) {
                            mHunger = 100;
                        }
                        if (mHunger < 0) {
                            mHunger = 0;
                        }
                        petState.setHunger(mHunger);

                        mClean = petState.getCleaning() - (int) (10 * random);
                        if (mClean > 100) {
                            mClean = 100;
                        }
                        if (mClean < 0) {
                            mClean = 0;
                        }
                        petState.setHunger(mClean);

                        mHappiness = petState.getHappiness() - (int) (15 * random);
                        if (mHappiness > 100) {
                            mHappiness = 100;
                        }
                        if (mHappiness < 0) {
                            mHappiness = 0;
                        }
                        petState.setHunger(mHappiness);

                        Pet.setProgress(mHunger, mClean, mHappiness);
                    }
                };
                mTimer.schedule(mTimerTask, 0, 900000);
                return super.onStartCommand(intent, flags, startId);
            }

            @Override
            public void onDestroy() {
                System.out.println("onDestory");
                super.onDestroy();
            }


}
