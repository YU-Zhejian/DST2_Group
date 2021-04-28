package com.example.util;

import com.example.Dst2Application;
import com.google.gson.Gson;
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

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test for the HttpCrawler
 *
 * @author Yihui-CEN
 */

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dst2Application.class)
public class HttpCrawlerTest {

    private final Logger log = LoggerFactory.getLogger(HttpCrawlerTest.class);

    @Autowired
    private HttpCrawler httpCrawler;

    @Test
    @Transactional
    @Rollback
    public void test() {

        log.info("test called");

        String url = "https://api.pharmgkb.org/v1/site/labelsByDrug";
        String jsonContent = httpCrawler.getURLContent(url);
        Gson gson = new Gson();
        Map drugLabels = gson.fromJson(jsonContent, Map.class);
        List<Map> dataSample = (List) drugLabels.get("data");
        assertNotNull(dataSample.get(0));

        log.info("test passed");
    }
}