package com.example.cstore.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {
    @SerializedName("_id")
    private String id;
    private String account;
    private List<ProductOrder> products;
    private String shipping;

    public Order() {
    }

    public Order(String account, List<ProductOrder> productOrderList, String shipping) {
        this.account = account;
        this.products = productOrderList;
        this.shipping = shipping;
    }

    public Order(String id, String account, List<ProductOrder> productOrderList, String shipping) {
        this.id = id;
        this.account = account;
        this.products = productOrderList;
        this.shipping = shipping;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<ProductOrder> getProductOrderList() {
        return products;
    }

    public void setProductOrderList(List<ProductOrder> productOrderList) {
        this.products = productOrderList;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }
}
