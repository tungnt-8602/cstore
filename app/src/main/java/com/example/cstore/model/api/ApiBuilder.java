package com.example.cstore.model.api;

import com.example.cstore.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiBuilder {
    ApiBuilder apiService = new Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiBuilder.class);

    @GET("api/products")
    Call<List<Product>> getProductList(@Query("search") String keyword);

}
