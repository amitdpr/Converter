package converter.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import converter.helper.LoggerService;
/**
 * This is Junit Test Suite combining all test cases.
 * 
 * @author : amitkumarsingapore@gmail.com
 * Date: Jan 19, 2015
 */

@RunWith(Suite.class)
//Test class names
@Suite.SuiteClasses({
	
	//Test classes to be added in suite
	converter.service.NumberPrinterTest.class,
	converter.service.WordConverterTest.class,
	converter.service.RomanConverterTest.class,
	converter.util.UtilityServiceTest.class,
	converter.helper.HelperServiceTest.class,
	
	//Regression test
	converter.regression.RegressionRomanConverterTest.class,
	converter.regression.RegressionWordConverterTest.class
})
public class JunitTestSuite {  
	//To display log information for testing
	static {
	final Logger logger = LoggerService
	.getLogger(JunitTestSuite.class);

	logger.log(Level.INFO, "Testing " + JunitTestSuite.class.getName());
	}
	
}  