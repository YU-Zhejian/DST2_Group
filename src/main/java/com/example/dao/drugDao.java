package com.example.dao;

import com.example.bean.drug;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * See implementation for more details
 * @author Jie Jin
 * @author Yaqi-SU
 */
public interface drugDao extends JpaRepository<drug,String> {
}
