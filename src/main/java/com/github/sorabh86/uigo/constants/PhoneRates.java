package com.github.sorabh86.uigo.constants;

public enum PhoneRates {
	One(1),
	Two(2),
	Three(3),
	Four(4),
	Five(5);
	
	private final int value;
	
	PhoneRates(final int newValue) {
		value = newValue;
	}
	public int getValue() {return value;}
}
