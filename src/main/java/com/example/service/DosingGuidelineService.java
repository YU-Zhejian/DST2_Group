package com.example.service;

import com.example.bean.DosingGuideline;

import java.util.List;

/**
 * See implementation for more details
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
public interface DosingGuidelineService {
	void save(DosingGuideline dosingGuideline);
	List<DosingGuideline> findAll(DosingGuideline dosingGuideline);
	List<DosingGuideline> findAll();
}
