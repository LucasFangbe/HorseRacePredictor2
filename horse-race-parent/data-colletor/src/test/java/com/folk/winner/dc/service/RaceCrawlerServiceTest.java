/**
 * 
 */
package com.folk.winner.dc.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.folk.winner.dc.domain.Race;
import com.folk.winner.dc.repository.CoachCrawlerRepository;
import com.folk.winner.dc.repository.JockeyCrawlerRepository;
import com.folk.winner.dc.repository.RaceCrawlerRepository;
import com.folk.winner.dc.service.RaceCrawlerService;
import com.folk.winner.dc.service.RaceCrawlerServiceImpl;

/**
 * @author fangbe
 *
 */
@RunWith(SpringRunner.class)
public class RaceCrawlerServiceTest {
	
	private static final String PAGE_URL = "src/test/resources/race-page.html";

	/**
	 * 
	 * @author fangbe
	 *
	 */
	@TestConfiguration
	public static class RaceCrawlerServiceTestContextConfiguration {
		
		@Bean
		public RaceCrawlerService crawler(){
			return new RaceCrawlerServiceImpl();
		}
		
	}
	
	@Autowired
	private RaceCrawlerService crawler;
	
	@MockBean
	private RaceCrawlerRepository repository;
	
	@MockBean
	private JockeyCrawlerRepository jockeyCrawlerRepository;
	
	@MockBean
	private CoachCrawlerRepository coachCrawlerRepository;

	/**
	 * 
	 */
	@Before
	public void setUp() {
		String url = PAGE_URL;
		
		Race race = new Race();
		race.setName("Prix des Equidays");
		race.setOrder(2);
		race.setLength(1900);
		race.setRopePosition("D");
		
		Mockito.when(repository.read(url)).thenReturn(race);
	}
	
	/**
	 * 
	 */
	@Test
	public void testCrawl(){
		Race race = crawler.crawl(PAGE_URL);
		Assert.assertEquals(race.getName(), "Prix des Equidays");
		Assert.assertEquals(race.getOrder(), 2);
	}

}
