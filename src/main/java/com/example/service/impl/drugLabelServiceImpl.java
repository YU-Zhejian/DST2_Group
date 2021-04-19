package com.example.service.impl;

import com.example.bean.DrugLabel;
import com.example.dao.drugLabelDao;
import com.example.service.drugLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class drugLabelServiceImpl implements drugLabelService {
    @Autowired
    private drugLabelDao drugLabelDao;

    @Override
    @Transactional
    public void save(DrugLabel drugLabel) {
        //查询原有数据
        DrugLabel param = new DrugLabel();
        param.setId(drugLabel.getId());
        //执行查询
        List<DrugLabel> list = this.findAll(param);
        //判断结果是否为空
        if (list.size() == 0) {
            //为空，新增或更新数据库
            this.drugLabelDao.save(drugLabel);
            this.drugLabelDao.flush();
        }

    }

    @Override
    public List<DrugLabel> findAll(DrugLabel drugLabel) {
        //设置查询条件
        Example<DrugLabel> example = Example.of(drugLabel);
        //执行查询
        List<DrugLabel> list = this.drugLabelDao.findAll(example);
        return list;
    }
}

