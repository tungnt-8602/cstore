package com.example.cstore.presentation.store.setting;

import com.example.cstore.common.base.BaseViewModel;
import com.example.cstore.model.Account;
import com.example.cstore.model.ProductOrder;

import java.util.List;

public class SettingViewModel extends BaseViewModel {
    public Account getAccount(){
        return local.getRegisteredAccount();
    }

    public void saveAccount(Account account){
        local.saveRegisteredAccount(account);
    }

    public void updateAllCart(List<ProductOrder> lpo){
        local.saveAllToCart(lpo);
    }
    public void updateShipping(String shipping){
        local.pickShippingDelivery(shipping);
    }
    public void updateShippingId(String shipping){
        local.saveShippingId(shipping);
    }
    public void updateShippingPrice(Integer price){
        local.saveShippingPrice(price);
    }
    public void updateProductPrice(Integer price){
        local.saveProductPrice(price);
    }
}
