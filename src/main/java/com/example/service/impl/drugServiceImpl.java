package com.example.service.impl;

import com.example.bean.drug;
import com.example.dao.drugDao;
import com.example.service.drugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Query or update drug database. See also: {@link drugLabelServiceImpl}
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Service
public class drugServiceImpl implements drugService {

    @Autowired
    private drugDao drugDao;

    /**
     * Update drug database
     *
     * @param drug Entry that needs to br inserted
     */
    @Override
    @Transactional
    public void save(drug drug) {
        drug param = new drug();
        param.setId(drug.getId());
        List<drug> list = this.findAll(param);
        if (list.size() == 0) {
            this.drugDao.save(drug);
            this.drugDao.flush();
        }
    }

    /**
     * Query dosing guideline database
     *
     * @param drug Entry that needs to be queried
     * @return List of found entries
     */
    @Override
    public List<drug> findAll(drug drug) {
        Example<drug> example = Example.of(drug);
        return this.drugDao.findAll(example);
    }
}
