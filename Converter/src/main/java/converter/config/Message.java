package converter.config;

/**
 * This is Interface for managing messages and user-text through out the
 * application. Actual values for these messages are being loaded by resources
 * bundle using resource file for Locale English, US. This class provides
 * flexibility to change message in property file without changing java code.
 * 
 * @link Please refer resource file /src/main/resources/message_en_US.properties.
 * @author : amitkumarsingapore@gmail.com Date: Jan 19, 2015
 */
public enum Message {
	/**
	 * All these messages are Key-value pairs that works as bridge between
	 * actual Caller program and Resource Bundle.
	 */
	INVALID_RANGE,
	INVALID_NUMBER,
	INVALID_ROMAN_NUMBER,
	CONVERTER_ERROR,
	SERVICE_FAILED,
	PROPERTY_LOADED,
	SERVICE_SUCCESS,
	SUCCESS_MESSAGE,
	USAGES_MESSAGES,
	INVALID_ARGUMENTS,
	INVALID_FORMAT,
	WORD_CONVERTER_ERROR
	
}
