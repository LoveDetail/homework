package com.gp.homework.pattern.factory.method;

import com.gp.homework.pattern.factory.simple.ChrysanthemumsFlower;
import com.gp.homework.pattern.factory.simple.Flower;

public class ChrysanthemumsFlowerMethodFactory implements FlowerMethodFactory{

	@Override
	public Flower create() {
		// TODO Auto-generated method stub
		return new ChrysanthemumsFlower() ;
	}

}
