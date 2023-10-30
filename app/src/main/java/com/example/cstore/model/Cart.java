package com.example.cstore.model;


import java.util.List;

public class Cart {
    private Integer id;
    private List<Order> orderList;
    private Integer accountId;

    public Cart() {
    }

    public Cart(Integer id, List<Order> orderList, Integer accountId) {
        this.id = id;
        this.orderList = orderList;
        this.accountId = accountId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
