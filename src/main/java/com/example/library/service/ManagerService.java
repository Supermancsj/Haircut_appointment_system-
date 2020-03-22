package com.example.library.service;

import com.example.library.entity.Manager;
import com.example.library.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Transactional
    public Manager managerlogin(int id,String password)
    {
        Manager manager = managerMapper.managerlogin(id,password);
        return manager;
    }
}
