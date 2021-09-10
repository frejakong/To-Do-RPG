package com.example.to_dorpg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


import com.example.to_dorpg.database.DBGoldHelper;
import com.example.to_dorpg.model.Gold;

import java.util.ArrayList;
import java.util.List;

public class Store extends Activity {
    private List<Gem> gemList;
    private GridView gemListView;
    private BaseAdapter gemAdapter;
    private DBGoldHelper mDBGoldHelper;
    private int coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        init();


        mDBGoldHelper=   new DBGoldHelper(this);
        gemListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        purchaseGem();

                    }

                    public void purchaseGem() {
                        ArrayList<Gold> data = mDBGoldHelper.getAllData();

                        for (int i = 0; i < data.size(); i++) {

                            coins = data.get(i).getCoins();

                        }
                        if (coins >= 50) {
                            mDBGoldHelper.buyGem(coins);

                            AlertDialog aldg;
                            AlertDialog.Builder adBd = new AlertDialog.Builder(Store.this);
                            adBd.setTitle("Reward Time!!");
                            adBd.setMessage("Buy this gem successfully! You can reward yourself for completing so many tasks!");
                            adBd.setPositiveButton("Yay!!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Store.this, Reward.class);
                                    startActivity(intent);
                                    finish();
                                }

                            });
                            adBd.setNegativeButton("Not now!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Store.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            aldg = adBd.create();
                            aldg.show();
                        } else {
                            AlertDialog aldg;
                            AlertDialog.Builder adBd = new AlertDialog.Builder(Store.this);
                            adBd.setTitle("Warning!!");
                            adBd.setMessage("Your coins is not sufficient! Complete more tasks to get some!");
                            adBd.setPositiveButton("Okay :-(", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Store.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                            });
                            adBd.setNegativeButton("Close :-(", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Store.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            aldg = adBd.create();
                            aldg.show();
                        }
                    }
                });
    }

    public void init() {
        gemList = new ArrayList<Gem>();
        int[] imageRes = {R.drawable.green_gem,R.drawable.red_gem,R.drawable.gray_gem,R.drawable.purple_gem,R.drawable.lime_gem,
                R.drawable.yellow_gem
        };


        for (int i = 0; i < imageRes.length; i++) {
            Gem gems = new Gem(imageRes[i]);
            gemList.add(gems);
        }

        gemListView = (GridView) findViewById(R.id.gridView_gem);
        gemAdapter = new gemAdapter();
        gemListView.setAdapter(gemAdapter);

    }

    public class gemAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return gemList.size();
        }

        @Override
        public Object getItem(int position) {

            return gemList.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View layout = View.inflate(Store.this, R.layout.gem_item, null);

            ImageView image = (ImageView) layout.findViewById(R.id.gem_image);
            Gem gems = gemList.get(position);
            image.setImageResource(gems.getImageSrc());

            return layout;
        }//getView
    }

}
