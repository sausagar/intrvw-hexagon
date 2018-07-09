package com.intrvw.hexagon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intrvw.hexagon.exceptions.BadRequestException;
import com.intrvw.hexagon.exceptions.InvalidCCType;
import com.intrvw.hexagon.exceptions.TechnicalException;
import com.intrvw.hexagon.model.CreditCardDetailsImpl;
import com.intrvw.hexagon.service.generator.CreditCardGenerator;

@Service
public class CreditCardGeneratorService {

	private CCGeneratorResolver ccGeneratorResolver;

	@Autowired
	public CreditCardGeneratorService(CCGeneratorResolver ccGeneratorResolver) {
		this.ccGeneratorResolver = ccGeneratorResolver;
	}

	public List<CreditCardDetailsImpl> getCreditCardsForType(String type, int totalCount)
			throws InvalidCCType, BadRequestException, TechnicalException, InterruptedException {

		if (totalCount < 1) {
			throw new BadRequestException("Count should be >= 1");
		}

		CreditCardGenerator generator = ccGeneratorResolver.getCCGeneratorForType(type);

		List<String> ccNumbers = new ArrayList<>();

		for (int i = 0; i < totalCount; ++i) {
			ccNumbers.add(generator.generateCCNumber());
		}

		// Validate these numbers and discard if not valid
		// the validation method concurrently handles the volume of the cards submitted for validation.
		
		return generator.filterAndGenerateValidCreditCards(ccNumbers);
		
	}

}
