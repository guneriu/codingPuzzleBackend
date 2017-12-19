package com.coding.puzzle.models;

/**
 * Base class to use for all models 
 * @author majidali
 *
 */
public abstract class Identifiable implements IIdentifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8227435493412570928L;
	
	private String id;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

}