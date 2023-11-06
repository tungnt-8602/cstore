package com.example.cstore.model;

import com.google.gson.annotations.SerializedName;

public class ProductOrder {
    private String id;
    private String name;
    private Integer price;
    private String color;
    private String size;
    private String categoryId;
    private String image;
//    @SerializedName("quantity")
    private Integer orderNumber;

    public ProductOrder() {
    }

    public ProductOrder(String id, String name, Integer price, String color, String size, String categoryId, String image, Integer orderNumber) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
        this.categoryId = categoryId;
        this.image = image;
        this.orderNumber = orderNumber;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
