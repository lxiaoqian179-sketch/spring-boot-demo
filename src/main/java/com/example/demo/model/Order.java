package com.example.demo.model;

public class Order {
    private Integer id;
    private Integer userId;
    private String product;
    private String status;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}