package com.example.library.mapper;

import com.example.library.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManagerMapper {
    @Select("select id,password from shop where id=#{id} and password=#{password}")
    Manager managerlogin(@Param("id") int id, @Param("password") String password);
}
