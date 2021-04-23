package com.example.service;

import com.example.bean.Drug;

import java.util.List;

public interface drugService {
    //保存信息

    public void save(Drug drug);

    //根据条件进行查询
    public List<Drug> findAll(Drug drug);

}
