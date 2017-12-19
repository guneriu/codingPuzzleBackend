package com.coding.puzzle;

import com.coding.puzzle.models.GameContents;
import com.coding.puzzle.service.IGameDataInitializationService;
import com.coding.puzzle.service.IGameLevelService;
import com.coding.puzzle.service.IGameService;
import com.coding.puzzle.service.IMenuService;
import com.coding.puzzle.service.IPlayerService;
import com.coding.puzzle.service.IPurchaseService;
import com.coding.puzzle.service.IWeaponService;
import com.coding.puzzle.service.impl.GameDataInitializationService;
import com.coding.puzzle.service.impl.GameLevelService;
import com.coding.puzzle.service.impl.GameService;
import com.coding.puzzle.service.impl.MenuService;
import com.coding.puzzle.service.impl.PlayerService;
import com.coding.puzzle.service.impl.PurchaseService;
import com.coding.puzzle.service.impl.WeaponService;

public class GameLauncher {

	public static void main(String[] args) {
		IGameDataInitializationService gameDataInitializationService = new GameDataInitializationService();
		GameContents gameContents = gameDataInitializationService.initializeGameContents();
		
		IWeaponService weaponService = new WeaponService(gameContents.getWeapons());
		IGameLevelService gameLevelService = new GameLevelService(gameContents.getGameLevels());
		IPlayerService playerService = new PlayerService();
		IPurchaseService purchaseService = new PurchaseService(weaponService);
		IGameService gameService = new GameService(weaponService, gameLevelService, playerService, purchaseService);
		IMenuService menuService = new MenuService(gameService);
		menuService.displayMainMenu();
	}

}
