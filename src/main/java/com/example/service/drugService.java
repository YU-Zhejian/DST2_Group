package com.example.service;

import com.example.bean.drug;

import java.util.List;

public interface drugService {
    //保存信息

    public void save(drug drug);

    //根据条件进行查询
    public List<drug> findAll(drug drug);

}
