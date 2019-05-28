package com.krautbernd.lauerkraut;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser {

    private TextView result;
    private RequestQueue queue;

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
                                int bernde = brettid.getInt("uniqueIps");
                                int pfostenprostunde = brettid.getInt("postsPerHour");

                                // String.valueOf für int wird nicht gebraucht
                                result.append(
                                        " /" + bretturi + "/ " + brettname + "\n  Users: " + bernde + ", PPS: " + pfostenprostunde + "\n\n"
                                );

                            }
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

        queue.add(request);
    }


}
