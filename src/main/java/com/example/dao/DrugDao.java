package com.example.dao;

import com.example.bean.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * See implementation for more details
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
public interface DrugDao extends JpaRepository<Drug, String> {}
