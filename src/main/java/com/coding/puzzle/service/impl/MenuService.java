/**
 * 
 */
package com.coding.puzzle.service.impl;

import static java.util.stream.IntStream.rangeClosed;

import com.coding.puzzle.models.Menu;
import com.coding.puzzle.service.IGameService;
import com.coding.puzzle.service.IMenuService;
import com.coding.puzzle.util.logging.Logger;
import com.coding.puzzle.util.logging.LoggerFactory;

/**
 * @author majidali
 *
 */
public class MenuService implements IMenuService {

	private static Logger logger = LoggerFactory.getLogger();

	private final IGameService gameService;

	public MenuService(IGameService gameService) {
		this.gameService = gameService;
	}

	public enum MainMenu {
		START("Start new game"), RESUME("Resume previous game");
		private final String title;

		MainMenu(String title) {
			this.title = title;
		}

		@Override
		public String toString() {
			return title;
		}
	}

	public enum GameMenu {
		PLAY("Play Game"), PURCHASE_WEAPON("Purchase Weapon"), HOW_TO_PLAY("How to play"), QUIT("Quit");

		private final String title;

		GameMenu(String title) {
			this.title = title;
		}

		@Override
		public String toString() {
			return title;
		}
	}

	@Override
	public void displayMainMenu() {
		Menu<MainMenu> menu = new Menu<>("Main menu", MainMenu.values());
		menu.draw();
		switch (menu.chooseItem()) {
		case START:
			gameService.startNewGame();
			displayGameMenu();
			break;
		case RESUME:
			gameService.resumeGame();
			break;
		default:
		}
	}

	private void displayGameMenu() {
		Menu<GameMenu> menu = new Menu<>("Game menu", GameMenu.values());
		menu.draw();
		switch (menu.chooseItem()) {
		case PLAY:
			gameService.playGame();
			break;
		case PURCHASE_WEAPON:
			gameService.purchaseWeapon();
			break;
		case HOW_TO_PLAY:
			gameService.showHowToPlay();
		case QUIT:
			gameService.quit();
		default:
		}
		logger.log(""); // empty line
		displayGameMenu();
	}

	private void clearConsole() {
		rangeClosed(1, 50).forEach(value -> System.out.println());
	}
}
