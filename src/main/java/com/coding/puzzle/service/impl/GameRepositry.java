/**
 * 
 */
package com.coding.puzzle.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.coding.puzzle.exceptions.InternalServerException;
import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Player;
import com.coding.puzzle.service.IGameRepositry;
import com.coding.puzzle.util.Constants;
import com.coding.puzzle.util.logging.Logger;
import com.coding.puzzle.util.logging.LoggerFactory;

/**
 * @author majidali
 *
 */
public class GameRepositry implements IGameRepositry {

	private static Logger logger = LoggerFactory.getLogger();

	@Override
	public void storeGame(Player player) {
		// Serialization
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.SAVED_GAME_FILE_NAME))) {
			// Method for serialization of object
			oos.writeObject(player);
			logger.log("Game Saved!");
		} catch (IOException ex) {
			throw new InternalServerException(ex.getMessage(), ex);
		}

	}

	@Override
	public Player getStoredGame() throws ResourceNotFoundException {
		Player player = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.SAVED_GAME_FILE_NAME))) {
			player = (Player) ois.readObject();
			Files.deleteIfExists(Paths.get(Constants.SAVED_GAME_FILE_NAME));
		} catch (FileNotFoundException ex) {
			throw new ResourceNotFoundException("No Saved Game Found!");
		} catch (IOException | ClassNotFoundException e ) {
			throw new InternalServerException(e.getMessage(), e);
		} 
		return player;
	}

}
