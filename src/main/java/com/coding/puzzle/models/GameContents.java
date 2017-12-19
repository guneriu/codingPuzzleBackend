/**
 * 
 */
package com.coding.puzzle.models;

import java.util.List;

/**
 * @author majidali
 *
 */
public class GameContents {

	private List<Weapon> weapons;
	private List<GameLevel> gameLevels;

	public GameContents(List<Weapon> weapons, List<GameLevel> gameLevels) {
		this.weapons = weapons;
		this.gameLevels = gameLevels;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}

	public List<GameLevel> getGameLevels() {
		return gameLevels;
	}

	public void setGameLevels(List<GameLevel> gameLevels) {
		this.gameLevels = gameLevels;
	}

}
