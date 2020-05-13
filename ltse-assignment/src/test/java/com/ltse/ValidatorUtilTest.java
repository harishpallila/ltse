package com.ltse;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Optional;

import org.junit.Test;


public class ValidatorUtilTest {

	@Test
	public void testIsValidText() {
		final Optional<String> result = ValidatorUtil.getString(null);
		assertEquals("has to be empty", true, result.isEmpty());
		
		final Optional<String> result2 = ValidatorUtil.getString(" AAPL ");
		assertEquals("has to be empty", "AAPL", result2.get());
		
	}


	@Test
	public void testGetLong() {
		final Optional<Long> result = ValidatorUtil.getLong(null);
		assertEquals("has to be empty", true, result.isEmpty());
		
		final Optional<Long> result2 = ValidatorUtil.getLong(" 120 ");
		assertEquals("has to be empty", 120L, ((Long) result2.get()).longValue());
		
	}

	@Test
	public void testGetTimestamp() throws ParseException {
		final Optional<Timestamp> result = ValidatorUtil.getTimestamp(null);
		assertEquals("has to be empty", true, result.isEmpty());
		
		final Optional<Timestamp> result2 = ValidatorUtil.getTimestamp("10/5/2019 11:10:23");
		assertEquals("has to be empty", 1570288223000L, ((Timestamp) result2.get()).getTime());
		
	}
}
