package com.java.summary.springboot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.java.summary.springboot.mybatis.mapper")
//启注解事务管理
@EnableTransactionManagement
public class App {
}
