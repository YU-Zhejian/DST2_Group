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
public class DosingGuidelineTest {

    private final Logger log = LoggerFactory.getLogger(DrugTest.class);

    @Test
    @DisplayName("Every setter and getter need to work to past the test")
    public void testDosingGuidelineGetterSetter() {
        log.info("Test Started");
        DosingGuideline dosingGuideline = new DosingGuideline();

        dosingGuideline.setId("");
        dosingGuideline.setObjCls("");
        dosingGuideline.setName("");
        dosingGuideline.setRecommendation(true);
        dosingGuideline.setDrugId("");
        dosingGuideline.setSource("");
        dosingGuideline.setTextMarkdown("");
        dosingGuideline.setSummaryMarkdown("");
        dosingGuideline.setRaw("");

        assertAll("Multiple judge in one test",
                () -> assertEquals("",dosingGuideline.getId()),
                () -> assertEquals("",dosingGuideline.getObjCls()),
                () -> assertEquals("",dosingGuideline.getName()),
                () -> assertTrue(dosingGuideline.isRecommendation()),
                () -> assertEquals("",dosingGuideline.getDrugId()),
                () -> assertEquals("",dosingGuideline.getSource()),
                () -> assertEquals("",dosingGuideline.getTextMarkdown()),
                () -> assertEquals("",dosingGuideline.getSummaryMarkdown()),
                () -> assertEquals("",dosingGuideline.getRaw())
        );
        log.info("Test Passed");
    }
}