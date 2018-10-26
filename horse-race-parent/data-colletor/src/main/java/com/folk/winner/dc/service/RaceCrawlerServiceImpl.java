/**
 * 
 */
package com.folk.winner.dc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folk.winner.dc.domain.Race;
import com.folk.winner.dc.domain.RacingHorse;
import com.folk.winner.dc.repository.CoachCrawlerRepository;
import com.folk.winner.dc.repository.JockeyCrawlerRepository;
import com.folk.winner.dc.repository.RaceCrawlerRepository;

/**
 * @author fangbe
 *
 */
@Service
public class RaceCrawlerServiceImpl implements RaceCrawlerService {
	
	@Autowired
	private RaceCrawlerRepository raceCrawlerRepository;
	
	@Autowired
	private JockeyCrawlerRepository jockeyCrawlerRepository;
	
	@Autowired
	private CoachCrawlerRepository coachCrawlerRepository;

	/**
	 * 
	 */
	public RaceCrawlerServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see com.folk.winner.dc.service.crawler.PageCrawler#crawl(java.lang.String)
	 */
	@Override
	public Race crawl(String url) {
		Race race = raceCrawlerRepository.read(url);
		if (race.getRacingHorses() != null) {
			for (RacingHorse racingHorse : race.getRacingHorses()) {
				if (racingHorse.getJockey() != null && racingHorse.getJockey().getUrl() != null) {
					float performance = jockeyCrawlerRepository.read(racingHorse.getJockey().getUrl());
					racingHorse.getJockey().setPerformance(performance);
				}
				
				if (racingHorse.getCoach() != null && racingHorse.getCoach().getUrl() != null) {
					float performance = coachCrawlerRepository.read(racingHorse.getCoach().getUrl());
					racingHorse.getCoach().setPerformance(performance);
				}
			} 
		}
		return race;
	}

}
