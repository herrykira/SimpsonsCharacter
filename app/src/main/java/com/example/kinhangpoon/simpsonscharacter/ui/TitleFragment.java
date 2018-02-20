package com.example.kinhangpoon.simpsonscharacter.ui;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kinhangpoon.simpsonscharacter.IPresenter;
import com.example.kinhangpoon.simpsonscharacter.MyInterface;
import com.example.kinhangpoon.simpsonscharacter.R;
import com.example.kinhangpoon.simpsonscharacter.adapter.MyAdapter;
import com.example.kinhangpoon.simpsonscharacter.model.Characters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by KinhangPoon on 16/2/2018.
 */

public class TitleFragment extends Fragment {
    RecyclerView recyclerView;
    ProgressDialog pd;
    ArrayList<Characters> charactersList;
    MyInterface myInterface;

    public String url ="http://api.duckduckgo.com/?q=simpsons+characters&format=json";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myInterface = (MyInterface) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.title_fragment,container,false);


        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);

        pd = new ProgressDialog(getContext());
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.show();

        charactersList = new ArrayList<>();
        StringRequest myStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
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
                    MyAdapter myAdapter = new MyAdapter(charactersList,getContext(),myInterface);
                    recyclerView.setAdapter(myAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(myStringRequest);

        return view;
    }
}
