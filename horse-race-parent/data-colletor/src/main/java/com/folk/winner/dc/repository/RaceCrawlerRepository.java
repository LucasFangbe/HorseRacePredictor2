/**
 * 
 */
package com.folk.winner.dc.repository;

import org.springframework.stereotype.Repository;

import com.folk.winner.dc.domain.Race;

/**
 * @author fangbe
 *
 */
@Repository
public class RaceCrawlerRepository implements CrawlerRepository<Race> {

	/**
	 * 
	 */
	public RaceCrawlerRepository() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see com.folk.winner.dc.repository.CrawlerRepository#read(java.lang.String)
	 */
	@Override
	public Race read(String url) {
		// TODO Auto-generated method stub
		return null;
	}

}
