package com.example.cstore.model;


public class Order {
    private String id;
    private Product product;
    private Integer orderNumber;
    private String shippingDeliveryId;

    public Order() {
    }

    public Order(String id, Product product, Integer orderNumber, String shippingDeliveryId) {
        this.id = id;
        this.product = product;
        this.orderNumber = orderNumber;
        this.shippingDeliveryId = shippingDeliveryId;
    }

    public String getShippingDeliveryId() {
        return shippingDeliveryId;
    }

    public void setShippingDeliveryId(String shippingDeliveryId) {
        this.shippingDeliveryId = shippingDeliveryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
