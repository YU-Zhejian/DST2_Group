package com.example.task;

import com.example.Dst2Application;
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

/**
 * Test for the Drug Crawler
 *
 * @author Yihui-CEN
 */

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dst2Application.class)
public class DrugTaskTest {

    private final Logger log = LoggerFactory.getLogger(DrugTaskTest.class);

    @Autowired
    private DrugTask drugTask;

    @Test
    public void testDrug() throws Exception {
        log.info("test called");

        drugTask.drugTask();

        log.info("test passed");
    }
}