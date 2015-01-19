package converter.core;

import java.io.Serializable;

/**
 * This Class Represent Object to store devision data. It is being used by
 * converter for Modular and Normal division.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public class DivisionResult implements Serializable {

	/* SerialVersionUID used for serialisation purposes */
	private static final long serialVersionUID = -762724258633849382L;
	private int divisor = -1;
	private int quotient = -1;
	private int reminder = -1;

	/**
	 * Constructor with object state of divisor , quotient and reminder.
	 * 
	 * @param divisor
	 *            : Divisor as integer
	 * @param quotaient
	 *            : Quotient after division with divisor
	 * @param reminder
	 *            : Reminder after division with divisor
	 */
	public DivisionResult(int divisor, int quotaient, int reminder) {
		this.divisor = divisor;
		this.quotient = quotaient;
		this.reminder = reminder;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DivisionResult other = (DivisionResult) obj;
		if (divisor != other.divisor)
			return false;
		if (quotient != other.quotient)
			return false;
		if (reminder != other.reminder)
			return false;
		return true;
	}

	/**
	 * @return the divisor
	 */
	public int getDivisor() {
		return divisor;
	}

	/**
	 * @return the quotient
	 */
	public int getQuotient() {
		return quotient;
	}

	/**
	 * @return the reminder
	 */
	public int getReminder() {
		return reminder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + divisor;
		result = prime * result + quotient;
		result = prime * result + reminder;
		return result;
	}

	/**
	 * @param divisor
	 *            the divisor to set
	 */
	public void setDivisor(int divisor) {
		this.divisor = divisor;
	}

	/**
	 * @param quotient
	 *            the quotient to set
	 */
	public void setQuotient(int quotient) {
		this.quotient = quotient;
	}

	/**
	 * @param reminder
	 *            the reminder to set
	 */
	public void setReminder(int reminder) {
		this.reminder = reminder;
	}

	@Override
	public String toString() {
		return "DivisionResult [divisor=" + divisor + ", quotient=" + quotient
		+ ", reminder=" + reminder + "]";
	}

}
