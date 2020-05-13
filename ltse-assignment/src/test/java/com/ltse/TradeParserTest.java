package com.ltse;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class TradeParserTest {

	@Test
	public void testWithoutHeader() throws IOException {
	    File temp = writeFile(false);
	    
	    TradeParser tp = new TradeParser();
	    tp.parseTrades(temp.getPath(), false);
	    
	    assertEquals("good trade count failed", 2, tp.getGoodrawTrades().size());
	    assertEquals("bad trade count failed", 1, tp.getBadRawTrades().size());
	}

	@Test
	public void testWithHeader() throws IOException {
	    File temp = writeFile(true);
	    
	    TradeParser tp = new TradeParser();
	    tp.parseTrades(temp.getPath(), true);
	    
	    assertEquals("good trade count failed", 2, tp.getGoodrawTrades().size());
	    assertEquals("bad trade count failed", 1, tp.getBadRawTrades().size());
	}

	private File writeFile(boolean addHeader) throws IOException {
		File temp = File.createTempFile("trades", ".tmp");
	    BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
	    
	    if (addHeader)
	    	bw.write("timestamp,broker,sequenceid,type,symbol,quantity,price,side\n");
	    bw.write("10/5/2017 10:00:00,Fidelity,1,2,BARK,100,1.195,Buy\n");
	    bw.write("10/5/2017 10:00:01,Charles Schwab,1,2,CARD,200,6.855,Sell\n");
	    bw.write("10/5/2017 10:00:03,Wells,1,2,CARD,6.855,Sell\n");
	    bw.close();
	    
		return temp;
	}
}
