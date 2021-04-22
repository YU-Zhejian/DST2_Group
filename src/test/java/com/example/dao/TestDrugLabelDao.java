package com.example.dao;

import com.example.bean.DrugLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDrugLabelDao extends JpaRepository<DrugLabel,String> {
}
