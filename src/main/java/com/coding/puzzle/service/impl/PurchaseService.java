/**
 * 
 */
package com.coding.puzzle.service.impl;

import static com.coding.puzzle.util.Constants.ErrorMessages.NOT_ENOUGH_BALANCE;

import com.coding.puzzle.exceptions.ForbiddenException;
import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.service.IPurchaseService;
import com.coding.puzzle.service.IWeaponService;

/**
 * @author majidali
 *
 */
public class PurchaseService implements IPurchaseService {

	private IWeaponService weaponService;

	public PurchaseService(IWeaponService weaponService) {
		this.weaponService = weaponService;
	}

	@Override
	public Weapon purchaseWeapon(final String weaponId, final Player player) throws ForbiddenException, ResourceNotFoundException {
		Weapon weapon = weaponService.getWeaponById(weaponId);
		if (weapon.getPrice() <= player.getAvailableBalance()) {
			player.setAvailableBalance(player.getAvailableBalance() - weapon.getPrice());
			return weapon;
		} else {
			throw new ForbiddenException(NOT_ENOUGH_BALANCE);
		}
	}

}
