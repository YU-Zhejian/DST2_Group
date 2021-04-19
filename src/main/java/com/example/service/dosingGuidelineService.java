package com.example.service;

import com.example.bean.DosingGuideline;

import java.util.List;

public interface dosingGuidelineService {
    public void save(DosingGuideline dosingGuideline);
    public List<DosingGuideline> findAll(DosingGuideline dosingGuideline);
}
