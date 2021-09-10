package com.example.to_dorpg;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.to_dorpg.database.DBOpenHelper;
import com.example.to_dorpg.model.User;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private DBOpenHelper mDBOpenHelper;
    private Button signIn;

    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;

    private Button mBtLoginactivityLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initView();

        mDBOpenHelper = new DBOpenHelper(this);

        mBtLoginactivityLogin.setOnClickListener(this);
        signIn.setOnClickListener(this);
    }


    private void initView() {
        mBtLoginactivityLogin = findViewById(R.id.bt_loginactivity_login);

       signIn= findViewById(R.id.sign_in);

        mEtLoginactivityUsername = findViewById(R.id.et_loginactivity_username);
        mEtLoginactivityPassword = findViewById(R.id.et_loginactivity_password);

        mBtLoginactivityLogin.setOnClickListener(this);
        signIn.setOnClickListener(this);

}
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in:
                startActivity(new Intent(this, SignIn.class));
                finish();
                break;

            case R.id.bt_loginactivity_login:
                String name = mEtLoginactivityUsername.getText().toString().trim();
                String password = mEtLoginactivityPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Uh oh, check your username or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Input your username and password!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}





