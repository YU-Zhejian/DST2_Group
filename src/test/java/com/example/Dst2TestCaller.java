package com.example;

import com.example.bean.DosingGuidelineTest;
import com.example.bean.DrugLabelTest;
import com.example.bean.DrugTest;
import com.example.dao.DosingGuidelineDaoTest;
import com.example.dao.DrugDaoTest;
import com.example.dao.DrugLabelDaoTest;
import com.example.service.impl.DosingGuidelineServiceImplTest;
import com.example.service.impl.DrugLabelServiceImplTest;
import com.example.service.impl.DrugServiceImplTest;
import com.example.task.DosingGuidelineTaskTest;
import com.example.task.DrugLabelTaskTest;
import com.example.task.DrugTaskTest;
import com.example.util.HttpCrawlerTest;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@RunWith(Suite.class)
@Suite.SuiteClasses({HttpCrawlerTest.class,DrugTest.class,DrugLabelTest.class,DosingGuidelineTest.class,DrugDaoTest.class,DrugLabelDaoTest.class,DosingGuidelineDaoTest.class,DrugServiceImplTest.class,DrugLabelServiceImplTest.class,DosingGuidelineServiceImplTest.class,DosingGuidelineTaskTest.class,DrugTaskTest.class,DrugLabelTaskTest.class})
@Transactional
@Rollback
public class Dst2TestCaller extends TestCase {

}