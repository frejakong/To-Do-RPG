package com.example.to_dorpg;

import android.os.Bundle;
import android.widget.TextView;

import com.example.to_dorpg.database.DBAttackHelper;
import com.example.to_dorpg.database.DBGoldHelper;
import com.example.to_dorpg.database.DBIntelligenceHelper;
import com.example.to_dorpg.database.DBStrengthHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
 private DBGoldHelper mDBGoldHelper;
 private DBAttackHelper mDBAttackHelper;
 private DBIntelligenceHelper mDBIntelligenceHelper;
 private DBStrengthHelper mDBStrengthHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_todo, R.id.navigation_home, R.id.navigation_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        mDBGoldHelper=  new DBGoldHelper(this);
        mDBGoldHelper.init();

        mDBStrengthHelper=  new DBStrengthHelper(this);
        mDBStrengthHelper.init();

        mDBIntelligenceHelper=  new DBIntelligenceHelper(this);
        mDBIntelligenceHelper.init();
        
        mDBAttackHelper=  new DBAttackHelper(this);
        mDBAttackHelper.init();
        

    }

}