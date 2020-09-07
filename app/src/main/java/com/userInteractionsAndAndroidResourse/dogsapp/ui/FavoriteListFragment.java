package com.userInteractionsAndAndroidResourse.dogsapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.userInteractionsAndAndroidResourse.dogsapp.R;
import com.userInteractionsAndAndroidResourse.dogsapp.ui.adapters.DoggyAdapter;


public class FavoriteListFragment extends Fragment {

    private static final String NAME_BREED = "fBreed";
    private static final String IMAGES_DOGS = "fBUrl";
    private static final String TIMESTAMP = "description";

    void favoriteListFragment() {
    }

    private DoggyAdapter adapter;
    private String fBreed;
    private String fBUrl;
    private String description;

        public static FavoriteListFragment newInstance (String fBreed, String fBUrl, String
        description){
            FavoriteListFragment fragment = new FavoriteListFragment();
            Bundle args = new Bundle();
            args.putString(NAME_BREED, fBreed);
            args.putString(TIMESTAMP, description);
            args.putString(IMAGES_DOGS, fBUrl);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                fBreed = getArguments().getString(NAME_BREED);
                fBUrl = getArguments().getString(IMAGES_DOGS);
                description = getArguments().getString(TIMESTAMP);
            }
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){

            View view = inflater.inflate(R.layout.fragment_breed_list, container, false);
            RecyclerView recyclerView = view.findViewById(R.id.recycledview);
            adapter = new DoggyAdapter(doggys, raza, getActivity());
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            return view;
        }
    }

