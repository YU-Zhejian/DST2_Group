package com.example.dao;

import com.example.bean.Drug;
import org.springframework.data.jpa.repository.JpaRepository;


public interface drugDao extends JpaRepository<Drug,String> {

}
