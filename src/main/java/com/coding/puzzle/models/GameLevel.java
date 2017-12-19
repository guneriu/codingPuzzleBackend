
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

	private Integer timeout;

	public GameLevel(String id, String summary, Integer timeout) {
		this.setId(id);
		this.summary = summary;
		this.timeout = timeout;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
}
