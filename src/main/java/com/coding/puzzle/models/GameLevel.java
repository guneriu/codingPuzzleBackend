
package com.coding.puzzle.models;

/**
 * @author majidali
 *
 */
public class GameLevel extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2143898345009225178L;

	private String summary;

	private String nextGameLevelId;

	private GameLevelTarget target;

	private boolean isCompleted;

	private Player enemy;
	
	private Location location;

	public GameLevel(String id, String summary, String nextGameLevelId, GameLevelTarget target, Player enemy, Location location) {
		this.setId(id);
		this.summary = summary;
		this.nextGameLevelId = nextGameLevelId;
		this.target = target;
		this.enemy = enemy;
		this.location = location;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getNextGameLevelId() {
		return nextGameLevelId;
	}

	public void setNextGameLevelId(String nextGameLevelId) {
		this.nextGameLevelId = nextGameLevelId;
	}

	public GameLevelTarget getTarget() {
		return target;
	}

	public void setTarget(GameLevelTarget target) {
		this.target = target;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Player getEnemy() {
		return enemy;
	}

	public void setEnemy(Player enemy) {
		this.enemy = enemy;
	}
}
