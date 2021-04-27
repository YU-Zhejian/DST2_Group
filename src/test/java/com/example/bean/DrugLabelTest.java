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
public class DrugLabelTest {

    private final Logger log = LoggerFactory.getLogger(DrugTest.class);

    @Test
    @DisplayName("Every setter and getter need to work to past the test")
    public void testDrugLabelGetterSetter() {
        log.info("Test Started");
        DrugLabel drugLabel = new DrugLabel();

        drugLabel.setId("");
        drugLabel.setName("");
        drugLabel.setObjCls("");
        drugLabel.setDrugId("");
        drugLabel.setHaveAlternateDrug(true);
        drugLabel.setHaveDosingInformation(true);
        drugLabel.setPrescribingMarkdown("");
        drugLabel.setSource("");
        drugLabel.setTextMarkdown("");
        drugLabel.setSummaryMarkdown("");
        drugLabel.setRaw("");



        assertAll("Multiple judge in one test",
                () -> assertEquals("",drugLabel.getId()),
                () -> assertEquals("",drugLabel.getName()),
                () -> assertEquals("",drugLabel.getObjCls()),
                () -> assertEquals("",drugLabel.getDrugId()),
                () -> assertTrue(drugLabel.isAlternateDrugAvailable()),
                () -> assertTrue(drugLabel.isDosingInformation()),
                () -> assertEquals("",drugLabel.getSource()),
                () -> assertEquals("",drugLabel.getTextMarkdown()),
                () -> assertEquals("",drugLabel.getSummaryMarkdown()),
                () -> assertEquals("",drugLabel.getRaw())
        );
        log.info("Test Passed");
    }
}