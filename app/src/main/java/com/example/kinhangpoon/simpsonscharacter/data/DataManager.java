package com.example.kinhangpoon.simpsonscharacter.data;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kinhangpoon.simpsonscharacter.adapter.MyAdapter;
import com.example.kinhangpoon.simpsonscharacter.model.Characters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KinhangPoon on 18/2/2018.
 */

public class DataManager implements IDataManager {
    public List<Characters> charactersList;
    public String url ="http://api.duckduckgo.com/?q=simpsons+characters&format=json";

    @Override
    public void getList( Context context) {
        charactersList = new ArrayList<>();
        StringRequest myStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject characterObject = new JSONObject(response);
                    JSONArray jsonArray = characterObject.getJSONArray("RelatedTopics");

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        String[] text = data.getString("Text").trim().split("-");
                        String myTitle = text[0];
                        Log.i("myTitle",myTitle);
                        String myDescription = text[1];
                        Log.i("myDescription",myDescription);
                        JSONObject icon = data.getJSONObject("Icon");
                        String myImage = icon.getString("URL").equals("")?"https://image.freepik.com/free-icon/picture-frame-with-mountain-image_318-40293.jpg":icon.getString("URL");
                        Log.i("myImage",myImage);

                        charactersList.add(new Characters(myTitle,myDescription,myImage));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(myStringRequest);
    }
}
