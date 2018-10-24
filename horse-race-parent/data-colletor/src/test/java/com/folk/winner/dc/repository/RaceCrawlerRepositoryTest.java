/**
 * 
 */
package com.folk.winner.dc.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.folk.winner.dc.domain.Race;

/**
 * @author fangbe
 *
 */
@RunWith(SpringRunner.class)
public class RaceCrawlerRepositoryTest {
	
	private static final String PAGE_URL = "src/test/resources/race-page.html";
	
	@Autowired
	private RaceCrawlerRepository raceCrawlerRepository;
	
	/**
	 * 
	 */
	public void testRead() {
		Race race = raceCrawlerRepository.read(PAGE_URL);
	}

}
