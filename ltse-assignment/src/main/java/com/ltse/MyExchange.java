package com.ltse;

import static com.ltse.Utils.writeToFile;
import static com.ltse.Utils.appendToFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyExchange {
	
	private final List<Trade> acceptedTrades = new ArrayList<Trade>();
	private final List<Trade> rejectedTrades = new ArrayList<Trade>();
	private final List<String> badTrades = new ArrayList<String>();
	
	private TradeParser tradeParser;
	private TradeValidator tradeValidator;
	private TradeRuleEngine ruleEngine;
	private String tradesFilePath;
	private String validTickersFilePath;

	public MyExchange(final TradeParser tradeParser, final TradeValidator tradeValidator, final TradeRuleEngine ruleEngine, final String tradesFilePath, final String validTickersFilePath) {
		this.tradeParser = tradeParser;
		this.tradeValidator = tradeValidator;
		this.ruleEngine = ruleEngine;
		this.tradesFilePath = tradesFilePath;
		this.validTickersFilePath = validTickersFilePath;
	}
	
	public List<Trade> getAcceptedTrades() {
		return acceptedTrades;
	}

	public List<Trade> getRejectedTrades() {
		return rejectedTrades;
	}

	public List<String> getBadTrades() {
		return badTrades;
	}

	private void initRulesEngine() throws FileNotFoundException {
		ruleEngine.init(this.validTickersFilePath);
	}
	
	public void runExchange() throws FileNotFoundException {
//		init
		initRulesEngine();
//		parse
		tradeParser.parseTrades(this.tradesFilePath, true);
		
		badTrades.addAll(tradeParser.getBadRawTrades());
		
		final List<RawTrade> goodRawTrades = tradeParser.getGoodrawTrades();

		for (final RawTrade eachRawTrade : goodRawTrades) {
//			validate
			final Trade processedTrade = tradeValidator.validateAndGenerateTrade(eachRawTrade);
			
			if (!processedTrade.isAcceptedTrade()) {
				rejectedTrades.add(processedTrade);
				continue;
			}
//			apply rules			
			ruleEngine.processTrade(processedTrade);
			
			if (processedTrade.isAcceptedTrade())
				acceptedTrades.add(processedTrade);
			else
				rejectedTrades.add(processedTrade);
		}
	}
		
	public static void main(String[] args) throws IOException {
		
		if (args.length != 3) {
			System.err.println("Please provide 3 args; 1st: tradesFilePath, 2nd: tickerFilePath, 3rd:outDir");
			System.exit(-1);
		}
		
		final String tradesFilePath = args[0];
		final String tickersFilePath = args[1];
		final String outDir = args[2];
		
		final TradeParser tradeParser = new TradeParser();
		final TradeValidator tradeValidator = new TradeValidator();
		final TradeRuleEngine ruleEngine = new TradeRuleEngine();

		final MyExchange exchange = new MyExchange(tradeParser, tradeValidator, ruleEngine, tradesFilePath, tickersFilePath);
		
		exchange.runExchange();
		
		writeToFile(exchange.getAcceptedTrades(), outDir + "/accepted.txt", false);
		writeToFile(exchange.getRejectedTrades(), outDir + "/rejected.txt", false);
		appendToFile(exchange.getBadTrades(), outDir + "/rejected.txt");
				
		writeToFile(exchange.getAcceptedTrades(), outDir + "/accepted_orders_json.txt", true);
		writeToFile(exchange.getRejectedTrades(), outDir + "/rejected_orders_json.txt", true);

	}

}
