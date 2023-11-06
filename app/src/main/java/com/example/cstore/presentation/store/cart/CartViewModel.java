package com.example.cstore.presentation.store.cart;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cstore.R;
import com.example.cstore.common.base.BaseViewModel;
import com.example.cstore.model.Account;
import com.example.cstore.model.ProductOrder;

import java.util.List;

public class CartViewModel extends BaseViewModel {
    Integer deleteConfirmTitle = R.string.confirm_delete;
    Integer deleteConfirmMessage = R.string.confirm_delete_po_content;
    Integer yes = R.string.yes_confirm;
    Integer no = R.string.no_confirm;
    Integer deleted = R.string.deleted_po;
    Integer fromCart = R.string.from_cart;
    Integer undo = R.string.undo;
    Integer undoDelete = R.string.undo_notice;
    Integer noPoInCart = R.string.no_po_in_cart;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<ProductOrder> getCart(){
        return local.getCart();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateAllCart(List<ProductOrder> lpo){
        local.saveAllToCart(lpo);
    }

    public String getShipping(){
        return local.getShippingDelivery();
    }

    public void updateShipping(String shipping){
        local.pickShippingDelivery(shipping);
    }

    public Integer getShippingPrice(){
        return local.getShippingPrice();
    }
    public void updateShippingId(String shipping){
        local.saveShippingId(shipping);
    }

    public String getShippingId(){
        return local.getShippingId();
    }

    public void updateShippingPrice(Integer price){
        local.saveShippingPrice(price);
    }

    public Integer getProductPrice(){
        return local.getProductPrice();
    }

    public void updateProductPrice(Integer price){
        local.saveProductPrice(price);
    }
    public Account getAccount(){
        return local.getRegisteredAccount();
    }

    public void saveAccount(Account account){
        local.saveRegisteredAccount(account);
    }

}
