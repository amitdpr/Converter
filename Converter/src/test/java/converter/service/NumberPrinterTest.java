package converter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import converter.config.Config;
import converter.core.NumberFormat;
import converter.exception.ConversionError;
import converter.helper.HelperService;
import converter.helper.LoggerService;

/**
 * This class represent the Unit and Sanity Test cases for NumberPrinter.
 * 
 * Knowingly there are calls mixed with Object and static implemenations of convert method.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 19, 2015
 */
public class NumberPrinterTest {

	/* Shared Converter for all test cases */
	static NumberPrinter printer1 = null;
	static NumberPrinter printer2 = null;
	static ResourceBundle message = null;
	static ResourceBundle roman = null;
	static ResourceBundle word = null;

	private static final Logger logger = LoggerService
			.getLogger(NumberPrinterTest.class);

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.log(Level.INFO, "Testing " + NumberPrinterTest.class.getName());

		message = HelperService.getResourceBundle(Config.MessageResourceBundle);
		roman = HelperService.getResourceBundle(Config.RomanRegressionFile);
		word = HelperService.getResourceBundle(Config.WordRegressionFile);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		message = null;
		roman = null;
		word = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * This method finds multiple values from resource bundle and concatenate.
	 * 
	 * @param resource
	 *            : resource Bundle with Key value pair
	 * @param start
	 *            : Start of key as number in resource bundle
	 * @param end
	 *            : End of key as number in resource bundle
	 * @return: Comma separated resource values within range.
	 */
	private String getConcatedResource(ResourceBundle resource, int start,
			int end) {
		String result = "";
		for (int i = start; i <= end; i++) {
			result += resource.getString(i + "") + ",";
		}
		// Remove last unused comma
		result = result.substring(0, result.length() - 1);
		return result;
	}

	/**
	 * Test method for {@link converter.service.NumberPrinter#NumberPrinter()}.
	 */
	@Test
	public void testNumberPrinter() {

		// Test for default constructors
		NumberPrinter pn = new NumberPrinter();

		// No INput given
		try {
			pn.convert();
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// With only format
		try {
			pn.convert(NumberFormat.WORD);
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {
			pn.convert("ROMAN");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Unrecognized format
		try {
			pn.convert(2, "c");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Negative cases
		try {
			new NumberPrinter().convert(new String[] { 4 + "", 24 + "",
					125 + "", 126 + "", "ROMAN" });
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Negative cases
		try {
			new NumberPrinter().convert(12, "BINARY");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Proper case

		// Conversion of number using ENUM
		assertEquals(pn.convert(23, NumberFormat.ROMAN), roman.getString("23"));
		assertEquals(NumberPrinter.convert(23, NumberFormat.ROMAN),
				roman.getString("23"));
		assertEquals(pn.convert(23, NumberFormat.WORD), word.getString("23"));
		assertEquals(NumberPrinter.convert(23, NumberFormat.WORD),
				word.getString("23"));

		// Conversion of number using String
		assertEquals(pn.convert(23, "ROMAN"), roman.getString("23"));
		assertEquals(NumberPrinter.convert(23, "ROMAN"), roman.getString("23"));
		assertEquals(pn.convert(23, "WORD"), word.getString("23"));
		assertEquals(NumberPrinter.convert(23, "WORD"), word.getString("23"));

		// Conversion of number using Argument list
		assertEquals(pn.convert(new String[] { 23 + "", "ROMAN" }),
				roman.getString("23"));
		assertEquals(NumberPrinter.convert(new String[] { 23 + "", "ROMAN" }),
				roman.getString("23"));
		assertEquals(pn.convert(new String[] { 23 + "", "WORD" }),
				word.getString("23"));
		assertEquals(NumberPrinter.convert(new String[] { 23 + "", "WORD" }),
				word.getString("23"));

		// Series print
		assertEquals(pn.convert(23, 25, NumberFormat.ROMAN),
				getConcatedResource(roman, 23, 25));
		assertEquals(NumberPrinter.convert(23, 25, NumberFormat.ROMAN),
				getConcatedResource(roman, 23, 25));

		// Series Print using argument list
		assertEquals(pn.convert(new String[] { 23 + "", 24 + "", "ROMAN" }),
				getConcatedResource(roman, 23, 24));
		assertEquals(NumberPrinter.convert(new String[] { 23 + "", 24 + "",
				"ROMAN" }), getConcatedResource(roman, 23, 24));
		assertEquals(pn.convert(new String[] { 23 + "", 24 + "", "WORD" }),
				getConcatedResource(word, 23, 24));
		assertEquals(NumberPrinter.convert(new String[] { 23 + "", 24 + "",
				"WORD" }), getConcatedResource(word, 23, 24));

	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#NumberPrinter(int)}.
	 */
	@Test
	public void testNumberPrinterInt() {

		// Conversion of number using ENUM
		assertEquals(new NumberPrinter(23).convert(NumberFormat.ROMAN),
				roman.getString("23"));
		assertEquals(new NumberPrinter(23).convert(NumberFormat.WORD),
				word.getString("23"));

		// Conversion of number using String
		assertEquals(new NumberPrinter(23).convert("ROMAN"),
				roman.getString("23"));
		assertEquals(new NumberPrinter(23).convert("WORD"),
				word.getString("23"));

		// Negative cases
		try {
			new NumberPrinter(23).convert(24, 25, "ROMAN");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#NumberPrinter(int, java.lang.String)}
	 * .
	 */
	@Test
	public void testNumberPrinterIntString() {
		// Conversion of number using String
		assertEquals(new NumberPrinter(23, "ROMAN").convert(),
				roman.getString("23"));
		assertEquals(new NumberPrinter(23, "WORD").convert(),
				word.getString("23"));

		// Negative cases
		try {
			new NumberPrinter(23, "ROMAN").convert(24, "ROMAN");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {
			new NumberPrinter(23, "ROMAN").convert("ROMAN");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#NumberPrinter(int, converter.core.NumberFormat)}
	 * .
	 */
	@Test
	public void testNumberPrinterIntNumberFormat() {
		// Conversion of number using ENUM
		assertEquals(new NumberPrinter(23, NumberFormat.ROMAN).convert(),
				roman.getString("23"));
		assertEquals(new NumberPrinter(23, NumberFormat.WORD).convert(),
				word.getString("23"));

		// Negative cases
		try {
			new NumberPrinter(23, NumberFormat.ROMAN).convert(24, "ROMAN");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {
			new NumberPrinter(23, NumberFormat.ROMAN)
					.convert(NumberFormat.ROMAN);
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#NumberPrinter(int, int)}.
	 */
	@Test
	public void testNumberPrinterIntInt() {

		// Conversion of number using ENUM
		assertEquals(new NumberPrinter(123, 125).convert(NumberFormat.ROMAN),
				getConcatedResource(roman, 123, 125));
		assertEquals(new NumberPrinter(123, 125).convert(NumberFormat.WORD),
				getConcatedResource(word, 123, 125));

		// Negative cases
		try {
			new NumberPrinter(123, 125).convert(24, 25, "ROMAN");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Negative cases
		try {
			new NumberPrinter(123, 125).convert(24, "ROMAN");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Negative cases
		try {
			new NumberPrinter(123, 125).convert(new String[] { 4 + "", 24 + "",
					125 + "", 126 + "", "ROMAN" });
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Negative cases
		try {
			new NumberPrinter(123, 125).convert("BINARY");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}
	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#NumberPrinter(int, int, int)}.
	 */
	@Test
	public void testNumberPrinterIntIntInt() {

		// Conversion of number using ENUM
		assertEquals(new NumberPrinter(123, 125, "ROMAN").convert(),
				getConcatedResource(roman, 123, 125));
		assertEquals(new NumberPrinter(123, 125, "WORD").convert(),
				getConcatedResource(word, 123, 125));

		// Negative cases
		try {
			new NumberPrinter(123, 125, "ROMAN").convert(24, 25, "ROMAN");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Negative cases
		try {
			new NumberPrinter(123, 125, "WORD").convert(24, "WORD");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#NumberPrinter(int, int, converter.core.NumberFormat)}
	 * .
	 */
	@Test
	public void testNumberPrinterIntIntNumberFormat() {
		// Conversion of number using ENUM
		assertEquals(new NumberPrinter(123, 125, NumberFormat.ROMAN).convert(),
				getConcatedResource(roman, 123, 125));
		assertEquals(new NumberPrinter(123, 125, NumberFormat.WORD).convert(),
				getConcatedResource(word, 123, 125));

		// Negative cases
		try {
			new NumberPrinter(123, 125, NumberFormat.ROMAN).convert(24, 25,
					"ROMAN");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Negative cases
		try {
			new NumberPrinter(123, 125, NumberFormat.WORD).convert(24, "WORD");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Negative cases
		try {
			new NumberPrinter(123, 125, NumberFormat.WORD)
					.convert(new String[] { 4 + "", 24 + "", 125 + "",
							126 + "", "ROMAN" });
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}
	}

	/**
	 * Test method for {@link converter.service.NumberPrinter#convert()}.
	 */
	@Test
	public void testConvert() {

		// Conversion of single number
		assertEquals(new NumberPrinter(23, NumberFormat.ROMAN).convert(),
				roman.getString("23"));
		assertEquals(new NumberPrinter(23, NumberFormat.WORD).convert(),
				word.getString("23"));

		// Series Printing
		assertEquals(new NumberPrinter(123, 125, NumberFormat.ROMAN).convert(),
				getConcatedResource(roman, 123, 125));
		assertEquals(new NumberPrinter(123, 125, NumberFormat.WORD).convert(),
				getConcatedResource(word, 123, 125));

		// Negative case
		try {
			new NumberPrinter().convert();
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {
			new NumberPrinter(123).convert();
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {// Missing format
			new NumberPrinter(123, 125).convert();
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#convert(converter.core.NumberFormat)}
	 * .
	 */
	@Test
	public void testConvertNumberFormat() {
		// Conversion of single number
		assertEquals(new NumberPrinter(23).convert(NumberFormat.ROMAN),
				roman.getString("23"));
		assertEquals(new NumberPrinter(23).convert(NumberFormat.WORD),
				word.getString("23"));

		// Series Printing
		assertEquals(new NumberPrinter(123, 125).convert(NumberFormat.ROMAN),
				getConcatedResource(roman, 123, 125));
		assertEquals(new NumberPrinter(123, 125).convert(NumberFormat.WORD),
				getConcatedResource(word, 123, 125));

		// Negative case
		try {
			new NumberPrinter().convert(NumberFormat.ROMAN);
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {
			new NumberPrinter(123, 125, NumberFormat.ROMAN)
					.convert(NumberFormat.ROMAN);
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#convert(java.lang.String)}.
	 */
	@Test
	public void testConvertString() {
		// Conversion of single number
		assertEquals(new NumberPrinter(23).convert("ROMAN"),
				roman.getString("23"));
		assertEquals(new NumberPrinter(23).convert("WORD"),
				word.getString("23"));

		// Series Printing
		assertEquals(new NumberPrinter(123, 125).convert("ROMAN"),
				getConcatedResource(roman, 123, 125));
		assertEquals(new NumberPrinter(123, 125).convert("WORD"),
				getConcatedResource(word, 123, 125));

		// Negative case
		try {
			new NumberPrinter().convert("ROMAN");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {
			new NumberPrinter(123, 125, "ROMAN").convert("WORD");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {
			new NumberPrinter(123, 125).convert("BINARY");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}
	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#convert(int, converter.core.NumberFormat)}
	 * .
	 */
	@Test
	public void testConvertIntNumberFormat() {
		// Conversion of single number
		assertEquals(NumberPrinter.convert(23, NumberFormat.ROMAN),
				roman.getString("23"));
		assertEquals(NumberPrinter.convert(23, NumberFormat.WORD),
				word.getString("23"));

		// Negative case
		try {
			new NumberPrinter(23, NumberFormat.WORD).convert(23,
					NumberFormat.WORD);
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {
			new NumberPrinter(123, 125).convert(23, NumberFormat.WORD);
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#convert(int, java.lang.String)}.
	 */
	@Test
	public void testConvertIntString() {
		// Conversion of single number
		assertEquals(NumberPrinter.convert(23, "ROMAN"), roman.getString("23"));
		assertEquals(NumberPrinter.convert(23, "WORD"), word.getString("23"));

		// Negative case
		try {
			new NumberPrinter(23, "WORD").convert(23, "WORD");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {
			new NumberPrinter(123, 125).convert(23, "WORD");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#convert(java.lang.String[])}.
	 */
	@Test
	public void testConvertStringArray() {

		// Conversion of number using Argument list
		assertEquals(
				NumberPrinter.convert(new String[] { 23 + "",
						NumberFormat.ROMAN.toString() }), roman.getString("23"));
		assertEquals(
				NumberPrinter.convert(new String[] { 23 + "",
						NumberFormat.WORD.toString() }), word.getString("23"));

		assertEquals(NumberPrinter.convert(new String[] { 23 + "", "ROMAN" }),
				roman.getString("23"));
		assertEquals(NumberPrinter.convert(new String[] { 23 + "", "WORD" }),
				word.getString("23"));

		assertEquals(NumberPrinter.convert(new String[] { 23 + "", 24 + "",
				"ROMAN" }), getConcatedResource(roman, 23, 24));
		assertEquals(NumberPrinter.convert(new String[] { 23 + "", 24 + "",
				"WORD" }), getConcatedResource(word, 23, 24));

		// Negative cases
		try {
			new NumberPrinter(123, 125, NumberFormat.WORD)
					.convert(new String[] { 4 + "", 24 + "", 125 + "",
							126 + "", NumberFormat.WORD.toString() });
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Negative cases
		try {
			new NumberPrinter(123, 125).convert(new String[] { 4 + "", 24 + "",
					125 + "", 126 + "", "ROMAN" });
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		// Negative cases
		try {
			new NumberPrinter().convert(new String[] { 4 + "", 24 + "",
					125 + "", 126 + "", NumberFormat.WORD.toString() });
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}
	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#convert(int, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testConvertIntIntString() {
		// Conversion of single number
		assertEquals(NumberPrinter.convert(23, 25, "ROMAN"),
				getConcatedResource(roman, 23, 25));
		assertEquals(NumberPrinter.convert(23, 25, NumberFormat.WORD),
				getConcatedResource(word, 23, 25));

		// Negative case
		try {
			new NumberPrinter(23, NumberFormat.WORD).convert(23, 25, "ROMAN");
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {
			new NumberPrinter(123).convert(23, 25, NumberFormat.WORD);
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

	}

	/**
	 * Test method for
	 * {@link converter.service.NumberPrinter#convert(int, int, converter.core.NumberFormat)}
	 * .
	 */
	@Test
	public void testConvertIntIntNumberFormat() {
		// Conversion of single number
		assertEquals(NumberPrinter.convert(23, 25, NumberFormat.ROMAN),
				getConcatedResource(roman, 23, 25));
		assertEquals(NumberPrinter.convert(23, 25, NumberFormat.WORD),
				getConcatedResource(word, 23, 25));

		// Negative case
		try {
			new NumberPrinter(23, NumberFormat.WORD).convert(23, 25,
					NumberFormat.ROMAN);
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

		try {
			new NumberPrinter(123).convert(23, 25, NumberFormat.WORD);
		} catch (Exception e) {
			assertTrue(e instanceof ConversionError);
		}

	}

}
