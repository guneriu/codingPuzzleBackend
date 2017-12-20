package com.coding.puzzle.models;

/**
 * @author majidali
 *
 */
public class Weapon extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -99335039012663923L;

	private String name;

	private Integer price;

	private Integer killAward;
	
	private Integer damage;

	public Weapon(String id, String name, Integer price, Integer killAward, Integer damage) {
		this.setId(id);
		this.name = name;
		this.price = price;
		this.killAward = killAward;
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getKillAward() {
		return killAward;
	}

	public void setKillAward(Integer killAward) {
		this.killAward = killAward;
	}
	
	public Integer getDamage() {
		return damage;
	}
	
	public void setDamage(Integer damage) {
		this.damage = damage;
	}
}
