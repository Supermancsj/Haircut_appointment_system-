package com.example.library.service;

import com.example.library.entity.Vip;
import com.example.library.mapper.VipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipService {
    @Autowired
    public VipMapper vipMapper;

    public List<Vip> findAllByShopId(int shopid){
        return vipMapper.findAllByShopId(shopid);
    }

    public List<Vip> findAllByUserId(int shopid,int userid){
        return vipMapper.findAllByUserId(shopid, userid);
    }

    public void UpdateVip(int shopid,int userid,double balance){
        vipMapper.UpdateVip(shopid, userid, balance);
    }

}
