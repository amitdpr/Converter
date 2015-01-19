package converter.regression;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import converter.config.Config;
import converter.core.SortedProperties;
import converter.exception.ConversionException;
import converter.helper.HelperService;
import converter.helper.LoggerService;
import converter.service.Converter;
import converter.service.RomanConverter;

/**
 * This is regression test class that generates all possibles numbers from 1 to
 * 3999. and verify its output and verify with regression-roman.properties file
 * located in resources folder
 * (${root}/src/test/resources/regression-roman.properties)
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 19, 2015
 */
public class RegressionRomanConverterTest {

	private static final Logger logger = LoggerService
			.getLogger(RegressionRomanConverterTest.class);

	/* Shared Converter for all test cases */
	static Converter converter = null;

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
	 * Test method for
	 * {@link converter.core.service.RomanConverter#convert(int)}.
	 * 
	 * @throws ConversionException
	 *             : If conversion fails then this exception is being thrown
	 */
	@Test
	public final void convert() throws ConversionException {

		int passCount = 0;
		int failCount = 0;

		SortedProperties properties = HelperService
				.getPropertyMap(Config.RomanRegressionFile);

		Enumeration<?> propertyName = properties.keys();

		while (propertyName.hasMoreElements()) {
			String key = (String) propertyName.nextElement();

			int number = new Integer(key).intValue();
			if (number <= 3999) {
				String resultVal = converter.convert(new Integer(key)
						.intValue());

				String expVal = properties.getProperty(key);

				// logger.log(Level.INFO,"Testing" + key + " -- " + val);
				String status = resultVal.toString().equals(expVal) ? "PASS"
						: "FAIL";
				logger.log(Level.INFO, "[" + status + "] Key:" + key
						+ " Exp-Val:" + expVal + " Res-val:" + resultVal);

				if ("PASS".equals(status))
					passCount++;
				else
					failCount++;
			}

		}
		logger.log(Level.INFO, "[PASS=" + passCount + "] [FAIL=:" + failCount
				+ "]");

	}

}
