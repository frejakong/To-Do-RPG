package com.example.to_dorpg.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.to_dorpg.model.Intelligence;

import java.util.ArrayList;

public class DBIntelligenceHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public DBIntelligenceHelper(Context context){
        super(context,"intelligence.db",null,1);
        db = getReadableDatabase();
    }

// intelligence in database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table intelligence(" +
                "id integer primary key autoincrement," +

                "value INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS intelligence");
        onCreate(db);
    }

    public void init(){
        db.execSQL("INSERT INTO intelligence (value) VALUES(?)",new Object[]{120});
    }

    public void rewardTime(int value){
        if (value>10){
        value=value-5;

        db.execSQL("UPDATE intelligence SET value = ?",new Object[]{value});}
        else{

        }
    }

    public void completeTasks(int value){
        if(value<180){
        value=value+10;
        db.execSQL("UPDATE intelligence SET value = ?",new Object[]{value});}
        else{

        }
    }




    public ArrayList<Intelligence> getAllData(){

        ArrayList<Intelligence> list = new ArrayList<Intelligence>();
        Cursor cursor = db.query("intelligence",null,null,null,null,null,"value");
        while(cursor.moveToNext()){

            int intelligence = cursor.getInt(cursor.getColumnIndex("value"));
            list.add(new Intelligence(intelligence));
        }
        return list;
    }
}
