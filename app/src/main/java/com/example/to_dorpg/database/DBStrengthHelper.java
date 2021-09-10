package com.example.to_dorpg.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.to_dorpg.model.Strength;

import java.util.ArrayList;
//strength value in database
public class DBStrengthHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public DBStrengthHelper(Context context){
        super(context,"strength.db",null,1);
        db = getReadableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table strength(" +
                "id integer primary key autoincrement," +

                "value INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS strength");
        onCreate(db);
    }

    public void init(){
        db.execSQL("INSERT INTO strength (value) VALUES(?)",new Object[]{200});
    }

    public void rewardTime(int value){
        value=value+10;

        db.execSQL("UPDATE strength SET value = ?",new Object[]{value});
    }

    public void completeTasks(int value){
        value=value-15;
        db.execSQL("UPDATE strength SET value = ?",new Object[]{value});
    }




    public ArrayList<Strength> getAllData(){

        ArrayList<Strength> list = new ArrayList<Strength>();
        Cursor cursor = db.query("strength",null,null,null,null,null,"value");
        while(cursor.moveToNext()){

            int strength = cursor.getInt(cursor.getColumnIndex("value"));
            list.add(new Strength(strength));
        }
        return list;
    }
}
