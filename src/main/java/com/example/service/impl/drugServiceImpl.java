package com.example.service.impl;

import com.example.bean.drug;
import com.example.dao.drugDao;
import com.example.service.drugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class drugServiceImpl implements drugService {

    @Autowired
    private drugDao drugDao;

    @Override
    @Transactional
    public void save(drug drug) {
        //查询原有数据
        drug param = new drug();
        param.setId(drug.getId());
        //执行查询
        List<drug> list = this.findAll(param);
        //判断结果是否为空
        if (list.size() == 0) {
            //为空，新增或更新数据库
            this.drugDao.save(drug);
            this.drugDao.flush();
        }

    }

    @Override
    public List<drug> findAll(drug drug) {
        //设置查询条件
        Example<drug> example = Example.of(drug);
        //执行查询
        List<drug> list = this.drugDao.findAll(example);
        return list;
    }
}
