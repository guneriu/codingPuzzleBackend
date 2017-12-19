/**
 * 
 */
package com.coding.puzzle.exceptions;

/**
 * @author majidali
 *
 */
public class InternalServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9160981585950542642L;

	public InternalServerException(String message, Exception e) {
		super(message, e);
	}

}
