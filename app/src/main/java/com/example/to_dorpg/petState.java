package com.example.to_dorpg;

public class petState {
    private static int mHunger = 100;
    private static int mCleaning = 100;
    private static int mHappiness= 100;

    private static String[] mHungerDialog = {
            "Yummy!! ",     "Taste not bad",
            "Want more",        "Hmm...."
    };



    private static String[] mCleanDialog = {
           "Aww....","Wow......"
    };


    private static String[] mPlayDialog = {
           "Love to be with you!", "Stay with me...."
    };

    private static String[] mNotificationDialog = {
           "You still have some tasks to complete"
    };



    public static int getHunger() {
        return mHunger;
    }

    public static void setHunger(int hunger) {
        mHunger = hunger;
    }

    public static int getCleaning() {
        return mCleaning;
    }

    public static void setCleaning(int cleaning) {
        mCleaning = cleaning;
    }

    public static void setHappiness(int happiness) {
        mHappiness = happiness;
    }

    public static String getHungerDialog(int index) {
        return mHungerDialog[index];
    }

    public void setHungerDialog(String[] hungerDialog) {
        mHungerDialog = hungerDialog;
    }

    public static String getCleanDialog(int index) {
        return mCleanDialog[index];
    }

    public void setCleanDialog(String[] cleanDialog) {
        mCleanDialog = cleanDialog;
    }

    public static String getPlayDialog(int index) {
        return mPlayDialog[index];
    }

    public void setPlayDialog(String[] playDialog) {
        mPlayDialog = playDialog;
    }

    public static int getHappiness() {
        return mHappiness;
    }
    public static String getNotificationDialog(int index) {
        return mNotificationDialog[index];
    }

    public void setNotificationDialog(String[] notificationDialog) {
        mNotificationDialog = notificationDialog;
    }

}
