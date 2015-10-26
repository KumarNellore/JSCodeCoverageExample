package com.demo.mainpage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.demo.base.SeleniumTestBase;

/**
 * All Api of Calculator Page has to be updated here
 * 
 * @author Nellore Krishna Kumar
 *
 */
public class Calculator {

	private static final By firstInputBox = By
			.cssSelector("input[ng-model='first']");

	private static final By secondInputBox = By
			.cssSelector("input[ng-model='second']");

	private static final By button = By.tagName("button");

	static WebDriver driver;

	//Gets Driver instance
	static {
		driver = SeleniumTestBase.getDriver();
	}

	public static void openMainPage() {
		driver.get("http://127.0.0.1:3456/jscoverage.html");
	}

	/**
	 * Set URL in Iframe
	 * 
	 * @param appUrl
	 */
	public static void setURL(String appUrl) {		
		WebElement urlElement = driver.findElement(By.id("location"));
		urlElement.clear();
		urlElement.sendKeys(appUrl);
		urlElement.sendKeys(Keys.ENTER);
		driver.switchTo().frame("browserIframe");
	}

	/**
	 * 
	 * @param firstInput
	 */
	public static void enterFirstText(String firstInput) {
		driver.findElement(firstInputBox).sendKeys(firstInput);
	}

	/**
	 * 
	 * @param secondInput
	 */
	public static void enterSecondText(String secondInput) {
		driver.findElement(secondInputBox).sendKeys(secondInput);
	}

	/**
	 * Click on Go Button
	 */
	public static void clickGoButton() {
		driver.findElements(button).get(0).click();
		driver.switchTo().defaultContent();
	}

}
