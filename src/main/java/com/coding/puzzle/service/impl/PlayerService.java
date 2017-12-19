/**
 * 
 */
package com.coding.puzzle.service.impl;

import com.coding.puzzle.models.Player;
import com.coding.puzzle.service.IPlayerService;
import com.coding.puzzle.util.input.InputReader;
import com.coding.puzzle.util.input.InputReaderFactory;
import com.coding.puzzle.util.logging.Logger;
import com.coding.puzzle.util.logging.LoggerFactory;

/**
 * @author majidali
 *
 */
public class PlayerService implements IPlayerService {

	private static Logger logger = LoggerFactory.getLogger();

	private final InputReader reader = InputReaderFactory.getInputReader();

	@Override
	public Player createNewPlayer() {
		logger.log("Please Enter your Name");
		return new Player(reader.readString());
	}

}
