package com.example.kinhangpoon.simpsonscharacter;

import android.view.Display;

import com.example.kinhangpoon.simpsonscharacter.ui.DetailFragment;

/**
 * Created by KinhangPoon on 16/2/2018.
 */

public interface IMainView {
    void detailFragment(boolean isTablet, DetailFragment detailFragment);
    void setAppTitle(String s);
    Display showDisplay();
}
