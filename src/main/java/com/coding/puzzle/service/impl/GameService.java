/**
 * 
 */
package com.coding.puzzle.service.impl;

import java.util.Timer;
import java.util.TimerTask;

import com.coding.puzzle.exceptions.ForbiddenException;
import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.GameLevel;
import com.coding.puzzle.models.GameLevelTarget;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.service.IGameLevelService;
import com.coding.puzzle.service.IGameService;
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
	public void playGame() {
		if (player.isAlive()) {
			if (player.getWeapon() == null) {
				logger.error("Please select weapon and try again.");
				return;
			}
			GameLevel level = player.getLevel();
			if (level.getTarget() == GameLevelTarget.DEFUSE_BOMB) {
				if (!player.getWeapon().getName().equalsIgnoreCase(Constants.DEFUSING_KIT)) {
					logger.error("Please purchase defusing kit before playing this level.");
					return;
				}
			}
			logger.log(level.getSummary());
			switch (level.getTarget()) {
			case FIGHT:
				fight(player, level.getEnemy());
				break;
			case DEFUSE_BOMB:
				Timer timer = plantBomb(level.getEnemy().getWeapon());
				defuseBomb(timer);
				break;
			default:
				break;
			}
			logger.log(player.toString());
		} else {
			logger.error("Purchase life before playing again.");
		}
	}

	public void fight(Player you, Player enemy) {
		do {
			logger.log("Press K to attack enemy with " + player.getWeapon().getName());
			String input = reader.readString();
			if (input.equalsIgnoreCase(Constants.KILL_KEY)) {
				attack(you, enemy);
			} else {
				attack(enemy, you);
			}

		} while (you.isAlive() && enemy.isAlive());

		if (player.isAlive()) {
			logger.log("Congratulations! You killed the enemy.\n");
			setGameLevelComplete();
		} else {
			logger.log("Alas! Enemy killed you.\n");
		}
	}

	private void attack(Player attacker, Player defender) {
		int health = defender.getHealth() - attacker.getWeapon().getDamage();
		logger.log(attacker.getName() + "'s attack does " + attacker.getWeapon().getDamage() + " damage!\n");
		logger.log(defender.getName() + "'s Health is " + health);
		defender.setHealth(health);
	}

	private Timer plantBomb(Weapon weapon) {
		TimerTask explodeBomb = new TimerTask() {
			public void run() {
				int health = player.getHealth() - weapon.getDamage();
				player.setHealth(health);
				logger.log("Bomb Exploded! You are killed. Press enter to continue");
				cancel();
			}
		};
		Timer timer = new Timer(true);
		timer.schedule(explodeBomb, 10000L);
		logger.log("A Bomb has been planted you have 10 seconds to defuse bomb.");
		return timer;
	}

	private void defuseBomb(Timer timer) {
		do {
			logger.log("Press D to defuse domb with " + player.getWeapon().getName());
			String input = reader.readString();
			if (Constants.DEFUSE_BOMB.equalsIgnoreCase(input)) {
				timer.cancel();
				logger.log("Congratulations! You have defused the bomb.\n");
				setGameLevelComplete();
				return;
			}

		} while (player.isAlive());

	}

	private void setGameLevelComplete() {
		GameLevel level = player.getLevel();
		level.setCompleted(true);
		player.addBalance(player.getWeapon().getKillAward());
		player.addExperience(Constants.LEVEL_COMPLETE_EXPERIENCE);
		if (level.getNextGameLevelId() == null || level.getNextGameLevelId().isEmpty()) {
			logger.log(player.toString());
			finishGame();
		} else {
			try {
				level = gameLevelService.getGameLevelById(level.getNextGameLevelId());
				player.setLevel(level);
			} catch (ResourceNotFoundException e) {
				logger.error(e.getMessage());
				return;
			}
		}
	}

	private void finishGame() {
		logger.log("The Game is finished!");
		System.exit(0);
	}


	@Override
	public void quit() {

	}

	@Override
	public void purchaseLife() {
		purchaseService.purchaseLife(player);
		logger.log(player.toString());
	}

}
