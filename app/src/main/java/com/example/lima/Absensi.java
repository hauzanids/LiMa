package com.example.lima;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Absensi extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SprintAdapter mSprintAdapter;
    private ArrayList<SprintItem> mSprintList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSprintList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON(){
        String url = "https://hauzanids.github.io/sprints.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject sprints = jsonArray.getJSONObject(i);

                                String sprint = sprints.getString("sprint");
                                String tanggal = sprints.getString("tanggal");
                                String jam_mulai = sprints.getString("jam_mulai");
                                String jam_akhir = sprints.getString("jam_akhir");

                                mSprintList.add(new SprintItem(sprint, tanggal, jam_mulai, jam_akhir));
                            }

                            mSprintAdapter = new SprintAdapter(Absensi.this, mSprintList);
                            mRecyclerView.setAdapter(mSprintAdapter);

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}
