package com.gp.homework.pattern.factory.abstractFactory;

import com.gp.homework.pattern.factory.simple.Flower;

//所有子工程实现这个工厂
//一个品牌的抽象
public interface IFlowerFactory {

	Flower createFlower() ;
	
	Fertilizer createFertilizer() ;
	
	FlowerPot createFlowerPot() ;
	
	
}
