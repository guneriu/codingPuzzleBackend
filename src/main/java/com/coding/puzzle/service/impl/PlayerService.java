/**
 * 
 */
package com.coding.puzzle.service.impl;

import static com.coding.puzzle.util.Constants.ErrorMessages.RECORD_NOT_FOUND;

import java.util.List;
import java.util.Optional;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
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

	private List<Player> players;
	
	@Override
	public List<Player> getAllPlayers() {
		return players;
	}

	@Override
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	
	@Override
	public Player getPlayerById(final String id) throws ResourceNotFoundException {
		Optional<Player> optionalPlayer = players.stream().filter(player -> player.getId().equals(id)).findFirst();
		if (optionalPlayer.isPresent()) {
			return optionalPlayer.get();
		} else {
			throw new ResourceNotFoundException(String.format(RECORD_NOT_FOUND, "Player", id));
		}
	}
	
	
	@Override
	public Player createNewPlayer() {
		logger.log("Please Enter your Name");
		Player player =  new Player(reader.readString());
		this.players.add(player);
		return player;
	}

}
