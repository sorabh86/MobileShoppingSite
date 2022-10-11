package com.github.sorabh86.uigo.constants;

public enum UserStatus {
	
	DEACTIVATE(0), 
	ACTIVATED(1), 
	DISABLED(2);
	
	private final int value;
	
	UserStatus(final int newValue) {
		value = newValue;
	}
	public int getValue() {return value;}
}
