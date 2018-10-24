/**
 * 
 */
package com.folk.winner.dc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author fangbe
 *
 */
public abstract class AbstractCrawlerRepository {

	protected WebDriver webDriver;

	@Value("${authentication.login}")
	protected String login;

	@Value("${authentication.password}")
	protected String password;
	
	@Value("${greeting.message}")
	protected String greetingMessage;

	/**
	 * 
	 */
	public AbstractCrawlerRepository() {
		setWebDriver();
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless");
		webDriver = new ChromeDriver(options);
	}
	
	/**
	 * 
	 */
	public void finalize() {
		webDriver.close();
		webDriver.quit();
	}

	/**
	 * 
	 */
	private void setWebDriver() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0) {
			System.setProperty("webdriver.chrome.driver", "drivers/windows/chromedriver.exe");
		} else if (os.indexOf("mac") >= 0) {
			System.setProperty("webdriver.chrome.driver", "drivers/mac/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", "drivers/linux/chromedriver");
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean isAuthenticated() {
		WebElement element = findElementOrNull("//div[@id='msg-ol-ctx']");
		if (element == null) {
			// try this element also for detail page of horse
			element = findElementOrNull("//div[@id='bu-abonne']/div[1]");
		}

		if (element != null && element.getText() != null && element.getText().contains(greetingMessage)) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @throws Exception
	 */
	protected void authenticate() {
		if(!isAuthenticated()) {
			WebElement loginElt = webDriver.findElement(By.id("loginDiv-field"));
			WebElement passwordElt = webDriver.findElement(By.id("passwordDiv-field"));

			if (loginElt == null || passwordElt == null) {
				return;
			}

			loginElt.sendKeys(this.login);
			passwordElt.sendKeys(this.password);

			passwordElt.submit();
			
			print();
		}
	}
	
	/**
	 * 
	 * @param xpath
	 * @return
	 */
	protected WebElement findElementOrNull(String xpath) {
		try {
			return webDriver.findElement(By.xpath(xpath));
		} catch (NoSuchElementException e) {
			// element not found
			return null;
		}
	}
	
	/**
	 * 
	 */
	protected void print() {
		//System.out.println(webDriver.getPageSource());
		webDriver.getPageSource();
	}
	
	/**
	 * @param url
	 */
	protected void load(String url) {
		webDriver.get(url);
		print();
	}
	
	/**
	 * 
	 * @param xpath
	 * @param attributeName
	 * @param extractorFunction
	 * @return
	 */
	private Optional<List<String>> doExtract(String xpath, String attributeName,
			BiFunction<WebElement, String, String> extractorFunction) {
		List<? extends WebElement> elements = webDriver.findElements(By.xpath(xpath));
		
		if (elements == null || elements.isEmpty()) {
			return Optional.ofNullable(null);
		}

		List<String> result = new ArrayList<>(elements.size());
		for (WebElement h : elements) {
			result.add(extractorFunction.apply(h, attributeName));
		}

		return Optional.of(result);
	}
	
	/**
	 * 
	 * @param h
	 * @return
	 */
	private static String getContent(WebElement h, String extra) {
		return h.getText();
	}

	/**
	 * 
	 * @param h
	 * @param attributeName
	 * @return
	 */
	private static String getAttributeValue(WebElement h, String attributeName) {
		return h.getAttribute(attributeName);
	}
	
	/**
	 * 
	 * @param xpath
	 * @return
	 */
	public Optional<List<String>> getContentValues(String xpath) {
		return doExtract(xpath, null, AbstractCrawlerRepository::getContent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.folk.winner.horse.race.predictor.scrape.Spider#getAttributeValues(java.
	 * lang.String, java.lang.String)
	 */
	public Optional<List<String>> getAttributeValues(String xpath, String attributeName) {
		return doExtract(xpath, attributeName, AbstractCrawlerRepository::getAttributeValue);
	}

}
