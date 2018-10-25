/**
 * 
 */
package com.folk.winner.dc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

/**
 * @author fangbe
 *
 */
@Repository
public class JockeyCrawlerRepositoryImpl extends AbstractCrawlerRepository implements JockeyCrawlerRepository {
	
	
	/*
	 * (non-Javadoc)
	 * @see com.folk.winner.dc.repository.CrawlerRepository#read(java.lang.String)
	 */
	@Override
	public Float read(String url) {
		initRead(url);
		StringBuilder performance = new StringBuilder();
		
		Optional<List<String>> namesOptional = getContentValues("//*[@id='jockey-pmu']/div[1]/div[2]");
		namesOptional.ifPresent(c -> {
			String marker = "Réussite à la place : ";
			String content = c.get(0);
			int pos1 = content.indexOf(marker);
			int pos2 = content.indexOf("%", pos1 + marker.length());
			if(pos1 >= 0 && pos2 >= 0){
				performance.append(content.substring(pos1, pos2).replace(",", "."));
			}
		});
		
		return performance.length() > 0 ? Float.valueOf(performance.toString()) : 0;
	}

	
}
