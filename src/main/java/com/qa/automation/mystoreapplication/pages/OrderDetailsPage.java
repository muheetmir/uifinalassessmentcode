package com.qa.automation.mystoreapplication.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;


import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;

import com.qa.automation.mystoreapplication.config.MyStoreAppConstants;
import com.qa.automation.mystoreapplication.excelreader.ExcelDataRead;
import com.qa.automation.mystoreapplication.utilities.MyStoreUtility;

/**
 * it contains methods which are used to perform actions on Order details page.
 * @author mir.ali
 *
 */
public class OrderDetailsPage {
	 Browser browser;
	
	private ReportLogService report=new ReportLogServiceImpl(OrderDetailsPage.class);
	
	static Properties orderDetailProperties=MyStoreUtility.loadProperties(MyStoreAppConstants.ORDERDETAIL_SPROPERTIES_FILE);
	
	ExcelDataRead testData = new ExcelDataRead();
	String testDataFilePath = MyStoreAppConstants.TESTDATA_EXCELFILE_PATH;
	String orderDetailsSheet="orderdetails";
	
	
// 	constructor	
	public OrderDetailsPage(Browser browser)
	{
		this.browser=browser;
	}
	
// 	clicking on the order details button
	public void clickOrderDetails()
	{	
		report.info("clicking on sign in link");
		String btnorderdetails=orderDetailProperties.getProperty("btnorderdetails");
		browser.getClick().performClick(LocatorType.XPATH, btnorderdetails);	
	}
	
	
// 	clicking on the order reference link	
	public void clickOrderrefLink()
	{	
		String order_ref=orderDetailProperties.getProperty("orderref");
		List<WebElement> reflink=browser.getDriver().findElements(By.xpath(order_ref));
		reflink.get(0).click();
		report.info("clicked on reference link");
	}
			
// verfiying the order detail reference	
	public void verifyOrderdetailreference()
	{
		verifyProductModelReference();
		verifyProductQuantity();
		verifyProductPrice();
		verifyTotalPrice();
		report.info("the product detail has been verified successfully");
		browser.getWait().HardPause(2000);
		browser.getPageScroll().up(1000);
	}
	
	
// verifying the product model reference on order details	
	public void verifyProductModelReference()
	{
		String[] exp_names=testData.readData(testDataFilePath, orderDetailsSheet, "productmodelname");
		String orderdetail_order_ref=orderDetailProperties.getProperty("orderdetail_ref_list");
		List<WebElement> act_modelnames=browser.getDriver().findElements(By.xpath(orderdetail_order_ref));
		MyStoreUtility.compareElements(exp_names, act_modelnames);
		report.info("product model name verified on order details");
		
	}

// verfying the product quantity on order details
	public void verifyProductQuantity()
	{
		String[] exp_prod_quantity=testData.readData(testDataFilePath, orderDetailsSheet, "productquantity");
		String orderdetail_qty=orderDetailProperties.getProperty("orderdetail_qty_list");
		List<WebElement> act_prod_quantity=browser.getDriver().findElements(By.xpath(orderdetail_qty));
		MyStoreUtility.compareElements(exp_prod_quantity, act_prod_quantity);
		report.info("product quantity verified on order details");
		
	}

// Verfiying the product price on order details
	public void verifyProductPrice()
	{
		String[] exp_prod_price=testData.readData(testDataFilePath, orderDetailsSheet, "productprice");
		String orderdetail_price=orderDetailProperties.getProperty("orderdetail_price_list");
		List<WebElement> act_prod_price=browser.getDriver().findElements(By.xpath(orderdetail_price));
		MyStoreUtility.compareElements(exp_prod_price, act_prod_price);	
		report.info("product price verified on order details");
	}
	
// Verifying the total price on Order details
	public void verifyTotalPrice()
	{
		String exp_totalPrice=MyStoreUtility.readDataByColumnName(testDataFilePath, orderDetailsSheet, "finaltotalprice");
		String ele_totalPrice=orderDetailProperties.getProperty("orderdetail_totalprice");
		WebElement ele_act_totalPrice_proDetails=browser.getFindFromBrowser().findElementByXpath(ele_totalPrice);
		MyStoreUtility.waitForElementToBeVisible(ele_act_totalPrice_proDetails, browser);
		String act_totalprice_proDetails=ele_act_totalPrice_proDetails.getText().trim();

		if(act_totalprice_proDetails.equalsIgnoreCase(exp_totalPrice)){
		System.out.println("total price has been verified on order details");
		
		}
		else{
			System.out.println("Total price is not matching with expected value");
		}
		
	}
	
	
	
	
	
}
