/**
 * 
 */
package com.coding.puzzle.service;

import java.util.List;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Location;

/**
 * @author majidali
 *
 */
public interface ILocationService {

	public List<Location> getAllLocations();

	public Location getLocationById(final String id) throws ResourceNotFoundException;
}
