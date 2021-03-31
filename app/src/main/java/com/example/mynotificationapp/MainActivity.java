package com.example.mynotificationapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mynotificationapp.Data.Cdatabase;
import com.example.mynotificationapp.sql.Database;
import com.example.mynotificationapp.sql.Methods;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    Counter counter;
    ConstraintLayout constraintLayout;
    TextInputEditText not_title, not_desc;
    LinearLayout lay;
    TextView count;

    int total =0, read=0;
    Database db;
    Cdatabase cdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        not_title = findViewById(R.id.titletext);
        not_desc = findViewById(R.id.desctext);

        counter = new Counter(findViewById(R.id.bell2));
        View noti_page = findViewById(R.id.bell2);
        count = findViewById(R.id.notification_number);
        lay = findViewById(R.id.notificationNumberContainer);
        constraintLayout = findViewById(R.id.parent);

        //final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.notification);

        db = new Database(getApplicationContext());
        cdb = new Cdatabase(getApplicationContext());
        getNotification();

        constraintLayout.setOnClickListener(MainActivity.this);
        noti_page.setOnClickListener(MainActivity.this);

        /*
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyBoard(v);
                //getNotification();
            }
        });
*/
        noti_page.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(),NotificationActivity.class);
            startActivity(it);
            finish();
        });

        btn.setOnClickListener(MainActivity.this);
/*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = not_title.getText().toString();
                String description = not_desc.getText().toString();
                if(title.equals("")){
                    Toast.makeText(getApplicationContext(),"Title is Required!",Toast.LENGTH_SHORT).show();
                }else
                if(description.equals("")){
                    Toast.makeText(getApplicationContext(),"Plz give the description!",Toast.LENGTH_SHORT).show();
                }else{
                    counter.increaseNumber();
                    Methods not = new Methods(title,description);
                    db.add(not);
                    //mediaPlayer.start();
                    not_title.setText("");
                    not_desc.setText("");
                }
                getNotification();
            }
        });*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.parent:
            {
                closeKeyBoard(v);
            }

            case R.id.button:
            {
                String title = not_title.getText().toString();
                String description = not_desc.getText().toString();
                if(title.equals("")){
                    Toast.makeText(getApplicationContext(),"Title is Required!",Toast.LENGTH_SHORT).show();
                }else
                if(description.equals("")){
                    Toast.makeText(getApplicationContext(),"Plz give the description!",Toast.LENGTH_SHORT).show();
                }else{
                    counter.increaseNumber();
                    Methods not = new Methods(title,description);
                    db.add(not);
                    //mediaPlayer.start();
                    not_title.setText("");
                    not_desc.setText("");
                }
                getNotification();
            }
        }
    }

    public void getNotification(){
        read = cdb.getCount();
        total = db.getCount();

        final int no_of_notification = total - read;
        if(no_of_notification>0){
            lay.setVisibility(View.VISIBLE);
            count.setText(String.valueOf(no_of_notification));
        }else{
            lay.setVisibility(View.INVISIBLE);
        }
    }

    private void closeKeyBoard(View view){
        view = this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void buttonClick(View view) {
    }
}