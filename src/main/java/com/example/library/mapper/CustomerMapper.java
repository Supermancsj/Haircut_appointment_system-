package com.example.library.mapper;

import com.example.library.entity.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Select("select id,password,email,telephone from user")
    List<Customer> findAll();

    @Insert("insert into user(password,email,telephone)VALUES(#{password},#{email},#{telephone})")
    void addUser(Customer user);

    @Update("update user set password=#{password},email=#{email},telephone=#{telephone} where id=#{id}")
    void updateUser(Customer user);

    @Delete("delete from user where id=#{id}")
    void deleteUser(int id);

    @Select("select * FROM user WHERE id=#{id}")
    Customer findUserById(int id);

    @Select("select * FROM user WHERE email=#{email}")
    Customer findUserByEmail(String email);
}
