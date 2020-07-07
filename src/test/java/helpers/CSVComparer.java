package helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class CSVComparer {

	    private static final char DEFAULT_SEPARATOR = ',';
	    private static final char DEFAULT_QUOTE = '"';

	    public static void CSVResults(String CSVActual, String CSVExpected, String CSVResults) throws Exception {

	     

	        Scanner scannerActual = new Scanner(new File(CSVActual));
	        Scanner scannerExpected = new Scanner(new File(CSVExpected));
	        FileWriter fileWriter = new FileWriter(CSVResults);

            int line = 0;
            List<String> lineExpected = null;
        	List<String> lineActual = null;
        	String resultString;
	        while (scannerExpected.hasNext()) {	
	        	lineExpected = parseLine(scannerExpected.nextLine());
	            lineActual = parseLine(scannerActual.nextLine());
	            if(line == 0) {
	            resultString = "Expected_" + lineExpected.get(0).toString() + ", " +"Actual_"+ lineActual.get(0).toString();
	            for(int lineCount = 1 ; lineCount < lineExpected.size(); lineCount++) {
	            	System.out.println(lineExpected.get(lineCount).toString());
	            	System.out.println(lineActual.get(lineCount).toString());
	                resultString = resultString + "," + "Expected_" + lineExpected.get(lineCount).toString() + ", " + "Actual_" +lineActual.get(lineCount).toString();
	                line = line + 1;
	            }
	            } else {
	            resultString = lineExpected.get(0).toString() + ", " + lineActual.get(0).toString();
	            for(int lineCount = 1 ; lineCount < lineExpected.size(); lineCount++) {
	            	System.out.println(lineExpected.get(lineCount).toString());
	            	System.out.println(lineActual.get(lineCount).toString());
	                resultString = resultString + ","  + lineExpected.get(lineCount).toString() + ", " + lineActual.get(lineCount).toString();
	            }
	            }
	            fileWriter.append(resultString);
	            fileWriter.append('\n'); 
	        }
	        scannerExpected.close();
	        scannerActual.close();
	        fileWriter.flush();
	        fileWriter.close();
	        

	    }

	    public static List<String> parseLine(String cvsLine) {
	        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
	    }

	    public static List<String> parseLine(String cvsLine, char separators) {
	        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
	    }

	    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

	        List<String> result = new ArrayList<>();

	        //if empty, return!
	        if (cvsLine == null && cvsLine.isEmpty()) {
	            return result;
	        }

	        if (customQuote == ' ') {
	            customQuote = DEFAULT_QUOTE;
	        }

	        if (separators == ' ') {
	            separators = DEFAULT_SEPARATOR;
	        }

	        StringBuffer curVal = new StringBuffer();
	        boolean inQuotes = false;
	        boolean startCollectChar = false;
	        boolean doubleQuotesInColumn = false;

	        char[] chars = cvsLine.toCharArray();

	        for (char ch : chars) {

	            if (inQuotes) {
	                startCollectChar = true;
	                if (ch == customQuote) {
	                    inQuotes = false;
	                    doubleQuotesInColumn = false;
	                } else {

	                    //Fixed : allow "" in custom quote enclosed
	                    if (ch == '\"') {
	                        if (!doubleQuotesInColumn) {
	                            curVal.append(ch);
	                            doubleQuotesInColumn = true;
	                        }
	                    } else {
	                        curVal.append(ch);
	                    }

	                }
	            } else {
	                if (ch == customQuote) {

	                    inQuotes = true;

	                    //Fixed : allow "" in empty quote enclosed
	                    if (chars[0] != '"' && customQuote == '\"') {
	                        curVal.append('"');
	                    }

	                    //double quotes in column will hit this!
	                    if (startCollectChar) {
	                        curVal.append('"');
	                    }

	                } else if (ch == separators) {

	                    result.add(curVal.toString());

	                    curVal = new StringBuffer();
	                    startCollectChar = false;

	                } else if (ch == '\r') {
	                    //ignore LF characters
	                    continue;
	                } else if (ch == '\n') {
	                    //the end, break!
	                    break;
	                } else {
	                    curVal.append(ch);
	                }
	            }

	        }

	        result.add(curVal.toString());

	        return result;
	    }

	}

