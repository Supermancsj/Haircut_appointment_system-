package com.example.library.service;

import com.example.library.entity.Order;
import com.example.library.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    //manager查询所有订单
    public List<Order> findAllOrder(int shopId){
        return orderMapper.findAllOrder(shopId);
    }

    //manager查询待处理订单
    public List<Order> findPOrdersByManagerId(int shopId){
        return orderMapper.findPOrdersByManagerId(shopId);
    }

    //用户最新订单
    public List<Order> findOrderByUserId(int userid){
        return orderMapper.findOrderByUserId(userid);
    }
    //用户历史订单
    public List<Order> findCOrderByUserId(int userid){
        return orderMapper.findCOrderByUserId(userid);
    }


    //生成订单
    @Transactional
    public void addOrder(int userid, int barberhairtypeid, Date time){

        orderMapper.addOrder(userid,barberhairtypeid,time);
    }

    //通用更新
    @Transactional
    public void updateOrder(int id,int state){
        orderMapper.updateOrder(id,state);
    }


    //barber
    @Transactional
    public List<Order> findPOrderByBarberId(int id) {
        return orderMapper.findPOrderByBarberId(id);
    }

    @Transactional
    public List<Order> findAOrderByBarberId(int id) {
        return orderMapper.findAOrderByBarberId(id);
    }

    @Transactional
    public List<Order> findCOrderByBarberId(int id) {
        return orderMapper.findCOrderByBarberId(id);
    }
    //barber
}
