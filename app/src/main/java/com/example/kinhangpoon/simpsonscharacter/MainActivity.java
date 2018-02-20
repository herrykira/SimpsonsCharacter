package com.example.kinhangpoon.simpsonscharacter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;

import com.example.kinhangpoon.simpsonscharacter.model.Characters;
import com.example.kinhangpoon.simpsonscharacter.ui.DetailFragment;
import com.example.kinhangpoon.simpsonscharacter.ui.TitleFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main,new TitleFragment()).commit();
    }

    @Override
    public void myMethod(int position, ArrayList<Characters> charactersList) {
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        bundle.putParcelableArrayList("charactersList",charactersList);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        if(checkIsTablet()){
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main2,detailFragment).commit();
        }
        else{
            setTitle(charactersList.get(position).getTitle());
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main,detailFragment).commit();
        }

    }
    private boolean checkIsTablet() {
        boolean isTablet = false;
        Display display = MainActivity.this.getWindowManager().getDefaultDisplay();
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
