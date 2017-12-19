/**
 * 
 */
package com.coding.puzzle.service;

import java.util.List;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Weapon;

/**
 * @author majidali
 *
 */
public interface IWeaponService {
			
	public List<Weapon> getAllWeapons();

	public Weapon getWeaponById(final String id) throws ResourceNotFoundException;
	
	public void displayAllWeapons();
}
