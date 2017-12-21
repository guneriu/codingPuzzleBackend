package com.coding.puzzle;

import com.coding.puzzle.repository.IGameRepositry;
import com.coding.puzzle.repository.impl.GameRepositry;
import com.coding.puzzle.service.IGameDataInitializationService;
import com.coding.puzzle.service.IGameLevelService;
import com.coding.puzzle.service.IGameService;
import com.coding.puzzle.service.ILocationService;
import com.coding.puzzle.service.IMenuService;
import com.coding.puzzle.service.IPlayerService;
import com.coding.puzzle.service.IPurchaseService;
import com.coding.puzzle.service.IWeaponService;
import com.coding.puzzle.service.impl.GameDataInitializationService;
import com.coding.puzzle.service.impl.GameLevelService;
import com.coding.puzzle.service.impl.GameService;
import com.coding.puzzle.service.impl.LocationService;
import com.coding.puzzle.service.impl.MenuService;
import com.coding.puzzle.service.impl.PlayerService;
import com.coding.puzzle.service.impl.PurchaseService;
import com.coding.puzzle.service.impl.WeaponService;

/**
 * @author majidali
 *
 */
public class GameLauncher {

	public static void main(String[] args) {

		// A framework(Spring) can be used to manage and initialize following
		// beans but due to java restriction I'm manually managing it.
		IWeaponService weaponService = new WeaponService();
		IGameLevelService gameLevelService = new GameLevelService();
		IPlayerService playerService = new PlayerService();
		IPurchaseService purchaseService = new PurchaseService(weaponService);
		ILocationService locationService = new LocationService();
		IGameDataInitializationService gameDataInitializationService = new GameDataInitializationService(weaponService,
				gameLevelService, locationService, playerService);
		// load game contents{weapons, game levels, locations, enemies} from
		// config files
		gameDataInitializationService.initializeGameContents();
		IGameRepositry gameRepositry = new GameRepositry();
		IGameService gameService = new GameService(weaponService, gameLevelService, playerService, purchaseService,
				gameRepositry);
		IMenuService menuService = new MenuService(gameService);
		// show game launch Menu
		menuService.displayMainMenu();
	}

}
