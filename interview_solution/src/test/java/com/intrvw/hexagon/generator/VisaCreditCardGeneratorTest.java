package com.intrvw.hexagon.generator;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intrvw.hexagon.conf.CCSuffix;
import com.intrvw.hexagon.exceptions.TechnicalException;
import com.intrvw.hexagon.model.CreditCardDetailsImpl;
import com.intrvw.hexagon.service.generator.VisaCreditCardGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
public class VisaCreditCardGeneratorTest {

	@InjectMocks
	private VisaCreditCardGenerator visaCreditCardGeneratorTest;
	
	@Mock
	private CCSuffix ccSuffix;
	
	
	@Test
	public void generateCCNumber() {
		Mockito.when(
          ccSuffix.getVisaSuffix() )
				.thenReturn("4");
		assertTrue(visaCreditCardGeneratorTest.generateCCNumber().startsWith("4"));
	}
	
	@Test
	public void filterAndGenerateValidCreditCardsTest() throws TechnicalException, InterruptedException {
		Mockito.when(
		          ccSuffix.getVisaSuffix() )
						.thenReturn("4");
		List<String> ccNumbers = new ArrayList<>(Arrays.asList("4565656","45655456", "123455","sasds"));
		List<CreditCardDetailsImpl> filteredList = visaCreditCardGeneratorTest.filterAndGenerateValidCreditCards(ccNumbers);
		assertTrue(filteredList.size() == 2);
		
		
	}

}
