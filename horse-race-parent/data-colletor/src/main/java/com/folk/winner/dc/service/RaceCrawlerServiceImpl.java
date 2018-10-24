/**
 * 
 */
package com.folk.winner.dc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folk.winner.dc.domain.Race;
import com.folk.winner.dc.repository.RaceCrawlerRepository;

/**
 * @author fangbe
 *
 */
@Service
public class RaceCrawlerServiceImpl implements RaceCrawlerService {
	
	@Autowired
	private RaceCrawlerRepository raceCrawlerRepository;

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
		return race;
	}

}
