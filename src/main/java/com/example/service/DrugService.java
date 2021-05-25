package com.example.service;

import com.example.bean.Drug;

import java.util.List;

/**
 * See implementation for more details
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
public interface DrugService {
	void save(Drug drug);
	List<Drug> findAll(Drug drug);
	List<Drug> findAll();
}
