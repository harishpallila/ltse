package com.ltse;

import java.sql.Timestamp;
import java.util.Optional;

public class TradeValidator {

	public Trade validateAndGenerateTrade(final RawTrade t) {
		final Trade.Builder tb = new Trade.Builder();
		
		Optional<? extends Object> result = null;
		
		result = TradeValidations.timestampValidation.getResult(t);
		if (result.isEmpty()) {
			tb.isAcceptedTrade(false).reasonCode("failed timestamp validation");
		} else {
			tb.timestamp((Timestamp) result.get());			
		}
		
		result = TradeValidations.brokerValidation.getResult(t);
		if (result.isEmpty()) {
			tb.isAcceptedTrade(false).reasonCode("failed broker validation");
		} else {
			tb.broker((String) result.get());			
		}

		result = TradeValidations.sequenceIdValidation.getResult(t);
		if (result.isEmpty()) {
			tb.isAcceptedTrade(false).reasonCode("failed sequence id validation");
		} else {
			tb.sequenceId((Long) result.get());			
		}

		result = TradeValidations.typeValidation.getResult(t);
		if (result.isEmpty()) {
			tb.isAcceptedTrade(false).reasonCode("failed type validation");
		} else {
			tb.type((String) result.get());			
		}

		result = TradeValidations.symbolValidation.getResult(t);
		if (result.isEmpty()) {
			tb.isAcceptedTrade(false).reasonCode("failed symbol validation");
		} else {
			tb.symbol((String) result.get());			
		}

		result = TradeValidations.quantityValidation.getResult(t);
		if (result.isEmpty()) {
			tb.isAcceptedTrade(false).reasonCode("failed quantity validation");
		} else {
			tb.quantity((Long) result.get());			
		}

		result = TradeValidations.priceValidation.getResult(t);
		if (result.isEmpty()) {
			tb.isAcceptedTrade(false).reasonCode("failed price validation");
		} else {
			tb.price((Double) result.get());			
		}

		result = TradeValidations.sideValidation.getResult(t);
		if (result.isEmpty()) {
			tb.isAcceptedTrade(false).reasonCode("failed side validation");
		} else {
			tb.side((String) result.get());
		}
		
		return tb.build();
	}
	
}
