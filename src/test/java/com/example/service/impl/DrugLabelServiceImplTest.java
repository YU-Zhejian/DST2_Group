package com.example.service.impl;

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
 * Test for the drugLabelService
 *
 * @author Yihui-CEN
 */

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dst2Application.class)
public class DrugLabelServiceImplTest {

    private final Logger log = LoggerFactory.getLogger(DrugLabelServiceImplTest.class);

    @Autowired
    private DrugLabelServiceImpl druglabelServiceImpl;

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

        druglabelServiceImpl.save(drugLabel1);
        druglabelServiceImpl.save(drugLabel2);

        assertAll(
                () -> assertEquals(drugLabel1.getId(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getId()),
                () -> assertEquals(drugLabel1.getName(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getName()),
                () -> assertEquals(drugLabel1.getDrugId(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getDrugId()),
                () -> assertEquals(drugLabel1.getRaw(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getRaw()),
                () -> assertEquals(drugLabel1.getPrescribingMarkdown(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getPrescribingMarkdown()),
                () -> assertEquals(drugLabel1.getSource(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getSource()),
                () -> assertEquals(drugLabel1.getSummaryMarkdown(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getSummaryMarkdown()),
                () -> assertEquals(drugLabel1.getTextMarkdown(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getTextMarkdown()),
                () -> assertEquals(drugLabel1.getObjCls(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getObjCls()),
                () -> assertEquals(drugLabel1.isAlternateDrugAvailable(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).isAlternateDrugAvailable()),
                () -> assertEquals(drugLabel1.isDosingInformation(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).isDosingInformation())
        );

        assertAll(
                () -> assertNotEquals(drugLabel2.getId(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getId()),
                () -> assertNotEquals(drugLabel2.getName(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getName()),
                () -> assertNotEquals(drugLabel2.getDrugId(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getDrugId()),
                () -> assertNotEquals(drugLabel2.getRaw(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getRaw()),
                () -> assertNotEquals(drugLabel2.getPrescribingMarkdown(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getPrescribingMarkdown()),
                () -> assertNotEquals(drugLabel2.getSource(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getSource()),
                () -> assertNotEquals(drugLabel2.getSummaryMarkdown(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getSummaryMarkdown()),
                () -> assertNotEquals(drugLabel2.getTextMarkdown(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getTextMarkdown()),
                () -> assertNotEquals(drugLabel2.getObjCls(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).getObjCls()),
                () -> assertNotEquals(drugLabel2.isAlternateDrugAvailable(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).isAlternateDrugAvailable()),
                () -> assertNotEquals(drugLabel2.isDosingInformation(), druglabelServiceImpl.findAll(drugLabel1 ).get(0).isDosingInformation())
        );

        log.info("test passed");
    }
}