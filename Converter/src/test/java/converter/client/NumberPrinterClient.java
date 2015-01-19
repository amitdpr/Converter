package converter.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import converter.core.NumberFormat;
import converter.helper.LoggerService;
import converter.service.NumberPrinter;

/**
 * This is client program to test NumberPrinter
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public class NumberPrinterClient {

	private static final Logger logger = LoggerService
			.getLogger(NumberPrinterClient.class);

	public static void main(String[] args) throws NumberFormatException,
			Exception {

		// Using
		int number = 45;

		// COnstructor with NUMBER,ENUM
		String word;
		String roman;

		// Static calls
		word = NumberPrinter.convert(number, NumberFormat.WORD);
		roman = NumberPrinter.convert(number, NumberFormat.ROMAN);
		logger.log(Level.INFO, "Case1.1:" + number + ", " + word + ", " + roman);

		// Object Calls
		word = new NumberPrinter(number, NumberFormat.WORD).convert();
		roman = new NumberPrinter(number, NumberFormat.ROMAN).convert();
		logger.log(Level.INFO, "Case1.2:" + number + ", " + word + ", " + roman);

		// COnstructor with NUMBER,STRING
		word = new NumberPrinter(number, "WORD").convert();
		roman = new NumberPrinter(number, "ROMAN").convert();
		logger.log(Level.INFO, "Case2:" + number + ", " + word + ", " + roman);

		// Default Constructor, in place this should use Static Invocations.
		// word=new NumberPrinter().convert(number,NumberFormat.WORD);
		// roman=new NumberPrinter().convert(number,NumberFormat.ROMAN);
		// logger.log(Level.INFO,"Case3:"+number+", "+word +", "+ roman);

		// NUmber initially and Format latter
		word = new NumberPrinter(number).convert(NumberFormat.WORD);// Error
																	// condition
		roman = new NumberPrinter(number).convert(NumberFormat.ROMAN);// Error
																		// condition
		logger.log(Level.INFO, "Case5:" + number + ", " + word + ", " + roman);

		// Number initially and Format latter for series Printing
		word = new NumberPrinter(number, number + 1).convert(NumberFormat.WORD);// Error
																				// condition
		logger.log(Level.INFO, "Case6:" + number + ", " + word);
		roman = new NumberPrinter(number, number + 1)
				.convert(NumberFormat.ROMAN);// Error condition
		logger.log(Level.INFO, "Case7:" + number + ", " + roman);

	}
}
