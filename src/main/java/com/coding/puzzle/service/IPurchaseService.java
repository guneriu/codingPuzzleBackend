/**
 * 
 */
package com.coding.puzzle.service;

import com.coding.puzzle.exceptions.ForbiddenException;
import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.models.Weapon;

/**
 * @author majidali
 *
 */
public interface IPurchaseService {
	public Weapon purchaseWeapon(final String weaponId, final Player player)
			throws ForbiddenException, ResourceNotFoundException;

	public void purchaseLife(final Player player);
}
