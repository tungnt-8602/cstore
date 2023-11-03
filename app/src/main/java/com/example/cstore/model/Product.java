package com.example.cstore.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class Product {

    @SerializedName("_id")
    private String id;
    private String name;
    private Integer price;
    private Integer quantity;
    private List<String> colors;
    private List<String> sizes;
    @SerializedName("category")
    private String categoryId;
    private List<Image> images;


    public Product() {
    }

    public Product(String id, String name, Integer price, Integer quantity, List<String> colors, List<String> sizes, String categoryId, List<Image> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.colors = colors;
        this.sizes = sizes;
        this.categoryId = categoryId;
        this.images = images;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> color) {
        this.colors = colors;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}