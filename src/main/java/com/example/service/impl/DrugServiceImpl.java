package com.example.service.impl;

import com.example.bean.Drug;
import com.example.dao.DrugDao;
import com.example.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Query or update drug database. See also: {@link DrugLabelServiceImpl}
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugDao drugDao;

    /**
     * Update drug database
     *
     * @param drug Entry that needs to br inserted
     */
    @Override
    @Transactional
    public void save(Drug drug) {
        Drug param = new Drug();
        param.setId(drug.getId());
        List<Drug> list = this.findAll(param);
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
    public List<Drug> findAll(Drug drug) {
        Example<Drug> example = Example.of(drug);
        return this.drugDao.findAll(example);
    }
}
