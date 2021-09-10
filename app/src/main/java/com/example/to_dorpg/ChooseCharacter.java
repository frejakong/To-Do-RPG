package com.example.to_dorpg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import com.example.to_dorpg.database.DBCharacterHelper;

public class ChooseCharacter extends FragmentActivity {
    private DBCharacterHelper mDBCharacterHelper;
    private List<Characters> characterList;
    private GridView characterListView;
    private BaseAdapter characterAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_character);
        init();
       mDBCharacterHelper= new DBCharacterHelper(this);

        characterListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        choose(position);
                        Intent intent = new Intent(ChooseCharacter.this, MainActivity.class);
                        choose(position);
                        startActivity(intent);


                    }
                }

        );

    }

    private void choose(int position) {


       int[] imageRes = {R.drawable.itori, R.drawable.jinka, R.drawable.kinika, R.drawable.nomi,
                R.drawable.mina, R.drawable.mogumi, R.drawable.dodoko, R.drawable.hana, R.drawable.taka,
                R.drawable.rika, R.drawable.risaka, R.drawable.ruki, R.drawable.yaka, R.drawable.asuka,
                R.drawable.sasuki, R.drawable.yori, R.drawable.nono, R.drawable.hiro
        };




        switch (position) {
            case 0:
                mDBCharacterHelper.add(imageRes[0]);

                break;

            case 1:
                mDBCharacterHelper.add(imageRes[1]);
             

                break;
            case 2:
                mDBCharacterHelper.add(imageRes[2]);
             

                break;
            case 3:
                mDBCharacterHelper.add(imageRes[3]);
             

                break;
            case 4:
                mDBCharacterHelper.add(imageRes[4]);
             

                break;
            case 5:
                mDBCharacterHelper.add(imageRes[5]);
             
                break;
            case 6:
                mDBCharacterHelper.add(imageRes[6]);
             
                break;
            case 7:
                mDBCharacterHelper.add(imageRes[7]);
             
                break;
            case 8:
                mDBCharacterHelper.add(imageRes[8]);
             
                break;
            case 9:
                mDBCharacterHelper.add(imageRes[9]);
             
                break;
            case 10:
                mDBCharacterHelper.add(imageRes[10]);
             
                break;
            case 11:
                mDBCharacterHelper.add(imageRes[11]);
             
                break;
            case 12:
                mDBCharacterHelper.add(imageRes[12]);
             
                break;
            case 13:
                mDBCharacterHelper.add(imageRes[13]);
             
                break;
            case 14:
                 mDBCharacterHelper.add(imageRes[14]);
             
                break;
            case 15:
                mDBCharacterHelper.add(imageRes[15]);
             

                break;
            case 16:
                mDBCharacterHelper.add(imageRes[16]);
             
                break;
            case 17:
                mDBCharacterHelper.add(imageRes[17]);
             
                break;

        }

    }



    public void init() {
        characterList = new ArrayList<Characters>();
        int[] imageRes = {R.drawable.itori, R.drawable.jinka, R.drawable.kinika, R.drawable.nomi,
                R.drawable.mina, R.drawable.mogumi, R.drawable.dodoko, R.drawable.hana, R.drawable.taka,
                R.drawable.rika, R.drawable.risaka, R.drawable.ruki, R.drawable.yaka, R.drawable.asuka,
                R.drawable.sasuki, R.drawable.yori, R.drawable.nono, R.drawable.hiro
        };


        for (int i = 0; i < imageRes.length; i++) {
            Characters characters = new Characters(imageRes[i]);
            characterList.add(characters);
        }

        characterListView = (GridView) findViewById(R.id.gridView_character);
        characterAdapter = new characterAdapter();
        characterListView.setAdapter(characterAdapter);

    }

        public class characterAdapter extends BaseAdapter {

            @Override
            public int getCount() {

                return characterList.size();
            }

            @Override
            public Object getItem(int position) {

                return characterList.get(position);
            }

            @Override
            public long getItemId(int position) {

                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View layout = View.inflate(ChooseCharacter.this, R.layout.character_item, null);

                ImageView image = (ImageView) layout.findViewById(R.id.character_image);
                Characters characters = characterList.get(position);
                image.setImageResource(characters.getImageSrc());

                return layout;
            }//getView
        }

    }

