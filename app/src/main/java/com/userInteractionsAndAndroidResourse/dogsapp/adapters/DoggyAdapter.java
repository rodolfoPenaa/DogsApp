package com.userInteractionsAndAndroidResourse.dogsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.userInteractionsAndAndroidResourse.dogsapp.R;

import java.util.ArrayList;


public class DoggyAdapter extends RecyclerView.Adapter<DoggyAdapter.ViewHolder> {


    private LayoutInflater mLayoutInflater;
    private ArrayList<String> dogs;
    private Context mContex;

    public DoggyAdapter(ArrayList<String> dogs, Context mContex) {
        this.dogs = dogs;
        this.mContex = mContex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mLayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_breed_image_list, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String breedImageListResponse = dogs.get(position);
        Glide.with(holder.imageView.getContext()).load(breedImageListResponse).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        dogs.size();
        return dogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        imageView = itemView.findViewById(R.id.vistaforDogs);

        }
    }
    public int getItemCount(ArrayList<String> dogs) {
        return dogs.size();
    }
}
