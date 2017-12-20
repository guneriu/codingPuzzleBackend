/**
 * 
 */
package com.coding.puzzle.service;

import java.util.List;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.GameLevel;

/**
 * @author majidali
 *
 */
public interface IGameLevelService {
		
	public List<GameLevel> getAllGameLevels();

	public GameLevel getGameLevelById(final String id) throws ResourceNotFoundException;

	public void setGameLevels(List<GameLevel> gameLevels);
}
