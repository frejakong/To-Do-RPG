package com.example.to_dorpg;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.to_dorpg.database.DBOpenHelper;


public class SignIn extends AppCompatActivity implements View.OnClickListener {



    private DBOpenHelper mDBOpenHelper;
    private Button mBtRegisteractivityRegister;
    private RelativeLayout mRlRegisteractivityTop;

    private EditText mEtRegisteractivityUsername;
    private EditText mEtRegisteractivityPassword1;
    private EditText mEtRegisteractivityPassword2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        initView();

        mDBOpenHelper = new DBOpenHelper(this);

    }

    private void initView(){
        mBtRegisteractivityRegister = findViewById(R.id.bt_registeractivity_register);
        mRlRegisteractivityTop = findViewById(R.id.rl_registeractivity_top);


        mEtRegisteractivityUsername = findViewById(R.id.et_sign_inactivity_username);
        mEtRegisteractivityPassword1 = findViewById(R.id.et_sign_inactivity_password1);
        mEtRegisteractivityPassword2 = findViewById(R.id.et_sign_inactivity_password2);




        mBtRegisteractivityRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bt_registeractivity_register:
                String username = mEtRegisteractivityUsername.getText().toString().trim();
                String password1 = mEtRegisteractivityPassword1.getText().toString().trim();
                String password2 = mEtRegisteractivityPassword2.getText().toString().trim();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password1)&& !TextUtils.isEmpty(password2)  ) {
                    if (password1.equals(password2)) {
                        mDBOpenHelper.add(username, password2);
                        Intent intent2 = new Intent(this, Login.class);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(this,  "Sign in successfully, welcome!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Double check, my dear!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Not completed, my dear!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

