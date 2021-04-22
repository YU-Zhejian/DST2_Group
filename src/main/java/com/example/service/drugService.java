package com.example.service;

import com.example.bean.drug;

import java.util.List;

/**
 * See implementation for more details
 * @author Jie Jin
 * @author Yaqi-SU
 */
public interface drugService {
    void save(drug drug);
    List<drug> findAll(drug drug);
}
