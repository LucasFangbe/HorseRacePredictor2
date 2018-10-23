/**
 * 
 */
package com.folk.winner.crawler;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.folk.winner.dc.domain.Race;
import com.folk.winner.dc.domain.RacingHorse;
import com.folk.winner.dc.service.CrawlerService;

/**
 * @author fangbe
 *
 */
@RunWith(SpringRunner.class)
public class RacePageCrawlerTest {
	
	@Autowired
	private CrawlerService<Race> crawler;

	/**
	 * 
	 */
	public RacePageCrawlerTest() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	public void testCrawl(){
		String url = ""; //local file
		//PageCrawler crawler = new RacePageCrawler(url);
		Race race = crawler.crawl(url);
		
	}

}
