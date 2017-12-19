/**
 * 
 */
package com.coding.puzzle.exceptions;

/**
 * throw this exception if given resource not found
 * 
 * @author majidali
 *
 */
public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2257696770381415913L;

	public ResourceNotFoundException(String message) {
		super(message);
	}


}
