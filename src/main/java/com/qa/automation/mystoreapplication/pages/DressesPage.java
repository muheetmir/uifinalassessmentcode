package com.qa.automation.mystoreapplication.pages;

import java.util.Properties;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;

import com.qa.automation.mystoreapplication.config.MyStoreAppConstants;
import com.qa.automation.mystoreapplication.utilities.MyStoreUtility;

/**
 * it contains methods which are used to perform actions on shopping page of dresses page
 * @author mir.ali
 *
 */
public class DressesPage {
	 Browser browser;
	 WebDriverWait wait;
	
	private ReportLogService report=new ReportLogServiceImpl(DressesPage.class);
	
	static Properties dressesProperties=MyStoreUtility.loadProperties(MyStoreAppConstants.DRESSES_PROPERTIES_FILE);
	
	public DressesPage(Browser browser)
	{
		
		this.browser=browser;
	}
	
	// clicking on Dresses
	public void clickDresses()
	{
		String lnkDresses=dressesProperties.getProperty("lnkdresses");
		WebElement ele_lnkDresses=browser.getFindFromBrowser().findElementByXpath(lnkDresses);
		MyStoreUtility.waitForElementToBeClickable(ele_lnkDresses, browser);
		browser.getClick().performClick(LocatorType.XPATH, lnkDresses);
		
	}
	
	// selecting product from casual dresses category
	public void selectProductInCasualDresses()
	{	
		report.info("Click on dresses");
		clickDresses();
		report.info("click on casual dresses category");
		String lnkCasualDress=dressesProperties.getProperty("lnkcasualdresses");
		WebElement ele_lnkCasualDress=browser.getFindFromBrowser().findElementByXpath(lnkCasualDress);
		MyStoreUtility.waitForElementToBeClickable(ele_lnkCasualDress, browser);
		browser.getClick().performClick(LocatorType.XPATH, lnkCasualDress);
		
		
		String imgCasualDress=dressesProperties.getProperty("imgcasualdress");
		WebElement casualDressItem=browser.getDriver().findElement(By.xpath(imgCasualDress));
		browser.getPageScroll().down(600);
		MyStoreUtility.waitForElementToBeVisible(casualDressItem, browser);
		browser.getMouse().mouseHover(casualDressItem);
		report.info("selecting the product from casual dress category");
		String btn_more_casualDress=dressesProperties.getProperty("btnmore_casualdress");
		WebElement ele_casualDress=browser.getFindFromBrowser().findElementByXpath(btn_more_casualDress);
		MyStoreUtility.waitForElementToBeVisible(ele_casualDress, browser);
		browser.getClick().performClick(LocatorType.XPATH, btn_more_casualDress);
	}
	
//  selecting product from evening dresses category	
	public void selectProductInEveningDresses()
	{	
		
		
		report.info("click on dresses");
		clickDresses();
		
		report.info("click on everning dresses category");
		String lnkEveningDress=dressesProperties.getProperty("lnkeveningdresses");
		WebElement ele_lnkEvngDress=browser.getFindFromBrowser().findElementByXpath(lnkEveningDress);
		MyStoreUtility.waitForElementToBeClickable(ele_lnkEvngDress, browser);
		browser.getClick().performClick(LocatorType.XPATH, lnkEveningDress);
		
		
		String img_evengDress=dressesProperties.getProperty("imgeveningdress");
		WebElement ele_evngDressimg=browser.getDriver().findElement(By.xpath(img_evengDress));
		browser.getPageScroll().down(700);
		MyStoreUtility.waitForElementToBeVisible(ele_evngDressimg, browser);
		browser.getMouse().mouseHover(ele_evngDressimg);
		
		
		report.info("selecting the product from everning dress category");
		String btn_more_evngDress=dressesProperties.getProperty("btnmore_evngdress");
		WebElement ele_evngDress=browser.getFindFromBrowser().findElementByXpath(btn_more_evngDress);
		MyStoreUtility.waitForElementToBeVisible(ele_evngDress, browser);
		
		browser.getClick().performClick(LocatorType.XPATH, btn_more_evngDress);
	}
	
// selecting product from summer dresses category	
	public void selectProductInSummerDresses()
	{		
		report.info("click on dresses");
		clickDresses();
		
		
		String lnkSummerDress=dressesProperties.getProperty("lnkSummerDresses");
		WebElement ele_lnkSummerDress=browser.getFindFromBrowser().findElementByXpath(lnkSummerDress);
		MyStoreUtility.waitForElementToBeClickable(ele_lnkSummerDress, browser);
		report.info("click on summer dresses category");
		browser.getClick().performClick(LocatorType.XPATH, lnkSummerDress);
		
		
		String img_summerDress=dressesProperties.getProperty("imgsummerdress");
		WebElement ele_summerDressImg=browser.getDriver().findElement(By.xpath(img_summerDress));
		MyStoreUtility.waitForElementToBeVisible(ele_summerDressImg, browser);
		browser.getPageScroll().down(700);
		
		
		browser.getMouse().mouseHover(ele_summerDressImg);
		report.info("selecting the product from summary dresses category ");

		String btn_more_summerDress=dressesProperties.getProperty("btnmore_summerdress");
		WebElement ele_summerDress=browser.getFindFromBrowser().findElementByXpath(btn_more_summerDress);
		MyStoreUtility.waitForElementToBeVisible(ele_summerDress, browser);
		browser.getClick().performClick(LocatorType.XPATH, btn_more_summerDress);
		
	}
		
	
}
