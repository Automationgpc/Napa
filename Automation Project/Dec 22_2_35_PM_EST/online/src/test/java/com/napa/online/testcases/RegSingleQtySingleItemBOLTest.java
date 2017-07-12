package com.napa.online.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.napa.online.pages.BuyOnlineAtcPage;
import com.napa.online.pages.BuyOnlineCheckoutPage;
import com.napa.online.pages.CheckoutSignInPage;
import com.napa.online.pages.HomePage;
import com.napa.online.pages.LaunchPage;
import com.napa.online.pages.LoginPage;
import com.napa.online.pages.OrderConfirmationPage;
import com.napa.online.pages.PDPpage;
import com.napa.online.pages.PLPGlobalKeywordSearchpage;
import com.napa.online.pages.PLPGlobalVehicleSelectedpage;
import com.napa.online.pages.PickUpInStorePage;
import com.napa.online.pages.ResContactInfoPage;
import com.napa.online.pages.ReviewOrderPage;
import com.napa.online.pages.ShippingBillingPage;
import com.napa.online.pages.ShoppingCartPage;
import com.napa.online.util.DataUtil;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.LogStatus;

public class RegSingleQtySingleItemBOLTest extends BaseTest{
	
	String testCaseName="RegSingleQtySingleItemBOLTest";	
	
	HomePage homePage = null;
	LoginPage loginPage = null;
	PDPpage pDPpage = null;
	PickUpInStorePage pickUpInStorePage = null;
	ShoppingCartPage shoppingCartPage = null;
	PLPGlobalKeywordSearchpage pLPGlobalKeywordSearchpage = null;
	PLPGlobalVehicleSelectedpage pLPGlobalVehicleSelectedpage = null;
	BuyOnlineAtcPage buyOnlineAtcPage = null;
	BuyOnlineCheckoutPage buyOnlineCheckoutPage = null;
	ShippingBillingPage shippingBillingPage = null;
	CheckoutSignInPage checkoutSignInPage = null;
	ResContactInfoPage resContactInfoPage = null;
	ReviewOrderPage reviewOrderPage = null;
	OrderConfirmationPage orderConfirmationPage =  null;
	
	//Vehicle input data variables
	String year = "";
	String make = "";
	String model = "";
	String vehicle = "";
	
	//Input Product Variables
	String product="";
	String partNumber ="";
	String qty="";
		
	boolean orderSuccess = false;
	String testResult = "FAIL";
	
	//for Cart page
	String productCart="";
	String partCart ="";	
	String unitPriceCart = "";
	String corePriceCart = "";
	String qtyCart = "";
	String subtotalCart = "";
	String discountCart = "";
	String totalCart = "";
	String fulfillmentCart ="";
	String storeCart = "";
	String rOLSubtotalCart = "";
	String bOLSubtotalCart = "";
	String orderSubtotalCart = "";
	String youSavedCart = "";
	
	//for PDP page		
	String productPDP="";
	String partPDP="";
	String priceFullPDP="";		
	String fitmentConfirmedPDP="";	
	
	//For Order values		
	String productsOrder="";
	String partsOrder ="";		
	String unitPricesOrder = "";
	String corePricesOrder = "";
	String qtysOrder = "";
	String subtotalsOrder = "";
	String discountsOrder = "";
	String itemTotalsOrder = "";
	
	@Test(dataProvider="getData")
	public void Reg_Single_Qty_Single_Item_BOL_Order(Hashtable<String,String> data) throws InterruptedException{
		
		String testDesc = data.get("TestDesc");
		//System.out.println("test Desc= "+testDesc);
		test = extent.startTest(testDesc);
		
		//getting input data from XL Sheet
		year = data.get("VehicleYear");
		make = data.get("VehicleMake");
		model = data.get("VehicleModel");
		vehicle = year+" "+make+" "+model;
		
		String newStoreAddress=data.get("StoreAddress");
		String newStoreZip=data.get("StoreZip");	
		
		String productKey = data.get("ProductKey");
		String productQty = data.get("ProductQty");
		String BOLPartNumber = data.get("ProductPartNumber").substring(4).trim();
		String paymentType = data.get("PaymentType");
		
		String addressType = data.get("AddressType");
		String firstName = data.get("FirstName");
		String lastName = data.get("LastName");
		String addressOne = data.get("AddressOne");
		String addressTwo = data.get("AddressTwo");
		String city = data.get("City");
		String state = data.get("State");
		String zipCode = data.get("ZipCode");
		String phoneNumber = data.get("PhoneNumber");
		
		String userName = DataUtil.getCommonData(xls, "Username");
		String password = DataUtil.getCommonData(xls, "Password");
		String userFullName = DataUtil.getCommonData(xls, "UserFullName");
		RunConfig.VISA = DataUtil.getCommonData(xls, "Visa");
		String mastercard = DataUtil.getCommonData(xls, "Mastercard");
		
		if(!DataUtil.isTestExecutable(xls, testCaseName) || data.get(RunConfig.RUNMODE_COL).equalsIgnoreCase("No")){
			test.log(LogStatus.SKIP, "SKIP: Skipping the test case as Runmode is No");
			testResult = "SKIP";
			System.out.println("Skipping the Test: "+testDesc+" since Runmode is No");
			throw new SkipException("Skipping the test as Runmode is No");
		}
		
		test.log(LogStatus.INFO, "Starting "+testDesc);	
		
		
		if (RunConfig.SELECT_BROWSER_FROM_XL){
			RunConfig.BROWSER=data.get("Browser");
		}else{
			RunConfig.BROWSER=RunConfig.BROWSER_XML;
		}
		
		System.out.println("Starting the Test: "+testDesc+" on "+RunConfig.BROWSER);
		init(RunConfig.BROWSER);	
					
		LaunchPage launchPage =new LaunchPage(driver,test);
		PageFactory.initElements(driver, launchPage);		
		homePage = launchPage.openHomePage();
		//String homePageURL = driver.getCurrentUrl();
		//System.out.println("homePageURL="+homePageURL);
		
		//String sample = "WIP 602443";
		//String shortSample = sample.substring(3);
		//System.out.println("BOLPartNumber="+BOLPartNumber);
		
		homePage.waitForPageLoad(driver);
		
		//Creating new user account
		homePage = homePage.registerNewUser();
		 
		//Checking if User is already logged in
		if (homePage.isElementPresent(homePage.header.MyMenuNameDD)){
			String nameDisplayed = homePage.header.MyMenuNameDD.getText().toString();
			//System.out.println("nameDisplayed= "+nameDisplayed);
				if(nameDisplayed.contains(userFullName)){
					test.log(LogStatus.PASS, "Logged in as: "+userFullName);
				}
		
		}else{
			//navigating to login page Since User is not logged in
			try{				
				loginPage = homePage.header.goToLoginPage();
				loginPage.waitForPageLoad(driver);
				if(loginPage.loginEmail.isDisplayed()){
					test.log(LogStatus.PASS, "PASS:Login Page is displayed");
				}else{
					test.log(LogStatus.FAIL, "FAIL:Login Page is NOT displayed");
				}
				//checking if Chat Window is displayed and closing it if present
				try{
					if (loginPage.isElementPresent(loginPage.chatWinCloseBy)){
						loginPage.closeChatWindow();
					}
					
				}catch(Exception e){
					
				}
				takeScreenShot();				
				
			}catch(Exception e){				
				test.log(LogStatus.FAIL, "FAIL:Login Page is NOT displayed");
				takeScreenShot();
				Assert.fail();
				
			}	
			
			//Loggingin from Login Page
			try{				
				homePage = loginPage.signIn(RunConfig.EMAIL_ID, RunConfig.PASSWORD);		
				homePage.waitForPageLoad(driver);
				if(homePage.header.MyMenuNameDD.isDisplayed()){
					String nameDisplayed = homePage.header.MyMenuNameDD.getText().toString();
					//System.out.println("nameDisplayed= "+nameDisplayed);
						if(nameDisplayed.contains(userFullName)){
							test.log(LogStatus.PASS, "PASS:Successfully logged in as: "+userFullName);
						}					
					
				}else{
					test.log(LogStatus.FAIL, "FAIL:User Login Failed");
				}
				
				takeScreenShot();				
				
			}catch(Exception e){				
				test.log(LogStatus.FAIL, "FAIL:User Login Failed");
				takeScreenShot();
				Assert.fail();
				
			}				
			
			
		}	
		
		
		
		//emptying shopping cart if it is not empty
		//homePage.header.emptyShoppingCart();
		TimeUnit.SECONDS.sleep(3);
		homePage = homePage.header.localizeStore(newStoreAddress, newStoreZip);			
		TimeUnit.SECONDS.sleep(3);
		homePage.waitForPageLoad(driver);
		homePage = homePage.header.addVehicleFromHdr(year, make, model);		
		homePage.waitForPageLoad(driver);	
		TimeUnit.SECONDS.sleep(3);		
		
		//Performing Key word search in Global Search bar and Navigating to PLP page
		try{				
			pLPGlobalVehicleSelectedpage = homePage.header.searchVehSpecificWithKeyword(productKey);
			pLPGlobalVehicleSelectedpage.waitForPageLoad(driver);	
			TimeUnit.SECONDS.sleep(2);
			test.log(LogStatus.PASS, "PASS:PLP page is displayed");	
			TimeUnit.SECONDS.sleep(3);
			takeScreenShot();				
			
		}catch(Exception e){				
			test.log(LogStatus.FAIL, "FAIL:PLP page is NOT displayed");
			takeScreenShot();
			Assert.fail();
			
		}		
		
		//Navigating to PDP page
		try{				
			pDPpage = pLPGlobalVehicleSelectedpage.clickProductInPLP(BOLPartNumber);
			pDPpage.waitForPageLoad(driver);
			TimeUnit.SECONDS.sleep(2);
			if(pDPpage.ProductNamePDP.isDisplayed()){
				test.log(LogStatus.PASS, "PASS:PDP page is displayed");
				
			}else{
				test.log(LogStatus.FAIL, "FAIL:PDP page is NOT displayed");
			}
			takeScreenShot();				
			
		}catch(Exception e){				
			test.log(LogStatus.FAIL, "FAIL:PDP page is NOT displayed");
			takeScreenShot();
			Assert.fail();
			
		}
		
		
		try{
			//Capturing Product values form PDP Page				
			productPDP = pDPpage.ProductNamePDP.getText().toString();
			//System.out.println("productPDP= " + productPDP);			
			partPDP = pDPpage.PartNamePDP.getText().toString();					
			//priceFullPDP = pDPpage.PricePDP.getText().toString();				
			fitmentConfirmedPDP = pDPpage.FitmentConfirmedPDP.getText().toString();
			//System.out.println("Captured values from PDP page successfully");
			
		}catch(Exception e){
			System.out.println("Exception occured while capturing Product values from PDP page: ");
		}	
		
		
		//Navigating to Buy Online ATC Modal
		try{
			buyOnlineAtcPage = pDPpage.clickBOLBtnFitmentConfirmed(productQty);
			buyOnlineAtcPage.waitForPageLoad(driver);
			TimeUnit.SECONDS.sleep(2);
			if(buyOnlineAtcPage.isElementPresent(buyOnlineAtcPage.AddToCartBtn)){				
				test.log(LogStatus.PASS, "PASS:Buy Online ATC Modal is displayed");	
				takeScreenShot();
			}
		}catch(Exception e){
			test.log(LogStatus.FAIL, "FAIL:Buy Online ATC Modal is NOT displayed");
			System.out.println("FAIL:Buy Online ATC Modal is displayed");
			takeScreenShot();
			Assert.fail();
			
		}
		
		//Navigating to Buy Online Check Out Modal
		try{
			
			buyOnlineCheckoutPage =buyOnlineAtcPage.clickAddToCart();			
			buyOnlineCheckoutPage.waitForPageLoad(driver);
			TimeUnit.SECONDS.sleep(2);
			//TimeUnit.SECONDS.sleep(2);
			test.log(LogStatus.PASS, "PASS:Buy Online Check Out Modal is displayed");
			takeScreenShot();
			
		}catch(Exception e){
			test.log(LogStatus.FAIL, "FAIL:Buy Online Check Out Modal is NOT displayed");
			System.out.println("FAIL:Buy Online Check out Modal is NOT displayed");
			takeScreenShot();
			Assert.fail();
			
		}
		
		//Navigating to Shopping Cart Page
		try{
			
			shoppingCartPage = buyOnlineCheckoutPage.clkCheckOutNow();			
			shoppingCartPage.waitForPageLoad(driver);
			TimeUnit.SECONDS.sleep(2);
			test.log(LogStatus.PASS, "PASS:Shopping Cart Page is displayed");
			takeScreenShot();
			
		}catch(Exception e){
			test.log(LogStatus.FAIL, "FAIL:Shopping Cart Page is NOT displayed");
			System.out.println("FAIL:Shopping Cart Page is NOT displayed");
			takeScreenShot();
			Assert.fail();
			
		}
		
		try{
			//Capturing Product values form Shopping Cart Page	
			//System.out.println("Capturing Product values form Shopping Cart Page");
			productCart = shoppingCartPage.ProductNameCart.getText().toString();
			//System.out.println("productCart= " + productCart);			
			partCart = shoppingCartPage.PartNumberCart.getText().toString();	
			//System.out.println("partCart= " + partCart);
			
			//System.out.println("storeCart1= " + storeCart1);
			unitPriceCart = shoppingCartPage.UnitPriceCart.getText().toString();	
			//System.out.println("unitPriceCart2= " + unitPriceCart2);
			corePriceCart = shoppingCartPage.CorePriceCart.getText().toString();			
			qtyCart = shoppingCartPage.QtyCart.getAttribute("value");
			//System.out.println("qtyCart1= " + qtyCart1);
			subtotalCart = shoppingCartPage.SubtotalCart.getText().toString();
			discountCart = "product not on sale.no discount.";
			if (shoppingCartPage.isElementPresent(shoppingCartPage.DiscountCart)){
				discountCart = shoppingCartPage.DiscountCart.getText().toString();	
			}
						
			totalCart = shoppingCartPage.TotalCart.getText().toString();	
			fulfillmentCart = shoppingCartPage.FulfillmentType.getText().toString();
			//System.out.println("fulfillment1= " + fulfillment1);
			//storeCart = shoppingCartPage.StoreAddress.getText().toString();		
			rOLSubtotalCart = shoppingCartPage.ROLSubtotal.getText().toString();			
			bOLSubtotalCart = shoppingCartPage.BOLSubtotal.getText().toString();
			//System.out.println("bOLSubtotal=" + bOLSubtotalCart);
			orderSubtotalCart = shoppingCartPage.OrderSubtotal.getText().toString();	
			
			youSavedCart = "no discount available";
			if (shoppingCartPage.isElementPresent(shoppingCartPage.YouSaved)){
				youSavedCart = shoppingCartPage.YouSaved.getText().toString();	
			}
			System.out.println("youSavedCart= " + youSavedCart);
			
			
		}catch(Exception e){
			System.out.println("Exception occured while capturing Product values from Cart: "+e.getMessage());
		}
		
		 
		//checking if Chat Window is displayed and closing it if present
		try{
			if (shoppingCartPage.isElementPresent(shoppingCartPage.chatWinCloseBy)){
				shoppingCartPage.closeChatWindow();
			}
			
		}catch(Exception e){
			
		}
		
		//Clicking Checkout now Button in Shopping Cart as a Signed in User and go to Reservation Contact info Page
		try{				
			shippingBillingPage = shoppingCartPage.clkBOLCheckoutSignedInUser();				
			shippingBillingPage.waitForPageLoad(driver);
			TimeUnit.SECONDS.sleep(2);
			
			if(shippingBillingPage.isElementPresent(shippingBillingPage.ShippingTitle)){				
				test.log(LogStatus.PASS, "PASS:Shipping & Billing Page is displayed");	
				takeScreenShot();
			}
		}catch(Exception e){
			test.log(LogStatus.FAIL, "FAIL:Shipping & Billing Page is NOT displayed");
			System.out.println("FAIL:Shipping & Billing Page is NOT displayed");
			takeScreenShot();
			Assert.fail();
			
		}		
		//Adding shipping and Billing details in Shipping Page and click Confirm Order Information Button
		try{			
			
			shippingBillingPage.addShippingDetails(addressType,firstName,lastName,addressOne, addressTwo, 
					city, state, zipCode, phoneNumber);			 
			
			if(shippingBillingPage.SameAsShipChkBx.isDisplayed()){
				shippingBillingPage.SameAsShipChkBx.click();
			}
			
			
			shippingBillingPage.addCardDetails(paymentType);
			reviewOrderPage = shippingBillingPage.ClickConfirmOrderInfo();
			TimeUnit.SECONDS.sleep(2);
			if(reviewOrderPage.ShippingAddress.isDisplayed()){
				test.log(LogStatus.PASS, "PASS:Review Order Page is displayed");
				takeScreenShot();
			}else{
				test.log(LogStatus.FAIL, "FAIL:Review Order Page is NOT displayed");
				takeScreenShot();
			}			
			
		}catch(Exception e){
			test.log(LogStatus.FAIL, "FAIL:Failed to Click Confirm Order Button");			
			takeScreenShot();
			Assert.fail();
			
		}	
		
		//clicking Submit Order Button from Review Order Page for BOL order
		try{				
			orderConfirmationPage = reviewOrderPage.SubmitOrder();	
			orderConfirmationPage.waitForPageLoad(driver);
			//test.log(LogStatus.PASS, "PASS:Order placed successfully. "+orderNumber+" displayed.");	
			
			WebElement OrderNumberTxtEle = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.elementToBeClickable(orderConfirmationPage.OrderNumberTxtBy));
			
			if(OrderNumberTxtEle.isDisplayed()){
				RunConfig.ORDER_NUMBER = orderConfirmationPage.OrderNumberTxt.getText().toString();
				
				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(orderConfirmationPage.TaxBy)).build().perform();
				
				test.log(LogStatus.PASS, "PASS:Order Placed. "+ RunConfig.ORDER_NUMBER);
				System.out.println("PASS:Order Placed. "+ RunConfig.ORDER_NUMBER);
				
				orderSuccess=true;
				testResult = "PASS";
				System.out.println("testResult= "+ testResult);
				takeScreenShot();
			}else{
				test.log(LogStatus.FAIL, "FAIL:Order Confimation Page is NOT displayed");
				takeScreenShot();
				Assert.fail();
				
			}				
			
		}catch(Exception e){				
			test.log(LogStatus.FAIL, "FAIL:Order Confimation Page is NOT displayed");	
			takeScreenShot();
			Assert.fail();
			takeScreenShot();
		}
		
		if (orderSuccess){	
			
			//Writing Product captured values from Shopping Cart to Test Results-Data Sheet
			try{
							
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 31,productCart);	
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 32,partCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 33,unitPriceCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 34,corePriceCart);			
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 35,qtyCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 36,subtotalCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 37,discountCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 38,totalCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 39,fulfillmentCart);					
				
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 41,rOLSubtotalCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 42,bOLSubtotalCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 43,orderSubtotalCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 44,youSavedCart);
				
			}catch(Exception e){				
				System.out.println("INFO:Failed to write captured values from Shopping Cart Page to Data Sheet");			
				
			}
			
			
			Hashtable<String, String> orderValues = orderConfirmationPage.getOrderDetailsBOL();
			
			try{	
							
				//Writing captured values from Order Confirmation Page to Test Results-Data Sheet
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 21,testResult);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 45,orderValues.get("orderNumber"));
				//Product1 details
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 46,orderValues.get("productNameOrder"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 47,orderValues.get("partNumberOrder"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 48,orderValues.get("fulfillmentTypeOrder"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 49,orderValues.get("unitPriceOrder"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 50,orderValues.get("corePriceOrder"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 51,orderValues.get("qtyOrder"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 52,orderValues.get("subtotalOrder"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 53,orderValues.get("discountOrder"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 54,orderValues.get("itemTotalOrder"));					
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 55,orderValues.get("shippingInfoOrder"));
				
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 56,orderValues.get("bOLSubtotalOrder"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 57,orderValues.get("taxOrder"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 58,orderValues.get("shippingCharge"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 59,orderValues.get("finalTotalBOLOrder"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 60,orderValues.get("youSavedOrder"));
				
				
			}catch(Exception e){				
				System.out.println("INFO:Failed to write captured values from Order Confirmation Page to Data Sheet");			
				
			}
			
		}
		 
		
		System.out.println("-------------------------Test Case execution ENDED--------------------- ");
		
	}
	
	@AfterMethod void quit(){
		if(extent!=null){
			extent.endTest(test);
			extent.flush();
		}
		if(driver!=null){
			driver.manage().deleteAllCookies();
			driver.quit();
		}
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(xls, testCaseName);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) {
	  //String seleniumHost = context.getCurrentXmlTest().getParameter("selenium.host");
	  //String seleniumPort = context.getCurrentXmlTest().getParameter("selenium.port");
	  String seleniumBrowser = context.getCurrentXmlTest().getParameter("browser");
	  //String seleniumUrl = context.getCurrentXmlTest().getParameter("selenium.url");
	  
	  //System.out.println("browser from ITestContext= "+seleniumBrowser);
	  RunConfig.BROWSER_XML=seleniumBrowser;
	  
	}
	 

}
