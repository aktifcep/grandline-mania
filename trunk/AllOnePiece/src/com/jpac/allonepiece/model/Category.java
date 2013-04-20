package com.jpac.allonepiece.model;

public enum Category {

	CHARACTER("CHARACTER"),
	
	LOCATION("LOCATION"),
	
	EVENT("EVENT"),
	
	TECHNIQUE("TECHNIQUE");
	
	private String name;
	
	Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
