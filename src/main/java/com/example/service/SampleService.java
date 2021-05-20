package com.example.service;

import com.example.bean.Sample;

import java.util.List;

/**
 * See implementation for more details
 *
 * @author Zhejian YU
 */
public interface SampleService {
	void save(Sample sample);
	List<Sample> findAll(Sample sample);
}
