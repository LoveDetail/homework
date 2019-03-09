package com.gp.homework.pattern.factory.simple;

public enum FlowerEnum {

	rose("rose"),chrysanthemums("chrysanthemums") ;
	
	private final String name;
    
    private FlowerEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
	
}
