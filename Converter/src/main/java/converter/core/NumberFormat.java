package converter.core;

/**
 * This NumberFormat Type hold format type of conversion.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public enum NumberFormat {

	WORD(1), ROMAN(2);

	private int formatType;// Format type i.e. WORD OR ROMAN

	NumberFormat(int formatType) {
		this.formatType = formatType;
	}

	/**
	 * Getter method for format type.
	 * 
	 * @return the formatType
	 */
	public int getFormatType() {
		return formatType;
	}
};


