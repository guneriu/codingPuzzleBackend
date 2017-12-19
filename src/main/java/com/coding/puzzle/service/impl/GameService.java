/**
 * 
 */
package com.coding.puzzle.service.impl;

import com.coding.puzzle.exceptions.ForbiddenException;
import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.service.IGameLevelService;
import com.coding.puzzle.service.IGameService;
import com.coding.puzzle.service.IPlayerService;
import com.coding.puzzle.service.IPurchaseService;
import com.coding.puzzle.service.IWeaponService;
import com.coding.puzzle.util.input.InputReader;
import com.coding.puzzle.util.input.InputReaderFactory;
import com.coding.puzzle.util.logging.Logger;
import com.coding.puzzle.util.logging.LoggerFactory;

/**
 * @author majidali
 *
 */
public class GameService implements IGameService {

	private static Logger logger = LoggerFactory.getLogger();

	private final IWeaponService weaponService;

	private final IGameLevelService gameLevelService;

	private final IPlayerService playerService;

	private final IPurchaseService purchaseService;

	private Player player;

	private final InputReader reader = InputReaderFactory.getInputReader();

	public GameService(IWeaponService weaponService, IGameLevelService gameLevelService, IPlayerService playerService,
			IPurchaseService purchaseService) {
		this.weaponService = weaponService;
		this.gameLevelService = gameLevelService;
		this.playerService = playerService;
		this.purchaseService = purchaseService;
	}

	@Override
	public void startNewGame() {
		player = playerService.createNewPlayer();
		logger.log(player.toString());
	}

	@Override
	public void resumeGame() {

	}

	@Override
	public void playGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void purchaseWeapon()  {
		weaponService.displayAllWeapons();
		Weapon weapon = null;
		while (weapon == null) {
			logger.log("Please type weapon Id to purchase weapon:");
			String id = reader.readString();
			try {
				weapon = purchaseService.purchaseWeapon(id, player);
			} catch (ForbiddenException | ResourceNotFoundException e) {
				logger.error(e.getMessage());
			}
		}
		player.setWeapon(weapon);
		logger.log(player.toString());
	}

	@Override
	public void showHowToPlay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub

	}

}
