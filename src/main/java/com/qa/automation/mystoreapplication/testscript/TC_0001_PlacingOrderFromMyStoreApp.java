package com.qa.automation.mystoreapplication.testscript;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.automation.mystoreapplication.config.MyStoreAppConstants;
import com.qa.automation.mystoreapplication.excelreader.ExcelReaderWithDataProvider;
import com.qa.automation.mystoreapplication.testsuite.TestSuiteBase;

public class TC_0001_PlacingOrderFromMyStoreApp extends TestSuiteBase {
	
	ExcelReaderWithDataProvider testData = new ExcelReaderWithDataProvider();
	String testDataFilePath = MyStoreAppConstants.TESTDATA_EXCELFILE_PATH;
	
	String testData_SignIn_SheetName = "Signin";
	
// using Data provider for sign in	
	@DataProvider(name = "testdatasignin")
	public Object[][] getSignInData() {
		Object[][] testdata1=testData.Registrationdata(testDataFilePath, testData_SignIn_SheetName);
		return testdata1;
	}

// Sign into the application
	@Test(priority=1,dataProvider = "testdatasignin")
	public void performSignINAndVerifyProductCategoriesOnHomePage(String username,String password)
	{
		signInApplication.clickSignInLink();
		signInApplication.enterUserName(username);
		signInApplication.enterPassword(password);
		signInApplication.clickSignInBtn();
		home.isLoginSuccessfull();
		home.verifyProductCategories();
	}
	

// Verifying the product detail on ProductDetails page
	@Test(priority=2)
	public void verifyProductDetailsAndCartTextOnProductDetailsPage()
	{
		
		try {
			signIn.performSignIn();
			
			home.verifyMyAccountPageTitle();
			
			dresses.selectProductInCasualDresses();
			
			productDetails.verifyProductDetails();
			
			productDetails.updateQuantity();
			
			productDetails.updateSize();
			
			productDetails.clickAddToCartBtn();
			
			productDetails.clickContinueShoppingbutton();
			
			productDetails.validateCartTextOnProductDetailsPage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

// Verifying the Product Details on Shopping Cart Summary page	
	@Test(priority=3)
	public void verifyProductDetailsOnShoppingCartSummary()
	{
		
		try {
			signIn.performSignIn();
			
			home.verifyMyAccountPageTitle();
			
			dresses.selectProductInCasualDresses();
			
			productDetails.clickAddToCartBtn();
			
			
			productDetails.clickProceedToCheckOut();
			
			shoppingCartCheckout.verifyProductDetailOnShoppingCart();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

// Verifying the address detail on Address page	
	@Test(priority=4)
	public void verifyAddressDetailsOnAddresssPage()
	{
		
		try {
			signIn.performSignIn();
			
			home.verifyMyAccountPageTitle();
			
			dresses.selectProductInCasualDresses();
			
			productDetails.clickAddToCartBtn();
			
			productDetails.clickProceedToCheckOut();
			
			shoppingCartCheckout.clickChkOutOnSummaryPage();
			
			addressCheckout.verifyAddressOnAddressCheckout();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

// Verifying the product details on payment page	
	@Test(priority=5)
	public void verifyProductDetailsOnPaymentPage()
	{
		
		try {
			signIn.performSignIn();
			
			home.verifyMyAccountPageTitle();
			
			dresses.selectProductInCasualDresses();
			
			productDetails.clickAddToCartBtn();
			
			productDetails.clickContinueShoppingbutton();
			
			dresses.selectProductInEveningDresses();
			
			productDetails.clickAddToCartBtn();
			
			productDetails.clickProceedToCheckOut();
			
			shoppingCartCheckout.clickChkOutOnSummaryPage();
			
			addressCheckout.checkOutAddress();
			
			shippingCheckout.checkoutShipping();
			
			payment.checkProductModelNameOnPayment();
			
			payment.checkProductUnitPriceOnPayment();
			
			payment.checkProductQuantityOnPayment();
			
			payment.checkProductFinalTotalPriceOnPayment();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
// Verifying the checkout by adding three product into cart	
	@Test(priority=6)
	public void verifyCheckout()
	{
		try {
			signIn.performSignIn();
			
// Adding 1 product from casual, evening ,summer dresses category
			dresses.selectProductInCasualDresses();
			productDetails.updateSize();
			
			productDetails.clickAddToCartBtn();
			
			productDetails.clickContinueShoppingbutton();
			
			dresses.selectProductInEveningDresses();
			productDetails.updateSize();
			productDetails.clickAddToCartBtn();
			
			productDetails.clickContinueShoppingbutton();
			
			dresses.selectProductInSummerDresses();
			productDetails.updateSize();
			
			productDetails.clickAddToCartBtn();
		
// performing checkout 		
			productDetails.clickProceedToCheckOut();
			
			shoppingCartCheckout.validateCartTextOnShoppingCartCheckout();
		
			shoppingCartCheckout.clickChkOutOnSummaryPage();
			
			addressCheckout.checkOutAddress();
			shippingCheckout.checkoutShipping();	
			
			payment.clickPayByCheck();
			payment.clickConfirmOrder();
			payment.verifyOrderPlaced();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
// Verifying the order detail after placing the order
	@Test(priority=7)
	public void verifyOrderDetails()
	{
		try {
			signIn.performSignIn();
			home.clickUserNameLink();
			orderDetail.clickOrderDetails();
			orderDetail.clickOrderrefLink();
			orderDetail.verifyOrderdetailreference();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
// Clicking on sign out, this method will perform after every @test	
	@AfterMethod
    public void signOutApp()
    {	
    	home.clickSignOut();
    	
    }
	
	
	

}
