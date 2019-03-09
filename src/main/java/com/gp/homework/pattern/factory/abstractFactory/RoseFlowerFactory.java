package com.gp.homework.pattern.factory.abstractFactory;

import com.gp.homework.pattern.factory.simple.Flower;
import com.gp.homework.pattern.factory.simple.RoseFlower;

public class RoseFlowerFactory implements IFlowerFactory{

	@Override
	public Flower createFlower() {
		// TODO Auto-generated method stub
		return new RoseFlower();
	}

	@Override
	public Fertilizer createFertilizer() {
		// TODO Auto-generated method stub
		return new RoseFertilizer() ;
	}

	@Override
	public FlowerPot createFlowerPot() {
		// TODO Auto-generated method stub
		return new RoseFlowerPot() ;
	}

}
