package com.gp.homework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@ServletComponentScan(basePackages = "com.gp.homework.config")
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages="com")
@MapperScan("com.gp.homework.domain.mapper")
public class HomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

}
