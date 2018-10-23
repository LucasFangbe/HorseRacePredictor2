/**
 * 
 */
package com.folk.winner.crawler;

import java.util.List;

/**
 * @author fangbe
 *
 */
public class RacePageCrawlerTest {

	/**
	 * 
	 */
	public RacePageCrawlerTest() {
		// TODO Auto-generated constructor stub
	}
	
	public void testCrawl(){
		RacePageCrawler crawler = new RacePageCrawler(url);
		List<HorseInfo> horseInfos = crawler.crawl();
	}

}
