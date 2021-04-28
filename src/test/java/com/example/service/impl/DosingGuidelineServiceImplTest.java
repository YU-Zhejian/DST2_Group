package com.example.service.impl;

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
 * Test for the dosingGuidelineService
 *
 * @author Yihui-CEN
 */

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dst2Application.class)
public class DosingGuidelineServiceImplTest {

    private final Logger log = LoggerFactory.getLogger(DosingGuidelineServiceImplTest.class);

    @Autowired
    private DosingGuidelineServiceImpl dosingGuidelineServiceImpl;

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

        dosingGuidelineServiceImpl.save(dosingGuideline1);
        dosingGuidelineServiceImpl.save(dosingGuideline2);

        System.out.println(dosingGuidelineServiceImpl.findAll(dosingGuideline1));

         assertAll(
                () -> assertEquals(dosingGuideline1.getId(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getId()),
                () -> assertEquals(dosingGuideline1.getName(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getName()),
                () -> assertEquals(dosingGuideline1.getObjCls(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getObjCls()),
                () -> assertEquals(dosingGuideline1.getDrugId(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getDrugId()),
                () -> assertEquals(dosingGuideline1.getRaw(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getRaw()),
                () -> assertEquals(dosingGuideline1.getSource(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getSource()),
                () -> assertEquals(dosingGuideline1.getSummaryMarkdown(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getSummaryMarkdown()),
                () -> assertEquals(dosingGuideline1.getTextMarkdown(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getTextMarkdown()),
                () -> assertEquals(dosingGuideline1.isRecommendation(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).isRecommendation())
        );

        assertAll(
                () -> assertNotEquals(dosingGuideline2.getId(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getId()),
                () -> assertNotEquals(dosingGuideline2.getName(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getName()),
                () -> assertNotEquals(dosingGuideline2.getDrugId(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getDrugId()),
                () -> assertNotEquals(dosingGuideline2.getRaw(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getRaw()),
                () -> assertNotEquals(dosingGuideline2.getSource(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getSource()),
                () -> assertNotEquals(dosingGuideline2.getSummaryMarkdown(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getSummaryMarkdown()),
                () -> assertNotEquals(dosingGuideline2.getTextMarkdown(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getTextMarkdown()),
                () -> assertNotEquals(dosingGuideline2.getObjCls(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).getObjCls()),
                () -> assertNotEquals(dosingGuideline2.isRecommendation(), dosingGuidelineServiceImpl.findAll(dosingGuideline1).get(0).isRecommendation())
        );

        log.info("test passed");
    }
}