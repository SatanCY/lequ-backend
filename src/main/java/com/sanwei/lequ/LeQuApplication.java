package com.sanwei.lequ;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author <a href="https://github.com">SatanCY</a>
 * @from <a href="https://github.com">GitHub</a>
 */
@SpringBootApplication
@MapperScan("com.sanwei.lequ.mapper")
@EnableScheduling
public class LeQuApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeQuApplication.class, args);
    }

}