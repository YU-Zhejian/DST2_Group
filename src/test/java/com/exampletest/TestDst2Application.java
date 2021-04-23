package com.exampletest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

@SpringBootTest(properties = "workerClassName=A")
class TestDst2Application{
    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws Exception {
        System.out.println("获取的数据库连接为:"+dataSource.getConnection());
    }


}
