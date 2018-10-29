/**
 * 
 */
package com.folk.winner.dc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author fangbe
 *
 */
public class Race implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String ropePosition;
	private int order;
	private float length;
	private Date date;
	private List<RacingHorse> racingHorses;

	/**
	 * 
	 */
	public Race() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ropePosition
	 */
	public String getRopePosition() {
		return ropePosition;
	}

	/**
	 * @param ropePosition the ropePosition to set
	 */
	public void setRopePosition(String ropePosition) {
		this.ropePosition = ropePosition;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the length
	 */
	public float getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(float length) {
		this.length = length;
	}

	/**
	 * @return the racingHorses
	 */
	public List<RacingHorse> getRacingHorses() {
		return racingHorses;
	}

	/**
	 * @param racingHorses the racingHorses to set
	 */
	public void setRacingHorses(List<RacingHorse> racingHorses) {
		this.racingHorses = racingHorses;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
