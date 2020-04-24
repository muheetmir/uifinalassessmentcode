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
 * this class contains the methods which are used to perform actions on payment page of My store application
 * @author mir.ali
 *
 */
public class PaymentPage {
	Browser browser;
	
	private ReportLogService report=new ReportLogServiceImpl(PaymentPage.class);

	static Properties paymentProperties=MyStoreUtility.loadProperties(MyStoreAppConstants.PAYMENT_PROPERTIES_FILE);
	
	ExcelDataRead testData = new ExcelDataRead();
	String testDataFilePath = MyStoreAppConstants.TESTDATA_EXCELFILE_PATH;
	
	String paymentSheet="payment_prod_details";
	
	public PaymentPage(Browser browser)
	{
		this.browser=browser;
	}
	
	//clicking on the paybycheck link
	public void clickPayByCheck()
	{
		report.info("clicking on the pay by check link");
		String chequepay=paymentProperties.getProperty("lnkchequepay");
		browser.getClick().performClick(LocatorType.XPATH, chequepay);
	}

	// clicking on the confirm order button
	public void clickConfirmOrder()
	{
		report.info("clicking on the confirm order button");
		String confirmorder=paymentProperties.getProperty("btnconfirmorder");
		browser.getClick().performClick(LocatorType.XPATH, confirmorder);
	}
	
	// verifying the order successfully placed statement.
	public void verifyOrderPlaced()
	{
		String confirmordertext=paymentProperties.getProperty("orderplacedmessage");
		String ordermesssage= browser.getFindFromBrowser().findElementByXpath(confirmordertext).getText();
		VerificationManager.verifyString(ordermesssage, "Your order on My Store is complete.", "verify order confirmaiton");
		browser.getWait().HardPause(2000);
	}
	
// verifying the model name on payment page	
	public void checkProductModelNameOnPayment()
	{
		
		String[] exp_modelname=testData.readData(testDataFilePath, paymentSheet, "prodname");
		String paypg_prod_modelname=paymentProperties.getProperty("paypg_prod_modelname");
		List<WebElement> act_prod_modelnames=browser.getDriver().findElements(By.xpath(paypg_prod_modelname));
		MyStoreUtility.compareElements(exp_modelname, act_prod_modelnames);
		report.info("product name verified");
	}

	// verifying the unit price on payment page
	public void checkProductUnitPriceOnPayment()
	{
		
		String[] exp_unitprice=testData.readData(testDataFilePath, paymentSheet, "untiprice");
		String paypg_prod_unitprice=paymentProperties.getProperty("paypg_prod_unitprice");
		List<WebElement> act_prod_unitprice=browser.getDriver().findElements(By.xpath(paypg_prod_unitprice));
		MyStoreUtility.compareElements(exp_unitprice, act_prod_unitprice);
		report.info("unit  price has been verified");
	}

// verifyiing the product quanitty on payment page	
	public void checkProductQuantityOnPayment()
	{
		
		String[] exp_quantity=testData.readData(testDataFilePath, paymentSheet, "quantity");
		String paypg_prod_qty=paymentProperties.getProperty("paypg_prod_qty");
		List<WebElement> act_prod_qty=browser.getDriver().findElements(By.xpath(paypg_prod_qty));
		MyStoreUtility.compareElements(exp_quantity, act_prod_qty);
		report.info("Quantity has been verified");
	}
	
// Verfiying the Fianl total price on payment page
	public void checkProductFinalTotalPriceOnPayment()
	{
		String exp_totalprice=MyStoreUtility.readDataByColumnName(testDataFilePath, paymentSheet, "totalprice");
		
		String paypg_prod_totalprice=paymentProperties.getProperty("paypg_prod_totalprice");
		String act_prod_totalprice=browser.getFindFromBrowser().findElementByXpath(paypg_prod_totalprice).getText().trim();
		Verify.verifyString(act_prod_totalprice, exp_totalprice, "Verifying the total price on payment page");
	}
	

	
	
	
	
	
}
