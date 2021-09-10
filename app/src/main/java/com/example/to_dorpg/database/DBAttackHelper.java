package com.example.to_dorpg.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.to_dorpg.model.Attack;
import com.example.to_dorpg.model.Gold;

import java.util.ArrayList;

public class DBAttackHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public DBAttackHelper(Context context){
        super(context,"attack.db",null,1);
        db = getReadableDatabase();
    }

// attack value in database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table attack(" +
                "id integer primary key autoincrement," +

                "value INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS attack");
        onCreate(db);
    }

    public void init(){
        db.execSQL("INSERT INTO attack (value) VALUES(?)",new Object[]{100});
    }

    public void rewardTime(int value){
        value=value-10;

        db.execSQL("UPDATE attack SET value = ?",new Object[]{value});
    }

    public void completeTasks(int value){
        value=value+15;
        db.execSQL("UPDATE attack SET value = ?",new Object[]{value});
    }




    public ArrayList<Attack> getAllData(){

        ArrayList<Attack> list = new ArrayList<Attack>();
        Cursor cursor = db.query("attack",null,null,null,null,null,"value");
        while(cursor.moveToNext()){

            int attack = cursor.getInt(cursor.getColumnIndex("value"));
            list.add(new Attack(attack));
        }
        return list;
    }

}
