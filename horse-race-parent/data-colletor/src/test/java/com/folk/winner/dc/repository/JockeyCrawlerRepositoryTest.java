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
public class JockeyCrawlerRepositoryTest {
	
	private static final String PAGE_URL = "src/test/resources/jockey-page.html";
	
	@TestConfiguration
	static class JockeyCrawlerRepositoryTestContextConfiguration {
		
		/**
		 * 
		 * @return
		 */
		@Bean
		public JockeyCrawlerRepository jockeyCrawlerRepository(){
			return new JockeyCrawlerRepositoryImpl();
		}
		
	}
	
	@Autowired
	private JockeyCrawlerRepository jockeyCrawlerRepository;
	
	/**
	 * 
	 */
	@Test
	public void testRead() {
		try {
			File page = new File(PAGE_URL);
			String url = page.toURI().toURL().toString();
			Float performance = jockeyCrawlerRepository.read(url);
			
			Assert.assertEquals(0.44f, performance.floatValue(), 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

}
