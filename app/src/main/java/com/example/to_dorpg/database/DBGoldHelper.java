package com.example.to_dorpg.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.example.to_dorpg.model.Character;
import com.example.to_dorpg.model.Gold;

import java.util.ArrayList;

public class DBGoldHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public DBGoldHelper(Context context){
        super(context,"gold.db",null,1);
        db = getReadableDatabase();
    }

// gold quantity in database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table gold(" +
                "id integer primary key autoincrement," +
                "coins INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS gold");
        onCreate(db);
    }

    public void init(){
        db.execSQL("INSERT INTO gold (coins) VALUES(?)",new Object[]{0});
    }

    public void buyGem(int coins){
        coins=coins-50;
        db.execSQL("UPDATE gold SET coins = ?",new Object[]{coins});
    }

    public void obtainGold(int coins){
        coins=coins+10;
        db.execSQL("UPDATE gold SET coins = ?",new Object[]{coins});
    }




    public ArrayList<Gold> getAllData(){

        ArrayList<Gold> list = new ArrayList<Gold>();
        Cursor cursor = db.query("gold",null,null,null,null,null,"coins");
        while(cursor.moveToNext()){

            int coins = cursor.getInt(cursor.getColumnIndex("coins"));
            list.add(new Gold(coins));
        }
        return list;
    }


}
