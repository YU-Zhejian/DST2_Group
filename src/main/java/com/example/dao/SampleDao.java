package com.example.dao;

import com.example.bean.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * See implementation for more details
 *
 * @author Zhejian YU
 */
public interface SampleDao extends JpaRepository<Sample, String> {}
