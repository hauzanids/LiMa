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
import java.util.Objects;

public class Absensi extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SprintAdapter mSprintAdapter;
    private ArrayList<SprintItem> mSprintList;
    private RequestQueue mRequestQueue;

    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);

        user_id = getIntent().getStringExtra("user_id");

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSprintList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON(){
        String url = "http://10.0.2.2/api/absence_logs.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray s = response.getJSONArray("data");

                            for (int i = 0; i < s.length(); i++) {
                                JSONObject sprints = s.getJSONObject(i);
                                String userid = sprints.getString("user_id");
                                if (userid.equals(user_id)){
                                    String sprint1 = sprints.getString("sprint_id");
                                    String sprint = String.format("Sprint %s", sprint1);
                                    String tanggal = sprints.getString("created_at");
                                    String jam_mulai = sprints.getString("jam_mulai");
                                    String jam_akhir = sprints.getString("jam_akhir");

                                    mSprintList.add(new SprintItem(sprint, tanggal, jam_mulai, jam_akhir));
                                }
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
