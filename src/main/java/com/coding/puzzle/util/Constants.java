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
	
	public static final String WEAPONS_FILE_NAME = "weapons.csv";
	
	public static final String GAME_LEVELS_FILE_NAME = "game_levels.csv";
	
	public static final String DEFAULT_CURRENCY = "$";
	
	public static final String WEAPON_TABLE_FORMAT = "%-4s %-20s %-5s %-5s";
	
	public static final String EMPTY_WEAPON = "No Weapon Selected";
	
	public interface ErrorMessages {
		String RECORD_NOT_FOUND = "%1$s not found, id:%2$s";
		String FILE_NOT_FOUND = "file not found, file:%1$s";
		String NOT_ENOUGH_BALANCE = "You do not have enough balance to purchase this weapon";
	}
}
