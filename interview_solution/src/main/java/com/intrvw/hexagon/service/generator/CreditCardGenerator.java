package com.intrvw.hexagon.service.generator;

import java.util.List;

import com.intrvw.hexagon.exceptions.TechnicalException;
import com.intrvw.hexagon.model.CreditCardDetailsImpl;

public interface CreditCardGenerator {

	public String generateCCNumber();

	List<CreditCardDetailsImpl> filterAndGenerateValidCreditCards(List<String> ccNumbers) throws TechnicalException, InterruptedException;

}
