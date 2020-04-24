/***
 *
 */
package com.qa.automation.mystoreapplication.testsuite;



import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Parameters;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.ui.selenium.Verify;
import com.atmecs.falcon.automation.util.logging.LogLevel;
import com.atmecs.falcon.automation.util.logging.LogManager;
import com.atmecs.falcon.automation.util.parser.PropertyParser;

import com.qa.automation.mystoreapplication.commonfunctions.SignInApp;
import com.qa.automation.mystoreapplication.pages.ProductDetailsPage;
import com.qa.automation.mystoreapplication.pages.ShippingCheckoutPage;
import com.qa.automation.mystoreapplication.pages.ShoppingCartCheckoutPage;
import com.qa.automation.mystoreapplication.pages.AddressCheckoutPage;

import com.qa.automation.mystoreapplication.pages.HomePage;
import com.qa.automation.mystoreapplication.pages.OrderDetailsPage;
import com.qa.automation.mystoreapplication.pages.PaymentPage;
import com.qa.automation.mystoreapplication.pages.DressesPage;
import com.qa.automation.mystoreapplication.pages.SignInPage;

public class TestSuiteBase {
	
	protected Browser browser;
	protected SignInApp signIn;
	protected SignInPage signInApplication;
	protected HomePage home;
	protected ProductDetailsPage productDetails;
	protected AddressCheckoutPage addressCheckout;
	protected ShippingCheckoutPage shippingCheckout;
	protected ShoppingCartCheckoutPage	shoppingCartCheckout;
	protected PaymentPage payment;
	protected DressesPage dresses;
	protected OrderDetailsPage orderDetail;
	
	
// Initializing the all the pages objects in before suite	
    @BeforeSuite
    public void preSetup() {
       browser = new Browser();
        LogManager.setLogLevel(LogLevel.valueOf(PropertyParser.readEnvOrConfigProperty("LOG_LEVEL")));
        
       signIn=new SignInApp(browser);
	       signInApplication=new SignInPage(browser);
	       home=new HomePage(browser);
	       productDetails=new ProductDetailsPage(browser);
	       addressCheckout=new AddressCheckoutPage(browser);
	       shippingCheckout=new ShippingCheckoutPage(browser);
	       shoppingCartCheckout=new ShoppingCartCheckoutPage(browser);
	       payment=new PaymentPage(browser);
	       dresses=new DressesPage(browser);
	       orderDetail=new OrderDetailsPage(browser);
	       
    }

   @AfterSuite
    public void teardown() {
        browser.closeBrowser();
    }
    
 // Launching the browser  
    @BeforeClass(alwaysRun=true)
    @Parameters({"os", "osVersion", "browser", "browserVersion"})
    public void launchApplication(String os, String osVersion, String br, String browserVersion) {
		browser.openURL("http://automationpractice.com/index.php", os, osVersion, br, browserVersion);
		browser.getWait().pageLoadTimeout(10000);
		System.out.println("Maximizing browser window");
		browser.maximizeWindow();
		String mystoretitle=browser.getCurrentPageTitle();
		Verify.verifyString(mystoretitle,"My Store", "Verifying the application title");
   	
}
}
    
