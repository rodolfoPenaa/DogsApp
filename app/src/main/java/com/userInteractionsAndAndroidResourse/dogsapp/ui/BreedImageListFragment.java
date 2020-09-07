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


import java.util.ArrayList;


public class BreedImageListFragment extends Fragment {

    private static ArrayList<String> doggys;
    private static DoggyAdapter adapter;
    private String raza;

    public BreedImageListFragment() {
        // Required empty public constructor
    }

    public static BreedImageListFragment newInstance(ArrayList<String> doggys, String choosenBreed) {
        BreedImageListFragment fragment = new BreedImageListFragment();
        Bundle args = new Bundle();
        args.putStringArrayList("DOGS", doggys);
        args.putString("raza", choosenBreed);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            doggys = getArguments().getStringArrayList("DOGS");
            raza = getArguments().getString("raza");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_breed_list,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycledview);
        adapter = new DoggyAdapter(doggys,raza, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }


}
