package com.example.dao;

import com.example.bean.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * See implementation for more details
 *
 * @author Zhejian YU
 */
public interface SampleDao extends JpaRepository<Sample, String> {
	@Query("SELECT sample FROM Sample sample WHERE sample.userName = ?1")
	List<Sample> findSamplesByUserName(String userName);
}
