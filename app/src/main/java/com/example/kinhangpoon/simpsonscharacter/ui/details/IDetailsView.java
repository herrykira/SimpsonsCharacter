package com.example.kinhangpoon.simpsonscharacter.ui.details;

import android.view.View;

import com.example.kinhangpoon.simpsonscharacter.model.Characters;

import java.util.ArrayList;

/**
 * Created by KinhangPoon on 20/2/2018.
 */

public interface IDetailsView {
    public void updateTextView(View view, int position, ArrayList<Characters> charactersList);
}
