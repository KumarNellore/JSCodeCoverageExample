package com.demo.coverage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.base.SeleniumTestBase;
/**
 * Get Report of Summary tab shown in JsCoverage.html file
 * 
 * @author Nellore Krishna Kumar
 *
 */
public class Report {

	static By summaryTab = By.cssSelector("#tabs #summaryTab");
	static By rowCoverageCount = By
			.xpath("//*[contains(text(),'angular.min.js')]/../../td[@class='numeric']");
	static WebDriver driver;
	
	/**
	 * Static block to get driver instance
	 */
	static {
		driver = SeleniumTestBase.getDriver();
	}
	
	/**
	 * Get coverage count from Summary tab
	 * @return
	 */
	public static List<String> getCoverageText() {		
		driver.findElement(summaryTab).click();
		List<String> coverage = new ArrayList<String>();
		
		//Wait condition of xpath to be available in webpage
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver){
			return driver.findElement(rowCoverageCount).isDisplayed();	
			}
		});
		
		List<WebElement> coverageElement = driver
				.findElements(rowCoverageCount);
		if (coverageElement.size() > 0) {
			coverage.add(coverageElement.get(0).getText());
			coverage.add(coverageElement.get(1).getText());
		}
		return coverage;
	}
}
