package com.intrvw.hexagon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intrvw.hexagon.exceptions.InvalidCCType;
import com.intrvw.hexagon.service.generator.AmexCreditCardGenerator;
import com.intrvw.hexagon.service.generator.CreditCardGenerator;
import com.intrvw.hexagon.service.generator.VisaCreditCardGenerator;

@Component
public class CCGeneratorResolver {

	private VisaCreditCardGenerator visaCreditCardGenerator;

	private AmexCreditCardGenerator amexCreditCardGenerator;

	@Autowired
	public CCGeneratorResolver(VisaCreditCardGenerator visaCreditCardGenerator,
			AmexCreditCardGenerator amexCreditCardGenerator) {
		super();
		this.visaCreditCardGenerator = visaCreditCardGenerator;
		this.amexCreditCardGenerator = amexCreditCardGenerator;
	}

	public CreditCardGenerator getCCGeneratorForType(String type) throws InvalidCCType {
		if (type.equalsIgnoreCase(CardTypes.VISA.getValue())) {
			return visaCreditCardGenerator;
		}
		if (type.equalsIgnoreCase(CardTypes.AMEX.getValue())) {
			return amexCreditCardGenerator;
		}
		throw new InvalidCCType("Cannot process CC of type " + type);
	}

}
