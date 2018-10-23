/**
 * 
 */
package com.folk.winner.dc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folk.winner.dc.domain.Race;
import com.folk.winner.dc.repository.CrawlerRepository;

/**
 * @author fangbe
 *
 */
@Service
public class RaceCrawlerService implements CrawlerService<Race> {
	
	@Autowired
	private CrawlerRepository<Race> crawlerRepository;

	/**
	 * 
	 */
	public RaceCrawlerService() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see com.folk.winner.dc.service.crawler.PageCrawler#crawl(java.lang.String)
	 */
	@Override
	public Race crawl(String url) {
		Race race = crawlerRepository.read(url);
		return race;
	}

}
