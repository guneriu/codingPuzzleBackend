
package com.coding.puzzle.util.logging;

/**
 * @author majidali
 *
 */
public class ApplicationLogger implements ILogger {

	@Override
	public void log(String textToLog) {
		System.out.println(textToLog);
	}

	@Override
	public void error(String textToLog) {
		System.err.println(textToLog);
	}

}
