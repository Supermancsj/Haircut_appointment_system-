package com.example.library.entity;



public class HairType {
    private int id;
    private String name;

    private float price;
    private int shopid;
    private int barberid;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public int getBarberid() {
        return barberid;
    }

    public void setBarberid(int barberid) {
        this.barberid = barberid;
    }
}
