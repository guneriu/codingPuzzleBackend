/**
 * 
 */
package com.coding.puzzle.service.impl;

import static com.coding.puzzle.util.Constants.ErrorMessages.RECORD_NOT_FOUND;

import java.util.List;
import java.util.Optional;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Location;
import com.coding.puzzle.service.ILocationService;

/**
 * @author majidali
 *
 */
public class LocationService implements ILocationService {

	private List<Location> locations;

	@Override
	public List<Location> getAllLocations() {
		return locations;
	}

	@Override
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	@Override
	public Location getLocationById(String id) throws ResourceNotFoundException {
		Optional<Location> optionalLocation = locations.stream().filter(location -> location.getId().equals(id))
				.findFirst();
		if (optionalLocation.isPresent()) {
			return optionalLocation.get();
		} else {
			throw new ResourceNotFoundException(String.format(RECORD_NOT_FOUND, "Location", id));
		}
	}

}
