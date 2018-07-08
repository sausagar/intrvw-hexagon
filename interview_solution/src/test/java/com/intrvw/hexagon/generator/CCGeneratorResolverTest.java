package com.intrvw.hexagon.generator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intrvw.hexagon.conf.CCSuffix;
import com.intrvw.hexagon.exceptions.InvalidCCType;
import com.intrvw.hexagon.service.CCGeneratorResolver;
import com.intrvw.hexagon.service.generator.AmexCreditCardGenerator;
import com.intrvw.hexagon.service.generator.VisaCreditCardGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
public class CCGeneratorResolverTest {

	@Mock
    private CCGeneratorResolver cCGeneratorResolver;
	
	@Mock
	private CCSuffix ccSuffix;
	
	@Test
	public void getCCGeneratorForTypeVisa() throws InvalidCCType {
		Mockito.when(
				cCGeneratorResolver.getCCGeneratorForType("visa") )
				.thenReturn(new VisaCreditCardGenerator(ccSuffix));
		assertTrue(cCGeneratorResolver.getCCGeneratorForType("visa") instanceof VisaCreditCardGenerator);
	}

	@Test
	public void getCCGeneratorForTypeAmex() throws InvalidCCType {
		Mockito.when(
				cCGeneratorResolver.getCCGeneratorForType("amex") )
				.thenReturn(new AmexCreditCardGenerator(ccSuffix));
		assertTrue(cCGeneratorResolver.getCCGeneratorForType("amex") instanceof AmexCreditCardGenerator);
	}
	
	@Test(expected = InvalidCCType.class)
	public void failGetCCGeneratorForInvalidType() throws InvalidCCType {
		Mockito.when(
				cCGeneratorResolver.getCCGeneratorForType("abc") )
				.thenThrow(new InvalidCCType(""));
		cCGeneratorResolver.getCCGeneratorForType("abc");
	}
	
	
}
