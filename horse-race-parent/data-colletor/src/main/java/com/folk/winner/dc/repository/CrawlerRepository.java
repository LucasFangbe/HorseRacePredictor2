/**
 * 
 */
package com.folk.winner.dc.repository;

/**
 * @author fangbe
 *
 */
public interface CrawlerRepository<T> {

	/**
	 * 
	 * @param url
	 * @return
	 */
	T read(String url);

}
