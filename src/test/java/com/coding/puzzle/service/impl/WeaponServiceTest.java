package com.coding.puzzle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.service.IWeaponService;

/**
 * @author majidali
 *
 */
public class WeaponServiceTest {

	private IWeaponService weaponService;
	private List<Weapon> weapons;
	private Weapon weapon;
	
	@Before
	public void setUp() throws Exception {
		weapons = new ArrayList<>();
		weapon = new Weapon("1", "Knife", 10, 20, 10);
		weapons.add(weapon);
		weaponService = new WeaponService();
		weaponService.setWeapons(weapons);
	}
	
	@Test
	public void testGetAllWeapons() {
		List<Weapon> weapons = weaponService.getAllWeapons();
		Assert.assertTrue(weapons.size() == 1);
	}


	@Test(expected = ResourceNotFoundException.class)
	public void testGetWeaponByIdWithInvalidId() throws ResourceNotFoundException {
		weaponService.getWeaponById("2");
	}

	@Test
	public void testGetWeaponByIdWithValidId() throws ResourceNotFoundException {
		Weapon actualWeapon = weaponService.getWeaponById("1");
		Assert.assertNotNull(actualWeapon);
		Assert.assertEquals(weapon.getId(), actualWeapon.getId());
		Assert.assertEquals(weapon.getName(), actualWeapon.getName());
		Assert.assertEquals(weapon.getPrice(), actualWeapon.getPrice());
		Assert.assertEquals(weapon.getKillAward(), actualWeapon.getKillAward());
		Assert.assertEquals(weapon.getDamage(), actualWeapon.getDamage());
	}

}
