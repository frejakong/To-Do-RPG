package com.example.to_dorpg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.solver.widgets.Helper;

import com.example.to_dorpg.R;
import com.example.to_dorpg.database.DBAttackHelper;
import com.example.to_dorpg.database.DBCharacterHelper;
import com.example.to_dorpg.database.DBGoldHelper;
import com.example.to_dorpg.database.DBIntelligenceHelper;
import com.example.to_dorpg.database.DBStrengthHelper;
import com.example.to_dorpg.database.TableFields;

import com.example.to_dorpg.database.TodoTaskManager;

import com.example.to_dorpg.model.Attack;
import com.example.to_dorpg.model.Gold;
import com.example.to_dorpg.model.Intelligence;
import com.example.to_dorpg.model.Strength;
import com.example.to_dorpg.model.TodoTask;
import com.example.to_dorpg.ui.todo.TodoFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends Activity {

	private static final String TAG = "com.example.to_dorpg.database.todo.DetailActivity";

	public static final String TASK_ID = "TASK_ID";

	private DBGoldHelper mDBGoldHelper;

	private EditText etTitle, etContent;

	private ImageButton btnMarkComplete, btnSave;


	private TodoTaskManager todoTaskManager;


	private boolean flagForUpdate = false;

	private String taskId;

	private boolean toMarkComplete = true;
    private int coins;
	private DBStrengthHelper mDBStrengthHelper;
	private DBIntelligenceHelper mDBIntelligenceHelper;
	private DBAttackHelper mDBAttackHelper;

	private int strength;
	private int intelligence;
	private int attack;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todo_detail);
		todoTaskManager = new TodoTaskManager(this);


		initComponents();


		btnSave = (ImageButton)findViewById(R.id.save);
		btnSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				ContentValues contentValues = new ContentValues();
				contentValues.put(TableFields.TodoTask.FLAG_COMPLETED, "N");
				contentValues.put(TableFields.TodoTask.COMPLETE_TIME, "");

				todoTaskManager.update(contentValues, TableFields.TodoTask.ID + " = ? ", new String[]{taskId});
				save();
				finish();
				Intent intent = new Intent(DetailActivity.this, MainActivity.class);
				startActivity(intent);
			}

		});

		mDBGoldHelper=   new DBGoldHelper(this);
		mDBAttackHelper = new DBAttackHelper(this);
		mDBIntelligenceHelper = new DBIntelligenceHelper(this);
		mDBStrengthHelper = new DBStrengthHelper(this);

		btnMarkComplete = findViewById(R.id.complete);
		btnMarkComplete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				ContentValues contentValues = new ContentValues();

				contentValues.put(TableFields.TodoTask.FLAG_COMPLETED, "Y");
				todoTaskManager.update(contentValues, TableFields.TodoTask.ID + " = ? ", new String[]{taskId});

				Toast.makeText(DetailActivity.this, "Congratulations! You got 10 gold coins! ", Toast.LENGTH_LONG).show();

				ArrayList<Gold> data = mDBGoldHelper.getAllData();
				for (int i = 0; i < data.size(); i++) {

					coins = data.get(i).getCoins();

				}
				mDBGoldHelper.obtainGold(coins);
				Intent intent = new Intent(DetailActivity.this, MainActivity.class);
				startActivity(intent);

				finish();



				ArrayList<Attack> data2 = mDBAttackHelper.getAllData();
				for (int i = 0; i < data2.size(); i++) {

					attack = data2.get(i).getValue();

				}
				mDBAttackHelper.completeTasks(attack);


				ArrayList<Intelligence> data3 = mDBIntelligenceHelper.getAllData();
				for (int i = 0; i < data3.size(); i++) {

					intelligence = data3.get(i).getValue();

				}
				mDBIntelligenceHelper.completeTasks(intelligence);

				ArrayList<Strength> data4 = mDBStrengthHelper.getAllData();
				for (int i = 0; i < data4.size(); i++) {

					strength = data4.get(i).getValue();

				}
				mDBStrengthHelper.completeTasks(strength);
				}
		});
	}




	protected void onDestroy() {
		super.onDestroy();
		todoTaskManager.close();

	}

	private void initComponents() {


		etTitle = (EditText) findViewById(R.id.etTitle);
		etContent = (EditText) findViewById(R.id.etContent);


		taskId = getIntent().getStringExtra(TASK_ID);
		if (taskId != null) {
			TodoTask todoTask = todoTaskManager.queryById(taskId);
			if (todoTask != null) {
				etTitle.setText(todoTask.getTitle());
				etContent.setText(todoTask.getContent());
				flagForUpdate = true;
				if ("N".equals(todoTask.getFlagCompleted())) {
					toMarkComplete = true;
				} else {
					toMarkComplete = false;
				}
			}
		}

			if (flagForUpdate) {
				if (toMarkComplete) {
					if (strength > 0){
						btnMarkComplete = findViewById(R.id.complete);
					btnMarkComplete.setVisibility(View.VISIBLE);
				}

				}
				else {
					Toast.makeText(this, "Sorry, you don't have enough strength now.", Toast.LENGTH_SHORT).show();
				}

			}
		}



			public void save () {
				String title = etTitle.getText().toString();
				String content = etContent.getText().toString();

					ContentValues contentValues = new ContentValues();
					contentValues.put(TableFields.TodoTask.TITLE, title);
					contentValues.put(TableFields.TodoTask.CONTENT, content);
					contentValues.put(TableFields.TodoTask.FLAG_COMPLETED, "N");
					if (flagForUpdate) {
						todoTaskManager.update(contentValues, TableFields.TodoTask.ID + " = ? ", new String[]{taskId});
					} else {
						long id = todoTaskManager.insert(contentValues);
						taskId = String.valueOf(id);
					}

				}

;

			}




