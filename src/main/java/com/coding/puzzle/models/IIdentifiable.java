package com.coding.puzzle.models;

import java.io.Serializable;

/**
 * @author majidali
 *
 */
public interface IIdentifiable extends Serializable {

	public String getId();
	
	public void setId(String id);
}
