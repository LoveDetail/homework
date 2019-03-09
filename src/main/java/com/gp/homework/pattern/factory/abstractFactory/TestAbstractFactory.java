package com.gp.homework.pattern.factory.abstractFactory;

import com.gp.homework.pattern.factory.simple.Flower;

public class TestAbstractFactory {
	public static void main(String[] args) {
		
		IFlowerFactory factory = new RoseFlowerFactory() ;
		
		Flower f = factory.createFlower() ;
		Fertilizer fer = factory.createFertilizer() ;
		FlowerPot fp = factory.createFlowerPot() ;
		
		f.flowerName();
		fer.name();
		fp.name();
	}
}
