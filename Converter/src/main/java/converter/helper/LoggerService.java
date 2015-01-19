package converter.helper;

import java.util.logging.ConsoleHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This class is Log Manager Server to create customised logger with One liner
 * Console formatter. All the logger statement in this project are created using
 * this service class.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public class LoggerService {

	/**
	 * This is Factory method to create customized Logger of different classes
	 * based on input criteria.
	 * 
	 * @param cls
	 *            : Class for which Logger to be created
	 * @return: Customised java.util.Logger with 1 line formatter
	 */
	public static Logger getLogger(Class<?> cls) {
		Logger logger = Logger.getLogger(cls.getName());
		logger.setUseParentHandlers(false);
		/* Create formatter */
		LogFormatter formatter = new LogFormatter();
		ConsoleHandler handler = new ConsoleHandler();
		/* Set formatter */
		handler.setFormatter(formatter);
		logger.addHandler(handler);
		return logger;
	}
	
	/**
	 * This will reset logger level or can off
	 */
	public static void reset(java.util.logging.Level level){
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(level);
	}
	
}
