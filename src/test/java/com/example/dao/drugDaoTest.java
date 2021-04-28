package com.example.dao;

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
 * Test for the drugDao
 *
 * @author Yihui-CEN
 */

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dst2Application.class)
public class DrugDaoTest {

    private final Logger log = LoggerFactory.getLogger(DrugDaoTest.class);

    @Autowired
    private DrugDao drugDao;

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

        drugDao.save(drug1);
        drugDao.save(drug2);

        assertAll(
                () -> assertEquals(drug1.getId(), drugDao.getOne("1").getId()),
                () -> assertEquals(drug1.getName(), drugDao.getOne("1").getName()),
                () -> assertEquals(drug1.isBiomarker(), drugDao.getOne("1").isBiomarker()),
                () -> assertEquals(drug1.getDrugUrl(), drugDao.getOne("1").getDrugUrl()),
                () -> assertEquals(drug1.getObjCls(), drugDao.getOne("1").getObjCls())
        );

        assertAll(
                () -> assertNotEquals(drug2.getId(), drugDao.getOne("1").getId()),
                () -> assertNotEquals(drug2.getName(), drugDao.getOne("1").getName()),
                () -> assertNotEquals(drug2.isBiomarker(), drugDao.getOne("1").isBiomarker()),
                () -> assertNotEquals(drug2.getDrugUrl(), drugDao.getOne("1").getDrugUrl()),
                () -> assertNotEquals(drug2.getObjCls(), drugDao.getOne("1").getObjCls())
        );

        log.info("test passed");
    }
}