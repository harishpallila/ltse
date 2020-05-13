package com.ltse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TradeParser {
	final List<RawTrade> goodrawTrades = new ArrayList<RawTrade>();
	final List<String> badRawTrades = new ArrayList<String>();
	
	public List<RawTrade> getGoodrawTrades() {
		return goodrawTrades;
	}

	public List<String> getBadRawTrades() {
		return badRawTrades;
	}

	public void parseTrades(final String filePath, final boolean skipHeader) throws FileNotFoundException {
		final Scanner scanner = new Scanner(new File(filePath));
		
		RawTrade.Builder tb = null;
		int count = 0;
		String[] split = null;

		if (skipHeader && scanner.hasNext())
			scanner.nextLine();
		
		try {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();				
				split = line.split(",");
				
				if (split.length != 8) {
					badRawTrades.add(line);
					continue;
				}
				
				if (count > 0 && count % 100 == 0)
					System.out.println("Processed " + count/100 + "00 trades..");
				
				tb = new RawTrade.Builder();
				tb.timestamp(split[0]).broker(split[1]).sequenceId(split[2]).type(split[3]).symbol(split[4]).quantity(split[5]).price(split[6]).side(split[7]);
				RawTrade each = tb.build();
				
				goodrawTrades.add(each);
				count++;
			}
			System.out.println("Processed " + count + " trades..");
		} finally {
			if (scanner != null)
				scanner.close();
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		TradeParser tp = new TradeParser();
		tp.parseTrades("/Users/harishpallila/Downloads/trades.csv", true);
		System.out.println(tp.goodrawTrades.size());
		System.err.println(tp.badRawTrades);
	}
}
