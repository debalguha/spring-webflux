package com.vinsguru.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;

/**
 * @author debal
 */
@Service
public class DataSetupService implements CommandLineRunner {

    @Value("classpath:h2/init.sql")
    private Resource initSql;

    @Autowired
    private R2dbcEntityTemplate entityTemplate;

    @Override
    public void run(String... args) throws Exception {
        final String sqlQuery = StreamUtils.copyToString(initSql.getInputStream(), Charset.defaultCharset());
        System.out.printf(sqlQuery);
        entityTemplate
                .getDatabaseClient()
                .sql(sqlQuery)
                .then()
                .subscribe();
    }
}
