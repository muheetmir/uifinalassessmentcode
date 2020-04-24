package com.qa.automation.mystoreapplication.pages;



import java.util.Properties;


import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;

import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.atmecs.falcon.automation.verifyresult.VerificationManager;
import com.qa.automation.mystoreapplication.config.MyStoreAppConstants;
import com.qa.automation.mystoreapplication.excelreader.ExcelDataRead;
import com.qa.automation.mystoreapplication.utilities.MyStoreUtility;


/**
 * this class contains the elements of checkout page and methods to perform checkout for placing the order
 * and it extends the MyStoreUtility class
 * @author mir.ali
 *
 */
public class ShippingCheckoutPage{
	Browser browser;
	
	private ReportLogService report=new ReportLogServiceImpl(ShippingCheckoutPage.class);

	static Properties shippingProperties=MyStoreUtility.loadProperties(MyStoreAppConstants.SHIPPING_PROPERTIES_FILE);
	
	ExcelDataRead testData = new ExcelDataRead();
	String testDataFilePath = MyStoreAppConstants.TESTDATA_EXCELFILE_PATH;
	String shoppingCartSummarySheet="prod_summary";
	
	
	public ShippingCheckoutPage(Browser browser)
	{
		this.browser=browser;
	}


//	 checkout on shipping page	
	public void checkoutShipping()
	{	
		String ship_exp_heading="SHIPPING";
		String ship_act_heading=browser.getFindFromBrowser().findElementByXpath(shippingProperties.getProperty("heading_shipping")).getText();
		VerificationManager.verifyString(ship_act_heading, ship_exp_heading, "Verifying shipping page heading");
		
		String termsofservice=shippingProperties.getProperty("chkboxTermsofservice");
		report.info("Accept the terms and conditions");
		browser.getClick().performClick(LocatorType.XPATH, termsofservice);
		report.info("click on checkout button");
		String chkoutshipping=shippingProperties.getProperty("chkoutshipping");
		WebElement ele_chkoutshipping=browser.getFindFromBrowser().findElementByName(chkoutshipping);
		MyStoreUtility.waitForElementToBeClickable(ele_chkoutshipping, browser);
		browser.getClick().performClick(LocatorType.NAME, chkoutshipping);
	}	
	
	

	
}
