package com.qa.automation.mystoreapplication.pages;



import java.util.Properties;


import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.ui.selenium.Verify;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;

import com.qa.automation.mystoreapplication.config.MyStoreAppConstants;


import com.qa.automation.mystoreapplication.utilities.MyStoreUtility;


/**
 * this class contains the elements of checkout page and methods to perform checkout for placing the order
 * and it extends the MyStoreUtility class
 * @author mir.ali
 *
 */
public class AddressCheckoutPage{
	Browser browser;
	
	private ReportLogService report=new ReportLogServiceImpl(AddressCheckoutPage.class);

	static Properties addressProperties=MyStoreUtility.loadProperties(MyStoreAppConstants.ADDRESSES_PROPERTIES_FILE);
	
	String testDataFilePath = MyStoreAppConstants.TESTDATA_EXCELFILE_PATH;
	String addressSheet="address";
	
	public AddressCheckoutPage(Browser browser)
	{
		this.browser=browser;
	}
		
	
// 	checkout on the address page
	public void checkOutAddress()
	{	
		String addr_expected_heading="ADDRESSES";
		WebElement ele_addr_act_heading=browser.getFindFromBrowser().findElementByXpath(addressProperties.getProperty("heading_addresses"));
		MyStoreUtility.waitForElementToBeVisible(ele_addr_act_heading, browser);
		String addr_actual_heading=ele_addr_act_heading.getText();
		System.out.println(addr_actual_heading);
		if(addr_actual_heading.contains(addr_expected_heading)){
			report.info("Addresses heading verified");
		}else{
			report.info("Addresses heading not verified");
		}
		
		report.info("click on proceed to checkout button on address page");
		String chkoutaddress=addressProperties.getProperty("chkoutaddress");
		WebElement ele_checkoutaddress=browser.getFindFromBrowser().findElementByName(chkoutaddress);
		MyStoreUtility.waitForElementToBeClickable(ele_checkoutaddress, browser);
		browser.getClick().performClick(LocatorType.NAME, chkoutaddress);
	}


// Verifying the address on address check out page	
	public void verifyAddressOnAddressCheckout()
	{
		String expected_address=MyStoreUtility.readDataByColumnName(testDataFilePath, addressSheet, "address1");
		String address=addressProperties.getProperty("txtaddress");
		String actual_address=browser.getFindFromBrowser().findElementByXpath(address).getText();
		Verify.verifyString(actual_address, expected_address, "Verifying the address on address page");
		
		
		
		String expected_ph_no=MyStoreUtility.readDataByColumnName(testDataFilePath, addressSheet, "phoneno");
		String cust_phn_num=addressProperties.getProperty("cust_phn_num");
		String actual_cust_phn_num=browser.getFindFromBrowser().findElementByXpath(cust_phn_num).getText();
		Verify.verifyString(actual_cust_phn_num, expected_ph_no, "Verfying the customer contact number on addresses page");
		
	}
	
}
