package com.example.to_dorpg;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.to_dorpg.database.DBCharacterHelper;
import com.example.to_dorpg.database.DBGoldHelper;

import java.util.ArrayList;
import java.util.List;


public class Adventure extends Activity {
 private Button startButton;
 private Button notnowButton;
 private Spinner spinner;
 private TextView place;
 private TextView space1;
 private TextView space2;


    private List<Place> placeList;
    
    private BaseAdapter placeAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adventure);
        ImageView angel = new ImageView(Adventure.this);
        TextView question = new TextView(Adventure.this);

        angel.setImageResource(R.drawable.angel);

        question.setText("Where do you want to go?");

        spinner= findViewById(R.id.places);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               travel(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        question.setTextSize(20);

        final PopupWindow popupWindow1 = new PopupWindow(angel, 400, 200);
        final PopupWindow popupWindow2 = new PopupWindow(question, 1000, 200);

        popupWindow1.setOutsideTouchable(false);
        popupWindow2.setOutsideTouchable(false);
        startButton = (Button) findViewById(R.id.start);
        notnowButton =(Button) findViewById(R.id.notnow);
        space1=(TextView) findViewById(R.id.space1);
        space2=(TextView) findViewById(R.id.space2);



        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.showAsDropDown(space2);
                popupWindow2.showAsDropDown(space1);
            }
        });

        init();

        notnowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adventure.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void travel(int position){

// different places we can go by jumping
        switch (position) {

            case 0:


                break;
            case 1:


                Intent intent = new Intent(Adventure.this, Forest.class);
                startActivity(intent);
                break;

            case 2:


                 intent = new Intent(Adventure.this, Mountain.class);
                startActivity(intent);

                break;
            case 3:

                intent = new Intent(Adventure.this, Beach.class);
                startActivity(intent);

                break;
            case 4:

               intent = new Intent(Adventure.this, Waterfall.class);
                startActivity(intent);

                break;


        }
    }
    public void init() {
        placeList = new ArrayList<Place>();
        String[] places = {
             "",   "Mysterious Forest","Fire Mountain","Sunshine Beach","Monster Waterfall"
        };


        for (int i = 0; i < places.length; i++) {
            Place place = new Place(places[i]);
            placeList.add(place);
        }


        placeAdapter = new Adventure.placeAdapter();
        spinner.setAdapter(placeAdapter);

    }

    public class placeAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return placeList.size();
        }

        @Override
        public Object getItem(int position) {

            return placeList.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View layout = View.inflate(Adventure.this, R.layout.spinner_item, null);

            place  = (TextView) layout.findViewById(R.id.places_item);
            Place places = placeList.get(position);
            place.setText(places.getPlace());

            return layout;
        }//getView
    }

}
