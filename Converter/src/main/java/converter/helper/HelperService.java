package converter.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import converter.config.Config;
import converter.config.Message;
import converter.core.SortedProperties;
import converter.exception.ConversionError;

/**
 * This is Helper class to support generic method shared across the project
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public class HelperService {

	private static final Logger logger = LoggerService
			.getLogger(HelperService.class);

	/**
	 * This method reads all properties in to memory and then convert them to
	 * STored Properties. Regression and sanity test case using this for loading
	 * predefined set of data
	 * 
	 * @param propertyfile
	 *            : File to be loaded and processed
	 * @return: Sorted properties KEYS in descending order
	 */
	public static SortedProperties getPropertyMap(String propertyfile) {
		SortedProperties prop = new SortedProperties();
		ResourceBundle resource = getResourceBundle(Config.MessageResourceBundle);

		// Check if Configuration file already loaded. Load it if it is not
		// already loaded.
		if(propertyfile==null){
			Config.init();
		}

		InputStream input = null;

		try {
			input = HelperService.class.getClassLoader().getResourceAsStream(
					propertyfile);

			// load a properties file
			prop.load(input);
			logger.log(Level.INFO,
					resource.getString(Message.PROPERTY_LOADED.toString())
							+ propertyfile);

			// get the property value and print it out
		} catch (IOException exception) {
			String errorMessage = resource.getString(Message.CONVERTER_ERROR
					.toString()) + exception.getMessage();
			logger.log(Level.SEVERE, errorMessage);
			// Throw Runtime error (Unchecked Exception) finaly can check in
			// Base Converter
			throw new ConversionError(errorMessage, exception);

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

	/**
	 * This method loads the resource Bundle to fetch the messages and labels
	 * from message.properties file.
	 * 
	 * @param propertyfile
	 *            : File to be loaded and processed
	 * @return All properties loaded as Resource Bundle
	 */
	public static ResourceBundle getResourceBundle(String propertyfile) {
		ResourceBundle bundle;
		try {
			// Check if Configuration file already loaded. Load it if it is not
			// already loaded.
			if(propertyfile==null){
				Config.init();
				propertyfile=Config.MessageResourceBundle;
			}

			//Portabality to load with and without .properties file extendsion
			if(propertyfile.endsWith(".properties")){
				propertyfile=propertyfile.substring(0, propertyfile.indexOf(".properties"));
			}
			
			Locale locale = new Locale("en", "US");
			bundle = ResourceBundle.getBundle(propertyfile, locale);
		} catch (Exception exception) {
			String errorMessage = exception.getMessage();
			logger.log(Level.SEVERE, errorMessage);
			// Throw Runtime error (Unchecked Exception)
			throw new ConversionError(errorMessage, exception);

		}
		return bundle;
	}

}
