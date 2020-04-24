package com.qa.automation.mystoreapplication.commonfunctions;


import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.qa.automation.mystoreapplication.config.MyStoreAppConstants;
import com.qa.automation.mystoreapplication.testsuite.TestSuiteBase;
import com.qa.automation.mystoreapplication.utilities.MyStoreUtility;

public class SignInApp extends TestSuiteBase{
	
	Browser browser;
	JavascriptExecutor executor;
	private ReportLogService report=new ReportLogServiceImpl(SignInApp.class);
	
	static Properties page=MyStoreUtility.loadProperties(MyStoreAppConstants.SIGNIN_PROPERTIES_FILE);
	
	public SignInApp(Browser browser)
	{
		this.browser=browser;
	}
	
	public void performSignIn()
	{
		report.info("clicking on sign in link");
		String signinlink=page.getProperty("signinLink");
		WebElement ele_signinlink=browser.getFindFromBrowser().findElementByXpath(signinlink);
		MyStoreUtility.waitForElementToBeClickable(ele_signinlink, browser);
		//browser.getWait().HardPause(2000);
		/*executor=(JavascriptExecutor)browser.getDriver();
		executor.executeScript("arguments[0].click();",ele_signinlink);*/
		
		browser.getClick().performClick(LocatorType.XPATH, signinlink);	
		
		report.info("Enter user name");
		String username=page.getProperty("txtusername");
		WebElement ele_username=browser.getFindFromBrowser().findElementById(username);
		MyStoreUtility.waitForElementToBeVisible(ele_username, browser);
		
		browser.getTextField().enterTextField(LocatorType.ID, username,"testautomation7416@gmail.com");

		report.info("enter the password");
		String password=page.getProperty("txtpassword");
		browser.getTextField().enterTextField(LocatorType.ID, password, "test123");
		
		report.info("click on sign in button");
		String signbtn=page.getProperty("btnsignin");
		browser.getClick().performClick(LocatorType.XPATH, signbtn);
		
		
	}
	
	
}

