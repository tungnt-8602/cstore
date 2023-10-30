package com.example.cstore.model;


public class Order {
    private Integer id;
    private Product product;
    private Integer orderNumber;
    private Integer shippingDeliveryId;

    public Order() {
    }

    public Order(Integer id, Product product, Integer orderNumber, Integer shippingDeliveryId) {
        this.id = id;
        this.product = product;
        this.orderNumber = orderNumber;
        this.shippingDeliveryId = shippingDeliveryId;
    }

    public Integer getShippingDeliveryId() {
        return shippingDeliveryId;
    }

    public void setShippingDeliveryId(Integer shippingDeliveryId) {
        this.shippingDeliveryId = shippingDeliveryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
