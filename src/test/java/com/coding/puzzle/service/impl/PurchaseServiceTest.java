package com.coding.puzzle.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.coding.puzzle.exceptions.ForbiddenException;
import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.service.IPurchaseService;
import com.coding.puzzle.service.IWeaponService;

public class PurchaseServiceTest {

	IPurchaseService purchaseService;

	@Before
	public void setUp() throws Exception {
		IWeaponService weaponService = Mockito.mock(WeaponService.class);
		this.purchaseService = new PurchaseService(weaponService);
		Weapon testWeapon = new Weapon("2", "MachineGun", 100, 100, 50);
		Mockito.when(weaponService.getWeaponById("2")).thenReturn(testWeapon);
		Mockito.when(weaponService.getWeaponById("1")).thenThrow(ResourceNotFoundException.class);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void purchaseInvalidWeapon() throws ForbiddenException, ResourceNotFoundException {
		Player player = new Player("Majid Ali");
		purchaseService.purchaseWeapon("1", player);
	}

	@Test(expected = ForbiddenException.class)
	public void purchaseValidWeaponWithPlayerNotEnoughBalance() throws ForbiddenException, ResourceNotFoundException {
		Player player = new Player("Majid Ali");
		purchaseService.purchaseWeapon("2", player);
	}

	@Test
	public void purchaseValidWeaponWithValidBalance() throws ForbiddenException, ResourceNotFoundException {
		Player player = new Player("Majid Ali");
		player.setAvailableBalance(200);
		Weapon purchasedWeapon = purchaseService.purchaseWeapon("2", player);
		Assert.assertNotNull(purchasedWeapon);
		Assert.assertEquals(purchasedWeapon.getId(), "2");
		Assert.assertTrue(player.getAvailableBalance() == 100);

	}
}
