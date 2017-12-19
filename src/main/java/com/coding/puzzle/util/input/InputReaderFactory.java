/**
 * 
 */
package com.coding.puzzle.util.input;

/**
 * @author majidali
 *
 */
public class InputReaderFactory {

	private InputReaderFactory() {
	}

	// Thread-Safe Singleton
	private static class SingletonHelper {
		private static InputReader READER = new InputReader(new ApplicationInputReader());
	}

	public static InputReader getInputReader() {
		return SingletonHelper.READER;
	}
}