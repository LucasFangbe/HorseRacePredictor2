/**
 * 
 */
package com.folk.winner.dc.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.folk.winner.dc.helper.RacingHorsePerformance;

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
		StringBuilder performance = new StringBuilder();
		
		Optional<List<String>> namesOptional = getContentValues(path);
		namesOptional.ifPresent(c -> {
			String marker = "Réussite à la place : ";
			String content = c.get(0);
			int pos1 = content.indexOf(marker);
			int pos2 = content.indexOf("%", pos1 + marker.length());
			if(pos1 >= 0 && pos2 >= 0){
				performance.append(content.substring(pos1 + marker.length(), pos2).replace(",", "."));
			}
		});
		
		return null;
	}

	
}
