/**
 * 
 */
package com.folk.winner.dc.service;

import java.util.ArrayList;
import java.util.List;

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

import com.folk.winner.dc.domain.Coach;
import com.folk.winner.dc.domain.Jockey;
import com.folk.winner.dc.domain.Race;
import com.folk.winner.dc.domain.RacingHorse;
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
		
		RacingHorse horse = new RacingHorse();
		Jockey j = new Jockey();
		Coach c = new Coach();
		
		j.setUrl("jockey-url");
		c.setUrl("coach-url");
		
		horse.setJockey(j);
		horse.setCoach(c);
		
		List<RacingHorse> horses = new ArrayList<>();
		horses.add(horse);
		
		race.setRacingHorses(horses);
		
		Mockito.when(repository.read(url)).thenReturn(race);
		Mockito.when(jockeyCrawlerRepository.read("jockey-url")).thenReturn(0.45f);
		Mockito.when(coachCrawlerRepository.read("coach-url")).thenReturn(0.55f);
	}
	
	/**
	 * 
	 */
	@Test
	public void testCrawl(){
		Race race = crawler.crawl(PAGE_URL);
		Assert.assertEquals("Prix des Equidays", race.getName());
		Assert.assertEquals(2, race.getOrder());
		
		Assert.assertEquals(0.45f, race.getRacingHorses().get(0).getJockey().getPerformance(), 0);
		Assert.assertEquals(0.55f, race.getRacingHorses().get(0).getCoach().getPerformance(), 0);
	}

}
