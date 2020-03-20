package com.userInteractionsAndAndroidResourse.dogsapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.Timestamp;
import com.userInteractionsAndAndroidResourse.dogsapp.R;
import com.userInteractionsAndAndroidResourse.dogsapp.model.FavoriteDog;


public class BreedListFragment extends Fragment {

    private static final String NAME_BREED = "param1";
    private static final String IMAGES_DOGS = "param2";
    private static final String TIMESTAMP = "param3";


    private String fBreed;
    private String fBUrl;
    private Timestamp description;

    public BreedListFragment() {

    }


    public static BreedListFragment newInstance(String fBreed, String fBUrl, Timestamp description) {
        BreedListFragment fragment = new BreedListFragment();
        Bundle args = new Bundle();
        args.putString(NAME_BREED, fBreed );
        args.putString(TIMESTAMP, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fBreed = getArguments().getString(NAME_BREED);
            fBUrl = getArguments().getString(IMAGES_DOGS);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breed_list, container, false);
    }
}
