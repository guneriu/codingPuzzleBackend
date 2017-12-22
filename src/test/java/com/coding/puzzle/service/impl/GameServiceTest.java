/**
 * 
 */
package com.coding.puzzle.service.impl;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.coding.puzzle.exceptions.ForbiddenException;
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

/**
 * @author majidali
 *
 */
@RunWith(PowerMockRunner.class)
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

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog().mute();

	@Rule
	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog().mute();

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
		weapon = new Weapon("1", "Knife", 10, 20, 10);
		Mockito.when(gameRepositry.getStoredGame()).thenReturn(player);
		Mockito.when(purchaseService.purchaseWeapon("1", player)).thenReturn(weapon);

	}

	@Test
	public void testStartNewGameCreatesNewPlayer() throws ResourceNotFoundException {
		gameService.startNewGame();
		Assert.assertEquals(player.toString(), systemOutRule.getLog().trim());
		Assert.assertNotNull(gameService.getPlayer());
		Assert.assertEquals(gameService.getPlayer().getName(), player.getName());
		Assert.assertEquals(gameService.getPlayer().getLevel().getId(), gameLevel.getId());
		Mockito.verify(playerService).createNewPlayer();
		Mockito.verify(gameLevelService).getGameLevelById(Constants.START_GAME_LEVEL_ID);
	}

	@Test
	public void testQuitGame() throws ResourceNotFoundException, ForbiddenException {
		gameService.startNewGame();
		exit.expectSystemExitWithStatus(0);
		gameService.quit();
	}

	@Test
	public void testResumeGame() throws ResourceNotFoundException, ForbiddenException {
		gameService.resumeGame();
		Assert.assertEquals(player.getId(), gameService.getPlayer().getId());
		Mockito.verify(gameRepositry).getStoredGame();
	}

	@Test(expected = NullPointerException.class)
	public void testPlayGameWithNoPlayer() {
		gameService.playGame();
	}

	@Test
	public void testPlayGameWithNoWeaponSelected() {
		gameService.startNewGame();
		gameService.playGame();
		Assert.assertEquals("Please select weapon and try again.", systemErrRule.getLog().trim());
	}
	
	@Test
	public void testPurchaseLife() {
		gameService.startNewGame();
		gameService.purchaseLife();
		Mockito.verify(purchaseService).purchaseLife(player);
	}
}
