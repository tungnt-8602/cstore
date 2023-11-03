package com.example.cstore.presentation.store.product.product_category;

import com.example.cstore.common.base.BaseViewModel;
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

    public List<ProductOrder> getCart(){
        return local.getCart();
    }
}
