package com.intrvw.hexagon.model;

import java.util.Date;

public class CreditCardDetailsImpl {

	private final String ccNumber;
	private final String cctype;
	private final Date expiryDate;
	
	public CreditCardDetailsImpl(String ccNumber, String cctype, Date expiryDate) {
		super();
		this.ccNumber = ccNumber;
		this.cctype = cctype;
		this.expiryDate = expiryDate;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}

	
	public String getCcNumber() {
		return ccNumber;
	}

	public String getCctype() {
		return cctype;
	}


}
