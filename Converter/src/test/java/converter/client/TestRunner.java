package converter.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import converter.helper.LoggerService;

/**
 * TestRunner responsible to run test suite.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 19, 2015
 */
public class TestRunner {

	private static final Logger logger = LoggerService
			.getLogger(TestRunner.class);

	/**
	 * This main method to run to call Test Suite of all test cases
	 * 
	 * @param args
	 *            : Arguments are not being used
	 */
	public static void main(String[] args) {
		//Set logge roff
		//LoggerService.reset(java.util.logging.Level.OFF);
		
		Result result = JUnitCore.runClasses(JunitTestSuite.class);
		for (Failure failure : result.getFailures()) {
			logger.log(Level.INFO, failure.toString());
		}
		int sucessCount = result.getRunCount() - result.getIgnoreCount()
				- result.getFailureCount();

		// Print all failures
		List<Failure> failureList = result.getFailures();
		for (Failure fail : failureList) {
			String failMessage = fail.toString() + ", Message:"
					+ fail.getMessage() + ", \n Stack Trace:" + fail.getTrace();

			logger.log(Level.INFO, failMessage);
		}
		logger.log(Level.INFO,
				"Total:" + result.getRunCount() + "," + "Sucess:" + sucessCount
						+ "," + "Failures:" + result.getFailureCount() + ","
						+ "Ignore:" + result.getIgnoreCount());

	}
}