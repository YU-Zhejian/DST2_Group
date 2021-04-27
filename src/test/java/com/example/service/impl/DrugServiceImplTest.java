package com.example.service.impl;

import com.example.Dst2Application;
import com.example.bean.Drug;
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
 * Test for the drugService
 *
 * @author Yihui-CEN
 */

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dst2Application.class)
public class DrugServiceImplTest {

    private final Logger log = LoggerFactory.getLogger(DrugServiceImplTest.class);

    @Autowired
    private DrugServiceImpl drugServiceImpl;

    @Test
    @Transactional
    @Rollback
    public void test() {
        log.info("test called");
        Drug drug1 = new Drug();
        drug1.setId("1");
        drug1.setName("testName1");
        drug1.setBiomarker(true);
        drug1.setDrugUrl("drugUrl1");
        drug1.setObjCls("objCls1");

        Drug drug2 = new Drug();
        drug2.setId("2");
        drug2.setName("testName2");
        drug2.setBiomarker(false);
        drug2.setDrugUrl("drugUrl2");
        drug2.setObjCls("objCls2");

        drugServiceImpl.save(drug1);
        drugServiceImpl.save(drug2);

        assertAll(
                () -> assertEquals(drug1.getId(), drugServiceImpl.findAll(drug1).get(0).getId()),
                () -> assertEquals(drug1.getName(), drugServiceImpl.findAll(drug1).get(0).getName()),
                () -> assertEquals(drug1.isBiomarker(), drugServiceImpl.findAll(drug1).get(0).isBiomarker()),
                () -> assertEquals(drug1.getDrugUrl(), drugServiceImpl.findAll(drug1).get(0).getDrugUrl()),
                () -> assertEquals(drug1.getObjCls(), drugServiceImpl.findAll(drug1).get(0).getObjCls())
        );

        assertAll(
                () -> assertNotEquals(drug2.getId(), drugServiceImpl.findAll(drug1).get(0).getId()),
                () -> assertNotEquals(drug2.getName(), drugServiceImpl.findAll(drug1).get(0).getName()),
                () -> assertNotEquals(drug2.isBiomarker(), drugServiceImpl.findAll(drug1).get(0).isBiomarker()),
                () -> assertNotEquals(drug2.getDrugUrl(), drugServiceImpl.findAll(drug1).get(0).getDrugUrl()),
                () -> assertNotEquals(drug2.getObjCls(), drugServiceImpl.findAll(drug1).get(0).getObjCls())
        );

        log.info("test passed");
    }
}