/**
 * 
 */
package com.folk.winner.dc.repository;

import java.util.Date;

import com.folk.winner.dc.helper.RacingHorsePerformance;

/**
 * @author fangbe
 *
 */
public interface RacingHorseCrawlerRepository extends CrawlerRepository<Void> {

	/**
	 * Just Read the performance from the horse details page
	 * 
	 * @param url
	 * @param date
	 * @return
	 */
	RacingHorsePerformance readForPerformance(String url, Date date);

}
