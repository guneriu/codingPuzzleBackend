/**
 * 
 */
package com.coding.puzzle.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.GameLevel;
import com.coding.puzzle.models.GameLevelTarget;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.repository.IGameRepositry;
import com.coding.puzzle.repository.impl.GameRepositry;
import com.coding.puzzle.service.IGameLevelService;
import com.coding.puzzle.service.IGameService;
import com.coding.puzzle.service.IPlayerService;
import com.coding.puzzle.service.IPurchaseService;
import com.coding.puzzle.service.IWeaponService;
import com.coding.puzzle.util.Constants;
import com.coding.puzzle.util.input.InputReaderFactory;

/**
 * @author majidali
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(InputReaderFactory.class)
public class GameServiceTest {

	private IGameService gameService;

	private IWeaponService weaponService;

	private IGameLevelService gameLevelService;

	private IPlayerService playerService;

	private IPurchaseService purchaseService;

	private IGameRepositry gameRepositry;

	private Player player;

	private GameLevel gameLevel;

	private Weapon weapon;


	@Before
	public void setUp() throws Exception {
		weaponService = Mockito.mock(WeaponService.class);
		gameLevelService = Mockito.mock(GameLevelService.class);
		playerService = Mockito.mock(PlayerService.class);
		purchaseService = Mockito.mock(PurchaseService.class);
		gameRepositry = Mockito.mock(GameRepositry.class);
		gameService = new GameService(weaponService, gameLevelService, playerService, purchaseService, gameRepositry);
		player = new Player("Majid Ali");
		Mockito.when(playerService.createNewPlayer()).thenReturn(player);
		gameLevel = new GameLevel("1", "level1", "2", GameLevelTarget.FIGHT, null, null);
		Mockito.when(gameLevelService.getGameLevelById(Constants.START_GAME_LEVEL_ID)).thenReturn(gameLevel);
		Mockito.when(gameRepositry.getStoredGame()).thenThrow(ResourceNotFoundException.class);
		weapon = new Weapon("1", "Knife", 10, 20, 10);
		Mockito.when(purchaseService.purchaseWeapon("1", player)).thenReturn(weapon);
	}

	@Test
	public void testStartNewGameCreatesNewPlayer() throws ResourceNotFoundException {
		gameService.startNewGame();
		Assert.assertNotNull(gameService.getPlayer());
		Assert.assertEquals(gameService.getPlayer().getName(), player.getName());
		Assert.assertEquals(gameService.getPlayer().getLevel().getId(), gameLevel.getId());
		Mockito.verify(playerService).createNewPlayer();
		Mockito.verify(gameLevelService).getGameLevelById(Constants.START_GAME_LEVEL_ID);
	}

	@Test
	public void testPurchaseLife() {
		gameService.startNewGame();
		gameService.purchaseLife();
		Mockito.verify(purchaseService).purchaseLife(player);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testResumeGameWithoutSavedGame() throws ResourceNotFoundException {
		gameService.resumeGame();
		Mockito.verify(gameRepositry).getStoredGame();
	}
}
