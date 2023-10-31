package com.example.cstore.model.api;

import com.example.cstore.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface ProductApi {
    @GET("api/products/{id}")
    Call<Product> getProductById(@Path("id") int productId);

    @GET("api/products")
    Call<List<Product>> getProductList(@Query("term") String keyword, @Query("key") String apiKey);

}