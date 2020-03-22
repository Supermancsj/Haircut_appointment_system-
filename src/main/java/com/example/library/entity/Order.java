package com.example.library.entity;
import java.sql.Date;
public class Order {
    static String[] stations={"等待理发师处理","理发师拒绝，再在请求店长","已拒绝","已同意","用户取消","已完成"};
    private int id;
    private int state;
    private Date time;
    private int hairtypeid;
    private int barberhairtypeid;
    private int userid;
    private String name;
    private int barberid;
    private int shopid;
    private String station;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
        station=stations[state];
    }

    public int getHairtypeid() {
        return hairtypeid;
    }

    public void setHairtypeid(int hairtypeid) {
        this.hairtypeid = hairtypeid;
    }

    public  String getStation() {
        return station;
    }

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public int getBarberhairtypeid() {
        return barberhairtypeid;
    }
    public void setBarberhairtypeid(int barberhairtypeid) {
        this.barberhairtypeid = barberhairtypeid;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public int getBarberid() {
        return barberid;
    }
    public int getShopid() {
        return shopid;
    }
    public String getName() {
        return name;
    }

    public void setBarberid(int barberid) {
        this.barberid = barberid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public void setName(String name) {
        this.name = name;
    }
}
