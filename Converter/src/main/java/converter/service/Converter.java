package converter.service;

import converter.exception.ConversionException;

/**
 * This interface is basic service interface and genric contract "convert".
 * 
 * @author : amitkumarsingapore@gmail.com
 * Date: Jan 18, 2015
 */
public interface Converter {

	/**
	 * This Method convert given input number to another format implemented by
	 * its concrete class.
	 * 
	 * @param number
	 *            : Number to be converted as integer
	 * @return : Converted number as String.
	 * @throws ConversionException
	 *             : If validation failed or any un-expected error occurs.
	 */
	public String convert(int number) throws ConversionException;

}
