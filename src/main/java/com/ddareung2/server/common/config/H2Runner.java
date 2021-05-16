package com.ddareung2.server.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@Component
public class H2Runner implements ApplicationRunner {
    private final DataSource dataSource;
 
    public H2Runner(DataSource dataSource) {
        this.dataSource = dataSource;
    }
 
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            log.info("url: "+connection.getMetaData().getURL());
            log.info("UserName: "+connection.getMetaData().getUserName());
        }
    }
}
