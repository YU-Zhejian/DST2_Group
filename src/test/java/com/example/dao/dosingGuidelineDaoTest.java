package com.example.dao;

import com.example.Dst2Application;
import com.example.bean.DosingGuideline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for the dosingGuidelineDao
 *
 * @author Yihui-CEN
 */

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dst2Application.class)
public class DosingGuidelineDaoTest {

    private final Logger log = LoggerFactory.getLogger(DosingGuidelineDaoTest.class);

    @Autowired
    private DosingGuidelineDao dosingGuidelineDao;

    @Test
    @Transactional
    @Rollback
    public void test() {
        log.info("test called");
        DosingGuideline dosingGuideline1 = new DosingGuideline();
        dosingGuideline1.setId("1");
        dosingGuideline1.setName("testName1");
        dosingGuideline1.setObjCls("objCls1");
        dosingGuideline1.setDrugId("testDrugId1");
        dosingGuideline1.setRaw("rawData1");
        dosingGuideline1.setSummaryMarkdown("summaryMarkdown1");
        dosingGuideline1.setTextMarkdown("textMarkdown1");
        dosingGuideline1.setSource("source1");
        dosingGuideline1.setRecommendation(true);

        DosingGuideline dosingGuideline2 = new DosingGuideline();
        dosingGuideline2.setId("2");
        dosingGuideline2.setName("testName2");
        dosingGuideline2.setObjCls("objCls2");
        dosingGuideline2.setDrugId("testDrugId2");
        dosingGuideline2.setRaw("rawData2");
        dosingGuideline2.setSummaryMarkdown("summaryMarkdown2");
        dosingGuideline2.setTextMarkdown("textMarkdown2");
        dosingGuideline2.setSource("source2");
        dosingGuideline2.setRecommendation(false);

        dosingGuidelineDao.save(dosingGuideline1);
        dosingGuidelineDao.save(dosingGuideline2);

        System.out.println(dosingGuidelineDao.findAll());
        assertAll(
                () -> assertEquals(dosingGuideline1.getId(), dosingGuidelineDao.getOne("1").getId()),
                () -> assertEquals(dosingGuideline1.getName(), dosingGuidelineDao.getOne("1").getName()),
                () -> assertEquals(dosingGuideline1.getObjCls(), dosingGuidelineDao.getOne("1").getObjCls()),
                () -> assertEquals(dosingGuideline1.getDrugId(), dosingGuidelineDao.getOne("1").getDrugId()),
                () -> assertEquals(dosingGuideline1.getRaw(), dosingGuidelineDao.getOne("1").getRaw()),
                () -> assertEquals(dosingGuideline1.getSource(), dosingGuidelineDao.getOne("1").getSource()),
                () -> assertEquals(dosingGuideline1.getSummaryMarkdown(), dosingGuidelineDao.getOne("1").getSummaryMarkdown()),
                () -> assertEquals(dosingGuideline1.getTextMarkdown(), dosingGuidelineDao.getOne("1").getTextMarkdown()),
                () -> assertEquals(dosingGuideline1.isRecommendation(), dosingGuidelineDao.getOne("1").isRecommendation())
        );

        assertAll(
                () -> assertNotEquals(dosingGuideline2.getId(), dosingGuidelineDao.getOne("1").getId()),
                () -> assertNotEquals(dosingGuideline2.getName(), dosingGuidelineDao.getOne("1").getName()),
                () -> assertNotEquals(dosingGuideline2.getDrugId(), dosingGuidelineDao.getOne("1").getDrugId()),
                () -> assertNotEquals(dosingGuideline2.getRaw(), dosingGuidelineDao.getOne("1").getRaw()),
                () -> assertNotEquals(dosingGuideline2.getSource(), dosingGuidelineDao.getOne("1").getSource()),
                () -> assertNotEquals(dosingGuideline2.getSummaryMarkdown(), dosingGuidelineDao.getOne("1").getSummaryMarkdown()),
                () -> assertNotEquals(dosingGuideline2.getTextMarkdown(), dosingGuidelineDao.getOne("1").getTextMarkdown()),
                () -> assertNotEquals(dosingGuideline2.getObjCls(), dosingGuidelineDao.getOne("1").getObjCls()),
                () -> assertNotEquals(dosingGuideline2.isRecommendation(), dosingGuidelineDao.getOne("1").isRecommendation())
        );

        log.info("test passed");
    }
}