/**
 * 
 */
package com.coding.puzzle.service;

import java.util.List;

import com.coding.puzzle.exceptions.ResourceNotFoundException;
import com.coding.puzzle.models.Player;

/**
 * @author majidali
 *
 */
public interface IPlayerService {
	
	public Player createNewPlayer();

	public List<Player> getAllPlayers();

	public void setPlayers(List<Player> players);

	public Player getPlayerById(String id) throws ResourceNotFoundException;
}
