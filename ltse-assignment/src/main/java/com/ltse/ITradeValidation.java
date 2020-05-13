package com.ltse;

import java.util.Optional;

public interface ITradeValidation<T> {

	public Optional<T> getResult(final RawTrade t);
	
}
