/**
 * 
 */
package com.folk.winner.dc.domain;

/**
 * @author fangbe
 *
 */
public class Horse extends PerformableItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private float value;
	private Coach coach;

	/**
	 * 
	 */
	public Horse() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the value
	 */
	public float getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(float value) {
		this.value = value;
	}

	/**
	 * @return the coach
	 */
	public Coach getCoach() {
		return coach;
	}

	/**
	 * @param coach the coach to set
	 */
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	
}
