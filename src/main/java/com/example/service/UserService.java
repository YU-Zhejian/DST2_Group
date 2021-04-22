package com.example.service;

import com.example.bean.drug;

import java.util.List;

/**
 * See implementation for more details
 * @author Zhejian YU
 */
public interface UserService {
    void save(drug drug);
    List<drug> findAll(drug drug);
}
