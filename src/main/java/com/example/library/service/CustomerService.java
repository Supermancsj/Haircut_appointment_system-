package com.example.library.service;

import com.example.library.entity.Customer;
import com.example.library.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    public CustomerMapper userMapper;

    public List<Customer> findAll(){
        return userMapper.findAll();
    }

    @Transactional
    public void addUser(Customer user){

        userMapper.addUser(user);
    }

    @Transactional
    public void updateUser(Customer barber){
        userMapper.updateUser(barber);
    }

    @Transactional
    public void deleteUser(int id){
        userMapper.deleteUser(id);
    }

    @Transactional
    public Customer findUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Transactional
    public Customer findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }
}
