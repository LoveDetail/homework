package com.gp.homework.pattern.factory.simple;

public class MyJavaTest {
	public static void main(String[] args) {
		
		Flower f = FlowerFactory.create(FlowerEnum.rose) ;
		f.flowerName();

		f = FlowerFactory.create(FlowerEnum.chrysanthemums) ;
		f.flowerName();
		
		
		//*******************************************
		f = FlowerFactory.create(RoseFlower.class) ;
		f.flowerName();
		
		f = FlowerFactory.create(ChrysanthemumsFlower.class) ;
		f.flowerName();
	}
}
