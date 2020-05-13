package com.ltse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static void writeToFile(final List<Trade> result, final String filePath, final boolean asJSON) throws IOException {
		
	    final ObjectMapper mapper = new ObjectMapper();
	    final SimpleDateFormat df = new SimpleDateFormat(com.ltse.ValidatorUtil.datePattern);	 
	    mapper.setDateFormat(df);
	    
	    final BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
	    
	    try {
		    for (final Trade each : result)
		    	writer.write(asJSON ? mapper.writeValueAsString(each) + "\n" : each.resultString() + "\n");
	    } finally {
	    	writer.close();
	    }
	}


	public static void appendToFile(final List<String> result, final String filePath) throws IOException {
		
	    final ObjectMapper mapper = new ObjectMapper();
	    final SimpleDateFormat df = new SimpleDateFormat(com.ltse.ValidatorUtil.datePattern);	 
	    mapper.setDateFormat(df);
	    
	    final BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
	    
	    try {
		    for (final String each : result)
		    	writer.write(each + "\n");
	    } finally {
	    	writer.close();
	    }
	}
}
