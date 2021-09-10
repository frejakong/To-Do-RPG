package com.example.to_dorpg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.to_dorpg.database.DBAttackHelper;
import com.example.to_dorpg.database.DBGoldHelper;
import com.example.to_dorpg.database.DBIntelligenceHelper;
import com.example.to_dorpg.database.DBStrengthHelper;
import com.example.to_dorpg.model.Attack;
import com.example.to_dorpg.model.Intelligence;
import com.example.to_dorpg.model.Strength;

import java.util.ArrayList;

public class Reward extends Activity {

    private DBAttackHelper mDBAttackHelper;
    private DBIntelligenceHelper mDBIntelligenceHelper;
    private DBStrengthHelper mDBStrengthHelper;
    private Button food;
    private Button bar;
    private Button nap;
    private Button games;
    private int strength;
    private int intelligence;
    private int attack;

    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward);
        food = (Button) findViewById(R.id.food);
        bar = (Button)findViewById(R.id.bar);
        nap = (Button)findViewById(R.id.nap);
        games = (Button)findViewById(R.id.games);
        
        
        mDBAttackHelper = new DBAttackHelper(this);
        ArrayList<Attack> data1 = mDBAttackHelper.getAllData();
        for (int i = 0; i < data1.size(); i++) {

            attack = data1.get(i).getValue();

        }
       

        mDBIntelligenceHelper = new DBIntelligenceHelper(this);


        ArrayList<Intelligence> data2 = mDBIntelligenceHelper.getAllData();
        for (int i = 0; i < data2.size(); i++) {

            intelligence = data2.get(i).getValue();

        }
       

        mDBStrengthHelper = new DBStrengthHelper(this);


        ArrayList<Strength> data3 = mDBStrengthHelper.getAllData();
        for (int i = 0; i < data3.size(); i++) {

            strength = data3.get(i).getValue();

        }

        food.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                reward();
                Intent intent = new Intent(Reward.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                reward();
                Intent intent = new Intent(Reward.this, MainActivity.class);
                startActivity(intent);
            }
        });

        nap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                reward();
                Intent intent = new Intent(Reward.this, MainActivity.class);
                startActivity(intent);
            }
        });

        games.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                reward();
                Intent intent = new Intent(Reward.this, MainActivity.class);
                startActivity(intent);
            }
        });


     }

     private void reward(){
        if(intelligence > 0 && attack > 0 && strength>0){
            mDBStrengthHelper.rewardTime(strength);
            mDBIntelligenceHelper.rewardTime(intelligence);
            mDBAttackHelper.rewardTime(attack);
            Intent intent = new Intent(Reward.this, Store.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Sorry, you cannot reward yourself now", Toast.LENGTH_SHORT ).show();

        }
     }
    }


