package converter.service;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import converter.config.Config;
import converter.config.Message;
import converter.exception.WordConversionException;
import converter.helper.LoggerService;

/**
 * This class is Concrete Implementation for Number to Word conversion in
 * English language. This class creates it basic units for division, quotient
 * and reminder calculations and patch it with BaseConverter.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 19, 2015
 */
public class WordConverter extends BasicConverter {

	Logger logger = LoggerService.getLogger(WordConverter.class);

	// Create Map of WORD basic Units being used for English translation
	/**
	 * All numbers/Units that will be used to divide the number to convert in
	 * Word. It will be used to represent "quotient APPEND divisor"
	 * combinations. i.e. Number=231, Divisor=100, Quotient=2. Hence it will be
	 * "two hundred" 10 is kept as blank "" because it is never written in two
	 * digits apart from its own representation. i.e. 10: ten 80,90 is divided
	 * by ten but ten never written
	 */
	static private HashMap<Integer, String> operationalUnitMap = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;
		{
			// Basic Units
			put(10, ""); /*
						 * In decimal digits no need to print TEN as word. i.e.
						 * 21 , 23, 98,99
						 */
			put(100, "hundred");
			put(1000, "thousand");
			put(100000, "million");
		}
	};

	// Create Map of Decimal Units
	/**
	 * These numbers are being used as basic units and where ever these number
	 * comes it will return its corresponding values. i.e. 13 "thirteen"
	 * directly return.
	 */
	static private HashMap<Integer, String> displayUnitMap = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;
		{
			// Basic Units
			put(1, "one");
			put(2, "two");
			put(3, "three");
			put(4, "four");
			put(5, "five");
			put(6, "six");
			put(7, "seven");
			put(8, "eight");
			put(9, "nine");
			put(10, "ten");
			// Unique teen representation
			put(11, "eleven");
			put(12, "twelve");
			put(13, "thirteen");
			put(14, "fourteen");
			put(15, "fifteen");
			put(16, "sixteen");
			put(17, "seventeen");
			put(18, "eighteen");
			put(19, "nineteen");
			// Unique ty representaion
			put(20, "twenty");
			put(30, "thirty");
			put(40, "forty");
			put(50, "fifty");
			put(60, "sixty");
			put(70, "seventy");
			put(80, "eighty");
			put(90, "ninety");
		}
	};

	/**
	 * Default public constructor that passes Unit amps for opertaion and
	 * display to its Base Implementation.
	 */
	public WordConverter() {
		super(operationalUnitMap, displayUnitMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see converter.service.BasicConverter#fixedConversion(int, boolean)
	 */
	@Override
	protected String fixedConversion(int number, boolean intermediateConversion) {
		String convertedVal = new String();
		// Check for Zero
		if (number == 0) {
			convertedVal = " ";
		}

		return convertedVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * converter.service.BasicConverter#divisionCriteriaMatch(java.lang.Integer,
	 * int, int)
	 */
	@Override
	protected boolean divisionCriteriaMatch(Integer unit, int quotient, int rem) {
		return quotient > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * converter.service.BasicConverter#processQuotientAndDivisor(java.lang.
	 * String, int, int, boolean)
	 */
	protected String processQuotientAndDivisor(String convertedText,
			int quotient, int divisor, boolean intermediateConversion)
			throws WordConversionException {
		String convertedValue;
		/*
		 * Decimal digits should not use only reminder division like 20,21,22,
		 * up to 99 i.e. Dec=21 Unit(Divisor)=10 Rem=1 Quotient=2 It should be
		 * handled Reminder=1 but actutal numbe should be treeted as 20
		 */
		if (divisor == 10) {
			quotient = quotient * divisor;
			/*
			 * To Format 21-99 values with "and" only if it is being called
			 * indirectly and not through recusion.
			 */
			if (intermediateConversion)
				convertedText += Config.AND_SEPRATOR;

		}

		/*
		 * Trim the converted text to remove unwanted space of seprator for 2
		 * digits values from 21 to 99 As unit 10 has level blank level ""
		 */
		try {
			convertedValue = super.processQuotientAndDivisor(convertedText,
					quotient, divisor, intermediateConversion).trim();
		} catch (Exception exception) {
			String errorMessage = resource
					.getString(Message.WORD_CONVERTER_ERROR.toString())
					+ exception.getMessage();
			logger.log(Level.SEVERE, errorMessage);
			throw new WordConversionException(errorMessage, exception);
		}

		return convertedValue;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see converter.service.BasicConverter#getQuotientSeprator()
	 */
	@Override
	protected String getQuotientSeprator() {
		return Config.WORD_SEPRATOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see converter.service.BasicConverter#getReminderSeprator()
	 */
	@Override
	protected String getReminderSeprator() {
		return Config.WORD_SEPRATOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see converter.service.BasicConverter#postConvert(java.lang.String)
	 */
	@Override
	protected String postConvert(String convertedText) {
		/*
		 * This methid for future purpose to do postprocessing that is not
		 * handled on conversion
		 */

		return super.postConvert(convertedText);

	}
}
