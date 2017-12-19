
package com.coding.puzzle.service.impl;

import static com.coding.puzzle.util.Constants.ErrorMessages.RECORD_NOT_FOUND;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.GameLevel;
import com.coding.puzzle.service.IGameLevelService;

/**
 * @author majidali
 *
 */
public class GameLevelService implements IGameLevelService {

	private final List<GameLevel> gameLevels;

	public GameLevelService(List<GameLevel> gameLevels) {
		Objects.requireNonNull(gameLevels, "GameLevels must not be null");
		this.gameLevels = gameLevels;
	}

	@Override
	public List<GameLevel> getAllGameLevels() {
		return gameLevels;
	}

	@Override
	public GameLevel getGameLevelById(final String id) throws ResourceNotFoundException {
		Optional<GameLevel> optionalLevel = gameLevels.stream().filter(level -> level.getId().equals(id)).findFirst();
		if (optionalLevel.isPresent()) {
			return optionalLevel.get();
		} else {
			throw new ResourceNotFoundException(String.format(RECORD_NOT_FOUND, "Level", id));
		}
	}

}
