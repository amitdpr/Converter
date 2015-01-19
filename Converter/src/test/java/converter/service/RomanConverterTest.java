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
import converter.service.RomanConverter;

/**
 * This class is Sanity test class RomanConverter
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public class RomanConverterTest {

	/* Shared Converter for all test cases */
	static Converter converter = null;
	ResourceBundle resource = HelperService.getResourceBundle(Config.MessageResourceBundle);
	private static final Logger logger = LoggerService
	.getLogger(RomanConverterTest.class);
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		converter = new RomanConverter();
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
		assertTrue(converter.convert(1).equals("I"));
		assertTrue(converter.convert(5).equals("V"));
		assertTrue(converter.convert(10).equals("X"));

		// Multi digits with same digits
		assertTrue(converter.convert(2).equals("II"));
		assertTrue(converter.convert(3).equals("III"));
		assertTrue(converter.convert(20).equals("XX"));
		assertTrue(converter.convert(30).equals("XXX"));

		// Prefix conditions;
		assertTrue(converter.convert(4).equals("IV"));
		assertTrue(converter.convert(9).equals("IX"));

		// Check post cases. i.e. vi, vii, xiii
		assertTrue(converter.convert(6).equals("VI"));
		assertTrue(converter.convert(7).equals("VII"));
		assertTrue(converter.convert(13).equals("XIII"));

		// check complex cases
		assertTrue(converter.convert(14).equals("XIV"));
		assertTrue(converter.convert(15).equals("XV"));
		assertTrue(converter.convert(16).equals("XVI"));
		assertTrue(converter.convert(18).equals("XVIII"));
		assertTrue(converter.convert(19).equals("XIX"));
		assertTrue(converter.convert(20).equals("XX"));
		assertTrue(converter.convert(28).equals("XXVIII"));
		assertTrue(converter.convert(38).equals("XXXVIII"));
		assertTrue(converter.convert(39).equals("XXXIX"));
		assertTrue(converter.convert(49).equals("XLIX"));
		assertTrue(converter.convert(88).equals("LXXXVIII"));
		assertTrue(converter.convert(90).equals("XC"));
		assertTrue(converter.convert(99).equals("XCIX"));

	}

	/**
	 * Test Method for Validate from RomanConverter Test method for
	 * {@link converter.service.RomanConverter#validate(int)}.
	 * 
	 * @throws ConversionException
	 */
	@Test
	public final void testValidate() {
		// Exceptional case: unsupported range -1,0, 4000
		String message=resource.getString(Message.CONVERTER_ERROR.toString())
		+ resource.getString(Message.INVALID_ROMAN_NUMBER
				.toString());
		logger.log(Level.INFO, "Exceptional condition test for:"
				+ message);
		
		try {
			converter.convert(0);
		} catch (Exception re) {
			assertTrue(re instanceof ConversionException);
			assertEquals(
					message, re.getMessage());
		}
		
		message=resource.getString(Message.CONVERTER_ERROR.toString())
		+ resource.getString(Message.INVALID_RANGE.toString());
		logger.log(Level.INFO, "Exceptional condition test for:"
				+ message);
		try {
			converter.convert(4000);
		} catch (Exception re) {
			assertTrue(re instanceof ConversionException);
			assertEquals(message,
					re.getMessage());
		}
	}

}
