package com.ltse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class TradeValidatorTest {

	@Test
	public void testValidateAndGenerateTrade() {
		TradeValidator tv = new TradeValidator();
		
		RawTrade.Builder builder = new RawTrade.Builder();
		builder.symbol("AAPL").broker("Fidelity").sequenceId("1").timestamp("10/5/2019 11:10:23");
		RawTrade rt = new RawTrade(builder);

		Trade result = tv.validateAndGenerateTrade(rt);

		assertEquals("Invalid trade", false, result.isAcceptedTrade());
		assertEquals("Incorrect reason code length", 4, result.getReasonCode().split(";").length);
		assertEquals("Incorrect reason code", "failed type validation;failed quantity validation;failed price validation;failed side validation", result.getReasonCode());
	}
	

	@Test
	public void testValidateAndGenerateTradeGoodTrade() {
		TradeValidator tv = new TradeValidator();
		
		RawTrade.Builder builder = new RawTrade.Builder();
		builder.symbol("AAPL").broker("Fidelity").sequenceId("1").timestamp("10/5/2019 11:10:23").type("1").quantity("100").price("100.10").side("Buy");
		RawTrade rt = new RawTrade(builder);

		Trade result = tv.validateAndGenerateTrade(rt);

		assertEquals("Valid trade", true, result.isAcceptedTrade());
		assertEquals("correct reason code", "", result.getReasonCode());
	}
}
