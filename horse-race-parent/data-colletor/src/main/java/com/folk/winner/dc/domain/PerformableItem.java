/**
 * 
 */
package com.folk.winner.dc.domain;

import java.io.Serializable;

/**
 * @author fangbe
 *
 */
public class PerformableItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected float performance;
	protected transient String url;

	/**
	 * 
	 */
	public PerformableItem() {
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
	 * @return the performance
	 */
	public float getPerformance() {
		return performance;
	}

	/**
	 * @param performance the performance to set
	 */
	public void setPerformance(float performance) {
		this.performance = performance;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
