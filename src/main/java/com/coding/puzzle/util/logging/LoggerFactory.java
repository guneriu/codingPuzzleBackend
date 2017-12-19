package com.coding.puzzle.util.logging;

/**
 * This class exhibits the Factory Design Pattern.
 * 
 * @author majidali
 *
 */
public class LoggerFactory {

	private LoggerFactory() {
	}

	// Thread-Safe Singleton
	private static class SingletonHelper {
		private static Logger LOGGER = new Logger(new ApplicationLogger());
	}

	public static Logger getLogger() {
		return SingletonHelper.LOGGER;
	}

}
