
package com.coding.puzzle.util;
/**
 * 
 */

/**
 * @author majidali
 *
 */
public final class Constants {

	public static final String SPLITTER = ",";

	public static final String LOCATIONS_FILE_NAME = "locations.csv";
	
	public static final String WEAPONS_FILE_NAME = "weapons.csv";
	
	public static final String ENEMIES_FILE_NAME = "enemies.csv";

	public static final String GAME_LEVELS_FILE_NAME = "game_levels.csv";
	
	public static final String SAVED_GAME_FILE_NAME = System.getProperty("user.home") + "/games.ser";

	public static final String DEFAULT_CURRENCY = "$";

	public static final String WEAPON_TABLE_FORMAT = "%-4s %-20s %-5s %-5s";

	public static final String EMPTY_WEAPON = "No Weapon Selected";

	public static final String START_GAME_LEVEL_ID = "1";

	public static final String KILL_KEY = "K";
	
	public static final String DEFUSE_BOMB = "D";
	
	public static final String DEFUSING_KIT = "Bomb Defusal Kit";

	public static final int LEVEL_COMPLETE_EXPERIENCE = 25;

	public interface ErrorMessages {
		String RECORD_NOT_FOUND = "%1$s not found, id:%2$s";
		String FILE_NOT_FOUND = "file not found, file:%1$s";
		String NOT_ENOUGH_BALANCE = "You do not have enough balance to purchase this weapon";
	}
	
	public interface GameLevelTarget {
		String RECORD_NOT_FOUND = "%1$s not found, id:%2$s";
		String FILE_NOT_FOUND = "file not found, file:%1$s";
		String NOT_ENOUGH_BALANCE = "You do not have enough balance to purchase this weapon";
	}
}
