package com.qa.automation.mystoreapplication.pages;


import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.ui.selenium.Verify;
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
public class ShoppingCartCheckoutPage{
	Browser browser;
	
	private ReportLogService report=new ReportLogServiceImpl(ShoppingCartCheckoutPage.class);

	static Properties shoppingCartProperties=MyStoreUtility.loadProperties(MyStoreAppConstants.SHOPPINGCARTCHECKOUT_PROPERTIES_FILE);
	
	ExcelDataRead testData = new ExcelDataRead();
	String testDataFilePath = MyStoreAppConstants.TESTDATA_EXCELFILE_PATH;
	String shoppingCartSummarySheet="prod_summary";
	
	
	public ShoppingCartCheckoutPage(Browser browser)
	{
		this.browser=browser;
	}
		
// 	click on the proceed to checkout on summary page
	public void clickChkOutOnSummaryPage()
	{	
		browser.getWait().HardPause(3000);
		String chkoutsummarybtn=shoppingCartProperties.getProperty("chkoutsummary");
		WebElement ele_chkoutsummarybtn=browser.getFindFromBrowser().findElementByXpath(chkoutsummarybtn);
	
		MyStoreUtility.waitForElementToBeClickable(ele_chkoutsummarybtn, browser);
		
		//browser.getClick().performClick(LocatorType.XPATH, chkoutsummarybtn);
		browser.getDriver().findElement(By.xpath(chkoutsummarybtn)).click();
	}
	
	
	
// Validating Cart text on Checkout page
	public void validateCartTextOnShoppingCartCheckout()
	{	
		String expectedcarttext=MyStoreUtility.readDataByColumnName(testDataFilePath, shoppingCartSummarySheet, "carttext");
		String carttext=shoppingCartProperties.getProperty("carttext");
		WebElement ele_carttext=browser.getFindFromBrowser().findElementByXpath(carttext);
		MyStoreUtility.waitForElementToBeVisible(ele_carttext, browser);
		String actualtext=browser.getFindFromBrowser().findElementByXpath(carttext).getText();
		VerificationManager.verifyString(actualtext, expectedcarttext, "verifying number of products added to cart");	
	}
	
	
// verifying the product details on shopping cart page	
	public void verifyProductDetailOnShoppingCart(){
		
		verifyProductModelNameOnShoppingCartSummary();
		verifyProductUnitPriceOnShoppingCartSummary();
		verifyProductQuantityOnShoppingCartSummary();
		verifyIndividualProductTotalPriceOnShoppingCartSummary();
		verifyProductFinalTotalPriceOnShoppingCartSummary();
		report.info("Shopping cart details have been verified");
	}

// verifying product model name on shopping cart summary page
	public void verifyProductModelNameOnShoppingCartSummary()
	{
		String[] exp_prodname=testData.readData(testDataFilePath, shoppingCartSummarySheet, "prodname");
		String shopcart_prod_modelname=shoppingCartProperties.getProperty("shopcart_prod_modelname");
		List<WebElement> act_prodname=browser.getDriver().findElements(By.xpath(shopcart_prod_modelname));
		MyStoreUtility.compareElements(exp_prodname, act_prodname);
		report.info("product name verified");
		
	}
	
// verifying the product unit price on shopping cart summary page
	public void verifyProductUnitPriceOnShoppingCartSummary()
	{
		String[] exp_produnitprice=testData.readData(testDataFilePath, shoppingCartSummarySheet, "unitprice");
		String shopcart_prod_unitprice=shoppingCartProperties.getProperty("shopcart_prod_unitprice");
		List<WebElement> act_produnitprice=browser.getDriver().findElements(By.xpath(shopcart_prod_unitprice));
		MyStoreUtility.compareElements(exp_produnitprice, act_produnitprice);
		report.info("product unit price verified");	
	}
	
// verifying the total price on individual product on shopping cart summary page	
	public void verifyIndividualProductTotalPriceOnShoppingCartSummary()
	{
		String[] exp_prodtotalprice=testData.readData(testDataFilePath, shoppingCartSummarySheet, "prodtotalprice");
		String shopcart_prod_totalprice=shoppingCartProperties.getProperty("shopcart_prod_ind_totalprice");
		List<WebElement> act_prodtotalprice=browser.getDriver().findElements(By.xpath(shopcart_prod_totalprice));
		MyStoreUtility.compareElements(exp_prodtotalprice, act_prodtotalprice);
		report.info("product total price verified");
	}
	
//	verifying the product quantity on shopping cart summary page
	public void verifyProductQuantityOnShoppingCartSummary()
	{
		//browser.getWait().HardPause(1000);
		String[] exp_prodqty=testData.readData(testDataFilePath, shoppingCartSummarySheet, "quantity");
		String shopcart_prod_quantity=shoppingCartProperties.getProperty("shopcart_prod_quantity");
		List<WebElement> act_prod_quantity=browser.getDriver().findElements(By.xpath(shopcart_prod_quantity));
		MyStoreUtility.compareByValue(exp_prodqty, act_prod_quantity);
		report.info("Product quantity verified");
	}

// verifying the final total price 
	public void verifyProductFinalTotalPriceOnShoppingCartSummary()
	{
		browser.getWait().HardPause(1000);
		String exp_finaltotalprice=MyStoreUtility.readDataByColumnName(testDataFilePath, shoppingCartSummarySheet, "finalprice");
		String shopcart_prod_totalprice=shoppingCartProperties.getProperty("shopcart_prod_finaltotalprice");
		WebElement ele_act_prod_totalprice=browser.getFindFromBrowser().findElementByXpath(shopcart_prod_totalprice);
		MyStoreUtility.waitForElementToBeVisible(ele_act_prod_totalprice, browser);
		String act_prod_totalprice=ele_act_prod_totalprice.getText().trim();
		Verify.verifyString(act_prod_totalprice, exp_finaltotalprice, "verifying the total price on shopping cart summry");
	}
	
	
	
	
}
