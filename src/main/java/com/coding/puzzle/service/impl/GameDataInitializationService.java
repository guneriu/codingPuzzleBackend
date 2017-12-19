/**
 * 
 */
package com.coding.puzzle.service.impl;

import static com.coding.puzzle.util.Constants.GAME_LEVELS_FILE_NAME;
import static com.coding.puzzle.util.Constants.LOCATIONS_FILE_NAME;
import static com.coding.puzzle.util.Constants.SPLITTER;
import static com.coding.puzzle.util.Constants.WEAPONS_FILE_NAME;
import static com.coding.puzzle.util.Constants.ENEMIES_FILE_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.coding.puzzle.models.GameContents;
import com.coding.puzzle.models.GameLevel;
import com.coding.puzzle.models.GameLevelTarget;
import com.coding.puzzle.models.Location;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.models.Weapon;
import com.coding.puzzle.service.IGameDataInitializationService;
import com.coding.puzzle.util.parsing.FileUtil;

/**
 * @author majidali
 *
 */
public class GameDataInitializationService implements IGameDataInitializationService {

	@Override
	public GameContents initializeGameContents() {
		List<Location> locations = readLocationData(FileUtil.readData(LOCATIONS_FILE_NAME));
		List<Weapon> weapons = readWeaponData(FileUtil.readData(WEAPONS_FILE_NAME));
		List<Player> enemies = readPlayersData(FileUtil.readData(ENEMIES_FILE_NAME), weapons);
		List<GameLevel> gameLevels = readGameLevelData(FileUtil.readData(GAME_LEVELS_FILE_NAME), enemies, locations);
		return new GameContents(weapons, gameLevels, locations);
	}

	private List<Player> readPlayersData(List<String> data, List<Weapon> weapons) {
		Objects.requireNonNull(data, "Enemies data must not be null");
		List<Player> allEnemies = new ArrayList<Player>();
		for (String line : data) {
			String[] enemies = line.split(SPLITTER);
			Weapon enemyWeapon = weapons.stream().filter(weapon->weapon.getId().equals(enemies[2])).findFirst().get();
			Player enemy = new Player(enemies[0], enemies[1], enemyWeapon);
			allEnemies.add(enemy);
		}
		return allEnemies;
	}

	private List<Location> readLocationData(List<String> data) {
		Objects.requireNonNull(data, "Location data must not be null");
		List<Location> allLocations = new ArrayList<Location>();
		for (String line : data) {
			String[] locations = line.split(SPLITTER);
			Location location = new Location(locations[0], locations[1]);
			allLocations.add(location);
		}
		return allLocations;
	}

	private List<GameLevel> readGameLevelData(final List<String> data, List<Player> enemies, List<Location> locations) {
		Objects.requireNonNull(data, "Game Level data must not be null");
		List<GameLevel> gameLevels = new ArrayList<GameLevel>();
		for (String line : data) {
			String[] levels = line.split(SPLITTER);
			Location location = locations.stream().filter(loc->loc.getId().equals(levels[4])).findFirst().get();
			String summary = levels[1].replace("{locationName}", location.getName());
			GameLevelTarget target = GameLevelTarget.valueOf(levels[2]);
			Player enemy = null;
			if(target == GameLevelTarget.KILL_ENEMY){
				enemy = enemies.stream().filter(player->player.getId().equals(levels[5])).findFirst().get();
			}
			GameLevel level = new GameLevel(levels[0], summary, levels[3], target, enemy, location);
			gameLevels.add(level);
		}
		return gameLevels;
	}

	private List<Weapon> readWeaponData(final List<String> data) {
		Objects.requireNonNull(data, "Weapon data must not be null");
		List<Weapon> availableWeapons = new ArrayList<>();
		for (String line : data) {
			String[] weapons = line.split(SPLITTER);
			Weapon weapon = new Weapon(weapons[0], weapons[1], Integer.parseInt(weapons[2]),
					Integer.parseInt(weapons[3]));
			availableWeapons.add(weapon);
		}
		return availableWeapons;
	}

}
