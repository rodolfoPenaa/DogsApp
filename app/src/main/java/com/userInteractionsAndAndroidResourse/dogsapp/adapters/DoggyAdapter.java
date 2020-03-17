package com.userInteractionsAndAndroidResourse.dogsapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.userInteractionsAndAndroidResourse.dogsapp.R;
import com.userInteractionsAndAndroidResourse.dogsapp.model.FavoriteDog;
import com.userInteractionsAndAndroidResourse.dogsapp.ui.BreedImageListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DoggyAdapter extends RecyclerView.Adapter<DoggyAdapter.ViewHolder> {


    private LayoutInflater mLayoutInflater;
    private ArrayList<String> dogs;
    private String url;
    private Timestamp timestamp;
    private String raza;
    private Context mContex;
    FirebaseFirestore db;

    public DoggyAdapter(ArrayList<String> dogs, String raza, Context mContex) {
        this.dogs = dogs;
        this.raza = raza;
        this.mContex = mContex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        db = FirebaseFirestore.getInstance();
        View view = mLayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_breed_image_list, parent,false);

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
                        Toast.makeText(mContex , "Added to Favorite", Toast.LENGTH_SHORT).show();
                    }
                });

                return true;
            }
        });


    }
    public void getfavoriteDogs(List<String> favoritesD){
        final FavoriteDog[] favorites = {new FavoriteDog(raza, url, timestamp)};
        db.collection("puppies").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        favorites[0] = (FavoriteDog) document.getData();
                    }
                }
            }
        });
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
    public class vHFavoriteDogs extends RecyclerView.ViewHolder{
        private EditText showmethebreed;
        private ImageView showmetheDog;

        public vHFavoriteDogs(@NonNull View itemView) {
            super(itemView);
            showmethebreed = itemView.findViewById(R.id.breed);
            showmetheDog = itemView.findViewById(R.id.vistaforfavDogs);

        }
    }


}
