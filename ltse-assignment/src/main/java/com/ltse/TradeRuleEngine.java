package com.ltse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TradeRuleEngine {
	
	private Set<String> ACCEPTED_TICKERS = new HashSet<String>();
	
	private Map<String, List<Long>> ORDERS_PER_MIN = new HashMap<String, List<Long>>();
	
	private Map<String, Set<Long>> UNIQUE_TRADE_IDS = new HashMap<String, Set<Long>>();

	public final IRule acceptedTickers = (t) -> {
		if (ACCEPTED_TICKERS.contains(t.getSymbol())) {
			return true;
		} else {
			t.acceptedTrade(false);
			t.reasonCode("not accepted ticker");
			return false;
		}
	};
	
	public final IRule ordersPerMin = (t) -> {
		@SuppressWarnings("deprecation")
		String key = t.getBroker() + "|" + t.getTimestamp().getMinutes();
		List<Long> tradeIds = null;
		if (!ORDERS_PER_MIN.containsKey(key)) {
			tradeIds = new ArrayList<Long>();
			tradeIds.add(t.getSequenceId());
			ORDERS_PER_MIN.put(key, tradeIds);
			return true;
		}
		
		if (ORDERS_PER_MIN.get(key).size() == 3) {
			t.acceptedTrade(false);
			t.reasonCode("exceeded 3 trades per min:"+key.split("|")[1]);
			return false;
		}
		
		tradeIds = ORDERS_PER_MIN.get(key);
		tradeIds.add(t.getSequenceId());
		
		return true;
	};
	

	public final IRule uniqueTradeIds = (t) -> {
		String key = t.getBroker();
		Set<Long> tradeIds = null;
		if (!UNIQUE_TRADE_IDS.containsKey(key)) {
			tradeIds = new HashSet<Long>();
			tradeIds.add(t.getSequenceId());
			UNIQUE_TRADE_IDS.put(key, tradeIds);
			return true;
		}
		
		boolean newTradeId = UNIQUE_TRADE_IDS.get(key).add(t.getSequenceId());
		
		if (newTradeId) {
			return true;
		} else {
			t.acceptedTrade(false);
			t.reasonCode("duplicate trade id:"+t.getSequenceId());
			return false;			
		}
	};
	
	private final List<IRule> allRules = List.of(acceptedTickers, ordersPerMin, uniqueTradeIds);
	
	public void init(final String tickersFilePath) throws FileNotFoundException {
		final Scanner scanner = new Scanner(new File(tickersFilePath));
		while (scanner.hasNextLine()) {
			String each = scanner.nextLine();
			ACCEPTED_TICKERS.add(each.trim());
		}
	}
	
	public void processTrade(final Trade t) {
		for (final IRule rule : allRules) {
			boolean pass = rule.apply(t);
			if (!pass) 
				break;
		}
	}

}
