package com.example.service.impl;

import com.example.bean.DrugLabel;
import com.example.dao.DrugLabelDao;
import com.example.service.DrugLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Query or update dosing label database
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
@Service
public class DrugLabelServiceImpl implements DrugLabelService {
	@Autowired
	private DrugLabelDao drugLabelDao;

	/**
	 * Update dosing label database
	 *
	 * @param drugLabel Entry that needs to br inserted
	 */
	@Override
	@Transactional
	public void save(DrugLabel drugLabel) {
		// Query existing data
		DrugLabel param = new DrugLabel();
		param.setId(drugLabel.getId());
		List<DrugLabel> list = this.findAll(param);
		if (list.size() == 0) {
			// Update if no items found
			this.drugLabelDao.save(drugLabel);
			this.drugLabelDao.flush();
		}
	}

	/**
	 * Query dosing guideline database
	 *
	 * @param drugLabel Entry that needs to be queried
	 * @return List of found entries
	 */
	@Override
	public List<DrugLabel> findAll(DrugLabel drugLabel) {
		// Setting querying protocol
		Example<DrugLabel> example = Example.of(drugLabel);
		// Executing querying
		return this.drugLabelDao.findAll(example); // Simplified by IDEA
	}
}
