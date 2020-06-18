package com.gp.homework.pattern.proxy.cglib;

import cn.hutool.core.util.StrUtil;
import com.gp.homework.domain.entity.TestUsers;

public class MyTestJava {
	
	public static void main(String[] args) {


		TestUsers users1 = new TestUsers() ;
		users1.setNick_name("en!!!~~");
		users1.setPassword("da");
		users1.setUser_sex("2");
		users1.setUsername("Fruit");


		TestUsers users2 = users1.clone() ;

		users2.setNick_name("果宝");

		System.out.println(StrUtil.format("{}*********{}", users1.getNick_name(), users2.getNick_name()));


	}

}
