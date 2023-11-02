package com.example.cstore.presentation.store.cart;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cstore.common.base.BaseViewModel;
import com.example.cstore.model.ProductOrder;
import com.example.cstore.model.Products;

import java.util.List;

public class CartViewModel extends BaseViewModel {
    /* **********************************************************************
     * Variable
     ********************************************************************** */

    /* **********************************************************************
     * Variable
     ********************************************************************** */
    public CartViewModel() {
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
    @RequiresApi(api = Build.VERSION_CODES.N)

    public List<ProductOrder> getCart(){
        return local.getCart();
    }
}
