package com.example.cstore.model.api;

import com.example.cstore.common.Utility;
import com.example.cstore.model.Account;
import com.example.cstore.model.Category;
import com.example.cstore.model.Order;
import com.example.cstore.model.Product;
import com.example.cstore.model.ShippingDelivery;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("api/product")
    Call<List<Product>> getProductsByName(@Query("name") String name);
    @GET("api/product/{id}")
    Call<Product> getProductById(@Path("id") String productId);

    @GET("api/shipping")
    Call<List<ShippingDelivery>> getAllShipping();

    @GET("api/shipping")
    Call<List<Order>> getOrderByAccountId(@Query("accountId") String accountId);

    @POST("api/order")
    Call<Order> saveOrder(@Body Order order);

    @POST("api/account/register")
    Call<Account> register(@Body Account account);
}
