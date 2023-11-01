package com.example.cstore.model;

public class ProductOrder {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private String color;
    private String size;
    private Integer categoryId;
    private String images;
    private Integer orderNumber;

    public ProductOrder() {
    }

    public ProductOrder(Integer id, String name, String description, Integer price, String color, String size, Integer categoryId, String images, Integer orderNumber) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.color = color;
        this.size = size;
        this.categoryId = categoryId;
        this.images = images;
        this.orderNumber = orderNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
