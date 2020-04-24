package com.qa.automation.mystoreapplication.pages;

import java.util.Properties;

import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.qa.automation.mystoreapplication.config.MyStoreAppConstants;
import com.qa.automation.mystoreapplication.utilities.MyStoreUtility;


/**
 * this class contains the methods to perform the actions on Sign in page of MyStore application
 * @author mir.ali
 *
 */
public class SignInPage {
		
	 Browser browser;
		
		
private ReportLogService report=new ReportLogServiceImpl(SignInPage.class);

static Properties signInProperties=MyStoreUtility.loadProperties(MyStoreAppConstants.SIGNIN_PROPERTIES_FILE);


	public SignInPage(Browser browser)
	{
			this.browser=browser;
	}

// Click on Sign in link
	public void clickSignInLink()
	{	
		report.info("clicking on sign in link");
		String signinlink=signInProperties.getProperty("signinLink");
		browser.getClick().performClick(LocatorType.XPATH, signinlink);	
	}
	
// entering user name	
	public void enterUserName(String uname)
	{
		report.info("enter the user name");
		
		String username=signInProperties.getProperty("txtusername");
		WebElement ele_username=browser.getFindFromBrowser().findElementById(username);
		MyStoreUtility.waitForElementToBeVisible(ele_username, browser);
		
		browser.getTextField().enterTextField(LocatorType.ID, username,uname);
	}
// entering password	
	public void enterPassword(String pwd)
	{	
		report.info("enter the password");
		String password=signInProperties.getProperty("txtpassword");
		browser.getTextField().enterTextField(LocatorType.ID, password, pwd);
	}
	
// clicking on sign in button	
	public void clickSignInBtn()
	{
		report.info("click on sign in button");
		String signbtn=signInProperties.getProperty("btnsignin");
		browser.getClick().performClick(LocatorType.XPATH, signbtn);
	}
	
	
}
