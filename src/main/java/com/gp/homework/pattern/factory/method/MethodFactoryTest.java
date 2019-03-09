package com.gp.homework.pattern.factory.method;

import com.gp.homework.pattern.factory.simple.Flower;

public class MethodFactoryTest {
	public static void main(String[] args) {
		
		FlowerMethodFactory factory = new RoseFlowerMethodFactory() ;
		Flower f = factory.create() ;
		f.flowerName();
		
		factory = new ChrysanthemumsFlowerMethodFactory ();
		f = factory.create();
		f.flowerName();
		
	}
}
