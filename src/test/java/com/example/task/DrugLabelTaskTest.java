package com.example.task;

import com.example.Dst2Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void test() throws Exception {
        log.info("test called");

        drugLabelTask.drugLabelTask();

        log.info("test passed");
    }
}