/**
 * 
 */
package com.folk.winner.dc.repository;

import java.util.List;
import java.util.Optional;

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
		load(url);
		authenticate();
		
		Race race = new Race();
		setNameAndOrder(race);
		setLengthAndRopePosition(race);
		
		return race;
		
	}

	/**
	 * @param race
	 */
	protected void setLengthAndRopePosition(Race race) {
		Optional<List<String>> raceInfo = getContentValues("//*[@id='yui-main']/div/div[2]/span[1]");
		raceInfo.ifPresent(c -> {
			String content = c.get(0);
			
			int pos1 = content.indexOf("€ - ");
			int pos2 = content.indexOf("m - ", pos1 + 4);
			String length = content.substring(pos1 + 4, pos2);
			race.setLength(Float.valueOf(length));
			
			int p1 = content.indexOf(" - corde : à ");
			String position = content.substring(p1 + 13, p1 + 13 + 6);
			race.setRopePosition("droite".equalsIgnoreCase(position) ? "D" : ("gauche".equalsIgnoreCase(position) ? "G" : null));
		});
	}

	/**
	 * @param race
	 */
	private void setNameAndOrder(Race race) {
		Optional<List<String>> namesOptional = getContentValues("//*[@id='yui-main']/div/div[2]/div[3]/span/strong");
		namesOptional.ifPresent(c -> {
			String content = c.get(0);
			String[] values = content.split("-");
			race.setName(values[1].trim());
			
			String order = values[0].trim().replace("ère course", "").replace("ème course", "");
			race.setOrder(Integer.valueOf(order));
		});
	}

}
