package com.gp.homework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages="com")
@MapperScan("com.gp.homework.domain.mapper")
@ServletComponentScan(basePackages = "com.gp.homework.config")
public class HomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate() ;
	}
}
