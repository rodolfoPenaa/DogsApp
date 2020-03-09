package com.userInteractionsAndAndroidResourse.dogsapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.userInteractionsAndAndroidResourse.dogsapp.R;
import com.userInteractionsAndAndroidResourse.dogsapp.adapters.DoggyAdapter;


import java.util.ArrayList;


public class BreedImageListFragment extends Fragment {


    private static String status;
    private static ArrayList<String> doggys;
    private static DoggyAdapter adapter;


    public BreedImageListFragment() {
        // Required empty public constructor
    }

    public static BreedImageListFragment newInstance(ArrayList<String> doggys) {
        BreedImageListFragment fragment = new BreedImageListFragment();
        Bundle args = new Bundle();
        args.putStringArrayList("DOGS", doggys);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            doggys = getArguments().getStringArrayList("DOGS");
            status = getArguments().getString("STATUS");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_breed_list,container,false);

       ArrayList<String> doggys = getArguments().getStringArrayList("DOGS");

       RecyclerView recyclerView = view.findViewById(R.id.recycledview);
       adapter = new DoggyAdapter(doggys, getActivity());
       recyclerView.setAdapter(adapter);
//       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


//        FragmentBreedImageListBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_breed_image_list,container,false);
//        View view = binding.getRoot();
//        Glide.with(getContext()).load(doggys).centerCrop().into(binding.recyclerviewrecycled);

        return view;
    }


}
