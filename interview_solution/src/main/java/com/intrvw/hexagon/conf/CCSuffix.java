package com.intrvw.hexagon.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CCSuffix {
	
	@Value("${cc.suffix.visa}")
	private String visaSuffix;

	@Value("${cc.suffix.amex}")
	private String amexSuffix;
	
	@Value("${cc.suffix.discovercard}")
	private String discoverCardsSuffix;
	
	@Value("${cc.suffix.mastercard}")
	private String mastercardSuffix;

	public String getVisaSuffix() {
		return visaSuffix;
	}

	public String getAmexSuffix() {
		return amexSuffix;
	}

	public String getDiscoverCardsSuffix() {
		return discoverCardsSuffix;
	}

	public String getMastercardSuffix() {
		return mastercardSuffix;
	}
	
	
	

}
