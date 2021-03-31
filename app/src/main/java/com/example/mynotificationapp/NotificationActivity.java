package com.example.mynotificationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotificationapp.Data.Cdatabase;
import com.example.mynotificationapp.sql.Database;
import com.example.mynotificationapp.sql.Methods;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    ImageView back;
    private Adapter recyclerViewAdapter;
    private ArrayList<Methods> notList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = findViewById(R.id.recview);

        back = findViewById(R.id.back);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Database db = new Database(this);
        notList = db.getAll();

        Cdatabase cdb = new Cdatabase(getApplicationContext());

        recyclerViewAdapter = new Adapter(this, notList, cdb);
        recyclerView.setAdapter(recyclerViewAdapter);

        back.setOnClickListener(NotificationActivity.this);

    }

    public void backClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
