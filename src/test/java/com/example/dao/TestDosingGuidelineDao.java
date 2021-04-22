package com.example.dao;

import com.example.bean.DosingGuideline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDosingGuidelineDao extends JpaRepository<DosingGuideline,String> {
}
