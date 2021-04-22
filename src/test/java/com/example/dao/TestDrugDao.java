package com.example.dao;

import com.example.bean.drug;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TestDrugDao extends JpaRepository<drug,String> {

}
