package com.example.cstore.presentation.store.cart.detail;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cstore.common.base.BaseViewModel;
import com.example.cstore.model.ProductOrder;

public class CartDetailViewModel extends BaseViewModel {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addToCart(ProductOrder po){
        local.saveToCart(po);
    }
}
