package com.example.service;

import com.example.bean.DrugLabel;


import java.util.List;

public interface drugLabelService {
    //保存信息

    public void save(DrugLabel drugLabel);

    //根据条件进行查询
    public List<DrugLabel> findAll(DrugLabel drugLabel);

}
