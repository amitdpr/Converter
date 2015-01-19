package converter.service;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import converter.config.Config;
import converter.config.Message;
import converter.core.NumberFormat;
import converter.exception.ConversionError;
import converter.exception.ConversionException;
import converter.exception.ConversionInputError;
import converter.helper.HelperService;
import converter.helper.LoggerService;

/**
 * This class is service provider for number conversion. It responsible for
 * calling Converter Interface and to convert a Arabic number in to any one of
 * the following: WORD: To convert in English Language ROMAN: To convert in
 * Romans
 * 
 * Also it allows to print either converted number or series of converted
 * numbers based on number of argument passed.
 * 
 * Number Conversion (Return single converted number): If 2 arguments are given,
 * one number and other format,
 * 
 * Series Conversion (Return Comma Separated list of converted numbers between
 * requested range): If 3 arguments given, start number end number and format
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public class NumberPrinter {

	static Logger logger = LoggerService.getLogger(NumberPrinter.class);
	static ResourceBundle resource = HelperService
			.getResourceBundle(Config.MessageResourceBundle);

	// Argument list as string that will be validated at convesrion time
	static private String[] argumentList = null;

	// Inner class to store Input values
	/**
	 * This inner type hold Input arguments passed to program.
	 * 
	 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
	 */
	static class Input {
		private Integer number1;// Number to be converetd
		private Integer number2; // Series Conversion end number in case 3
		// arguments provided
		private NumberFormat format;

		Input(Integer number, NumberFormat format) {
			number1 = number;
			this.format = format;
		}

		Input(Integer number1, Integer number2, NumberFormat format) {
			this.number1 = number1;
			this.number2 = number2;
			this.format = format;
		}
	}

	/**
	 * This constructor can be used when conversion to be performed at latter
	 * stage
	 */
	public NumberPrinter() {
	}

	/**
	 * This constructor can be used when conversion parameters are known at
	 * construction.
	 * 
	 * @param number
	 *            : Number to be converted
	 */
	public NumberPrinter(int number) {
		// Conversion format can be given latter on
		argumentList = new String[] { number + "" };
	}

	/**
	 * This constructor can be used when conversion parameters are known at
	 * construction.
	 * 
	 * @param number
	 *            : Number to be converted
	 * @param format
	 *            : Format of conversion. It should be either "WORD" or "ROMAN"
	 */
	public NumberPrinter(int number, String format) {
		// Store in argument list. That will be validated on calling convert.
		argumentList = new String[] { number + "", format };
	}

	/**
	 * This constructor can be used when conversion parameters are known at
	 * construction.
	 * 
	 * @param number
	 *            : Number to be converted
	 * @param format
	 *            : Format of conversion using NumberFormat It should be either
	 *            NumberFormat.WORD or NumberFormat.ROMAN
	 */
	public NumberPrinter(int number, NumberFormat format) {
		// Store in argument list. That will be validated on calling convert.
		argumentList = new String[] { number + "", format.getFormatType() + "" };
	}

	/**
	 * To be uses for Series conversion, Comma Separated list of converted
	 * numbers Start to end series. Format can be given with convert method
	 * 
	 * @param startNumber
	 *            : Start number of series to be converted
	 * @param endNumber
	 *            : End number of series to be converted
	 */
	public NumberPrinter(int startNumber, int endNumber) {
		// Store in argument list. That will be validated on calling convert.
		argumentList = new String[] { startNumber + "", endNumber + "" };
	}

	/**
	 * To be uses for Series conversion, Comma Separated list of converted
	 * numbers. This constructor can be used when conversion parameters are
	 * known at construction.
	 * 
	 * @param startNumber
	 *            : Start number of series to be converted
	 * @param endNumber
	 *            : End number of series to be converted
	 * @param format
	 *            : Format of conversion using NumberFormat It should be either
	 *            NumberFormat.WORD or NumberFormat.ROMAN
	 */
	public NumberPrinter(int startNumber, int endNumber, String format) {
		// Store in argument list. That will be validated on calling convert.
		argumentList = new String[] { startNumber + "", endNumber + "",
				format + "" };
	}

	/**
	 * To be uses for Series conversion, Comma Separated list of converted
	 * number. This constructor can be used when conversion parameters are 
	 * known at construction.
	 * 
	 * @param startNumber
	 *            : Start number of series to be converted
	 * @param endNumber
	 *            : End number of series to be converted
	 * @param format
	 *            : Format of conversion using NumberFormat It should be either
	 *            NumberFormat.WORD or NumberFormat.ROMAN
	 */
	public NumberPrinter(int startNumber, int endNumber, NumberFormat format) {
		// Store in argument list. That will be validated on calling convert.
		argumentList = new String[] { startNumber + "", endNumber + "",
				format.getFormatType() + "" };
	}

	/**
	 * This method convert based on number and format given at construction time
	 * Non static method as it will not convert untill Object created with
	 * required numbers.
	 * 
	 * @return String WORD(ENGLISH)/ROMAN equivalent to arabicNumber
	 */
	public String convert() {
		return convert(argumentList);
	}

	/**
	 * This method convert based on number and format given at construction time
	 * Non static method as it will not convert untill Object created with
	 * required numbers.
	 * 
	 * @param conversionFormat
	 *            : To either Word or Roman
	 * @return String WORD(ENGLISH)/ROMAN equivalent to arabicNumber
	 */
	public String convert(NumberFormat conversionFormat) {
		String convertedText="";
		try{
			convertedText=convert(conversionFormat.toString());
		} catch (Exception exception) {
			String errorMessage = resource.getString(Message.SERVICE_FAILED
					.toString()) + exception.getMessage();
			logger.log(Level.SEVERE, errorMessage);
			throw new ConversionError(errorMessage, exception);
		}
		return convertedText;
	}

	/**
	 * This method convert based on number and format given at construction time
	 * 
	 * @param conversionFormat
	 *            : To either Word or Roman
	 * @return String WORD(ENGLISH)/ROMAN equivalent to arabicNumber
	 */
	public String convert(String conversionFormat) {
		String[] newArgs = null;
		if (argumentList != null) {
			newArgs = new String[argumentList.length + 1];
			for (int i = 0; i < argumentList.length; i++) {
				newArgs[i] = argumentList[i];
			}
			newArgs[newArgs.length - 1] = conversionFormat;
		}
		return convert(newArgs);
	}

	/**
	 * To be uses for Series conversion, that return comma Separated list of
	 * converted numbers.
	 * 
	 * This method convert a given Arabic number to specified format. It
	 * underlying usages Converter API to convert in specified number systems.
	 * 
	 * @param startNumber
	 *            : Start number of series to be converted
	 * @param endNumber
	 *            : End number of series to be converted
	 * @param format
	 *            : Format of conversion using NumberFormat It should be either
	 *            NumberFormat.WORD or NumberFormat.ROMAN return: Comma
	 *            Separated list of converted numbers
	 * @return: Comma Separated list of converted numbers between start and end number requested.
	 *
	 */
	public static String convert(int startNumber, int endNumber,
			String conversionFormat) {
		return convert(new String[] { startNumber + "", endNumber + "",
				conversionFormat });
	}

	/**
	 * To be uses for Series conversion, that return comma Separated list of
	 * converted numbers.
	 * 
	 * This method convert a given Arabic number to specified format. It
	 * underlying usages Converter API to convert in specified number systems.
	 * 
	 * @param startNumber
	 *            : Start number of series to be converted
	 * @param endNumber
	 *            : End number of series to be converted
	 * @param format
	 *            : Format of conversion using NumberFormat It should be either
	 *            NumberFormat.WORD or NumberFormat.ROMAN
	 * @return: Comma Separated list of converted numbers between start and end number requested.
	 */
	public static String convert(int startNumber, int endNumber,
			NumberFormat conversionFormat) {
		return convert(new String[] { startNumber + "", endNumber + "",
				conversionFormat.toString() });
	}

	/**
	 * This method convert a given Arabic number to specified format It
	 * underlying usages Converter API to convert in specified number systems.
	 * 
	 * @param arabicNumber
	 *            : Number to be converted
	 * @param conversionFormat
	 *            : To either Word or Roman
	 * @return String WORD(ENGLISH)/ROMAN equivalent to arabicNumber
	 */
	public static String convert(int arabicNumber, NumberFormat conversionFormat) {

		String convertedNumber = new String();// Blank and not null String
		// reference

		// Interface/API for highest level of Converter
		Converter converter = null;
		try {
			if (NumberFormat.WORD.equals(conversionFormat)) {
				converter = new WordConverter();
			} else if (NumberFormat.ROMAN.equals(conversionFormat)) {
				converter = new RomanConverter();
			}

			// Convert by corresponding converter
			convertedNumber = converter.convert(arabicNumber);
			logger.log(Level.INFO, MessageFormat.format(
					resource.getString(Message.SUCCESS_MESSAGE.toString()),
					arabicNumber, conversionFormat, convertedNumber));

			logger.log(Level.INFO,
					resource.getString(Message.SERVICE_SUCCESS.toString()));

		} catch (ConversionException exception) {
			String errorMessage = resource.getString(Message.SERVICE_FAILED
					.toString()) + exception.getMessage();
			logger.log(Level.SEVERE, errorMessage);
			throw new ConversionError(errorMessage, exception);
		}catch (Exception exception) {
			String errorMessage = resource.getString(Message.SERVICE_FAILED
					.toString()) + exception.getMessage();
			logger.log(Level.SEVERE, errorMessage);
			throw new ConversionError(errorMessage, exception);
		}

		return convertedNumber;
	}

	/**
	 * This method convert a given Arabic number to specified format. It
	 * underlying usages Converter API to convert in specified number systems.
	 * 
	 * @param arabicNumber
	 *            : Number to be converted
	 * @param conversionFormat
	 *            : To either Word or Roman
	 * @return String WORD(ENGLISH)/ROMAN equivalent to arabicNumber
	 */
	public static String convert(int arabicNumber, String conversionFormat) {
		return convert(new String[] { arabicNumber + "", conversionFormat });
	}

	/**
	 * This method is wrapper to convert(int,Numberformat) to do pre-processing
	 * for converting inputs to standard format and to follow input validations.
	 * 
	 * It allows either 2 or 3 arguments and work as per below behaviour.
	 * 
	 * MODE-1: Number Conversion args[0]:Mandetory.It expect Arabic Number. A
	 * number that needs to be converted. args[1]:Mandetory.It expect Conversion
	 * Format. To either Word or Roman For WORD: 1 OR WORD For ROMAN: 2 OR ROMAN
	 * 
	 * MODE-2: Series Conversion args[0]:Start-Number.Start number of series
	 * conversion. args[1]:Start-Number.End number of series conversion.
	 * args[2]:Mandetory.It expect Conversion Format. To either Word or Roman
	 * For WORD: 1 OR WORD For ROMAN: 2 OR ROMAN
	 * 
	 * @param args
	 *            : An array of input element as above
	 * 
	 * @return String WORD(ENGLISH)/ROMAN equivalent to arabicNumber
	 */
	public static String convert(String[] args) {
		String convertedText = new String();

		try{
		// Do input validation
		Input input = validate(args);

		if (input.number2 == null) {
			convertedText = convert(input.number1, input.format);
		} else {
			for (int number = input.number1; number <= input.number2; number++) {
				//For inouts with series output will be COMMA Separate string of converted numbers
				convertedText += convert(number, input.format) + ",";
			}
			// Removed last unused comma
			convertedText=convertedText.substring(0,convertedText.length()-1);
		}
		// Call convertion API
		}catch (Exception exception) {
			String errorMessage = resource.getString(Message.SERVICE_FAILED
					.toString()) + exception.getMessage();
			logger.log(Level.SEVERE, errorMessage);
			throw new ConversionError(errorMessage, exception);
		}
		
		return convertedText;

	}

	/**
	 * This method validate input and throw error if it fails. This also sends
	 * Usages message for converter.
	 * 
	 * @param args
	 */
	private static Input validate(String[] args) {
		Input input = null;// Input object to hold all input arguments
		NumberFormat numberFormat = null; // enum to hold number format
		String usagesMessage = new String();
		boolean valid = true;
		Integer number = null; // As well as can use as series start
		Integer seriesEnd = null;
		String format = new String();

		// Check only 2 or 3 arguments allowed
		if (args == null || args.length < 2 || args.length > 3) {
			usagesMessage = resource.getString(Message.CONVERTER_ERROR
					.toString())
					+ "\n"
					+ resource.getString(Message.USAGES_MESSAGES.toString());
			valid = false;
		} else {

			try {
				// Validation for Mode1(2 args: Number conversion)
				if (args.length == 2) {
					number = Integer.parseInt(args[0]);
					format = args[1];
				}

				// Series printing
				if (args.length == 3) {
					number = Integer.parseInt(args[0]);
					seriesEnd = Integer.parseInt(args[1]);
					format = args[2];
				}

				// Check if Fromat is OK
				format = format.toUpperCase().trim();

				if (NumberFormat.WORD.toString().equals(format)
						|| new String("" + NumberFormat.WORD.getFormatType())
								.equals(format)) {
					numberFormat = NumberFormat.WORD;
				} else if (NumberFormat.ROMAN.toString().equals(format)
						|| new String("" + NumberFormat.ROMAN.getFormatType())
								.equals(format)) {
					numberFormat = NumberFormat.ROMAN;
				} else {
					valid = false;
					usagesMessage += resource.getString(Message.INVALID_FORMAT
							.toString());
				}

			} catch (NumberFormatException nfe) {
				valid = false;
				usagesMessage += resource.getString(Message.INVALID_ARGUMENTS
						.toString());
			}
		}
		if (!valid) {
			String errorMessage = resource.getString(Message.SERVICE_FAILED
					.toString());
			errorMessage += usagesMessage;
			logger.log(Level.SEVERE, errorMessage);
			throw new ConversionInputError(errorMessage);
		}

		/*
		 * At this point all inputs are valid Create input inner type from
		 * NumberPrinter
		 */
		input = seriesEnd == null ? new NumberPrinter.Input(number,
				numberFormat) : new NumberPrinter.Input(number, seriesEnd,
				numberFormat);

		return input;
	}
}
