/**
 * 
 */
package com.coding.puzzle.util.input;

import java.util.function.Predicate;

/**
 * @author majidali
 *
 */
public interface IInputReader {
	public String readString();
	public int readIntegerUntil(Predicate<String> userCondition, Runnable onFail);

}
