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

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.service.IGameDataInitializationService;
import com.coding.puzzle.service.IGameLevelService;
import com.coding.puzzle.service.ILocationService;
import com.coding.puzzle.service.IPlayerService;
import com.coding.puzzle.service.IWeaponService;
import com.coding.puzzle.util.Constants;
import com.coding.puzzle.util.parsing.FileUtil;


@RunWith(PowerMockRunner.class)
@PrepareForTest(FileUtil.class) 
public class GameDataInitializationServiceTest {

	IGameDataInitializationService gameDataInitializationService;
	IWeaponService weaponService;
	IGameLevelService gameLevelService;
	ILocationService locationService;
	IPlayerService playerService;

	@Before
	public void setUp() {

		weaponService = new WeaponService();
		gameLevelService = new GameLevelService();
		locationService = new LocationService();
		playerService = new PlayerService();
		gameDataInitializationService = new GameDataInitializationService(weaponService, gameLevelService, locationService, playerService);
		List<String> gameLevels = new ArrayList<>();
		gameLevels.add("1,You are at the {locationName} your target is to kill Enemies here,FIGHT,2,1,1");
		List<String> weapons = new ArrayList<>();
		weapons.add("1,Knife,0,10,20");
		PowerMockito.mockStatic(FileUtil.class);
		
		List<String> players = new ArrayList<>();
		players.add("1,Enemy1,1");
		
		List<String> locations = new ArrayList<>();
		locations.add("1,Ground Floor");
		
		Mockito.when(FileUtil.readData(Constants.GAME_LEVELS_FILE_NAME)).thenReturn(gameLevels);
		Mockito.when(FileUtil.readData(Constants.WEAPONS_FILE_NAME)).thenReturn(weapons);
		Mockito.when(FileUtil.readData(Constants.LOCATIONS_FILE_NAME)).thenReturn(locations);
		Mockito.when(FileUtil.readData(Constants.ENEMIES_FILE_NAME)).thenReturn(players);
	}

	@Test
	public void testInitializeGameContents() throws ResourceNotFoundException {
		gameDataInitializationService.initializeGameContents();
		Assert.assertTrue(weaponService.getAllWeapons().size() == 1);
		Assert.assertNotNull(weaponService.getWeaponById("1"));
		Assert.assertTrue(gameLevelService.getAllGameLevels().size() == 1);
		Assert.assertNotNull(gameLevelService.getGameLevelById("1"));
		Assert.assertTrue(locationService.getAllLocations().size() == 1);
		Assert.assertNotNull(locationService.getLocationById("1"));
	}

}
