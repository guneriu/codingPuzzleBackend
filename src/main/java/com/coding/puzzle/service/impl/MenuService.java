/**
 * 
 */
package com.coding.puzzle.service.impl;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
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
		PLAY("Play Game"), PURCHASE_WEAPON("Purchase Weapon"), PURCHASE_LIFE("Purchase Life"), QUIT("Quit");

		private final String title;

		GameMenu(String title) {
			this.title = title;
		}

		@Override
		public String toString() {
			return title;
		}
	}

	public enum GameQuitMenu {
		YES("Yes"), CANCEL("Cancel");

		private final String title;

		GameQuitMenu(String title) {
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
			try {
				gameService.resumeGame();
				displayGameMenu();
			} catch (ResourceNotFoundException e) {
				logger.log(e.getMessage());
				displayMainMenu();
			}
			break;
		default:
		}
	}

	private void displayGameQuitMenu() {
		Menu<GameQuitMenu> menu = new Menu<>("Are you sure you want to quit?", GameQuitMenu.values());
		menu.draw();
		switch (menu.chooseItem()) {
		case YES:
			gameService.quit();
			displayGameMenu();
			break;
		case CANCEL:
			return;
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
		case PURCHASE_LIFE:
			gameService.purchaseLife();
			break;
		case QUIT:
			displayGameQuitMenu();
		default:
		}
		logger.log(""); // empty line
		displayGameMenu();
	}
}
