package com.example.library.entity;

public class Vip {
    private int userid;
    private int shopid;
    private double balance;

    public int getShopid() {
        return shopid;
    }

    public int getUserid() {
        return userid;
    }

    public double getBalance() {
        return balance;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
