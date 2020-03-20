package com.userInteractionsAndAndroidResourse.dogsapp.ui.adapters;

import android.content.Context;
import android.net.sip.SipAudioCall;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.userInteractionsAndAndroidResourse.dogsapp.R;
import com.userInteractionsAndAndroidResourse.dogsapp.model.FavoriteDog;

import java.util.ArrayList;


public class DoggyAdapter extends RecyclerView.Adapter<DoggyAdapter.ViewHolder> {


    private LayoutInflater mLayoutInflater;
    private ArrayList<String> dogs;
    private String raza;
    private Context mContex;
    private SipAudioCall.Listener listener;
    public FirebaseFirestore db;

    public DoggyAdapter(ArrayList<String> dogs, String raza, Context mContex) {
        this.dogs = dogs;
        this.raza = raza;
        this.mContex = mContex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mLayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_breed_image_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String breedImageListResponse = dogs.get(position);

        Glide.with(holder.imageView.getContext()).load(breedImageListResponse).into(holder.imageView);

        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                CollectionReference dbFavDogs = db.collection("puppies");
                FavoriteDog favpuppies = new FavoriteDog(raza, dogs.get(position), Timestamp.now());
                dbFavDogs.add(favpuppies).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(mContex, "Added to Favorite", Toast.LENGTH_SHORT).show();
                    }
                });

                return true;
            }
        });


    }

    @Override
    public int getItemCount() {

        return dogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.vistaforDogs);

        }
    }
}