package com.demo.base;

import java.net.MalformedURLException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.demo.listener.EventHandler;

/**
 * 
 * @author Nellore Krishna Kumar
 *
 */
public class SeleniumTestBase {

	protected static ThreadLocal<WebDriver> threadDriver = null;

	ProcessBuilder builder = null;
	
	@BeforeTest
	public void setUp(ITestContext aTestContext) throws MalformedURLException {

		threadDriver = new ThreadLocal<WebDriver>();
		String browserParameter = aTestContext.getCurrentXmlTest()
				.getParameter("browser");
		if ((browserParameter.equalsIgnoreCase("chrome"))) {
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			String[] switches = { "--ignore-certificate-errors",
					"--disable-popup-blocking", "--disable-translate" };
			dc.setCapability("chrome.switches", Arrays.asList(switches));
			String chromeDriverPath = "src/main/resources/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			threadDriver.set(new ChromeDriver());
		} else if (browserParameter.equalsIgnoreCase("firefox")) {
			threadDriver.set(new FirefoxDriver());
		}

	}

	public static WebDriver getDriver() {
		WebDriver driver = threadDriver.get();
		EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
		EventHandler handler = new EventHandler();
		eventDriver.register(handler);
		return eventDriver;
	}

	@AfterTest
	public void closeBrowser() {
		if (threadDriver != null) {
			getDriver().quit();
		}
	}
}
