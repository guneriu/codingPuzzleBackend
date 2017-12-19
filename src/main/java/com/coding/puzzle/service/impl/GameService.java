/**
 * 
 */
package com.coding.puzzle.service.impl;

import java.util.Objects;

import com.coding.puzzle.exceptions.ForbiddenException;
import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.GameLevel;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.service.IGameLevelService;
import com.coding.puzzle.service.IGameService;
import com.coding.puzzle.service.ILocationService;
import com.coding.puzzle.service.IPlayerService;
import com.coding.puzzle.service.IPurchaseService;
import com.coding.puzzle.service.IWeaponService;
import com.coding.puzzle.util.Constants;
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
	
	private final ILocationService locationService;


	private Player player;

	private final InputReader reader = InputReaderFactory.getInputReader();

	public GameService(IWeaponService weaponService, IGameLevelService gameLevelService, IPlayerService playerService,
			IPurchaseService purchaseService, ILocationService locationService) {
		this.weaponService = weaponService;
		this.gameLevelService = gameLevelService;
		this.playerService = playerService;
		this.purchaseService = purchaseService;
		this.locationService = locationService;
	}

	@Override
	public void startNewGame() {
		player = playerService.createNewPlayer();
		GameLevel gameLevel;
		try {
			gameLevel = gameLevelService.getGameLevelById(Constants.START_GAME_LEVEL_ID);
			player.setLevel(gameLevel);
		} catch (ResourceNotFoundException e) {
			logger.error(e.getMessage());
		}
		logger.log(player.toString());
	}

	@Override
	public void resumeGame() {

	}

	@Override
	public void playGame() {
		if (player.getWeapon() == null) {
			logger.error("Please select weapon and try again.");
			return;
		}
		GameLevel currentGameLevel = player.getLevel();
		if (currentGameLevel.isCompleted()) {
			try {
				currentGameLevel = gameLevelService.getGameLevelById(currentGameLevel.getNextGameLevelId());
				player.setLevel(currentGameLevel);
			} catch (ResourceNotFoundException e) {
				logger.error(e.getMessage());
				return;
			}
		}

		switch (currentGameLevel.getTarget()) {
		case KILL_ENEMY:
			fightWithEnemy();
			break;
		case DEFUSE_BOMB:
			defuseBomb();
			break;
		default:
			break;
		}
	}

	private void defuseBomb() {
		// TODO Auto-generated method stub

	}

	private void fightWithEnemy() {
		GameLevel currentLevel = player.getLevel();
		Player enemy = currentLevel.getEnemy();
		Objects.requireNonNull(enemy, "No Enemy found.");
		logger.log(currentLevel.getSummary());
		String input = null;
		int missedCounter = 0;
		while (!Constants.KILL_KEY.equalsIgnoreCase(input)) {
			logger.log("Press K to kill enemy with " + player.getWeapon().getName());
			input = reader.readString();
			if (!Constants.KILL_KEY.equalsIgnoreCase(input)) {
				missedCounter++;
				logger.error("You missed the target.\n");
				if (missedCounter > Constants.TARGET_MISS_THRESHOLD) {
					logger.log("Alas! You are killed by enemy with " + enemy.getWeapon().getName());
					return;
				}
			}
		}

		logger.log("Congratulations! You killed the enemy.\n");
		player.updateKillReward();
		player.getLevel().setCompleted(true);
		logger.log(player.toString());
	}

	@Override
	public void purchaseWeapon() {
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
