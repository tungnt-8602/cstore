package com.example.cstore.presentation.store.product.product_category;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cstore.common.base.BaseViewModel;
import com.example.cstore.model.Product;
import com.example.cstore.model.ProductOrder;

import java.util.List;

public class ProductCategoryViewModel extends BaseViewModel {
    /* **********************************************************************
     * Variable
     ********************************************************************** */

    public ProductCategoryViewModel() {
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addToCart(Product p){
        local.saveToCart(p);
    }

    public void saveAllToCart(List<ProductOrder> poList){
        local.saveAllToCart(poList);
    }

    public List<ProductOrder> getCart(){
        return local.getCart();
    }
}
