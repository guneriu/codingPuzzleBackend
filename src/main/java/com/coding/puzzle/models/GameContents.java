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
	
	private List<Location> locations;

	public GameContents(List<Weapon> weapons, List<GameLevel> gameLevels, List<Location> locations) {
		this.weapons = weapons;
		this.gameLevels = gameLevels;
		this.setLocations(locations);
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

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

}
