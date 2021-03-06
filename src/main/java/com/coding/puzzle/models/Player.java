
package com.coding.puzzle.models;

import com.coding.puzzle.util.Constants;

/**
 * @author majidali
 *
 */
public class Player extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -491465379684792137L;

	private String name;

	private Weapon weapon;

	private Integer experience;

	private Integer health;
	
	private Integer availableBalance;

	private GameLevel level;

	public Player(String name) {
		this.setId(name);
		this.name = name;
		this.experience = 0;
		this.availableBalance = 0;
		this.health = 100;
	}

	public Player(String id, String name, Weapon weapon) {
		this.setId(id);
		this.name = name;
		this.weapon = weapon;
		this.health = 100;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	
	public void addExperience(Integer experience) {
		this.experience += experience;
	}

	public GameLevel getLevel() {
		return level;
	}

	public void setLevel(GameLevel level) {
		this.level = level;
	}

	public Integer getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Integer availableBalance) {
		this.availableBalance = availableBalance;
	}

	public void addBalance(Integer balance) {
		this.availableBalance += balance;
	}

	public boolean isAlive() {
		return health > 1;
	}
	
	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("Id=");
		sb.append(this.name);
		sb.append(",");
		sb.append("Name=");
		sb.append(this.name);
		sb.append(",");
		sb.append("AvailableBalance=");
		sb.append(Constants.DEFAULT_CURRENCY);
		sb.append(this.availableBalance);
		sb.append(",");
		sb.append("Experience=");
		sb.append(this.experience);
		sb.append(",");
		sb.append("Health=");
		sb.append(this.health);
		sb.append(",");
		if (this.level != null) {
			sb.append("Level=");
			sb.append(this.level.getId());
		} 
		sb.append(",");
		sb.append("Weapon=");
		if (this.weapon == null) {
			sb.append(Constants.EMPTY_WEAPON);
		} else {
			sb.append(this.weapon.getName());
		}
		sb.append("]");
		return sb.toString();
	}
}
