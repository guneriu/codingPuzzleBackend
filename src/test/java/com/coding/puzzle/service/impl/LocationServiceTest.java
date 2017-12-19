package com.coding.puzzle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Location;
import com.coding.puzzle.service.ILocationService;

public class LocationServiceTest {

	private ILocationService locationService;
	private List<Location> locations;
	private Location location;
	
	@Before
	public void setUp() throws Exception {
		locations = new ArrayList<>();
		location = new Location("1", "First Floor");
		locations.add(location);
		this.locationService = new LocationService(locations);
	}
	
	@Test
	public void testGetAllLocations() {
		List<Location> locations = locationService.getAllLocations();
		Assert.assertTrue(locations.size() == 1);
	}


	@Test(expected = ResourceNotFoundException.class)
	public void testGetLocationByIdWithInvalidId() throws ResourceNotFoundException {
		locationService.getLocationById("2");
	}

	@Test
	public void testGetWeaponByIdWithValidId() throws ResourceNotFoundException {
		Location actualLocation = locationService.getLocationById("1");
		Assert.assertNotNull(actualLocation);
		Assert.assertEquals(location.getId(), actualLocation.getId());
		Assert.assertEquals(location.getName(), actualLocation.getName());
	}

}
