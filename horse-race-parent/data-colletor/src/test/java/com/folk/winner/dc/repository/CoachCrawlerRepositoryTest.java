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

/**
 * @author fangbe
 *
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CoachCrawlerRepositoryTest {
	
	private static final String PAGE_URL = "src/test/resources/coach-page.html";
	
	@TestConfiguration
	static class CoachCrawlerRepositoryTestContextConfiguration {
		
		/**
		 * 
		 * @return
		 */
		@Bean
		public CoachCrawlerRepository coachCrawlerRepository(){
			return new CoachCrawlerRepositoryImpl();
		}
		
	}
	
	@Autowired
	private CoachCrawlerRepository coachCrawlerRepository;
	
	/**
	 * 
	 */
	@Test
	public void testRead() {
		try {
			File page = new File(PAGE_URL);
			String url = page.toURI().toURL().toString();
			Float performance = coachCrawlerRepository.read(url);
			
			Assert.assertEquals(0.5f, performance.floatValue(), 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

}
