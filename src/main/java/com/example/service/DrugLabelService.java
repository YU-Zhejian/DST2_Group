package com.example.service;

import com.example.bean.DrugLabel;


import java.util.List;

/**
 * See implementation for more details
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
public interface DrugLabelService {
	void save(DrugLabel drugLabel);
	List<DrugLabel> findAll(DrugLabel drugLabel);
	List<DrugLabel> findAll();
}
