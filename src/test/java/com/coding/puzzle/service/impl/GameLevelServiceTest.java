package com.coding.puzzle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.GameLevel;
import com.coding.puzzle.models.GameLevelTarget;
import com.coding.puzzle.service.IGameLevelService;

/**
 * @author majidali
 *
 */
public class GameLevelServiceTest {

	private IGameLevelService gameLevelService;
	private List<GameLevel> gameLevels;
	private GameLevel gameLevel;

	@Before
	public void setUp() throws Exception {
		gameLevels = new ArrayList<>();
		gameLevel = new GameLevel("1", "level1", "2", GameLevelTarget.FIGHT, null, null);
		gameLevels.add(gameLevel);
		gameLevelService = new GameLevelService();
		gameLevelService.setGameLevels(gameLevels);
	}

	@Test
	public void testGetAllGameLevels() {
		List<GameLevel> actualLevels = gameLevelService.getAllGameLevels();
		Assert.assertTrue(actualLevels.size() == 1);
	}

	@Test
	public void testGetLevelByIdWithValidId() throws ResourceNotFoundException {
		GameLevel actualModel = gameLevelService.getGameLevelById("1");
		Assert.assertNotNull(actualModel);
		Assert.assertEquals(gameLevel.getId(), actualModel.getId());
		Assert.assertEquals(gameLevel.getSummary(), actualModel.getSummary());
		Assert.assertEquals(gameLevel.getNextGameLevelId(), actualModel.getNextGameLevelId());
		Assert.assertEquals(gameLevel.getTarget(), actualModel.getTarget());
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testGetLevelByIdWithInvalidId() throws ResourceNotFoundException {
		gameLevelService.getGameLevelById("2");
	}

}
