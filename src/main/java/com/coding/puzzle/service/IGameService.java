/**
 * 
 */
package com.coding.puzzle.service;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Player;

/**
 * @author majidali
 *
 */
public interface IGameService {
	public void startNewGame();
	public void resumeGame() throws ResourceNotFoundException;
	public void playGame();
	public void purchaseWeapon();
	public void quit();
	public void purchaseLife();
	Player getPlayer();
}
