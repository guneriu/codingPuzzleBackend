/**
 * 
 */
package com.coding.puzzle.service;

/**
 * @author majidali
 *
 */
public interface IGameService {
	public void startNewGame();
	public void resumeGame();
	public void playGame();
	public void purchaseWeapon();
	public void showHowToPlay();
	public void quit();
}
