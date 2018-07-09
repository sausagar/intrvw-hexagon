package com.intrvw.hexagon.service;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intrvw.hexagon.exceptions.BadRequestException;
import com.intrvw.hexagon.exceptions.InvalidCCType;
import com.intrvw.hexagon.exceptions.TechnicalException;
import com.intrvw.hexagon.model.CreditCardDetailsImpl;
import com.intrvw.hexagon.service.generator.VisaCreditCardGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
public class CreditCardGeneratorServiceTest {

	@InjectMocks
	private CreditCardGeneratorService creditCardGeneratorService;
	
	@Mock
	private CCGeneratorResolver ccGeneratorResolver;
	
	@Mock
	private VisaCreditCardGenerator visaCreditCardGenerator;
	
	@Test
	public void getCreditCardsForTypeTest() throws InvalidCCType, BadRequestException, TechnicalException, InterruptedException {
		
		Mockito.when(
				ccGeneratorResolver.getCCGeneratorForType(Mockito.anyString()))
				.thenReturn(visaCreditCardGenerator);
		
		Mockito.when(
				visaCreditCardGenerator.generateCCNumber())
				.thenReturn("423456");
		Mockito.when(
				visaCreditCardGenerator.filterAndGenerateValidCreditCards(Mockito.any(List.class)))
				.thenReturn(Arrays.asList("423456","423456"));
		
		
		List<CreditCardDetailsImpl> cc = creditCardGeneratorService.getCreditCardsForType("visa", 2);
		assertTrue(cc.size() == 2);
		
	}

	@Test(expected = BadRequestException.class)
	public void failCreditCardsForTypeWithNegativeCount() throws InvalidCCType, BadRequestException, TechnicalException, InterruptedException {
		
		Mockito.when(
				ccGeneratorResolver.getCCGeneratorForType(Mockito.anyString()))
				.thenReturn(visaCreditCardGenerator);
		
		Mockito.when(
				visaCreditCardGenerator.generateCCNumber())
				.thenReturn("423456");
		
		
		List<CreditCardDetailsImpl> cc = creditCardGeneratorService.getCreditCardsForType("visa", -2);
		
		
	}
	
}
