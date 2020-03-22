package com.example.library.service;

import com.example.library.entity.Barber;
import com.example.library.mapper.BarberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BarberService {
    @Autowired
    public BarberMapper barberMapper;

    @Transactional
    public void addBarber(String email,String telephone,int shopid){

        barberMapper.addBarber(email,telephone,shopid);
    }

    //查询店铺里所有理发师
    public List<Barber>findAllByShopId(int shopId){
        return barberMapper.findAllByShopId(shopId);
    }

    @Transactional
    public void updateBarber(Barber barber){
        barberMapper.updateBarber(barber);
    }

    @Transactional
    public boolean deleteBerber(int id){
        try {
            barberMapper.deleteBarber(id);
        }catch (Exception e){
            System.out.println("无法删除,该理发师有订单");
            return false;
        }
        return true;
    }

    @Transactional
    public Barber findBarberById(int id) {
        return barberMapper.findBarberById(id);
    }

    @Transactional
    public Barber findBarberByEmail(String email) {
        return barberMapper.findBarberByEmail(email);
    }
}
