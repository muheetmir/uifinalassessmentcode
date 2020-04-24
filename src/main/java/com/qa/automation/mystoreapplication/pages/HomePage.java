package com.qa.automation.mystoreapplication.pages;

import java.util.Properties;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.atmecs.falcon.automation.verifyresult.VerificationManager;
import com.qa.automation.mystoreapplication.config.MyStoreAppConstants;
import com.qa.automation.mystoreapplication.utilities.MyStoreUtility;

/**
 * it contains methods which are used to perform actions on home page elements.
 * @author mir.ali
 *
 */
public class HomePage {
	 Browser browser;
	JavascriptExecutor js;
	
	private ReportLogService report=new ReportLogServiceImpl(HomePage.class);
	
	static Properties homePageProperties=MyStoreUtility.loadProperties(MyStoreAppConstants.HOMEPAGE_PROPERTIES_FILE);
	
	public HomePage(Browser browser)
	{
		this.browser=browser;
	}
	
	
	//verifying the user authentication after login 
	public void isLoginSuccessfull()
	{	
		
		String uname=homePageProperties.getProperty("user_first_last_name");
		WebElement ele_accountusername=browser.getFindFromBrowser().findElementByXpath(uname);
		MyStoreUtility.waitForElementToBeVisible(ele_accountusername, browser);
		String useraccount=ele_accountusername.getText();
		VerificationManager.verifyString(useraccount, "test automation", "verifying user first and last name");
	}
			
	//click on sign out
	@SuppressWarnings("static-access")
	public void clickSignOut()
	{	
		report.info("click on sign out");
		browser.getRequiredCapabilities().internetExplorer().setCapability("nativeEvents", false);
		String signout=homePageProperties.getProperty("btnsignout");
		WebElement ele_signout=browser.getFindFromBrowser().findElementByXpath(signout);
		MyStoreUtility.waitForElementToBeClickable(ele_signout, browser);
		browser.getClick().performClick(LocatorType.XPATH, signout);
		report.info("Singout of application");
	}
	
// Click on Username link
	public void clickUserNameLink(){
		report.info("click on user name link");
		String lnkUserName=homePageProperties.getProperty("linkusername");
		browser.getClick().performClick(LocatorType.XPATH, lnkUserName);	
	}

// verifying the My account page title
	public void verifyMyAccountPageTitle()
	{
		String myAccount_title=browser.getCurrentPageTitle();
		VerificationManager.verifyString(myAccount_title, "My account - My Store", "Verify my account page title");
	}
	
	public void verifyProductCategories()
	{
		report.info("verifying the different product categories on home page");
		String lnkWomen=homePageProperties.getProperty("lnkwomen");
		browser.getFindFromBrowser().findElementByXpath(lnkWomen).isDisplayed();
		
		String lnkDresses=homePageProperties.getProperty("lnkdresses");
		browser.getFindFromBrowser().findElementByXpath(lnkDresses).isDisplayed();
		
		String lnkTshirts=homePageProperties.getProperty("lnktshirts");
		browser.getFindFromBrowser().findElementByXpath(lnkTshirts).isDisplayed();
		
	}
	
}
