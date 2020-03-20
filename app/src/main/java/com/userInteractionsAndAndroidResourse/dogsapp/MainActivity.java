package com.userInteractionsAndAndroidResourse.dogsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.userInteractionsAndAndroidResourse.dogsapp.Presenter.breedListPresenter.IViewListBreed;
import com.userInteractionsAndAndroidResourse.dogsapp.model.api.APIResponse;
import com.userInteractionsAndAndroidResourse.dogsapp.model.api.RetrofitClient;
import com.userInteractionsAndAndroidResourse.dogsapp.model.BreedImageListResponse;
import com.userInteractionsAndAndroidResourse.dogsapp.model.BreedListResponse;
import com.userInteractionsAndAndroidResourse.dogsapp.ui.BreedImageListFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IViewListBreed {
    private String choosedbreed;
    private ArrayList<String> dogs;
    private ImageButton favorite;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();


                instanceFFragment();
            }
        });

        APIResponse service = RetrofitClient.getRetrofitInstance().create(APIResponse.class);
        Call<BreedListResponse> call = service.getBreedList();
        call.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {
                if (response.body() != null) {
                    dogs = response.body().getBreedList();
                }

                final ArrayAdapter adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, dogs);                                                       //ADAPTER
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        choosedbreed = dogs.get(position);
                        whoLetTheDogsOut(choosedbreed);
                        instanceFragment(dogs, choosedbreed);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onFailure(Call<BreedListResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Fail Try Again", Toast.LENGTH_LONG).show();
            }
        });



    }

    private void whoLetTheDogsOut(final String ichooseU) {
        APIResponse servicetwo = RetrofitClient.getRetrofitInstance().create(APIResponse.class);
        Call<BreedImageListResponse> callImages = servicetwo.getBreedImageList(ichooseU);
        callImages.enqueue(new Callback<BreedImageListResponse>() {

            @Override
            public void onResponse(Call<BreedImageListResponse> call, Response<BreedImageListResponse> response) {
                ArrayList<String> imagesURL = response.body().getImageUrl();
                instanceFragment(imagesURL, ichooseU);

            }

            @Override
            public void onFailure(Call<BreedImageListResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void instanceFragment(ArrayList<String> puppies, String choosenBreed){
        BreedImageListFragment breedimagelistFragment = BreedImageListFragment.newInstance(puppies, choosenBreed);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.vistadogs, breedimagelistFragment, "DetailFragment")
                .addToBackStack("Dog")
                .commit();
    }

    public void initializeView(){
        spinner = findViewById(R.id.tryspinner);
        favorite = findViewById(R.id.Favorite);

    }

    private void instanceFFragment(ArrayList<String> favorites, String favoritebreed){
        BreedImageListFragment detailFragment = BreedImageListFragment.newInstance(favorites, favoritebreed);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.vistadogs, detailFragment, "DetailFavorites")
                .addToBackStack("favorites")
                .commit();
    }

    @Override
    public void showBreedList(List<String> dogBreedList) {




    }
}
