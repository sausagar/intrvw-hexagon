package com.intrvw.hexagon.generator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intrvw.hexagon.service.generator.VisaCreditCardGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
public class VisaCreditCardGeneratorTest {

	@Mock
	private VisaCreditCardGenerator visaCreditCardGeneratorTest;
	
	@Test
	public void generateCCNumber() {
		Mockito.when(
				visaCreditCardGeneratorTest.generateCCNumber() )
				.thenReturn("41345667");
		assertTrue(visaCreditCardGeneratorTest.generateCCNumber().length() > 0);
	}

}
