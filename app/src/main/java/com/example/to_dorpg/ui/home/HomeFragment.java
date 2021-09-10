package com.example.to_dorpg.ui.home;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.to_dorpg.Adventure;
import com.example.to_dorpg.ChooseCharacter;

import com.example.to_dorpg.Pet;
import com.example.to_dorpg.R;
import com.example.to_dorpg.Reward;
import com.example.to_dorpg.Store;
import com.example.to_dorpg.Waterfall;
import com.example.to_dorpg.database.DBGoldHelper;
import com.example.to_dorpg.model.Gold;


import java.util.ArrayList;



public class HomeFragment extends Fragment {
    private Button btnCharacter;
    private Button btnStore;
    private Button btnAdventure;
    private Button btnPet;
    private DBGoldHelper mDBGoldHelper;
    int mcoins;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;


    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mDBGoldHelper = new DBGoldHelper(this.getActivity());
        ArrayList<Gold> data2 = mDBGoldHelper.getAllData();

        for (int i = 0; i < data2.size(); i++) {

            mcoins = data2.get(i).getCoins();

        }

        btnStore = (Button) getActivity().findViewById(R.id.store);
        btnStore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Store.class);
                startActivity(intent);
            }
        });

        btnCharacter = (Button) getActivity().findViewById(R.id.character);
        btnCharacter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseCharacter.class);
                startActivity(intent);
            }
        });

        btnAdventure = (Button) getActivity().findViewById(R.id.adventure);
        btnAdventure.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (mcoins < 100) {
                    Toast.makeText(getActivity(), "Sorry, you cannot take an adventure now.", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(getActivity(), Adventure.class);
                    startActivity(intent);
                }
            }
        });


        btnPet = (Button) getActivity().findViewById(R.id.pet);
        btnPet.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (mcoins < 100) {
                    Toast.makeText(getActivity(), "Sorry, you don't have a pet now.", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(getActivity(), Pet.class);
                    startActivity(intent);
                }
            }
        });


    }

}