package com.example.cstore.model;


import java.util.List;

public class Cart {
    private String id;
    private List<ProductOrder> orderList;
    private String accountId;

    public Cart() {
    }

    public Cart(String id, List<ProductOrder> orderList, String accountId) {
        this.id = id;
        this.orderList = orderList;
        this.accountId = accountId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<ProductOrder> orderList) {
        this.orderList = orderList;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
