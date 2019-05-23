package com.example.demo.gk;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Statement;

@Component
public class CoreKeyGen implements KeyGenerator {
    private Logger log = LoggerFactory.getLogger(CoreKeyGen.class);
    @Override
    public void processBefore(Executor executor, MappedStatement mappedStatement, Statement statement, Object o) {
        log.info("object class" + o.getClass().getName());
//        statement.executeQuery();
    }

    @Override
    public void processAfter(Executor executor, MappedStatement mappedStatement, Statement statement, Object o) {

    }
}
