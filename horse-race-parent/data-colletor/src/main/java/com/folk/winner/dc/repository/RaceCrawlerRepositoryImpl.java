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
public class RaceCrawlerRepositoryImpl extends AbstractCrawlerRepository implements RaceCrawlerRepository {
	
	
	/*
	 * (non-Javadoc)
	 * @see com.folk.winner.dc.repository.CrawlerRepository#read(java.lang.String)
	 */
	@Override
	public Race read(String url) {
		authenticate();
		
		
		
	}

}
