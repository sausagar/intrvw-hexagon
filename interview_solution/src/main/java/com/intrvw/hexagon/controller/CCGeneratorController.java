package com.intrvw.hexagon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.intrvw.hexagon.exceptions.BadRequestException;
import com.intrvw.hexagon.exceptions.InvalidCCType;
import com.intrvw.hexagon.exceptions.TechnicalException;
import com.intrvw.hexagon.model.CreditCardDetailsImpl;
import com.intrvw.hexagon.service.CreditCardGeneratorService;

@RestController

public class CCGeneratorController {
   
	@Autowired
	private CreditCardGeneratorService cardGeneratorService;
	
	@GetMapping(value = "/CCEngine/{ccType}/{totalCount}")
	public List<CreditCardDetailsImpl> generateCCNumbersWithExpiry(@PathVariable("ccType") String ccType, @PathVariable("totalCount") int totalCount) throws InvalidCCType, BadRequestException, TechnicalException {
		
		return cardGeneratorService.getCreditCardsForType(ccType, totalCount);
		
	}
	
	
}
