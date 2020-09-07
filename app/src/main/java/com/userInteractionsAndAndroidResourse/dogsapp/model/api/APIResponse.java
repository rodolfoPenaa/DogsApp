package com.userInteractionsAndAndroidResourse.dogsapp.model.api;
import com.userInteractionsAndAndroidResourse.dogsapp.model.BreedImageListResponse;
import com.userInteractionsAndAndroidResourse.dogsapp.model.BreedListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIResponse {

    @GET("api/breeds/list/")
    Call<BreedListResponse> getBreedList();

    @GET("api/breed/{breed}/images/")
    Call<BreedImageListResponse> getBreedImageList(@Path("breed") String catchedBreed); // nombre de variables deben ser iguales//


}
