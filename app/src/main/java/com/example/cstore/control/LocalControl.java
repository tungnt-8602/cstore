package com.example.cstore.control;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cstore.model.Product;
import com.example.cstore.model.ProductOrder;
import com.example.cstore.model.Products;

import java.util.List;

public class LocalControl {
    /* **********************************************************************
     * Singleton
     ********************************************************************** */
    private static LocalControl instance;
    private LocalControl() {
        // constructor of the SingletonExample class
    }
    public static LocalControl getInstance() {
        if(instance == null) {
            instance = new LocalControl();
        }

        // returns the singleton object
        return instance;
    }
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    private final Preference preference = new Preference();

    /* **********************************************************************
     * Set Context
     ********************************************************************** */
    public void setContext(Context context){
        preference.initPreference(context);
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void saveToCart(Product p) {
        preference.saveToCart(p);
    }

    public void saveAllToCart(List<ProductOrder> poList) {
        preference.updateAllCart(poList);
    }

    public List<ProductOrder> getCart() {
        return preference.getCart();
    }
}
