package com.gp.homework.pattern.factory.simple;

import java.util.Objects;

public class FlowerFactory {

	public static Flower create(Enum en) {
		if(Objects.equals("rose",en.name())) {
			return new RoseFlower() ;
		}
		
		else if(Objects.equals("chrysanthemums",en.name())) {
			return new ChrysanthemumsFlower() ;
		}
		
		return null ;
	}
	
	public static Flower create(Class clazz) {
		try {
			if(clazz != null) {
				return (Flower)clazz.newInstance();
			}
			return null ;
		} catch (Exception e) {
			return null;
		}
	}
	
}
