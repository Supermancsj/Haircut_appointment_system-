package com.example.library.mapper;

import com.example.library.entity.Order;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface OrderMapper {

    //manager查看本店所有订单
    @Select("select _order.id,time,state,name,shopid,barberid,userid from _order,barberhairtype,hairtypeprice where shopid = #{shopid} and barberhairtypeid=barberhairtype.id and hairtypeid=hairtypeprice.id")
    List<Order> findAllOrder(int shopid);

    @Select("select _order.id,time,state,name,shopid,barberid,userid from _order,barberhairtype,hairtypeprice where shopid=#{shopid} and state=1 and barberhairtypeid=barberhairtype.id and hairtypeid=hairtypeprice.id")
    List<Order>findPOrdersByManagerId(int shopId);


    //有效订单
    @Select("select _order.id,time,state,name,shopid,barberid,userid from _order,barberhairtype,hairtypeprice where state not in (2,4,5) and userid=#{userid} and barberhairtypeid=barberhairtype.id and hairtypeid=hairtypeprice.id")
    List<Order> findOrderByUserId(int userid);
    //历史记录
    @Select("select _order.id,time,state,name,shopid,barberid,userid from _order,barberhairtype,hairtypeprice where state in (2,4,5) and userid=#{userid} and barberhairtypeid=barberhairtype.id and hairtypeid=hairtypeprice.id")
    List<Order> findCOrderByUserId(int userid);

    //用户下单
    @Insert("insert into _order(time,barberhairtypeid,userid)VALUES(#{time},#{barberhairtypeid},#{userid})")
    void addOrder(int userid, int barberhairtypeid, Date time);


    //更改订单状态，三端通用
    @Update("update _order set state=#{state} where id=#{id}")
    void updateOrder(int id,int state);



    //barber待处理
    @Select("select _order.id,time,state,name,shopid,barberid,userid FROM _order,barberhairtype,hairtypeprice WHERE state=0 and barberhairtype.barberid=#{id} and barberhairtype.id=barberhairtypeid and hairtypeid=hairtypeprice.id")
    List<Order> findPOrderByBarberId(@Param("id") int id);

    //barber已同意
    @Select("select _order.id,time,state,name,shopid,barberid,userid FROM _order,barberhairtype,hairtypeprice WHERE state in(3,5) and barberhairtype.barberid=#{id} and barberhairtype.id=barberhairtypeid and hairtypeid=hairtypeprice.id")
    List<Order> findAOrderByBarberId(@Param("id") int id);

    //barber已拒绝
    @Select("select _order.id,time,state,name,shopid,barberid,userid FROM _order,barberhairtype,hairtypeprice WHERE state=2 and barberhairtype.barberid=#{id} and barberhairtype.id=barberhairtypeid and hairtypeid=hairtypeprice.id")
    List<Order> findCOrderByBarberId(@Param("id") int id);
}
