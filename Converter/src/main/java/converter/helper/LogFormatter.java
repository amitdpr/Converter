package converter.helper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * This class represent custom formatter used by java.util.Logger. It support 1
 * line printing as opposed to its default 2 line console printing.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public final class LogFormatter extends Formatter {

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	/**
	 * Custom behaviour of logger to change its formatting to one line in place
	 * of 2 line default formatting.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	@Override
	public String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();

		sb.append(record.getLevel().getLocalizedName()).append(": ")
				.append(formatMessage(record)).append(LINE_SEPARATOR);

		if (record.getThrown() != null) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				record.getThrown().printStackTrace(pw);
				pw.close();
				sb.append(sw.toString());
			} catch (Exception ex) {
				// ignore
			}
		}

		return sb.toString();
	}
}