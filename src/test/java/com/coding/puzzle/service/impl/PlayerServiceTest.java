package com.coding.puzzle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.service.IPlayerService;

public class PlayerServiceTest {

	private IPlayerService playerService;
	private List<Player> players;
	private Player player;

	@Before
	public void setUp() throws Exception {
		players = new ArrayList<>();
		Weapon testWeapon = new Weapon("1", "Knife", 10, 20, 10);
		player = new Player("1", "Enemy1", testWeapon);
		players.add(player);
		playerService = new PlayerService();
		playerService.setPlayers(players);
	}

	@Test
	public void testGetAllPlayers() {
		List<Player> players = playerService.getAllPlayers();
		Assert.assertTrue(players.size() == 1);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testGetPlayerByIdWithInvalidId() throws ResourceNotFoundException {
		playerService.getPlayerById("2");
	}

	@Test
	public void testGetPlayerByIdWithValidId() throws ResourceNotFoundException {
		Player actualPlayer = playerService.getPlayerById("1");
		Assert.assertNotNull(actualPlayer);
		Assert.assertEquals(player.getId(), actualPlayer.getId());
		Assert.assertEquals(player.getName(), actualPlayer.getName());
		Assert.assertEquals(player.getWeapon().getId(), actualPlayer.getWeapon().getId());
		
	}
}
