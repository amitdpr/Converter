package converter.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import converter.config.Config;
import converter.config.Message;
import converter.exception.ConversionError;
import converter.helper.HelperService;
import converter.helper.LoggerService;

/**
 * This class is Represent Sorted Properties Data structure. It Sort underlying
 * properties based on Key names. Sorting order is Descending (that is being
 * used by conversion programs to store units key values and process them in
 * highest divisor order.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public class SortedProperties extends Properties implements Serializable {
	
	public SortedProperties(){
		
	}
	
	@Override
	public String toString() {
		return "SortedProperties [defaults=" + defaults + "]";
	}

	private static final long serialVersionUID = 1L;

	private ResourceBundle resource = HelperService
			.getResourceBundle(Config.MessageResourceBundle);
	Logger logger = LoggerService.getLogger(SortedProperties.class);

	public Enumeration<Object> keys() {

		// Get the preoprties keys
		Enumeration<Object> keysEnum = super.keys();
		List<Object> keyList = new ArrayList<Object>();

		// Add all keys to list
		try {
			while (keysEnum.hasMoreElements()) {
				keyList.add(keysEnum.nextElement());
			}

			/*
			 * Sort the keys of properties in descending order
			 */
			Collections.sort(keyList, new Comparator<Object>() {
				@Override
				public int compare(Object o1, Object o2) {
					return new Integer(o1.toString()).compareTo(new Integer(o2
							.toString()));
				}
			});
		} catch (Exception exception) {
			String errorMessage = resource.getString(Message.CONVERTER_ERROR
					.toString()) + exception.getMessage();
			logger.log(Level.SEVERE, errorMessage);
			// Throw Runtime error (Unchecked Exception) finaly can check in
			// Base Converter
			throw new ConversionError(errorMessage, exception);
		}

		return Collections.enumeration(keyList);
	}
}