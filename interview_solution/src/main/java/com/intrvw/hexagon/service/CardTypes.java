package com.intrvw.hexagon.service;

public enum CardTypes {

	VISA("visa"), AMEX("amex"), DISCOVER("discovercards"), MASTERCARD("mastercard");

	private String value;

	public String getValue() {
		return value;
	}

	private CardTypes(String val) {
		this.value = val;
	}

}
