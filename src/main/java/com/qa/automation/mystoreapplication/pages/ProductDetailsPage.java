package com.qa.automation.mystoreapplication.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.ui.selenium.Verify;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.atmecs.falcon.automation.verifyresult.VerificationManager;
import com.qa.automation.mystoreapplication.config.MyStoreAppConstants;

import com.qa.automation.mystoreapplication.utilities.MyStoreUtility;


/**
 * It contains methods which are use to add the item into cart and it extends MyStoreUtility class.
 * @author mir.ali
 *
 */
public class ProductDetailsPage {
	Browser browser;
	JavascriptExecutor executor;
	
	private ReportLogService report=new ReportLogServiceImpl(ProductDetailsPage.class);

	static Properties productDetailProperties=MyStoreUtility.loadProperties(MyStoreAppConstants.PRODUCTDETAILS_PROPERTIES_FILE);
	
	String testDataFilePath=MyStoreAppConstants.TESTDATA_EXCELFILE_PATH;
	String addToCartSheet="addtocart";
	
	public ProductDetailsPage(Browser browser)
	{
		this.browser=browser;
	}
	
	
	// clicking on the add to cart button
	public void clickAddToCartBtn()
	{
		report.info("click on add to cart button");
		String btnaddtocart=productDetailProperties.getProperty("btnaddtocart");
		browser.getClick().performClick(LocatorType.NAME, btnaddtocart);
		
	}
	
	// updating the quantity of the product selected on add to cart page
	public void updateQuantity()
	{
		String quantity=MyStoreUtility.readDataByColumnName(testDataFilePath, addToCartSheet, "quantity");
		report.info("updating the item quantity");
		String qty=productDetailProperties.getProperty("txtquantity");
		browser.getFindFromBrowser().findElementByXpath(qty).clear();
		browser.getTextField().enterTextField(LocatorType.XPATH,qty,quantity);
	}
	
	
	// updating the size of selected product at add to cart page.
	public void updateSize()
	{	
		String size=MyStoreUtility.readDataByColumnName(testDataFilePath, addToCartSheet, "sizebyvalue");
		report.info("updating the item size");
		browser.getSelectDropDown().selectByValue(LocatorType.ID, "group_1", size);
	}

	
// validating the cart text on ProductDetails page	
	public void validateCartTextOnProductDetailsPage()
	{	
		String exp_carttext=MyStoreUtility.readDataByColumnName(testDataFilePath, addToCartSheet, "carttext");
		browser.getWait().HardPause(2000);
		String carttext=productDetailProperties.getProperty("carttext");
		String actualtext=browser.getFindFromBrowser().findElementByXpath(carttext).getText();
		VerificationManager.verifyString(actualtext, exp_carttext, "verifying number of products added to cart");	
	}

	
// click on proceed to checkout button
	public void clickProceedToCheckOut()
	{	
		report.info("click on proceed to checkout button");
		String proc_chkout=productDetailProperties.getProperty("btnproceed_chkout");
		WebElement proceedtochkoutbtn=browser.getDriver().findElement(By.xpath(proc_chkout));
		
		MyStoreUtility.waitForElementToBeClickable(proceedtochkoutbtn, browser);
		executor=(JavascriptExecutor)browser.getDriver();
		executor.executeScript("arguments[0].click();",proceedtochkoutbtn);
		System.out.println("proceed to check out is clicked");
	}
	
// Clicking on continue shopping button
	public void clickContinueShoppingbutton()
	{
		
		report.info("click on continue shopping button");
		String ctnshopbtn=productDetailProperties.getProperty("btncntshop");
		System.out.println("waiting for element");
		
		WebElement continueshoppingbtn=browser.getDriver().findElement(By.xpath(ctnshopbtn));
		MyStoreUtility.waitForElementToBeClickable(continueshoppingbtn, browser);
		executor=(JavascriptExecutor)browser.getDriver();
		executor.executeScript("arguments[0].click();", continueshoppingbtn);
		System.out.println("Continue shopping button is clicked");
		
	}
	
// verifying the product detail on product Details page	
	public void verifyProductDetails()
	{	
		
		String exp_prodname=MyStoreUtility.readDataByColumnName(testDataFilePath, addToCartSheet, "prodname");
		String prod_modelname=productDetailProperties.getProperty("prod_modelname");
		WebElement ele_modelname=browser.getFindFromBrowser().findElementByXpath(prod_modelname);
		MyStoreUtility.waitForElementToBeVisible(ele_modelname, browser);
		String act_modelname=ele_modelname.getText();
		Verify.verifyString(act_modelname, exp_prodname, "Verifying product model name on Product details page");
		
		String exp_prodprice=MyStoreUtility.readDataByColumnName(testDataFilePath, addToCartSheet, "prodprice");
		String prod_price=productDetailProperties.getProperty("prod_price");
		String act_prodprice=browser.getFindFromBrowser().findElementByXpath(prod_price).getText();
		Verify.verifyString(act_prodprice, exp_prodprice, "Verifying the product price on product details page ");
		
		
	}
	
}
