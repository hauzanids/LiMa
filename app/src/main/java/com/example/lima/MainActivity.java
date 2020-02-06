package com.example.lima;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {

    EditText pas,usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usr = (EditText) findViewById(R.id.username);
        pas = (EditText) findViewById(R.id.password);
    }

    public void loginBtn(View view){
        String user = usr.getText().toString();
        String pass = pas.getText().toString();

        background bg = new background(this);
        bg.execute(user,pass);
    }
}
