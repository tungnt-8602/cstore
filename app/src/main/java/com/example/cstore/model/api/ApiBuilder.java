package com.example.cstore.model.api;

import com.example.cstore.common.Utility;
import com.example.cstore.model.Category;
import com.example.cstore.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiBuilder {
    ApiBuilder apiService = new Retrofit.Builder()
            .baseUrl(Utility.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiBuilder.class);

    @GET("api/product")
    Call<List<Product>> getProductList();

    @GET("api/category/all")
    Call<List<Category>> getAllCategory();

    @GET("api/product")
    Call<List<Product>> getProductsByCategoryId(@Query("categoryId") String categoryId);

    @GET("api/product/{id}")
    Call<Product> getProductById(@Path("id") String productId);
}
