package com.example.mynotificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynotificationapp.R;

public class DisplayNotification extends AppCompatActivity implements View.OnClickListener {

    ImageView back_rec;
    TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_notification);
        back_rec = findViewById(R.id.back_recView);
        title = findViewById(R.id.title_show);
        description = findViewById(R.id.show_desc);

        Intent it = getIntent();

        String name = it.getStringExtra("Rname");
        String dname = it.getStringExtra("Rdesc");

        title.setText(name);
        description.setText(dname);
        back_rec.setOnClickListener(this);

    }


    public void imgClick(View view) {
        startActivity(new Intent(getApplicationContext(),NotificationActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),NotificationActivity.class));
        finish();
    }
}
