/**
 * 
 */
package com.coding.puzzle.models;

/**
 * @author majidali
 *
 */
public class Location extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7040395906429889793L;
	
	private String name;
	
	public Location(final String id, final String name){
		this.setId(id);
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
