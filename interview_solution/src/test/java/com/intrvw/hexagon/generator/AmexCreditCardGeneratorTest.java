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
import com.intrvw.hexagon.service.generator.AmexCreditCardGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
public class AmexCreditCardGeneratorTest {

	@InjectMocks
	private AmexCreditCardGenerator amexCreditCardGenerator;
	
	@Mock
	private CCSuffix ccSuffix;
	
	
	@Test
	public void generateCCNumber() {
		Mockito.when(
          ccSuffix.getAmexSuffix() )
				.thenReturn("37");
		assertTrue(amexCreditCardGenerator.generateCCNumber().startsWith("37"));
	}
	
	@Test
	public void filterAndGenerateValidCreditCardsTest() throws TechnicalException, InterruptedException {
		Mockito.when(
		          ccSuffix.getAmexSuffix() )
						.thenReturn("37");
		List<String> ccNumbers = new ArrayList<>(Arrays.asList("4565656","45655456", "373455","37434343"));
		List<CreditCardDetailsImpl> filteredList = amexCreditCardGenerator.filterAndGenerateValidCreditCards(ccNumbers);
		assertTrue(filteredList.size() == 2);
		
		
	}

}
