/**
 * 
 */
package com.folk.winner.dc.repository;

import org.springframework.stereotype.Repository;

/**
 * @author fangbe
 *
 */
@Repository
public class CoachCrawlerRepositoryImpl extends JockeyCrawlerRepositoryImpl implements CoachCrawlerRepository {
	
	/**
	 * 
	 */
	public CoachCrawlerRepositoryImpl() {
		super("//*[@id='entraineur-pmu']/div[1]/div[2]");
	}
}
