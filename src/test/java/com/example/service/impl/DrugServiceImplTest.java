package com.example.service.impl;

import com.example.Dst2Application;
import com.example.bean.Drug;
import com.example.task.DosingGuidelineTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for the crawler wrapper
 *
 * @author Yihui-CEN
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dst2Application.class)
public class DrugServiceImplTest {

    private final Logger log = LoggerFactory.getLogger(DosingGuidelineTask.class);

    @Autowired    // FIXME: error creating bean
    private DrugServiceImpl drugServiceImpl;

    @Test
    public void test() {
        log.info("test called");
        Drug drug1 = new Drug();
        drug1.setId("1");
        drug1.setName("1");
        drug1.setBiomarker(true);
        drug1.setDrugUrl("1");
        drug1.setObjCls("1");

        Drug drug2 = new Drug();
        drug2.setId("1");
        drug2.setName("1");
        drug2.setBiomarker(true);
        drug2.setDrugUrl("1");
        drug2.setObjCls("1");

        assertEquals(drug1, drugServiceImpl.findAll(drug1).get(1));
        assertEquals(drug2, drugServiceImpl.findAll(drug2).get(1));

        log.info("test passed");
    }

}