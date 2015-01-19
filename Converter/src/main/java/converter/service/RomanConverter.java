package converter.service;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import converter.config.Config;
import converter.config.Message;
import converter.exception.ConversionException;
import converter.exception.RomanConversionException;
import converter.helper.LoggerService;
import converter.util.UtilityService;

/**
 * This class is Concrete Implementation for Number to Roman conversion. This
 * class creates it basic units for division, quotient and reminder calculations
 * and patch it with BaseConverter.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public class RomanConverter extends BasicConverter {

	Logger logger = LoggerService.getLogger(RomanConverter.class);

	// Create KeyValue-set of Roman Basic Units
	/**
	 * Basic Unit map for Roman numbers is from Roman individual char I,V,X etc.
	 * There are only few/given combinations in Roman where prefix can create
	 * corresponding Romans in place of normal Suffixing solution. To handle
	 * this situation, All these combinations IV,IX,XL,XC,CD, CM added as basic
	 * unit.
	 */
	static HashMap<Integer, String> operationalUnitMap = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;
		{
			// Basic Units
			put(1, "I");
			put(5, "V");
			put(10, "X");
			put(50, "L");
			put(100, "C");
			put(500, "D");
			put(1000, "M");

			/**
			 * Complex Units: I can be placed before V and X to make 4 units
			 * (IV) and 9 units (IX) respectively X can be placed before L and C
			 * to make 40 (XL) and 90 (XC) respectively C can be placed before D
			 * and M to make 400 (CD) and 900 (CM) respectively
			 */
			put(4, "IV");
			put(9, "IX");
			put(40, "XL");
			put(90, "XC");
			put(400, "CD");
			put(900, "CM");

		}
	};

	/**
	 * Default RomanConverter that plug the basic unit in to its base
	 * constructor implementation.
	 * 
	 * @param operationalUnitMap
	 */
	public RomanConverter() {
		super(operationalUnitMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see converter.service.BasicConverter#fixedConversion(int, boolean)
	 */
	@Override
	protected String fixedConversion(int number, boolean intermediateConversion) {
		String convertedValue = new String("");
		// Check if Zero is intermediately expression. Return as it is lowest
		// expression.
		if (number == Config.ZERO)
			convertedValue = Config.WORD_SEPRATOR; //i.e. WIth SPACE
		// else if(decimal==9) return "IX";/* Not needed this logic as placed
		// IX, IV etc as base unit*/
		return convertedValue;// WIthoutspace
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
			throws RomanConversionException {
		// Get Roman Char from divisor (Max one) i.e. for 6 it will retrun 5
		String divisorStr;
		try {
			divisorStr = convert(divisor);
		} catch (Exception exception) {
			String errorMessage = "Roman Converter Error:"
					+ exception.getMessage();
			logger.log(Level.SEVERE, errorMessage);
			throw new RomanConversionException(errorMessage, exception);
		}

		/*
		 * Quotient should not be more then 3. i.e. a number can not repeated
		 * more then 3 times In this case it move to NextUnit with differences.
		 * i.e. for Dec=4 Unit =1(I) will return quotient =4:NOT CORRECT Move to
		 * next Unit AND Markas Prefix Next_Unit =5(V) and Diff=Next_Unit -
		 * Decimal= 5-4=1 Now process Further same in recursion
		 */

		/*
		 * divisor should be reapted quoteient times i.e. for 21 div=10 and
		 * quot=2.Hence it should be repeatt 2 time
		 */
		// Quoteient will be alwyes >0 i.e. Decimal=20 Quotient=2
		// Suffix its Roman equiv of reminder
		convertedText = UtilityService.fillStr(divisorStr, quotient);

		return convertedText;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see converter.service.BasicConverter#getQuotientSeprator()
	 */
	@Override
	protected String getQuotientSeprator() {
		return Config.NO_CONNECTOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see converter.service.BasicConverter#getReminderSeprator()
	 */
	@Override
	protected String getReminderSeprator() {
		return Config.NO_CONNECTOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see converter.service.BasicConverter#postConvert(java.lang.String)
	 */
	@Override
	protected String postConvert(String convertedText) {
		/*
		 * This method for future purpose to do postprocessing that is not
		 * handled on conversion
		 */

		return super.postConvert(convertedText);
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
		return quotient > 0 && quotient <= 3;
	}

	/**
	 * This Method perform input validations It should be overridden by its
	 * concrete class to put more specific validations.
	 * 
	 * @param number
	 *            : Number to be converted as integer
	 * @return : True if valid number as per Impmlenting Number System.
	 * @throws ConversionException
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see converter.service.BasicConverter#validate(int)
	 */
	public void validate(int number) throws ConversionException {
		if (number == Config.ZERO) {
			throw new RomanConversionException(
					resource.getString(Message.INVALID_ROMAN_NUMBER.toString()));
		} else {
			// For Heigher end validation i.e. MAX RANGE to call base validation
			super.validate(number);
		}
	}
}
