/**
 * 
 */
package com.folk.winner.dc.repository;

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
}
