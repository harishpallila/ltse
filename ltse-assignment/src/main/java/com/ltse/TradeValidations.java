package com.ltse;

import java.sql.Timestamp;

public class TradeValidations {

	public static final ITradeValidation<Timestamp> timestampValidation = (t) -> {
		return ValidatorUtil.getTimestamp(t.getTimestamp());
	}; 

	public static final ITradeValidation<String> brokerValidation = (t) -> {
		return ValidatorUtil.getString(t.getBroker());
	}; 

	public static final ITradeValidation<Long> sequenceIdValidation = (t) -> {
		return ValidatorUtil.getLong(t.getSequenceId());
	}; 

	public static final ITradeValidation<String> typeValidation = (t) -> {
		return ValidatorUtil.getString(t.getType());
	}; 

	public static final ITradeValidation<String> symbolValidation = (t) -> {
		return ValidatorUtil.getString(t.getSymbol());
	}; 

	public static final ITradeValidation<Long> quantityValidation = (t) -> {
		return ValidatorUtil.getLong(t.getQuantity());
	}; 

	public static final ITradeValidation<Double> priceValidation = (t) -> {
		return ValidatorUtil.getDouble(t.getPrice());
	}; 

	public static final ITradeValidation<String> sideValidation = (t) -> {
		return ValidatorUtil.getString(t.getSide());
	};
		
}
