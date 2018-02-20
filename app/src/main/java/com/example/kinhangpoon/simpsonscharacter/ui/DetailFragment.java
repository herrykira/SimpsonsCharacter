package com.example.kinhangpoon.simpsonscharacter.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kinhangpoon.simpsonscharacter.R;
import com.example.kinhangpoon.simpsonscharacter.data.DataManager;
import com.example.kinhangpoon.simpsonscharacter.model.Characters;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KinhangPoon on 18/2/2018.
 */

public class DetailFragment extends Fragment{

    List<Characters> charactersList;
    TextView titleTextView;
    TextView descriptionTextView;
    ImageView imageView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_item,container,false);

        int position = getArguments().getInt("position");
        ArrayList<Characters> charactersList = getArguments().getParcelableArrayList("charactersList");
        Log.i("list size",charactersList.size()+"");
        titleTextView = view.findViewById(R.id.textViewTitle);
        descriptionTextView = view.findViewById(R.id.textViewDescription);
        imageView = view.findViewById(R.id.imageView);

        Characters character = charactersList.get(position);
        titleTextView.setText(character.getTitle());
        descriptionTextView.setText(character.getDescription());
        Picasso.with(getContext()).load(character.getImageUrl()).into(imageView);

        return view;
    }
}
