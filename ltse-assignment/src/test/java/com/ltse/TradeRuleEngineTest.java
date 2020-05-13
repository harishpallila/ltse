package com.ltse;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;


public class TradeRuleEngineTest {
	
	private Trade createTrade(final String ticker, final String broker, final long seqId, final Timestamp ts) {
		Trade.Builder builder = new Trade.Builder();
		builder.symbol(ticker).broker(broker).sequenceId(seqId).timestamp(ts);
		return new Trade(builder);
	}
	
	@Test
	public void testAcceptedTickers() throws IOException {
	    File temp = File.createTempFile("tickerFile", ".tmp");
	    BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
	    bw.write("AAPL\n");
	    bw.write("GOOG\n");
	    bw.close();
		
	    TradeRuleEngine tre = new TradeRuleEngine();
	    tre.init(temp.getPath());
	    
	    Trade trade1 = createTrade("AAPL", "Fidelity", 1, new Timestamp(60));
	    Trade trade2 = createTrade("MSFT", "Fidelity", 1, new Timestamp(60));
	    
		processTrades(tre.acceptedTickers, List.of(trade1, trade2));
		
		assertEquals("Trade should be accepted", true, trade1.isAcceptedTrade());
		assertEquals("Trade should be rejected", false, trade2.isAcceptedTrade());
	}

	@Test
	public void testOrdersPerMin() throws IOException {
	    TradeRuleEngine tre = new TradeRuleEngine();
	    
	    Trade trade1 = createTrade("AAPL", "Fidelity", 1, new Timestamp(20 * 1000));
	    Trade trade2 = createTrade("MSFT", "Fidelity", 2, new Timestamp(30 * 1000));
	    Trade trade3 = createTrade("AMZN", "Fidelity", 3, new Timestamp(40 * 1000));
	    Trade trade4 = createTrade("INTL", "Fidelity", 4, new Timestamp(50 * 1000));
	    Trade trade5 = createTrade("INTL", "Fidelity", 4, new Timestamp(70 * 1000));
	    
		processTrades(tre.ordersPerMin, List.of(trade1, trade2, trade3, trade4, trade5));

		assertEquals("Trade should be accepted", true, trade1.isAcceptedTrade());
		assertEquals("Trade should be accepted", true, trade2.isAcceptedTrade());
		assertEquals("Trade should be accepted", true, trade3.isAcceptedTrade());
		assertEquals("Trade should be rejected", false, trade4.isAcceptedTrade());
		assertEquals("Trade should be accepted", true, trade5.isAcceptedTrade());
	
	}

	@Test
	public void testUniqueTradeIds() throws IOException {
	    TradeRuleEngine tre = new TradeRuleEngine();
	    
	    Trade trade1 = createTrade("AAPL", "Fidelity", 1, new Timestamp(20 * 1000));
	    Trade trade2 = createTrade("MSFT", "Fidelity", 1, new Timestamp(30 * 1000));
	    Trade trade3 = createTrade("AMZN", "Wells", 1, new Timestamp(30 * 1000));

		processTrades(tre.uniqueTradeIds, List.of(trade1, trade2, trade3));

	    assertEquals("Trade should be accepted", true, trade1.isAcceptedTrade());
	    assertEquals("Trade should be rejected", false, trade2.isAcceptedTrade());
	    assertEquals("Trade should be accepted", true, trade3.isAcceptedTrade());	
	}
	
	private void processTrades(IRule rule, List<Trade> trades) {
		for (Trade each : trades)
			rule.apply(each);	
	}
	
}
