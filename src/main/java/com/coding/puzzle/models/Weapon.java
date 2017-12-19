package com.coding.puzzle.models;

import com.coding.puzzle.util.Constants;

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

	public Weapon(String id, String name, Integer price, Integer killAward) {
		this.setId(id);
		this.name = name;
		this.price = price;
		this.killAward = killAward;
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("Id=");
		sb.append(this.getId());
		sb.append(",");
		sb.append("Name=");
		sb.append(this.name);
		sb.append(",");
		sb.append("Price=");
		sb.append(Constants.DEFAULT_CURRENCY);
		sb.append(this.price);
		sb.append(",");
		sb.append("KillAward=");
		sb.append(this.killAward);
		sb.append("]");
		return sb.toString();
	}
}
