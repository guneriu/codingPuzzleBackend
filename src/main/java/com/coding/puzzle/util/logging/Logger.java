package com.coding.puzzle.util.logging;

/**
 * 
 * Using Dependency Inversion Principle (DIP) so that we can select different
 * type of logger any time
 * 
 * @author majidali
 *
 */
public class Logger {

	private ILogger logger;

	public Logger(ILogger logger) {
		this.logger = logger;
	}

	public void log(String textToLog) {
		logger.log(textToLog);
	}

	public void error(String textToLog) {
		logger.error(textToLog);
	}
}
