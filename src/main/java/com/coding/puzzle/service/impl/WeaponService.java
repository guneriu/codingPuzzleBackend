/**
 * 
 */
package com.coding.puzzle.service.impl;

import static com.coding.puzzle.util.Constants.ErrorMessages.RECORD_NOT_FOUND;

import java.util.List;
import java.util.Optional;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.service.IWeaponService;
import com.coding.puzzle.util.Constants;
import com.coding.puzzle.util.logging.Logger;
import com.coding.puzzle.util.logging.LoggerFactory;

/**
 * @author majidali
 *
 */
public class WeaponService implements IWeaponService {

	private static Logger logger = LoggerFactory.getLogger();

	private List<Weapon> weapons;

	@Override
	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}

	@Override
	public Weapon getWeaponById(final String id) throws ResourceNotFoundException {
		Optional<Weapon> optionalWeapon = weapons.stream().filter(weapon -> weapon.getId().equals(id)).findFirst();
		if (optionalWeapon.isPresent()) {
			return optionalWeapon.get();
		} else {
			throw new ResourceNotFoundException(String.format(RECORD_NOT_FOUND, "Weapon", id));
		}
	}

	@Override
	public List<Weapon> getAllWeapons() {
		return this.weapons;
	}

	@Override
	public void displayAllWeapons() {
		// Print the list objects in tabular format.
		logger.log(String.format(Constants.WEAPON_TABLE_FORMAT, "ID", "Name", "Price", "KillAward"));
		for (Weapon weapon : weapons) {
			logger.log(String.format(Constants.WEAPON_TABLE_FORMAT, weapon.getId(), weapon.getName(),
					(Constants.DEFAULT_CURRENCY + weapon.getPrice()),
					(Constants.DEFAULT_CURRENCY + weapon.getKillAward())));
		}
	}
}
