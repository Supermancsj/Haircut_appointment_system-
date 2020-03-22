package com.example.library.mapper;

import com.example.library.entity.HairType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HairTypeMapper {


    /****
     fang
     */
    @Select("select id,name,price,shopid from hairtypeprice")
    List<HairType> findAllH();

    @Insert("insert into hairtypeprice(name,price,shopid)VALUES(#{name},#{price},#{shopid})")
    void addHaircut(String name,double price,int shopid);

    @Delete("delete from hairtypeprice where id=#{id}")
    void deleteHaircut(int id);

    @Update("update hairtypeprice set name=#{name},price=#{price},shopid=#{shopid} where id=#{id}")
    void updateHaircut(HairType haircut);



    /****
     fang
     */

    //理发师待选技能
    @Select("SELECT hairtypeprice.id,name,price from hairtypeprice,barber " +
            "where barber.shopid=hairtypeprice.shopid " +
            "and barber.id=#{barberid} and hairtypeprice.id not in (" +
            "select barberhairtype.hairtypeid from barberhairtype where barberhairtype.barberid=#{barberid})")
    List<HairType> findByShopidForBarber(int barberid);

    //理发师已选技能
    @Select("select barberhairtype.id,name,price from barberhairtype,hairtypeprice where barberid=#{barberid} and hairtypeid=hairtypeprice.id")
    List<HairType> findHairTypebyBarberid(int barberid);

    //用户预约查看
    @Select("select barberhairtype.id,name,price,shopid,barberid from barberhairtype,hairtypeprice where hairtypeid=hairtypeprice.id")
    List<HairType> findAllHairtype();

    //理发师添加技能
    @Insert("insert into barberhairtype(barberid,hairtypeid)VALUES(#{barberid},#{hairtypeid})")
    void addBarberHairtype(int barberid,int hairtypeid);

    //删除理发师已选技能
    @Delete("delete from barberhairtype where id=#{id}")
    void  deleteBarberHairtype(int id);

}
