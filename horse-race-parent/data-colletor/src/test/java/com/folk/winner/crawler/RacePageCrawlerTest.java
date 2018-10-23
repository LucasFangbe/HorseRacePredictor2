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
	
	private PageCrawler crawler;

	/**
	 * 
	 */
	public RacePageCrawlerTest() {
		// TODO Auto-generated constructor stub
	}
	
	public void testCrawl(){
		String url = ""; //local file
		//PageCrawler crawler = new RacePageCrawler(url);
		List<HorseDecorator> horseDecorators = crawler.crawl(url);
		
	}

}
