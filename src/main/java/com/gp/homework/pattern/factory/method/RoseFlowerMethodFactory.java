package com.gp.homework.pattern.factory.method;

import com.gp.homework.pattern.factory.simple.Flower;
import com.gp.homework.pattern.factory.simple.RoseFlower;

public class RoseFlowerMethodFactory implements FlowerMethodFactory{

	@Override
	public Flower create() {
		// TODO Auto-generated method stub
		return new RoseFlower() ;
	}

}
