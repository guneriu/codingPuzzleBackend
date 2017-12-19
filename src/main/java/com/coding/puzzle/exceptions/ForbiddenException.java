/**
 * 
 */
package com.coding.puzzle.exceptions;

/**
 * throw this exception if user is not allowed for an action
 * 
 * @author majidali
 *
 */
public class ForbiddenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1303722602877378230L;
	
	public ForbiddenException(String message) {
		super(message);
	}

}
