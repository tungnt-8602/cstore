package com.example.cstore.model;

public class ShippingDelivery {
    private Integer id;
    private String name;
    private Integer price;
    private Integer deliveryTime;

    public ShippingDelivery() {
    }

    public ShippingDelivery(Integer id, String name, Integer price, Integer deliveryTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.deliveryTime = deliveryTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
