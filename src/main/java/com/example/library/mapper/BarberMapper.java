package com.example.library.mapper;

import com.example.library.entity.Barber;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BarberMapper {

    @Select("select id,password,email,shopid,telephone from barber where shopid=#{shopId}")
    List<Barber>findAllByShopId(int shopId);

    @Insert("insert into barber(email,shopid,telephone)VALUES(#{email},#{shopid},#{telephone})")
    void addBarber(String email,String telephone,int shopid);

    @Update("update barber set password=#{password},email=#{email},shopid=#{shopid},telephone=#{telephone} where id=#{id}")
    void updateBarber(Barber barber);

    @Delete("delete from barber where id=#{id}")
    void deleteBarber(int id);

    @Select("select * FROM barber WHERE id=#{id}")
    Barber findBarberById(int id);

    @Select("select * FROM barber WHERE email=#{email}")
    Barber findBarberByEmail(String email);
}
