package converter.client;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import converter.config.Config;
import converter.core.SortedProperties;
import converter.exception.ConversionException;
import converter.helper.HelperService;
import converter.helper.LoggerService;

import converter.service.Converter;
import converter.service.RomanConverter;

/**
 * This Clients class convert from 1 to 3999 numbers to Roman and verify with
 * regression-roman.properties file located in resources folder
 * (${root}/src/test/resources/regression-roman.properties)
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 19, 2015
 */
public class RomanConverterClient {

	private static final Logger logger = LoggerService
			.getLogger(RomanConverterClient.class);

	/**
	 * Convert numbers from 1 to 3999 and verify with pre-generated numbers in
	 * regression-roman.properties
	 * 
	 * @param args
	 *            : Not being used
	 * @throws ConversionException
	 *             : If any failure by Converter
	 */
	public static void main(String[] args) throws ConversionException {
		int passCount = 0;
		int failCount = 0;

		Converter converter = new RomanConverter();

		SortedProperties properties = HelperService
				.getPropertyMap(Config.RomanRegressionFile);

		Enumeration<?> propertyName = properties.keys();

		while (propertyName.hasMoreElements()) {
			String key = (String) propertyName.nextElement();

			String expVal = properties.getProperty(key);

			if (new Integer(key).intValue() <= 3999) {
				String resultVal = converter.convert(new Integer(key).intValue());

				// logger.log(Level.INFO,"Testing" + key + " -- " + val);
				String status = resultVal.equals(expVal) ? "PASS" : "FAIL";
				logger.log(Level.INFO, "[" + status + "] Key:" + key
						+ " Exp-Val:" + expVal + " Res-val:" + resultVal);

				if ("PASS".equals(status))
					passCount++;
				else
					failCount++;
			}

		}

		logger.log(Level.INFO, "[PASS=" + passCount + "] [FAIL=Key:"
				+ failCount + "]");

	}
}
