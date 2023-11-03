package com.example.cstore.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.example.cstore.model.Product;
import com.example.cstore.model.ProductOrder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Preference {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    private final String preferenceName = "Preference";
    private final String cartList = preferenceName + ":listCart";

    private SharedPreferences shared = null;
    private SharedPreferences.Editor editor = null;
    private final Gson gson = new Gson();

    /* **********************************************************************
     * Function - Init
     ********************************************************************** */
    void initPreference(Context context) {
        shared = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        if (shared != null) {
            editor = shared.edit();
        }
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
    @RequiresApi(api = Build.VERSION_CODES.N)
    void saveToCart(Product p) {
        try {
            List<ProductOrder> poList = getCart();
            ProductOrder poToCart = new ProductOrder(
                    p.getId(), p.getName(), p.getPrice(),
                    p.getColors().get(0), p.getSizes().get(0),
                    p.getCategoryId(), p.getImages().get(0).getUrl(), 1);
            Boolean duplicate = checkAddDuplicateProductToCart(poToCart, poList);
            String pastList = shared.getString(cartList, "");
            if (duplicate) {
                String jsonList = gson.toJson(poList);
                editor.putString(cartList, jsonList.replace("[", "").replace("]", "")).apply();
            } else {
                String json = gson.toJson(poToCart);
                if (!(pastList.equals(""))) {
                    pastList += ",";
                }
                editor.putString(cartList, pastList + json).apply();
                Log.d("add not duplicate", "saveToCart: " + pastList + json);
            }
        } catch (Exception e) {
            Log.d("add ERROR", "saveToCart: " + e.getMessage());
        }
    }

    Boolean checkAddDuplicateProductToCart(ProductOrder poToCart, List<ProductOrder> poList) {
        boolean duplicate = false;
        Log.d("list cart", "saveToCart: " + poList);
        if (!poList.isEmpty()) {
            for (ProductOrder poElement : poList) {
                if (Objects.equals(poElement.getId(), poToCart.getId()) &&
                        Objects.equals(poElement.getName(), poToCart.getName()) &&
                        Objects.equals(poElement.getSize(), poToCart.getSize()) &&
                        Objects.equals(poElement.getColor(), poToCart.getColor())) {
                    poElement.setOrderNumber(poElement.getOrderNumber() + 1);
                    duplicate = true;
                    Log.d("add duplicate", "saveToCart: order number" + poElement.getOrderNumber());
                    break;
                }
            }
        }
        return duplicate;
    }

    List<ProductOrder> getCart() {
        List<ProductOrder> listRemote = new ArrayList<>();
        try {
            String serializedObject = shared.getString(cartList, null);
            if (serializedObject != null) {
                Type type = new TypeToken<List<ProductOrder>>() {
                }.getType();
                listRemote = gson.fromJson("[" + serializedObject + "]", type);
            }
        } catch (Exception e) {
            Log.d("ERROR", "getCart: " + e.getMessage());
        }
        return listRemote;
    }

    void updateAllCart(List<ProductOrder> poList) {
        try {
            Type type = new TypeToken<List<ProductOrder>>() {
            }.getType();
            String json = gson.toJson(poList, type);
            if (editor != null) {
                editor.putString(cartList, json.replace("[", "").replace("]", "")).apply();
            }
        } catch (Exception e) {
            Log.d("ERROR", "getCart: " + e.getMessage());
        }
    }
}
