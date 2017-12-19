/**
 * 
 */
package com.coding.puzzle.util.input;

import java.util.function.Predicate;

/**
 * Using Dependency Inversion Principle (DIP) so that we can select different
 * type of input reader any time
 * 
 * @author majidali
 *
 */
public class InputReader {

	private IInputReader inputReader;

	public InputReader(IInputReader inputReader) {
		this.inputReader = inputReader;
	}

	public String readString() {
		return inputReader.readString();
	}

	public int readIntegerUntil(Predicate<String> userCondition, Runnable onFail) {
		return inputReader.readIntegerUntil(userCondition, onFail);
	}
}
