package com.example.dao;

import com.example.bean.DrugLabel;
import com.example.bean.Sample;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * See implementation for more details
 * @author Zhejian YU
 */
public interface SampleDao extends JpaRepository<DrugLabel,String> {
}
