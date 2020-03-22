package com.example.library.service;

import com.example.library.entity.HairType;
import com.example.library.mapper.HairTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HairTypeService {
    @Autowired
    private HairTypeMapper hairTypeMapper;

    /**
      fang
     */

    public List<HairType> findAllH(){
        return hairTypeMapper.findAllH();
    }

    @Transactional
    public void addHaircut(String name,double price,int shopid){
        hairTypeMapper.addHaircut(name,price,shopid);
    }

    @Transactional
    public void deleteHaircut(int id){
        hairTypeMapper.deleteHaircut(id);
    }

    @Transactional
    public void updateHaircut(HairType haircut){ hairTypeMapper.updateHaircut(haircut);
    }



    /*
    fang
     */
    public List<HairType> findByShopidForBarber(int barberid){
        return hairTypeMapper.findByShopidForBarber(barberid);
    }

    //理发师已选
    @Transactional
    public List<HairType> findHairTypebyBarberid(int barberid){
        return hairTypeMapper.findHairTypebyBarberid(barberid);
    }

    //理发师添加技能
    @Transactional
    public void addBarberHairtype(int barberid,int hairtypeid){
        hairTypeMapper.addBarberHairtype(barberid,hairtypeid);
    }

    public List<HairType> findAllHairtype(){
        return hairTypeMapper.findAllHairtype();
    }

    //删除理发师已选技能
    public boolean deleteBarberHairtype(int id) {
        try{
            hairTypeMapper.deleteBarberHairtype(id);

        }catch (Exception e){
            return false;
        }
        return true;
    }
}
