package com.demo.examples;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.base.SeleniumTestBase;
import com.demo.coverage.Report;
import com.demo.mainpage.Calculator;

/**
 * An Example for Code Coverage for JavaScript Application
 * 
 * @author Nellore Krishna Kumar
 *
 */

//@Listeners({ com.demo.listener.EventHandler.class }) [If enabled we can debug listener]
public class CodeCoverageExample extends SeleniumTestBase{

	String appUrl = "http://127.0.0.1:3456/app/index.html";
	
	@Test
	public void demoClass() throws InterruptedException {
		Calculator.openMainPage();		
		Calculator.setURL(appUrl);
		Calculator.enterFirstText("1");
		Calculator.enterSecondText("2");
		Calculator.clickGoButton();		
		Assert.assertEquals(Report.getCoverageText().get(0),"240");
		Assert.assertEquals(Report.getCoverageText().get(1),"212");
	}

}
