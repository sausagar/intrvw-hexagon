package com.intrvw.hexagon.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.intrvw.hexagon.InterviewSolutionApplication;
import com.intrvw.hexagon.controller.CCGeneratorController;
import com.intrvw.hexagon.model.CreditCardDetailsImpl;
import com.intrvw.hexagon.service.CreditCardGeneratorService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {InterviewSolutionApplication.class})
@TestPropertySource(properties = {
	    "cc.suffix.visa=4",
	    "cc.suffix.amex=37",
	    "cc.suffix.discovercard=5",
        "cc.suffix.mastercard=6"
	    
	})
@WebAppConfiguration
public class CCGeneratorControllerTest {

	
	private MockMvc mockMvc;
	
	@InjectMocks
    private CCGeneratorController ccController;

	@MockBean
	private CreditCardGeneratorService creditCardGeneratorService;

	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(ccController)
                .build();
    }

	
	@Test
	public void generateCCNumbersWithExpiryTest() throws Exception {
		
		List<CreditCardDetailsImpl> mockList = new ArrayList<>();
		mockList.add(new CreditCardDetailsImpl("413242342", "visa", new Date()));
		mockList.add(new CreditCardDetailsImpl("413242344", "visa", new Date()));
		
		Mockito.when(
				creditCardGeneratorService.getCreditCardsForType(Mockito.anyString(), Mockito.anyInt()))
				.thenReturn(mockList);
		
		mockMvc.perform(get("/CCEngine/visa/2")).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$", Matchers.hasSize((2))))
        .andExpect(jsonPath("$[0].ccNumber", Matchers.is("413242342")));
		
	}

}
