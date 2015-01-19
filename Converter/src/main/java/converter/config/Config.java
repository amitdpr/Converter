package converter.config;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import converter.exception.ConversionError;
import converter.helper.LoggerService;

/**
 * This Class is Interface for Constants being used throughout the systems and
 * thier respective values in constant file named configuration.properties. It
 * Loads all configuration
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 19, 2015
 */
public class Config {
	private static final Logger logger = LoggerService.getLogger(Config.class);

	/**
	 * To ensure that all configuration loaded exactly once
	 */
	static boolean configLoaded = false;

	/**
	 * Maximum number allowed by converter
	 */
	public static int MAX_NUMBER_RANGE;

	/**
	 * Minimum number allowed by converter
	 */
	public static int MIN_NUMBER_RANGE;

	/**
	 * Various resource bundle properties
	 */
	public static String MessageResourceBundle; // ResourceBundle
	public static String WordRegressionFile;
	public static String RomanRegressionFile;

	/**
	 * Constants being used throughout the systems
	 */
	public static String AND_SEPRATOR;
	public static String WORD_SEPRATOR;
	public static String NO_CONNECTOR;
	public static int ZERO;

	/**
	 * This method initialise all the configuration parameter by reader from
	 * property file config.properties.
	 */
	public static void init() {

		// No action needed if configuration is already loaded.
		if (configLoaded)
			return;

		try {

			ResourceBundle rb = ResourceBundle.getBundle("configuration");

			MAX_NUMBER_RANGE = Integer.parseInt(rb
					.getString("MAX_NUMBER_RANGE"));
			MIN_NUMBER_RANGE = Integer.parseInt(rb
					.getString("MIN_NUMBER_RANGE"));
			MessageResourceBundle = rb.getString("MessageResourceBundle");
			WordRegressionFile = rb.getString("WordRegressionFile");
			RomanRegressionFile = rb.getString("RomanRegressionFile");

			// constants for formatting numbers
			AND_SEPRATOR = rb.getString("AND_SEPRATOR");
			WORD_SEPRATOR = rb.getString("WORD_SEPRATOR");
			NO_CONNECTOR = rb.getString("NO_CONNECTOR");
			ZERO = Integer.parseInt(rb.getString("ZERO"));

			logger.log(Level.INFO, "Master Configuration loaded");
			logger.log(Level.INFO, "[MAX_NUMBER_RANGE:" + MAX_NUMBER_RANGE
					+ "]");
			logger.log(Level.INFO, "[MIN_NUMBER_RANGE:" + MIN_NUMBER_RANGE
					+ "]");
			logger.log(Level.INFO, "[MessageResourceBundle:"
					+ MessageResourceBundle + "]");
			logger.log(Level.INFO, "[WordRegressionFile:" + WordRegressionFile
					+ "]");
			logger.log(Level.INFO, "[RomanRegressionFile:"
					+ RomanRegressionFile + "]");
			logger.log(Level.INFO, "[AND_SEPRATOR:" + AND_SEPRATOR + "]");
			logger.log(Level.INFO, "[WORD_SEPRATOR:" + WORD_SEPRATOR + "]");
			logger.log(Level.INFO, "[NO_CONNECTOR:" + NO_CONNECTOR + "]");
			logger.log(Level.INFO, "[ZERO:" + ZERO + "]");

			configLoaded = true;

		} catch (Exception exception) {
			String errorMessage = exception.getMessage();
			logger.log(Level.SEVERE, errorMessage);
			// Throw Runtime error (Unchecked Exception)
			throw new ConversionError(errorMessage, exception);

		}

	}

}
