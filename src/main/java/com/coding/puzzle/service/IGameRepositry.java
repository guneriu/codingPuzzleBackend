/**
 * 
 */
package com.coding.puzzle.service;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Player;

/**
 * Purspose of this service is to get stored games and store games
 * 
 * @author majidali
 *
 */
public interface IGameRepositry {
	public void storeGame(Player player);
	public Player getStoredGame() throws ResourceNotFoundException;
}
