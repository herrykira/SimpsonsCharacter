package com.example.kinhangpoon.simpsonscharacter.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kinhangpoon.simpsonscharacter.R;
import com.example.kinhangpoon.simpsonscharacter.model.Characters;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KinhangPoon on 18/2/2018.
 */
//assume details fragment to be a view
    //mvp philosopy view should forward user input events to presenter
public class DetailFragment extends Fragment implements IDetailsView{

    List<Characters> charactersList;
    TextView titleTextView;
    TextView descriptionTextView;
    ImageView imageView;
    IDetailsPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailsPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_item,container,false);

        int position = getArguments().getInt("position");
        ArrayList<Characters> charactersList = getArguments().getParcelableArrayList("charactersList");
        Log.i("list size",charactersList.size()+"");
        //
        //presetner evetnt is onViewCreated ---  mr presenter my view is ready -  ui event
        presenter.onViewCreated(view,position,charactersList);
        //view evernt is set the textview -- presenter has to update the view


        return view;
    }


    @Override
    public void updateTextView(View view, int position, ArrayList<Characters> mcharactersList) {
        titleTextView = view.findViewById(R.id.textViewTitle);
        descriptionTextView = view.findViewById(R.id.textViewDescription);
        imageView = view.findViewById(R.id.imageView);

        Characters character = mcharactersList.get(position);
        titleTextView.setText(character.getTitle());
        descriptionTextView.setText(character.getDescription());
        Picasso.with(getContext()).load(character.getImageUrl()).into(imageView);

    }
}
