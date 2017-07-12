package com.napa.online.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

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
import com.napa.online.pages.ShoppingCartPage;
import com.napa.online.util.DataUtil;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.LogStatus;

public class RegMultiQtySingleItemROLTest extends BaseTest{
	
	
		String testCaseName="RegMultiQtySingleItemROLTest";	
		
		HomePage homePage = null;
		LoginPage loginPage = null;
		PDPpage pDPpage = null;
		PickUpInStorePage pickUpInStorePage = null;
		ShoppingCartPage shoppingCartPage = null;
		PLPGlobalKeywordSearchpage pLPGlobalKeywordSearchpage = null;
		PLPGlobalVehicleSelectedpage pLPGlobalVehicleSelectedpage = null;
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
		String rOLSubtotal = "";
		String bOLSubtotal = "";
		String orderSubtotal = "";
		String youSaved = "";
		
		//for PDP page		
		String productPDP="";
		String partPDP="";
		String priceFullPDP="";		
		String fitmentConfirmedPDP="";
		
		
		
		@Test(dataProvider="getData")
		public void Reg_Multi_Qty_Single_Item_ROL_Order(Hashtable<String,String> data) throws InterruptedException{
			
			String testDesc = data.get("TestDesc");
			//System.out.println("test Desc= "+testDesc);
			test = extent.startTest(testDesc);
			
			year = data.get("VehicleYear");
			make = data.get("VehicleMake");
			model = data.get("VehicleModel");
			vehicle = year+" "+make+" "+model;
			
			String newStoreAddress=data.get("StoreAddress");
			String newStoreZip=data.get("StoreZip");
			
			String productKey = data.get("ProductKey");
			String productQty = data.get("ProductQty");
			
			
			String userFullName = DataUtil.getCommonData(xls, "UserFullName");
			
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
			
			homePage.waitForPageLoad(driver);		
			
			//homePage.header.emptyShoppingCart();					
			homePage = homePage.registerNewUser();
			homePage.waitForPageLoad(driver);
			
			//Checking if User is already logged in
			if (homePage.isElementPresent(homePage.header.MyMenuNameDD)){
				String nameDisplayed = homePage.header.MyMenuNameDD.getText().toString();
				System.out.println("nameDisplayed= "+nameDisplayed);
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
			
			/*homePage.header.emptyShoppingCart();			
			homePage.waitForPageLoad(driver);*/
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
				pDPpage = pLPGlobalVehicleSelectedpage.clickProduct();
				pDPpage.waitForPageLoad(driver);
				TimeUnit.SECONDS.sleep(3);
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
				System.out.println("productPDP= " + productPDP);			
				partPDP = pDPpage.PartNamePDP.getText().toString();					
				priceFullPDP = pDPpage.PricePDP.getText().toString();				
				fitmentConfirmedPDP = pDPpage.FitmentConfirmedPDP.getText().toString();
				
			}catch(Exception e){
				System.out.println("Exception occured while capturing Product values from PDP page: ");
			}	
				
			pickUpInStorePage = pDPpage.clickROLBtnFitmentConfirmed(productQty);
			pickUpInStorePage.waitForPageLoad(driver);
			TimeUnit.SECONDS.sleep(3);
			//Switching to Pick Up-Check Out Window 
			String puCheckoutWindow1 = driver.getWindowHandle();
			driver.switchTo().window(puCheckoutWindow1);
			
			try{
				if(pickUpInStorePage.isElementPresent(pickUpInStorePage.AtcConfirmationTxt)){
					System.out.println("AtcConfirmationTxt:"+pickUpInStorePage.AtcConfirmationTxt.getText().toString());
					if(pickUpInStorePage.AtcConfirmationTxt.getText().toString().contains("Successfully added to cart")){
						test.log(LogStatus.PASS, "PASS: "+productPDP+" added successfully to Cart.");
					}
				}
			}catch(Exception e){
				test.log(LogStatus.FAIL, "FAIL: "+productPDP+" NOT added to Cart. Exception occured");
				System.out.println(productPDP+" NOT added to Cart. Exception occured:");
				takeScreenShot();
				Assert.fail();
				
			}
			
			shoppingCartPage =pickUpInStorePage.clickCheckOutNowPU();
			
			shoppingCartPage.waitForPageLoad(driver);
			takeScreenShot();
			
			try{
				//Capturing Product values form Shopping Cart Page				
				productCart = shoppingCartPage.ProductNameCart.getText().toString();
				System.out.println("productCart= " + productCart);			
				partCart = shoppingCartPage.PartNumberCart.getText().toString();	
				System.out.println("partCart= " + partCart);
				
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
				storeCart = shoppingCartPage.StoreAddress.getText().toString();		
				rOLSubtotal = shoppingCartPage.ROLSubtotal.getText().toString();			
				bOLSubtotal = shoppingCartPage.BOLSubtotal.getText().toString();			
				orderSubtotal = shoppingCartPage.OrderSubtotal.getText().toString();	
				
				youSaved = "product not on sale.no discount.";
				if (shoppingCartPage.isElementPresent(shoppingCartPage.YouSaved)){
					youSaved = shoppingCartPage.YouSaved.getText().toString();	
				}
				System.out.println("youSaved= " + youSaved);
				
				
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
				resContactInfoPage=shoppingCartPage.clkCheckoutInCartSignedInUser();	
				resContactInfoPage.waitForPageLoad(driver);
				TimeUnit.SECONDS.sleep(3);
				takeScreenShot();				
				
			}catch(Exception e){				
				test.log(LogStatus.FAIL, "FAIL:Check out as Returning User failed. Reservation Contact Info Page is NOT displayed");	
				Assert.fail();
				takeScreenShot();
			}
			
			//clicking Review Order Button from Reservation Contact Info Page for ROL order
			try{				
				reviewOrderPage = resContactInfoPage.ClkReviewOrder();	
				reviewOrderPage.waitForPageLoad(driver);
				TimeUnit.SECONDS.sleep(3);
				takeScreenShot();				
				
			}catch(Exception e){				
				test.log(LogStatus.FAIL, "FAIL:Review Order Page is NOT displayed");
				Assert.fail();
				takeScreenShot();
			}
			
			//clicking Submit Order Button from Review Order Page for ROL order
			try{				
				orderConfirmationPage = reviewOrderPage.SubmitOrder();	
				orderConfirmationPage.waitForPageLoad(driver);
							
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
				} else{
					test.log(LogStatus.FAIL, "FAIL:Order Confimation Page is NOT displayed");
					Assert.fail();
					takeScreenShot();
				}
				
				
			}catch(Exception e){				
				test.log(LogStatus.FAIL, "FAIL:Order Confimation Page is NOT displayed");	
				Assert.fail();
				takeScreenShot();
			}
			
			if (orderSuccess){	
				
				//Writing Product values from PDP page to Test Results-Data Sheet
				try{	
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 11,testResult);
					
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 17,productPDP);	
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 18,partPDP);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 19,priceFullPDP);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 20,fitmentConfirmedPDP);			
							
					
				}catch(Exception e){				
					System.out.println("INFO:Failed to write captured values from PDP Page to Data Sheet");			
					
				}
				
				//Writing Product values from Shopping Cart to Test Results-Data Sheet
				try{				
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 21,productCart);	
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 22,partCart);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 23,unitPriceCart);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 24,corePriceCart);			
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 25,qtyCart);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 26,subtotalCart);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 27,discountCart);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 28,totalCart);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 29,fulfillmentCart);				
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 30,storeCart);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 31,rOLSubtotal);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 32,bOLSubtotal);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 33,orderSubtotal);				
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 34,youSaved);
					
					
				}catch(Exception e){				
					System.out.println("INFO:Failed to write captured values from Shopping Cart Page to Data Sheet");			
					
				}
				
				Hashtable<String, String> orderValues = orderConfirmationPage.getOrderDetails();
				
				try{	
					//Writing captured values from Order Confirmation Page to Test Results-Data Sheet
					
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 35,orderValues.get("orderNumber"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 36,orderValues.get("productName"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 37,orderValues.get("partNumber"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 38,orderValues.get("fulfillmentType"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 39,orderValues.get("unitPrice"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 40,orderValues.get("corePrice"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 41,orderValues.get("qty"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 42,orderValues.get("subtotal"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 43,orderValues.get("discount"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 44,orderValues.get("itemTotal"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 45,orderValues.get("pickUpLocation"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 46,orderValues.get("pickUpPerson"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 47,orderValues.get("rOLSubtotal"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 48,orderValues.get("tax"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 49,orderValues.get("finalTotal"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 50,orderValues.get("youSaved"));
					
					
				}catch(Exception e){				
					System.out.println("INFO:Failed to write captured values from Order Confirmation Page to Data Sheet");			
					
				}
			}
			 
			
	
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
