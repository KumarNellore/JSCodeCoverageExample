package com.demo.listener;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.velocity.VelocityContext;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.uncommons.reportng.HTMLReporter;

import com.demo.base.ReportUtils;
import com.demo.base.SeleniumTestBase;

/**
 * 
 * @author Nellore Krishna Kumar
 *
 */
public class EventHandler extends HTMLReporter implements
		WebDriverEventListener, ITestListener {

	static Logger logger = null;

	static {
		logger = Logger.getLogger("EventHandler");
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
	}

	public static final String DRIVER_ATTRIBUTE = "driver";
	private static final String UTILS_KEY = "utils";
	private static final ReportUtils REPORT_UTILS = new ReportUtils();

	protected VelocityContext createContext() {
		final VelocityContext context = super.createContext();
		context.put(UTILS_KEY, REPORT_UTILS);
		return context;
	}

	/**
	 * Take screen shot of application
	 * 
	 * @param result
	 * @param driver
	 */
	private void createScreenshot(final ITestResult result,
			final WebDriver driver) {
		final DateFormat timeFormat = new SimpleDateFormat(
				"MM.dd.yyyy HH-mm-ss");
		final String fileName = result.getMethod().getMethodName() + "_"
				+ timeFormat.format(new Date());
		try {
			File scrFile;

			if (driver.getClass().equals(RemoteWebDriver.class)) {
				scrFile = ((TakesScreenshot) new Augmenter().augment(driver))
						.getScreenshotAs(OutputType.FILE);
			} else {
				scrFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
			}

			String outputDir = result.getTestContext().getOutputDirectory();
			outputDir = outputDir.substring(0, outputDir.lastIndexOf('\\'))
					+ "\\html";			
			final File saved = new File(outputDir, fileName + ".png");
			FileUtils.copyFile(scrFile, saved);
			logger.debug("Screen shot taken "+ saved.getName());
			result.setAttribute("screenshot", saved.getName());
		} catch (IOException e) {
			result.setAttribute("reportGeneratingException", e);
		}

		result.setAttribute("screenshotURL", driver.getCurrentUrl());
		result.removeAttribute(DRIVER_ATTRIBUTE);
	}

	public void afterChangeValueOf(WebElement arg0, WebDriver webDriver) {
		logger.debug("inside method afterChangeValueOf on " + arg0.toString());
	}

	public void afterClickOn(WebElement arg0, WebDriver webDriver) {
		logger.debug("inside method afterClickOn on " + arg0.toString());
	}

	public void afterFindBy(By arg0, WebElement arg1, WebDriver webDriver) {

		logger.debug("Find happened on  Using method " + webDriver.toString());
	}

	public void afterNavigateBack(WebDriver webDriver) {
		logger.info("Inside the after navigateback to "
				+ webDriver.getCurrentUrl());
	}

	public void afterNavigateForward(WebDriver webDriver) {
		logger.info("Inside the afterNavigateForward to "
				+ webDriver.getCurrentUrl());
	}

	public void afterNavigateTo(String arg0, WebDriver webDriver) {
		logger.info("Inside the afterNavigateTo to " + arg0);
	}

	public void afterScript(String arg0, WebDriver webDriver) {
		logger.info("Inside the afterScript to, Script is " + arg0);
	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver webDriver) {
		logger.info("Inside the beforeChangeValueOf method");
	}

	public void beforeClickOn(WebElement arg0, WebDriver webDriver) {
		logger.info("About to click on the " + arg0.toString());
	}

	public void beforeFindBy(By arg0, WebElement arg1, WebDriver webDriver) {
		if (arg1 == null) {
			logger.info("Just before finding element: " + arg0.toString()
					+ " on browser: " + webDriver.toString());

		} else {
			logger.info("Just before finding element: " + arg0.toString()
					+ " inside " + arg1.toString()
					+ " Web element on browser: " + webDriver.toString());
		}
		logger.info("Just before finding element " + webDriver.toString());
	}

	public void beforeNavigateBack(WebDriver webDriver) {
		logger.info("Just before beforeNavigateBack "
				+ webDriver.getCurrentUrl());
	}

	public void beforeNavigateForward(WebDriver webDriver) {
		logger.info("Just before beforeNavigateForward "
				+ webDriver.getCurrentUrl());
	}

	public void beforeNavigateTo(String arg0, WebDriver webDriver) {
		logger.info("Just before beforeNavigateTo " + arg0);
	}

	public void beforeScript(String arg0, WebDriver webDriver) {
		logger.info("Just before beforeScript " + arg0);
	}

	public void onException(Throwable arg0, WebDriver webDriver) {
		logger.info("Exception occured at " + arg0.getMessage());
	}

	public void onTestStart(ITestResult result) {
		logger.info("Execution of test case started " + result.getName());
	}
	
	/**
	 * Take screen shot of application, even if it pass
	 */
	public void onTestSuccess(ITestResult result) {
		WebDriver driver = SeleniumTestBase.getDriver();
		if (driver != null) {
			logger.info("Taking screen shot of pass test case " + result.getName());
			createScreenshot(result, driver);
			driver.quit();
		}
	}

	public void onTestFailure(ITestResult result) {
		WebDriver driver = SeleniumTestBase.getDriver();
		if (driver != null) {
			logger.info("Taking screen shot of failure test case " + result.getName());
			createScreenshot(result, driver);
		}
	}

	public void onTestSkipped(ITestResult result) {
		logger.info("Skipped Test Case Name" + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		for (ITestResult temp : failedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				failedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					failedTests.remove(temp);
				}
			}
		}
	}
}