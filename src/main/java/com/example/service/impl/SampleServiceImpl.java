package com.example.service.impl;

import com.example.bean.Sample;
import com.example.dao.SampleDao;
import com.example.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Query or update sample database. See also: {@link DrugLabelServiceImpl}
 *
 * @author Zhejian YU
 */
@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleDao sampleDao;

    /**
     * Update drug database
     *
     * @param sample Entry that needs to br inserted
     */
    @Override
    @Transactional
    public void save(Sample sample) {
        Sample param = new Sample();
        param.setId(sample.getId());
        List<Sample> list = this.findAll(sample);
        if (list.size() == 0) {
            this.sampleDao.save(sample);
            this.sampleDao.flush();
        }
    }

    /**
     * Query dosing guideline database
     *
     * @param sample Entry that needs to be queried
     * @return List of found entries
     */
    @Override
    public List<Sample> findAll(Sample sample) {
        Example<Sample> example = Example.of(sample);
        return this.sampleDao.findAll(example);
    }
}
