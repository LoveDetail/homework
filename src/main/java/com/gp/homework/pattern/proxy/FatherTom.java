package com.gp.homework.pattern.proxy;

public class FatherTom implements Person {

	@Override
	public void takeCareOfBaby(String fruitName,String toyName) {
		System.out.println("不能吃\""+fruitName+"\"，会过敏!!!");

		System.out.println("睡觉要抱着\""+toyName+"\"，否则会哭闹 !!!");
	}
	@Override
	public void washFace() {
		System.out.println("开始洗脸!!");
	}

}
