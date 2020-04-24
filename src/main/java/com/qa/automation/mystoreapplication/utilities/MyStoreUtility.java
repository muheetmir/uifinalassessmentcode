package com.qa.automation.mystoreapplication.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.parser.XlsReader;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;


/**
 * This class acts as utility and contains different methods which can be re usable 
 * @author mir ali
 *
 */
public class MyStoreUtility {
	static Browser browser;
	Properties prop = new Properties();
	static XlsReader xlsReader =new XlsReader();
	 
	private static ReportLogService report = new ReportLogServiceImpl(MyStoreUtility.class);
/**
 * This method reads the properties file
 * @param propertyFilePath path of the file where it is located
 * @return object of properties class
 */
	 public static Properties loadProperties(String propertyFilePath ) { 
		 File file=new File(propertyFilePath);
		 FileInputStream fileInput=null; 
		 try {
			 fileInput=new FileInputStream(file);
		 }catch(FileNotFoundException e) { 
		e.printStackTrace(); } 
		Properties prop=new 
		Properties(); 
		try { 
			prop.load(fileInput); 
		}catch(IOException e) {
			e.printStackTrace();
			}
		return prop; 
	  }

	 
// 	 This method is used as utility and can be used when user wants to wait for elemnt to be get visible
 public static WebElement waitForElementToBeVisible(WebElement elementkey,Browser browser){
			WebDriverWait wait=new WebDriverWait(browser.getDriver(), 20);
			
			WebElement element=wait.until(ExpectedConditions.visibilityOf(elementkey));
			return element;
		}
 
// method is utility and can be used when waiting for element to be clickable 
 public static WebElement waitForElementToBeClickable(WebElement elementkey,Browser browser){
		WebDriverWait wait=new WebDriverWait(browser.getDriver(), 20);
		
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(elementkey));
		return element;
	}
 
// method is used as utility  and can be used when comparing the elements of array and list
 public static boolean compareElements(String[] arr,List<WebElement> ele){
	 
	List<String> liarr=new ArrayList<>(Arrays.asList(arr));
	  String elements=null;
	 for (WebElement we:ele) {
	         elements= we.getText().trim();
	   }
	 if(liarr.equals(elements)){
	return true;
	 }
	 return false;	 
 	}
 
 
// method is used as utilityy and can be used when comparing the value attribute of elements in array and list 
 public static boolean compareByValue(String[] arr,List<WebElement> ele){
	 
		List<String> liarr=new ArrayList<>(Arrays.asList(arr));
		  String elements=null;
		 for (WebElement we:ele) {
		         elements= we.getAttribute("value").trim();
		   }
		 if(liarr.equals(elements)){
		return true;
		 }
		 return false;	 
	 	}
	     
// this method is usable to read the data from excel by using column name	
	public static String readDataByColumnName(String testDataFilePath,String testDataSheetName,String ColumnName){
		
	    try {
	    xlsReader.setPath(testDataFilePath);
	    } catch (IOException ioException) {
	    report.error("IOExeption occured as " + ioException.getMessage());
	    }
	    int rowcount = xlsReader.getRowCount(testDataSheetName);
	    String data=new String();
	    for(int i=0;i<rowcount;i++) {
	    data=xlsReader.getCellDataByColumnName(testDataSheetName, ColumnName, (i+1));   
	    }
	
	return data;
	}
	
 
}
