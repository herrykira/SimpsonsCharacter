package com.example.kinhangpoon.simpsonscharacter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kinhangpoon.simpsonscharacter.MyInterface;
import com.example.kinhangpoon.simpsonscharacter.R;
import com.example.kinhangpoon.simpsonscharacter.model.Characters;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KinhangPoon on 16/2/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<Characters> characterList;
    Context context;
    MyInterface myInterface;

    public MyAdapter(ArrayList<Characters> characterList, Context context, MyInterface myInterface) {
        this.characterList = characterList;
        this.context = context;
        this.myInterface = myInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Characters character = characterList.get(position);
        holder.titleTextView.setText(character.getTitle());
        holder.titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInterface.myMethod(position,characterList);;
            }
        });
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewTitleList);
        }
    }
}
