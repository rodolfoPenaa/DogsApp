package com.userInteractionsAndAndroidResourse.dogsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.userInteractionsAndAndroidResourse.dogsapp.api.APIResponse;
import com.userInteractionsAndAndroidResourse.dogsapp.api.RetrofitClient;
import com.userInteractionsAndAndroidResourse.dogsapp.model.BreedImageListResponse;
import com.userInteractionsAndAndroidResourse.dogsapp.model.BreedListResponse;
import com.userInteractionsAndAndroidResourse.dogsapp.ui.BreedImageListFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String choosedbreed;
    private RecyclerView recyclerView;
    private ArrayList<String> dogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIResponse service = RetrofitClient.getRetrofitInstance().create(APIResponse.class);
        Call<BreedListResponse> call = service.getBreedList();
        call.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {
                if (response.body() != null) {
                    dogs = response.body().getBreedList();
                }
                Spinner spinner = findViewById(R.id.tryspinner);
                final ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, dogs);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        choosedbreed = dogs.get(position);
                        whoLetTheDogsOut(choosedbreed);
                        instanceFragment(dogs);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

//                Log.e("PUPPIES", String.valueOf(perro1));

            }

            @Override
            public void onFailure(Call<BreedListResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Fail Try Again", Toast.LENGTH_LONG).show();
//                Log.e("PUPPIES", String.valueOf(t));
            }
        });

    }

    private void whoLetTheDogsOut(String choosedbreed) {
        APIResponse servicetwo = RetrofitClient.getRetrofitInstance().create(APIResponse.class);
        Call<BreedImageListResponse> callImages = servicetwo.getBreedImageList(choosedbreed);
        callImages.enqueue(new Callback<BreedImageListResponse>() {

            @Override
            public void onResponse(Call<BreedImageListResponse> call, Response<BreedImageListResponse> response) {
                ArrayList<String> imagesURL = response.body().getImageUrl();
                instanceFragment(imagesURL);
//                Log.e("IMAGESDOGS", String.valueOf(imagesURL));
            }

            @Override
            public void onFailure(Call<BreedImageListResponse> call, Throwable t) {
//                Log.e("ERRORS", String.valueOf(t));
            }
        });

    }
    private void instanceFragment(ArrayList<String> dogs){
        BreedImageListFragment detailFragment = BreedImageListFragment.newInstance(dogs);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.vistadogs, detailFragment, "DetailFragment")
                .addToBackStack("Dog")
                .commit();
    }
}
