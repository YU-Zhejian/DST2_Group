package com.example.service.impl;

import com.example.bean.DosingGuideline;
import com.example.dao.DosingGuidelineDao;
import com.example.service.DosingGuidelineService;
import com.example.servlet.DosingGuidelineServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Query or update dosing guideline database. See also: {@link DrugLabelServiceImpl}
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Service
public class DosingGuidelineServiceImpl implements DosingGuidelineService {
	@Autowired
	private DosingGuidelineDao dosingGuidelineDao;

	/**
	 * Update dosing guideline database
	 *
	 * @param dosingGuideline Entry that needs to br inserted
	 */
	@Override
	@Transactional
	public void save(DosingGuideline dosingGuideline) {
		DosingGuideline param = new DosingGuideline();
		param.setId(dosingGuideline.getId());
		List<DosingGuideline> list = this.findAll(param);
		if (list.size() == 0) {
			this.dosingGuidelineDao.save(dosingGuideline);
			this.dosingGuidelineDao.flush();
		}
	}

	/**
	 * Query dosing guideline database
	 *
	 * @param dosingGuideline Entry that needs to be queried
	 * @return List of found entries
	 */
	@Override
	public List<DosingGuideline> findAll(DosingGuideline dosingGuideline) {
		Example<DosingGuideline> example = Example.of(dosingGuideline);
		return this.dosingGuidelineDao.findAll(example);
	}

	@Override
	public List<DosingGuideline> findAll() {
		return dosingGuidelineDao.findAll();
	}
}
