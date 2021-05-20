package com.example.task;

import com.example.Dst2Application;
import com.example.dao.DrugDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * Test for the DrugLabel Crawler
 *
 * @author Yihui-CEN
 */

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dst2Application.class)
public class DrugLabelTaskTest {

    private final Logger log = LoggerFactory.getLogger(DrugLabelTaskTest.class);

    @Autowired
    private DrugLabelTask drugLabelTask;

    @Autowired
    private DrugDao drugDao;

    @Test
    public void testDrugLabelTask() throws Exception {
        log.info("test called");
        DrugTask.Ids.clear();
        for(int i = 1; i < drugDao.findAll().size();i++){
            String Id = drugDao.findAll().get(i).getId();
            DrugTask.Ids.add(Id);
        }// FIXME: the use of static member leads to the recalculation for it in the test
        System.out.println(DrugTask.Ids);
        drugLabelTask.drugLabelTask();

        log.info("test passed");
    }
}