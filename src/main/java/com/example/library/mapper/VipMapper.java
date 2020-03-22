package com.example.library.mapper;

import com.example.library.entity.Vip;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VipMapper {

    @Select("select userid,shopid,balance from vip where shopid=#{shopid}")
    List<Vip> findAllByShopId(int shopid);

    @Select("select userid,shopid,balance from vip where shopid=#{shopid} and userid=#{userid}")
    List<Vip> findAllByUserId(int shopid,int userid);

    @Update("update vip set balance=#{balance} where shopid=#{shopid} and userid=#{userid}")
    void UpdateVip(int shopid,int userid,double balance);
}
