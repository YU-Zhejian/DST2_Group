package com.example.service.impl;

import com.example.bean.DosingGuideline;
import com.example.dao.dosingGuidelineDao;
import com.example.service.dosingGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class dosingGuidelineServiceImpl implements dosingGuidelineService {
    @Autowired
    private dosingGuidelineDao dosingGuidelineDao;


    @Override
    @Transactional
    public void save(DosingGuideline dosingGuideline) {
        DosingGuideline param = new DosingGuideline();
        param.setId(dosingGuideline.getId());
        List<DosingGuideline> list = this.findAll(param);
        if(list.size()==0){
            this.dosingGuidelineDao.save(dosingGuideline);
            this.dosingGuidelineDao.flush();
        }

    }

    @Override
    public List<DosingGuideline> findAll(DosingGuideline dosingGuideline) {
        Example<DosingGuideline> example = Example.of(dosingGuideline);
        List<DosingGuideline> list = this.dosingGuidelineDao.findAll(example);
        return list;
    }
}
