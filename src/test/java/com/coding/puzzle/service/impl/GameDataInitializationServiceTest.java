package com.coding.puzzle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.coding.puzzle.models.GameContents;
import com.coding.puzzle.service.IGameDataInitializationService;
import com.coding.puzzle.util.Constants;
import com.coding.puzzle.util.parsing.FileUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileUtil.class)
public class GameDataInitializationServiceTest {

	IGameDataInitializationService gameDataInitializationService;

	@Before
	public void setUp() {
		gameDataInitializationService = new GameDataInitializationService();
		List<String> gameLevels = new ArrayList<>();
		gameLevels.add("1,Level1,20");
		List<String> weapons = new ArrayList<>();
		weapons.add("1,Knife,20,20");
		PowerMockito.mockStatic(FileUtil.class);
		Mockito.when(FileUtil.readData(Constants.GAME_LEVELS_FILE_NAME)).thenReturn(gameLevels);
		Mockito.when(FileUtil.readData(Constants.WEAPONS_FILE_NAME)).thenReturn(weapons);
	}

	@Test
	public void testLoadGameContents() {
		GameContents gameContent = gameDataInitializationService.initializeGameContents();
		Assert.assertNotNull(gameContent);
		Assert.assertTrue(gameContent.getGameLevels().size() == 1);
		Assert.assertTrue(gameContent.getWeapons().size() == 1);
	}

}
