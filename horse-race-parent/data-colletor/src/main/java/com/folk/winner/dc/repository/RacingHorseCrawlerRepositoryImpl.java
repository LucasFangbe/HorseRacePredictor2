/**
 * 
 */
package com.folk.winner.dc.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.folk.winner.dc.helper.RacingHorsePerformance;
import com.folk.winner.dc.helper.RacingHorsePerformance.RacingHorsePerformanceBuilder;

/**
 * @author fangbe
 *
 */
@Repository
public class RacingHorseCrawlerRepositoryImpl extends AbstractCrawlerRepository implements RacingHorseCrawlerRepository {

	/*
	 * (non-Javadoc)
	 * @see com.folk.winner.dc.repository.CrawlerRepository#read(java.lang.String)
	 */
	@Override
	public Void read(String url) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.folk.winner.dc.repository.RacingHorseCrawlerRepository#readForPerformance(java.lang.String, java.util.Date)
	 */
	@Override
	public RacingHorsePerformance readForPerformance(String url, Date date) {
		initRead(url);
		
		
		RacingHorsePerformanceBuilder racingHorsePerformanceBuilder = new RacingHorsePerformanceBuilder();
		
		Optional<List<String>> raceCountOptional = getContentValues("//*[@id='fc']/table[1]/tbody/tr[2]/td[2]");
		raceCountOptional.ifPresent(c -> {
			String content = c.get(0);
			racingHorsePerformanceBuilder.setRaceCount(Integer.valueOf(content));
		});
		
		Optional<List<String>> placedCountOptional = getContentValues("//*[@id='fc']/table[1]/tbody/tr[2]/td[4]");
		placedCountOptional.ifPresent(c -> {
			String content = c.get(0);
			racingHorsePerformanceBuilder.setPlacedCount(Integer.valueOf(content));
		});
		
		
		
		
		return null;
	}

	
}
