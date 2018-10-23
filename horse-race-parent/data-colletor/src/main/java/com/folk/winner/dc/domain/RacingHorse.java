/**
 * 
 */
package com.folk.winner.dc.domain;

/**
 * @author fangbe
 *
 */
public class RacingHorse extends Horse {
	
	private int rope; // la corde
	private float weight;
	private String accessories;
	private Jokey jokey;
	private Race race;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public RacingHorse() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the rope
	 */
	public int getRope() {
		return rope;
	}

	/**
	 * @param rope the rope to set
	 */
	public void setRope(int rope) {
		this.rope = rope;
	}

	/**
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}

	/**
	 * @return the accessories
	 */
	public String getAccessories() {
		return accessories;
	}

	/**
	 * @param accessories the accessories to set
	 */
	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	/**
	 * @return the jokey
	 */
	public Jokey getJokey() {
		return jokey;
	}

	/**
	 * @param jokey the jokey to set
	 */
	public void setJokey(Jokey jokey) {
		this.jokey = jokey;
	}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	/**
	 * @param race the race to set
	 */
	public void setRace(Race race) {
		this.race = race;
	}
}
