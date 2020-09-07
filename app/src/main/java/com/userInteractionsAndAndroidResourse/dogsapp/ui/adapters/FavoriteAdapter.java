package com.userInteractionsAndAndroidResourse.dogsapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.userInteractionsAndAndroidResourse.dogsapp.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    FirebaseFirestore db;
    private LayoutInflater mLayoutInflater;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        db = FirebaseFirestore.getInstance();

        View view = mLayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_breed_favorite_image_list, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {





    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private EditText showmethebreed;
        private ImageView showmetheDog;
        private EditText tDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            showmethebreed = itemView.findViewById(R.id.breed);
            showmetheDog = itemView.findViewById(R.id.vistaforfavDogs);
            tDescription = itemView.findViewById(R.id.timestamp);

        }

    }
    public void getfavoriteDogs(List<String> favoritesBREED, List<String> favoritesURL ) {

        //final FavoriteDog[] favorites = {new FavoriteDog(raza, url, timestamp)};


        // get parent (root)
        //get child
        //get snapshot

        db.collection("puppies").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {


                        //favorites[0] = (FavoriteDog) document.getData();
                    }
                }
            }
        });
    }
}
