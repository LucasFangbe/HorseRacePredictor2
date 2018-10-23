/**
 * 
 */
package com.folk.winner.dc.service;

/**
 * @author fangbe
 *
 */
public interface CrawlerService<T> {
	
	/**
	 * Crawles a specific page and return the specify objects
	 * 
	 * @param url
	 * @return
	 */
	T crawl(String url);

}
