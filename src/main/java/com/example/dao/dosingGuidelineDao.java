package com.example.dao;

import com.example.bean.DosingGuideline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface dosingGuidelineDao extends JpaRepository<DosingGuideline,String> {
}
