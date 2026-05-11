package com.example.demo.model;
//User.java（Model 層）
//這個類別是資料的模型，對應到資料庫的 users 表。

public class User {
    private Integer id;
    private String name;
    private String email;
//    每個 private 變數對應一個欄位

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
//getId() / setId() 這類方法叫做 getter / setter，讓其他類別可以存取這些私有變數

//用一句話說：「定義一筆使用者資料長什麼樣子」