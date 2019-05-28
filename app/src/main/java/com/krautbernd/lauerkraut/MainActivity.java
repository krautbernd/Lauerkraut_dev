package com.krautbernd.lauerkraut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BoardCardAdapter boardCardAdapter;
    private ArrayList<BoardCard> boardCardArrayList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rc_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        boardCardArrayList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        parseboards();
    }

    private void parseboards(){
        String url = "https://kohlchan.net/boards.js?json=1";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject mett = response.getJSONObject("data");
                            JSONArray jsonArray = mett.getJSONArray("boards");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject brettid = jsonArray.getJSONObject(i);

                                String bretturi = brettid.getString("boardUri");
                                String brettname = brettid.getString("boardName");
                                int pfostenprostunde = brettid.getInt("postsPerHour");

                                boardCardArrayList.add(new BoardCard(bretturi, brettname, pfostenprostunde));

                            }

                            boardCardAdapter = new BoardCardAdapter(MainActivity.this, boardCardArrayList);
                            recyclerView.setAdapter(boardCardAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);

    }
}
