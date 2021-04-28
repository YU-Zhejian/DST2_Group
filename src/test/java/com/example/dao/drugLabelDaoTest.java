package com.example.dao;

import com.example.Dst2Application;
import com.example.bean.DrugLabel;
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
 * Test for the drugLabelDao
 *
 * @author Yihui-CEN
 */

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dst2Application.class)
public class DrugLabelDaoTest {

    private final Logger log = LoggerFactory.getLogger(DrugLabelDaoTest.class);

    @Autowired
    private DrugLabelDao drugLabelDao;

    @Test
    @Transactional
    @Rollback
    public void test() {
        log.info("test called");
        DrugLabel drugLabel1 = new DrugLabel();
        drugLabel1.setId("1");
        drugLabel1.setName("testName1");
        drugLabel1.setObjCls("objCls1");
        drugLabel1.setHaveDosingInformation(true);
        drugLabel1.setDrugId("testDrugId1");
        drugLabel1.setRaw("rawData1");
        drugLabel1.setSummaryMarkdown("summaryMarkdown1");
        drugLabel1.setHaveAlternateDrug(true);
        drugLabel1.setTextMarkdown("textMarkdown1");
        drugLabel1.setSource("source1");
        drugLabel1.setPrescribingMarkdown("prescribingMarkdown1");

        DrugLabel drugLabel2 = new DrugLabel();
        drugLabel2.setId("2");
        drugLabel2.setName("testName2");
        drugLabel2.setObjCls("objCls2");
        drugLabel2.setHaveDosingInformation(false);
        drugLabel2.setDrugId("testDrugId2");
        drugLabel2.setRaw("rawData2");
        drugLabel2.setSummaryMarkdown("summaryMarkdown2");
        drugLabel2.setHaveAlternateDrug(false);
        drugLabel2.setTextMarkdown("textMarkdown2");
        drugLabel2.setSource("source2");
        drugLabel2.setPrescribingMarkdown("prescribingMarkdown2");

        drugLabelDao.save(drugLabel1);
        drugLabelDao.save(drugLabel2);


        assertAll(
                () -> assertEquals(drugLabel1.getId(), drugLabelDao.getOne("1").getId()),
                () -> assertEquals(drugLabel1.getName(), drugLabelDao.getOne("1").getName()),
                () -> assertEquals(drugLabel1.getDrugId(), drugLabelDao.getOne("1").getDrugId()),
                () -> assertEquals(drugLabel1.getRaw(), drugLabelDao.getOne("1").getRaw()),
                () -> assertEquals(drugLabel1.getPrescribingMarkdown(), drugLabelDao.getOne("1").getPrescribingMarkdown()),
                () -> assertEquals(drugLabel1.getSource(), drugLabelDao.getOne("1").getSource()),
                () -> assertEquals(drugLabel1.getSummaryMarkdown(), drugLabelDao.getOne("1").getSummaryMarkdown()),
                () -> assertEquals(drugLabel1.getTextMarkdown(), drugLabelDao.getOne("1").getTextMarkdown()),
                () -> assertEquals(drugLabel1.getObjCls(), drugLabelDao.getOne("1").getObjCls()),
                () -> assertEquals(drugLabel1.isAlternateDrugAvailable(), drugLabelDao.getOne("1").isAlternateDrugAvailable()),
                () -> assertEquals(drugLabel1.isDosingInformation(), drugLabelDao.getOne("1").isDosingInformation())
        );

        assertAll(
                () -> assertNotEquals(drugLabel2.getId(), drugLabelDao.getOne("1").getId()),
                () -> assertNotEquals(drugLabel2.getName(), drugLabelDao.getOne("1").getName()),
                () -> assertNotEquals(drugLabel2.getDrugId(), drugLabelDao.getOne("1").getDrugId()),
                () -> assertNotEquals(drugLabel2.getRaw(), drugLabelDao.getOne("1").getRaw()),
                () -> assertNotEquals(drugLabel2.getPrescribingMarkdown(), drugLabelDao.getOne("1").getPrescribingMarkdown()),
                () -> assertNotEquals(drugLabel2.getSource(), drugLabelDao.getOne("1").getSource()),
                () -> assertNotEquals(drugLabel2.getSummaryMarkdown(), drugLabelDao.getOne("1").getSummaryMarkdown()),
                () -> assertNotEquals(drugLabel2.getTextMarkdown(), drugLabelDao.getOne("1").getTextMarkdown()),
                () -> assertNotEquals(drugLabel2.getObjCls(), drugLabelDao.getOne("1").getObjCls()),
                () -> assertNotEquals(drugLabel2.isAlternateDrugAvailable(), drugLabelDao.getOne("1").isAlternateDrugAvailable()),
                () -> assertNotEquals(drugLabel2.isDosingInformation(), drugLabelDao.getOne("1").isDosingInformation())
        );

        log.info("test passed");
    }
}