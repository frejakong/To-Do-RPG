package com.example.to_dorpg.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.example.to_dorpg.ChooseCharacter;

import com.example.to_dorpg.model.Character;

public class DBCharacterHelper extends SQLiteOpenHelper  {
    private SQLiteDatabase db;
    public DBCharacterHelper(Context context){
        super(context,"character.db",null,1);
        db = getReadableDatabase();
    }


    //characters in database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table character(" +
                "id integer primary key autoincrement," +
                "img_src INTEGER)"
               );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS character");
        onCreate(db);
    }

    public void add(int imgSrc){
        db.execSQL("INSERT INTO character (img_src) VALUES(?)",new Object[]{imgSrc});
    }

    public ArrayList<Character> getAllData(){

        ArrayList<Character> list = new ArrayList<Character>();
        Cursor cursor = db.query("character",null,null,null,null,null,"img_src");
        while(cursor.moveToNext()){

            int img_src = cursor.getInt(cursor.getColumnIndex("img_src"));
            list.add(new Character(img_src));
        }
        return list;
    }


}
