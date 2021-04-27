package com.example.bean;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Drug bean
 *
 * @author Yihui-CEN
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DrugTest {

    private final Logger log = LoggerFactory.getLogger(DrugTest.class);

    @Test
    @DisplayName("Every setter and getter need to work to past the test")
    public void testDrugGetterSetter() {
        log.info("Test Started");
        Drug drug = new Drug();

        drug.setId("");
        drug.setName("");
        drug.setBiomarker(true);
        drug.setDrugUrl("");
        drug.setObjCls("");

        assertAll("Multiple judge in one test",
                () -> assertEquals("",drug.getId()),
                () -> assertEquals("",drug.getName()),
                () -> assertTrue(drug.isBiomarker()),
                () -> assertEquals("",drug.getDrugUrl()),
                () -> assertEquals("",drug.getObjCls())
        );
        log.info("Test Passed");
    }
}