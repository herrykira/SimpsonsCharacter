package com.example.kinhangpoon.simpsonscharacter.ui.details;

import android.view.View;

import com.example.kinhangpoon.simpsonscharacter.model.Characters;

import java.util.ArrayList;

/**
 * Created by KinhangPoon on 20/2/2018.
 */

public class DetailsPresenterImpl implements IDetailsPresenter{
    IDetailsView iDetailsView;

    public DetailsPresenterImpl(DetailFragment detailFragment){
        iDetailsView = detailFragment;
    }

    @Override
    public void onViewCreated(View view, int position, ArrayList<Characters> charactersList) {
        iDetailsView.updateTextView(view,position,charactersList);
    }
}
