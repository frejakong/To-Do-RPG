package com.example.to_dorpg.ui.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.to_dorpg.R;
import com.example.to_dorpg.database.DBAttackHelper;
import com.example.to_dorpg.database.DBCharacterHelper;
import com.example.to_dorpg.database.DBGoldHelper;
import com.example.to_dorpg.database.DBIntelligenceHelper;
import com.example.to_dorpg.database.DBStrengthHelper;
import com.example.to_dorpg.model.Attack;
import com.example.to_dorpg.model.Character;
import com.example.to_dorpg.model.Gold;
import com.example.to_dorpg.model.Intelligence;
import com.example.to_dorpg.model.Strength;

import java.util.ArrayList;

public class MeFragment extends Fragment {

    private DBCharacterHelper mDBCharacterHelper;
    private DBGoldHelper mDBGoldHelper;
    private DBStrengthHelper mDBStrengthHelper;
    private DBIntelligenceHelper mDBIntelligenceHelper;
    private DBAttackHelper mDBAttackHelper;
    private ImageView img;
    private TextView gold;
    private TextView mAttack;
    private TextView mStrength;
    private TextView mIntelligence;
    private ProgressBar strength_p;
    private ProgressBar intelligence_p;
    private ProgressBar attack_p;
    private int strength;
    private int intelligence;
    private int attack;
    private int imgSrc;
    private int coins;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        strength_p = view.findViewById(R.id.pb_strength);

        intelligence_p= view.findViewById(R.id.pb_intelligence);
        attack_p = view.findViewById(R.id.pb_attack);


        return view;

    }


    
    

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        img = (ImageView) getActivity().findViewById(R.id.img_me);
        gold= (TextView) getActivity().findViewById(R.id.my_coins);

        mAttack=(TextView) getActivity().findViewById(R.id.my_attack);
        mIntelligence=(TextView) getActivity().findViewById(R.id.my_intelligence);
        mStrength=(TextView) getActivity().findViewById(R.id.my_strength);



        mDBCharacterHelper = new DBCharacterHelper(this.getActivity());
        ArrayList<Character> data1 = mDBCharacterHelper.getAllData();
        for (int i = 0; i < data1.size(); i++) {

            imgSrc = data1.get(i).getImgSrc();
        }
        img.setImageResource(imgSrc);

        mDBGoldHelper = new DBGoldHelper(this.getActivity());


        ArrayList<Gold> data2 = mDBGoldHelper.getAllData();
        for (int i = 0; i < data2.size(); i++) {

            coins = data2.get(i).getCoins();

        }
        gold.setText(String.valueOf(coins));

        mDBAttackHelper = new DBAttackHelper(this.getActivity());


        ArrayList<Attack> data3 = mDBAttackHelper.getAllData();
        for (int i = 0; i < data3.size(); i++) {

            attack = data3.get(i).getValue();

        }
        mAttack.setText(String.valueOf(attack));

        mDBIntelligenceHelper = new DBIntelligenceHelper(this.getActivity());


        ArrayList<Intelligence> data4 = mDBIntelligenceHelper.getAllData();
        for (int i = 0; i < data4.size(); i++) {

            intelligence = data4.get(i).getValue();

        }
        mIntelligence.setText(String.valueOf(intelligence));

        mDBStrengthHelper = new DBStrengthHelper(this.getActivity());


        ArrayList<Strength> data5 = mDBStrengthHelper.getAllData();
        for (int i = 0; i < data5.size(); i++) {

            strength = data5.get(i).getValue();

        }
        mStrength.setText(String.valueOf(strength));

        strength_p.setProgress((int) (strength/5));
        intelligence_p.setProgress((int)(intelligence/1.8));
        attack_p.setProgress((int)(attack/2));
      
    }
}    
