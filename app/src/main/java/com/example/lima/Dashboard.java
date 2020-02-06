package com.example.lima;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class Dashboard extends AppCompatActivity {
    private Button button;
    private Button button1;

    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbsensi();
            }
        });

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbsen();
            }
        });

        user_id = getIntent().getStringExtra("user_id");
    }

    public void openAbsensi(){
        Intent intent = new Intent(this, Absensi.class);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }

    public void openAbsen(){
        Intent intent = new Intent(this, Absen.class);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }
}
