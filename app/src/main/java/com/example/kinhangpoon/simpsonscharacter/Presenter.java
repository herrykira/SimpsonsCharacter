package com.example.kinhangpoon.simpsonscharacter;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;

import com.example.kinhangpoon.simpsonscharacter.model.Characters;
import com.example.kinhangpoon.simpsonscharacter.ui.DetailFragment;

import java.util.ArrayList;

/**
 * Created by KinhangPoon on 16/2/2018.
 */

public class Presenter implements IPresenter,MyInterface{
    IMainView iMainView;

    public Presenter(MainActivity mainActivity) {
        iMainView = mainActivity;
    }

    @Override
    public void myMethod(int position, ArrayList<Characters> charactersList) {
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        bundle.putParcelableArrayList("charactersList",charactersList);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        if(checkIsTablet()){
            iMainView.detailFragment(true,detailFragment);
        }
        else{
            iMainView.setAppTitle(charactersList.get(position).getTitle());
            iMainView.detailFragment(false,detailFragment);
        }
    }
    private boolean checkIsTablet() {
        boolean isTablet = false;
        Display display = iMainView.showDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        float widthInches = metrics.widthPixels / metrics.xdpi;
        float heightInches = metrics.heightPixels / metrics.ydpi;
        double diagonalInches = Math.sqrt(Math.pow(widthInches, 2) + Math.pow(heightInches, 2));
        if (diagonalInches >= 7.0) {
            isTablet = true;
        }

        return isTablet;
    }
}
