package com.example.lima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.lima.R.id.listView;

public class Absensi extends AppCompatActivity {
    private static final String JSON_URL = "https://hauzanids.github.io/sprints.json";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);
        listView = findViewById(listView);
    }

    private void loadSprint() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    listView = findViewById(listView);
                    JSONObject obj = new JSONObject(response);
                    JSONArray sprintArray = obj.getJSONArray("result");

                    ArrayList<SprintItem> sprintItemList;
                    for (int i = 0; i < sprintArray.length(); i++) {
                        JSONObject sprintObject = sprintArray.getJSONObject(i);
                        SprintItem sprintItem = new SprintItem(
                                sprintObject.getString("tanggal"),
                                sprintObject.getString("jam_mulai"),
                                sprintObject.getString("jam_akhir"));
                        sprintItemList.add(sprintItem);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(sprintItemList, getApplicationContext());
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
