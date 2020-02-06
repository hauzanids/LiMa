package com.example.lima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Absen extends AppCompatActivity {
    EditText p1, sid1;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);

        button = (Button) findViewById(R.id.absen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAbsen();
            }
        });

        p1 = (EditText) findViewById(R.id.pin);
        sid1 = (EditText) findViewById(R.id.sid);
    }

    public void doAbsen(){
        String p = p1.getText().toString();
        String sid = sid1.getText().toString();
        String user_id = getIntent().getStringExtra("user_id");

        background1 bg = new background1(this);
        bg.execute(p, sid, user_id);
    }
}
