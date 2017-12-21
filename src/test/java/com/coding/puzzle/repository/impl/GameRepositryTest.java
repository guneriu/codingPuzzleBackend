/**
 * 
 */
package com.coding.puzzle.repository.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.repository.IGameRepositry;

/**
 * @author majidali
 *
 */
public class GameRepositryTest {

	private Player player;

	IGameRepositry gameRepository;

	@Before
	public void setUp() throws Exception {
		Weapon testWeapon = new Weapon("1", "Knife", 10, 20, 10);
		player = new Player("1", "Enemy1", testWeapon);
		gameRepository = new GameRepositry();
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testGetUnStoredGame() throws ResourceNotFoundException {
		gameRepository.getStoredGame();
	}

	@Test
	public void testGetStoredGame() throws ResourceNotFoundException {
		gameRepository.storeGame(player);
		Player storedPlayer = gameRepository.getStoredGame();
		Assert.assertEquals(player.getId(), storedPlayer.getId());
		Assert.assertEquals(player.getName(), storedPlayer.getName());
		Assert.assertEquals(player.getWeapon().getId(), storedPlayer.getWeapon().getId());
	}

}
