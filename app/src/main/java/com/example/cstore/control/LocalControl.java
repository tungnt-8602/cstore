package com.example.cstore.control;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cstore.model.ProductOrder;

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
    public void saveToCart(ProductOrder po) {
        preference.saveToCart(po);
    }

    public void saveAllToCart(List<ProductOrder> poList) {
        preference.updateAllCart(poList);
    }

    public List<ProductOrder> getCart() {
        return preference.getCart();
    }

    public void pickShippingDelivery(String shippingName){
        preference.saveShippingDelivery(shippingName);
    }

    public String getShippingDelivery(){
        return preference.getShippingDelivery();
    }
    public void saveShippingPrice(Integer price){
        preference.saveShippingPrice(price);
    }

    public Integer getShippingPrice(){
        return preference.getShippingPrice();
    }

    public void saveProductPrice(Integer price){
        preference.saveProductPrice(price);
    }

    public Integer getProductPrice(){
        return preference.getProductPrice();
    }
}
