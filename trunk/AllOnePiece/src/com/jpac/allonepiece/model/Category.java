package com.jpac.allonepiece.model;

public enum Category {

	CHARACTER("CHARACTER"),
	
	ISLAND("ISLAND"),
	
	CITY("CITY/TOWN"),
	
	EVENT("EVENT"),
	
	WEAPON("WEAPON"),
	
	TECHNIQUE("TECHNIQUE");
	
	private String name;
	
	Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
