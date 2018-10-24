/**
 * 
 */
package com.folk.winner.dc.repository;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.folk.winner.dc.domain.Race;

/**
 * @author fangbe
 *
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class RaceCrawlerRepositoryTest {
	
	private static final String PAGE_URL = "src/test/resources/race-page.html";
	
	@TestConfiguration
	static class RaceCrawlerRepositoryTestContextConfiguration {
		
		/**
		 * 
		 * @return
		 */
		@Bean
		public RaceCrawlerRepository raceCrawlerRepository(){
			return new RaceCrawlerRepositoryImpl();
		}
		
	}
	
	@Autowired
	private RaceCrawlerRepository raceCrawlerRepository;
	
	/**
	 * 
	 */
	@Test
	public void testRead() {
		try {
			File page = new File(PAGE_URL);
			String url = page.toURI().toURL().toString();
			Race race = raceCrawlerRepository.read(url);
			
			Assert.assertEquals(1900, race.getLength(), 0);
			Assert.assertEquals(3, race.getOrder());
			Assert.assertEquals("Grand Handicap de la Fibrée", race.getName());
			Assert.assertEquals("D", race.getRopePosition());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

}
