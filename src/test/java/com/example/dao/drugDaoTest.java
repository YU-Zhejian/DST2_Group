package com.example.dao;

import com.example.bean.Drug;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.Assert.assertThat;

/**
 * Test for DrugDao
 *
 * @author Yihui-CEN
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DrugDaoTest {

    private final Logger log = LoggerFactory.getLogger(DrugDaoTest.class);

    @Autowired
    private DrugDao drugDao;

    @Test
    public void testDrugDao() {
        log.info("Test Started");
        Drug drug = new Drug();
        drug.setName("Peter");
        assertThat(drug.getName(), Matchers.is(drugDao.save(drug).getName()));
        log.info("Test Passed");
    }
}