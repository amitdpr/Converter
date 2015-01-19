package converter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import converter.config.Config;
import converter.config.Message;
import converter.exception.ConversionException;
import converter.helper.HelperService;
import converter.helper.LoggerService;

/**
 * This class is Sanity test class WordConverter
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public class WordConverterTest {

	/* Shared Converter for all test cases */
	static Converter converter = null;
	ResourceBundle resource = HelperService
			.getResourceBundle(Config.MessageResourceBundle);
	private static final Logger logger = LoggerService
			.getLogger(WordConverterTest.class);

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		converter = new WordConverter();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		converter = null;
	}

	/**
	 * Test Method for Convert from RomanConverter
	 * 
	 * @throws ConversionException
	 */
	@Test
	public final void testConvert() throws ConversionException {

		// Single digits
		assertTrue(converter.convert(1).equals("one"));
		assertTrue(converter.convert(5).equals("five"));
		assertTrue(converter.convert(10).equals("ten"));

		// Up to 20
		assertTrue(converter.convert(11).equals("eleven"));
		assertTrue(converter.convert(19).equals("nineteen"));
		assertTrue(converter.convert(20).equals("twenty"));

		// tens number up to hundred
		assertTrue(converter.convert(30).equals("thirty"));
		assertTrue(converter.convert(40).equals("forty"));
		assertTrue(converter.convert(90).equals("ninety"));

		// other number up to hundred
		assertTrue(converter.convert(28).equals("twenty eight"));
		assertTrue(converter.convert(85).equals("eighty five"));
		assertTrue(converter.convert(99).equals("ninety nine"));

		// HUndred chceks
		assertTrue(converter.convert(100).equals("one hundred"));
		assertTrue(converter.convert(120).equals("one hundred twenty"));
		assertTrue(converter.convert(127)
				.equals("one hundred and twenty seven"));
		assertTrue(converter.convert(929)
				.equals("nine hundred and twenty nine"));

		// Thousands check
		assertTrue(converter.convert(1000).equals("one thousand"));
		assertTrue(converter.convert(1137).equals(
				"one thousand one hundred and thirty seven"));
		assertTrue(converter.convert(2679).equals(
				"two thousand six hundred and seventy nine"));
		assertTrue(converter.convert(3990).equals(
				"three thousand nine hundred ninety"));
	}

	/**
	 * Test Method for Validate RomanConverter number Test method for
	 * {@link converter.service.WordConverter#validate(int)}.
	 * 
	 * @throws ConversionException
	 */
	@Test
	public final void testValidate() {
		// Exceptional case: unsupported range -1,0, 4000
		String exceptionalCondition = resource
				.getString(Message.CONVERTER_ERROR.toString())
				+ resource.getString(Message.INVALID_RANGE.toString());
		logger.log(Level.INFO, "Exceptional condition test for:"
				+ exceptionalCondition);
		try {
			converter.convert(0);
		} catch (Throwable re) {
			assertTrue(re instanceof ConversionException);
			assertEquals(exceptionalCondition, re.getMessage());
		}
		
		exceptionalCondition = resource.getString(Message.CONVERTER_ERROR
				.toString())
				+ resource.getString(Message.INVALID_RANGE.toString());
		logger.log(Level.INFO, "Exceptional condition test for:"
				+ exceptionalCondition);
		try {
			
			converter.convert(4000);
		} catch (Throwable re) {
			assertTrue(re instanceof ConversionException);
			assertEquals(exceptionalCondition, re.getMessage());
		}
	}

}
