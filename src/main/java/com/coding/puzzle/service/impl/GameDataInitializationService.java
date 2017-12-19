/**
 * 
 */
package com.coding.puzzle.service.impl;

import static com.coding.puzzle.util.Constants.GAME_LEVELS_FILE_NAME;
import static com.coding.puzzle.util.Constants.SPLITTER;
import static com.coding.puzzle.util.Constants.WEAPONS_FILE_NAME;

import java.util.ArrayList;
import java.util.List;

import com.coding.puzzle.models.GameContents;
import com.coding.puzzle.models.GameLevel;
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
		List<Weapon> weapons = readWeaponData(FileUtil.readData(WEAPONS_FILE_NAME));
		List<GameLevel> gameLevels = readGameLevelData(FileUtil.readData(GAME_LEVELS_FILE_NAME));
		return new GameContents(weapons, gameLevels);
	}

	private List<GameLevel> readGameLevelData(final List<String> data) {
		if (data == null) {
			throw new NullPointerException("Game Level data must not be null");
		}
		List<GameLevel> gameLevels = new ArrayList<GameLevel>();
		for (String line : data) {
			String[] levels = line.split(SPLITTER);
			GameLevel level = new GameLevel(levels[0], levels[1], Integer.parseInt(levels[2]));
			gameLevels.add(level);
		}
		return gameLevels;
	}

	private List<Weapon> readWeaponData(final List<String> data) {
		if (data == null) {
			throw new NullPointerException("Weapon data must not be null");
		}
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
