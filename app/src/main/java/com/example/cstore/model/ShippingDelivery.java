package com.example.cstore.model;

import com.google.gson.annotations.SerializedName;

public class ShippingDelivery {
    @SerializedName("_id")
    private String id;
    private String name;
    private Integer price;
    private Integer deliveryTime;

    public ShippingDelivery() {
    }

    public ShippingDelivery(String id, String name, Integer price, Integer deliveryTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.deliveryTime = deliveryTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
