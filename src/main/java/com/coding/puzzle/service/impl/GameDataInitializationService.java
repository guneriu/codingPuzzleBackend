/**
 * 
 */
package com.coding.puzzle.service.impl;

import static com.coding.puzzle.util.Constants.ENEMIES_FILE_NAME;
import static com.coding.puzzle.util.Constants.GAME_LEVELS_FILE_NAME;
import static com.coding.puzzle.util.Constants.LOCATIONS_FILE_NAME;
import static com.coding.puzzle.util.Constants.SPLITTER;
import static com.coding.puzzle.util.Constants.WEAPONS_FILE_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.GameLevel;
import com.coding.puzzle.models.GameLevelTarget;
import com.coding.puzzle.models.Location;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.service.IGameDataInitializationService;
import com.coding.puzzle.service.IGameLevelService;
import com.coding.puzzle.service.ILocationService;
import com.coding.puzzle.service.IPlayerService;
import com.coding.puzzle.service.IWeaponService;
import com.coding.puzzle.util.logging.Logger;
import com.coding.puzzle.util.logging.LoggerFactory;
import com.coding.puzzle.util.parsing.FileUtil;

/**
 * @author majidali
 *
 */
public class GameDataInitializationService implements IGameDataInitializationService {

	private static Logger logger = LoggerFactory.getLogger();

	private final IWeaponService weaponService;

	private final IGameLevelService gameLevelService;

	private final ILocationService locationService;

	private final IPlayerService playerService;

	public GameDataInitializationService(IWeaponService weaponService, IGameLevelService gameLevelService,
			ILocationService locationService, IPlayerService playerService) {
		this.weaponService = weaponService;
		this.gameLevelService = gameLevelService;
		this.locationService = locationService;
		this.playerService = playerService;
	}

	@Override
	public void initializeGameContents() {
		setLocationData(FileUtil.readData(LOCATIONS_FILE_NAME));
		setWeaponData(FileUtil.readData(WEAPONS_FILE_NAME));
		setPlayersData(FileUtil.readData(ENEMIES_FILE_NAME));
		setGameLevelData(FileUtil.readData(GAME_LEVELS_FILE_NAME));
	}

	private void setPlayersData(List<String> data) {
		Objects.requireNonNull(data, "Enemies data must not be null");
		List<Player> allEnemies = new ArrayList<Player>();
		for (String line : data) {
			String[] enemies = line.split(SPLITTER);
			String weaponId = enemies[2];
			Weapon enemyWeapon;
			try {
				enemyWeapon = weaponService.getWeaponById(weaponId);
				Player enemy = new Player(enemies[0], enemies[1], enemyWeapon);
				allEnemies.add(enemy);
			} catch (ResourceNotFoundException e) {
				logger.error(e.getMessage());
			}

		}
		playerService.setPlayers(allEnemies);
	}

	private void setLocationData(List<String> data) {
		Objects.requireNonNull(data, "Location data must not be null");
		List<Location> allLocations = new ArrayList<Location>();
		for (String line : data) {
			String[] locations = line.split(SPLITTER);
			Location location = new Location(locations[0], locations[1]);
			allLocations.add(location);
		}
		locationService.setLocations(allLocations);
	}

	private void setGameLevelData(final List<String> data) {
		Objects.requireNonNull(data, "Game Level data must not be null");
		List<GameLevel> gameLevels = new ArrayList<GameLevel>();
		for (String line : data) {
			String[] levels = line.split(SPLITTER);
			String locationId = levels[4];
			Location location = null;
			try {
				location = locationService.getLocationById(locationId);
				String summary = levels[1].replace("{locationName}", location.getName());
				GameLevelTarget target = GameLevelTarget.valueOf(levels[2]);
				String playerId = levels[5];
				Player enemy = playerService.getPlayerById(playerId);
				GameLevel level = new GameLevel(levels[0], summary, levels[3], target, enemy, location);
				gameLevels.add(level);
			} catch (ResourceNotFoundException e) {
				logger.error(e.getMessage());
			}

		}
		gameLevelService.setGameLevels(gameLevels);
	}

	private void setWeaponData(final List<String> data) {
		Objects.requireNonNull(data, "Weapon data must not be null");
		List<Weapon> availableWeapons = new ArrayList<>();
		for (String line : data) {
			String[] weapons = line.split(SPLITTER);
			Weapon weapon = new Weapon(weapons[0], weapons[1], Integer.parseInt(weapons[2]),
					Integer.parseInt(weapons[3]), Integer.parseInt(weapons[4]));
			availableWeapons.add(weapon);
		}
		weaponService.setWeapons(availableWeapons);
	}

}
