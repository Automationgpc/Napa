package com.napa.online.testcases;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.napa.online.pages.CableChainPLPPage;
import com.napa.online.pages.CableChainPage;
import com.napa.online.pages.CategoryPage;
import com.napa.online.pages.ChangeStoreWinPage;
import com.napa.online.pages.CheckoutSignInPage;
import com.napa.online.pages.HomePage;
import com.napa.online.pages.LaunchPage;
import com.napa.online.pages.LoginPage;
import com.napa.online.pages.OrderConfirmationPage;
import com.napa.online.pages.ResContactInfoPage;
import com.napa.online.pages.ReviewOrderPage;
import com.napa.online.pages.SelectStoreNewPage;
import com.napa.online.pages.SelectStorePage;
import com.napa.online.pages.ShoppingCartPage;
import com.napa.online.util.DataUtil;
import com.napa.online.util.FileIO;
import com.napa.online.util.Helper;
import com.napa.online.util.RunConfig;
import com.napa.online.util.XLWriter;
import com.relevantcodes.extentreports.LogStatus;



public class GuestUserROLTest extends BaseTest{
	
	String testCaseName="GuestUserROLTest";	
	
	HomePage homePage = null;
	LoginPage loginPage = null;
	ChangeStoreWinPage changeStoreWinPage = null; 
	SelectStorePage selectStorePage = null;
	SelectStoreNewPage selectStoreNewPage = null;
	CategoryPage categoryPage = null;
	CableChainPage cableChainPage = null;
	CableChainPLPPage cableChainPLPPage = null;
	ShoppingCartPage shoppingCartPage = null;
	CheckoutSignInPage checkoutSignInPage = null;
	ResContactInfoPage resContactInfoPage = null;
	ReviewOrderPage reviewOrderPage = null;
	OrderConfirmationPage orderConfirmationPage = null;	
	
	String year = "";
	String make = "";
	String model = "";
	String vehicle = "";

	boolean isPageDisplayed=false;
	
	//String orderNumber="none";
	boolean orderSuccess = false;
	String testResult = "FAIL";
	
	String productNameCart="";
	String partNumberCart ="";
	String fulfillmentType ="";
	String storeAddress = "";
	String unitPriceCart = "";
	String corePriceCart = "";
	String qtyCart = "";
	String subtotalCart = "";
	String discountCart = "";
	String totalCart = "";
	String rOLSubtotal = "";
	String bOLSubtotal = "";
	String orderSubtotal = "";
	String youSaved = "";
	
	@Test(dataProvider="getData")
	public void Guest_Select_Part_MMTE_Veh_Store_Header_ROL_Order(Hashtable<String,String> data) throws InterruptedException{	
			
		year = data.get("VehicleYear");
		make = data.get("VehicleMake");
		model = data.get("VehicleModel");
		vehicle = year+" "+make+" "+model;
		
		String newStoreAddress=data.get("StoreAddress");
		String newStoreZip=data.get("StoreZip");
			String testDesc = data.get("TestDesc");
			//System.out.println("test Desc= "+testDesc);
			test = extent.startTest(testDesc);
			
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
			
			//DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 11,"2016");
			homePage.waitForPageLoad(driver);	
			
			//homePage.header.emptyShoppingCart();
			
						
			homePage = homePage.header.localizeStore(newStoreAddress, newStoreZip);			
			homePage.waitForPageLoad(driver);
			//test.log(LogStatus.PASS, "Store Localized to: "+data.get("StoreAddress")+","+data.get("StoreZip"));
			TimeUnit.SECONDS.sleep(5);
			homePage = homePage.header.addVehicleFromHdr(year, make, model);			
			homePage.waitForPageLoad(driver);
			TimeUnit.SECONDS.sleep(3);
			homePage.takeScreenShot();			 
			 
			if (homePage.isElementPresent(homePage.header.ToolsEquipSuppliesDD)){
				//homePage.header.clkToolsEquipSuppLnk();
				while(categoryPage==null){
					categoryPage=homePage.header.clickCableChainTELnk();
				}
				categoryPage.waitForPageLoad(driver);
				TimeUnit.SECONDS.sleep(2);
				//categoryPage=homePage.header.clickCableChainTELnk();
				if (categoryPage.isElementPresent(categoryPage.CableChainCatLeftLnk)){
					test.log(LogStatus.PASS, "Cable&Chain Link was CLICKED from Tools&Equipment Menu AND Category page is displayed");
				}else{
					test.log(LogStatus.FAIL, "Category page is NOT displayed");
				}
								
				categoryPage.takeScreenShot();
			} else{
				test.log(LogStatus.FAIL, "Tools, Equipment & Supplies Drop Down was not Found in Mega Menu");
				homePage.takeScreenShot();
				
			}
					 
			
			if (categoryPage.isElementPresent(categoryPage.CableChainCatLeftLnk)){
				cableChainPage = categoryPage.clkCableChainCatLeftLnk();
				cableChainPage.waitForPageLoad(driver);
				TimeUnit.SECONDS.sleep(2);
				System.out.println("Cable Chain link in Category Clicked");
				//Thread.sleep(5000);
				
				isPageDisplayed = cableChainPage.isPageDisplayed(cableChainPage.CableChainSubCatLnk);
				
				if (isPageDisplayed){
					test.log(LogStatus.PASS, "Cable&Chain Page is displayed");
					cableChainPage.takeScreenShot();
					
				}else{
					
					test.log(LogStatus.FAIL, "Cable&Chain Page is NOT displayed");
					cableChainPage.takeScreenShot();
					Assert.assertEquals(isPageDisplayed, true);
				}
				
			} else{
				test.log(LogStatus.FAIL, "Cable&Chain Left Link in Category Page was not Found");
			}
			
			if (cableChainPage.isElementPresent(cableChainPage.CableChainSubCatLnk)){
				cableChainPLPPage = cableChainPage.clkCabChainSubCatLnk();	
				cableChainPLPPage.waitForPageLoad(driver);
				TimeUnit.SECONDS.sleep(2);
				System.out.println("Cable Chain link in Cable Chain Page Clicked");
				//Thread.sleep(5000);
				
				//cableChainPage.waitForPageLoad(driver);
				isPageDisplayed = cableChainPLPPage.isPageDisplayed(cableChainPLPPage.CabChainPLPHeadTxt);
				if (isPageDisplayed){
					test.log(LogStatus.PASS, "Cable&Chain PLP Page is displayed");
					cableChainPLPPage.takeScreenShot();
					
				}else{
					test.log(LogStatus.FAIL, "Cable&Chain PLP Page is NOT displayed");					
					cableChainPLPPage.takeScreenShot();
					Assert.assertEquals(isPageDisplayed, true);
				}
				
			} else{
				test.log(LogStatus.FAIL, "Cable&Chain Link in Cable&Chain Page was not Found");
			}
			//List<WebElement> PaginationElements = driver.findElements(cableChainPLPPage.PaginationBy);			
			
			List<WebElement> ROLBtnElements = driver.findElements(cableChainPLPPage.RolBtnBy);
			System.out.println("Total ROL elements= " + ROLBtnElements.size());			
			
			while(ROLBtnElements.size()==0){
				driver.findElement(cableChainPLPPage.nextBy).click();
				ROLBtnElements = driver.findElements(cableChainPLPPage.RolBtnBy);
				//System.out.println("Total ROL elements inside loop= " + ROLBtnElements.size());	
			}			
			
			String btnId = ROLBtnElements.get(0).getAttribute("id");
			System.out.println("btnId= " + btnId);	
			
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(cableChainPLPPage.prodPricePLPBy)).build().perform();
			//System.out.println("----------------------------");
			/*String prodNamePLP = driver.findElement(cableChainPLPPage.prodNamePLPBy).getText().toString();			
			String prodPartPLP = driver.findElement(cableChainPLPPage.prodPartPLPBy).getText().toString();			
			String pricePLP = driver.findElement(cableChainPLPPage.prodPricePLPBy).getText().toString();
			System.out.println("pricePLP= " + pricePLP);
			//String[] pricePLPArray = pricePLPText.split("/");			
			//String pricePLP = pricePLPArray[0].toString();
			
			String beforePricePLP = "product not on sale";
			if (cableChainPLPPage.isElementPresent(cableChainPLPPage.beforePricePLPBy)){
				String beforePLPText = driver.findElement(cableChainPLPPage.beforePricePLPBy).getText().toString();
				beforePricePLP = beforePLPText.substring(3);	
			}
											
			String qtyPLP = driver.findElement(cableChainPLPPage.prodQtyPLPBy).getAttribute("value");			*/
			
			ROLBtnElements.get(0).click();
			test.log(LogStatus.PASS, "ROL button clicked. Pick Up in Store window is displayed");			
			 
			//Switching to Pick Up-Check Out Window 
			String puCheckoutWindow = driver.getWindowHandle();
			driver.switchTo().window(puCheckoutWindow);
			
			takeScreenShot();
			/*//Capturing values form PU Window
			String prodNamePU = driver.findElement(cableChainPLPPage.prodNamePU1By).getText().toString();
			System.out.println("prodNamePU= " + prodNamePU);
			//String prodPartPU = driver.findElement(cableChainPLPPage.partNumberPU1By).getText().toString();	
			String prodPartPU = driver.findElement(cableChainPLPPage.partNumberPU1By).getText().toString();	
			String pricePU = driver.findElement(cableChainPLPPage.pricePU1By).getText().toString();	
			
			String beforePricePU = "product not on sale";
			if (cableChainPLPPage.isElementPresent(cableChainPLPPage.beforePU1By)){
				String beforePUText = driver.findElement(cableChainPLPPage.beforePU1By).getText().toString();			
				beforePricePU = beforePUText.substring(3);	
			}
						
			String qtyPU = driver.findElement(cableChainPLPPage.qtyPU1By).getAttribute("value");
			
			prodNamePLP = prodNamePU;
			prodPartPLP = prodPartPU;
			
			*/
			shoppingCartPage = cableChainPLPPage.clickCheckOutNowPU();			
			shoppingCartPage.waitForPageLoad(driver);
			takeScreenShot();
			
			//checking if Chat Window is displayed and closing it if present
			if (shoppingCartPage.isElementPresent(shoppingCartPage.chatWinCloseBy)){
				shoppingCartPage.closeChatWindow();
			}
			try{
				//Capturing values form Shopping Cart Page				
				productNameCart = shoppingCartPage.ProductNameCart.getText().toString();
				System.out.println("productNameCart= " + productNameCart);			
				partNumberCart = shoppingCartPage.PartNumberCart.getText().toString();			
				fulfillmentType = shoppingCartPage.FulfillmentType.getText().toString();			
				storeAddress = shoppingCartPage.StoreAddress.getText().toString();			
				unitPriceCart = shoppingCartPage.UnitPriceCart.getText().toString();		
				corePriceCart = shoppingCartPage.CorePriceCart.getText().toString();			
				qtyCart = shoppingCartPage.QtyCart.getAttribute("value");			
				subtotalCart = shoppingCartPage.SubtotalCart.getText().toString();
				discountCart = "product not on sale.no discount.";
				if (shoppingCartPage.isElementPresent(shoppingCartPage.DiscountCart)){
					discountCart = shoppingCartPage.DiscountCart.getText().toString();	
				}
							
				totalCart = shoppingCartPage.TotalCart.getText().toString();			
				rOLSubtotal = shoppingCartPage.ROLSubtotal.getText().toString();			
				bOLSubtotal = shoppingCartPage.BOLSubtotal.getText().toString();			
				orderSubtotal = shoppingCartPage.OrderSubtotal.getText().toString();	
				youSaved = "product not on sale.no discount.";
				if (shoppingCartPage.isElementPresent(shoppingCartPage.YouSaved)){
					youSaved = shoppingCartPage.YouSaved.getText().toString();	
				}
				
			}catch(Exception e){
				System.out.println("Exception occured while capturing values from Cart: "+e.getMessage());
			}
			
			
			/*//checking if Chat Window is displayed and closing it if present
			if (shoppingCartPage.isElementPresent(shoppingCartPage.chatWinCloseBy)){
				shoppingCartPage.closeChatWindow();
			}*/
						
			
			checkoutSignInPage=shoppingCartPage.clkCheckoutBtnInCart();			
			checkoutSignInPage.waitForPageLoad(driver);
			
			TimeUnit.SECONDS.sleep(2);
			
			takeScreenShot();
			
			
			//Creating New Customer Account from Sign In Page and navigating to Reservation Contact Info Page
			try{				
				resContactInfoPage = checkoutSignInPage.creatNewCustAccount();	
				resContactInfoPage.waitForPageLoad(driver);				
				TimeUnit.SECONDS.sleep(2);
				
				takeScreenShot();				
				
			}catch(Exception e){				
				test.log(LogStatus.FAIL, "FAIL:Reservation Contact Info Page is NOT displayed");			
				takeScreenShot();
			}
			
			//clicking Review Order Button from Reservation Contact Info Page for ROL order
			try{				
				reviewOrderPage = resContactInfoPage.ClkReviewOrder();	
				reviewOrderPage.waitForPageLoad(driver);				
				TimeUnit.SECONDS.sleep(2);
				takeScreenShot();				
				
			}catch(Exception e){				
				test.log(LogStatus.FAIL, "FAIL:Review Order Page is NOT displayed");			
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
					
					test.log(LogStatus.PASS, "PASS:Order Placed. "+ RunConfig.ORDER_NUMBER);
					System.out.println("PASS:Order Placed. "+ RunConfig.ORDER_NUMBER);
					
					orderSuccess=true;
					testResult = "PASS";
					System.out.println("testResult= "+ testResult);
					takeScreenShot();
				} else{
					test.log(LogStatus.FAIL, "FAIL:Order Confimation Page is NOT displayed");			
					takeScreenShot();
				}
				
				
			}catch(Exception e){				
				test.log(LogStatus.FAIL, "FAIL:Order Confimation Page is NOT displayed");			
				takeScreenShot();
			}
			
			//Verifying Order Confirmation Page
			/*try{				
				if (orderConfirmationPage.isOrderPlaced()){
					orderSuccess=true;
					testResult = "PASS";
					test.log(LogStatus.PASS, "PASS:Order Placed. "+ RunConfig.ORDER_NUMBER);
					orderConfirmationPage.takeScreenShot();	
				}else{		
					test.log(LogStatus.FAIL, "FAIL:Order Confimation & Order Number NOT displayed");			
					reviewOrderPage.takeScreenShot();
				}
				
				
			}catch(Exception e){				
				test.log(LogStatus.FAIL, "FAIL:Order Confimation & Order Number NOT displayed");			
				reviewOrderPage.takeScreenShot();
			}
*/			
			if (orderSuccess){		
				
				/*//Writing captured values from PLP to Data Sheet
				try{				
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 9,prodNamePLP);	
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 10,prodPartPLP);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 11,pricePLP);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 12,beforePricePLP);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 13,qtyPLP);					
					
				}catch(Exception e){				
					System.out.println("INFO:Failed to write captured values from PLP & PU window to Data Sheet");			
					
				}*/
				
				//Writing captured values from Shopping Cart to Test Results-Data Sheet
				try{				
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 18,productNameCart);	
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 19,partNumberCart);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 20,fulfillmentType);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 21,storeAddress);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 22,unitPriceCart);				
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 23,corePriceCart);	
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 24,qtyCart);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 25,subtotalCart);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 26,discountCart);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 27,totalCart);
					//Order Summary Values
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 28,rOLSubtotal);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 29,bOLSubtotal);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 30,orderSubtotal);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 31,youSaved);
					
				}catch(Exception e){				
					System.out.println("INFO:Failed to write captured values from Shopping Cart Page to Data Sheet");			
					
				}
				
				Hashtable<String, String> orderValues = orderConfirmationPage.getOrderDetails();
				
				try{	
					//Writing captured values from Order Confirmation Page to Test Results-Data Sheet
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 8,testResult);
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 32,orderValues.get("orderNumber"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 33,orderValues.get("productName"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 34,orderValues.get("partNumber"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 35,orderValues.get("fulfillmentType"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 36,orderValues.get("unitPrice"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 37,orderValues.get("corePrice"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 38,orderValues.get("qty"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 39,orderValues.get("subtotal"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 40,orderValues.get("discount"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 41,orderValues.get("itemTotal"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 42,orderValues.get("pickUpLocation"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 43,orderValues.get("pickUpPerson"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 44,orderValues.get("rOLSubtotal"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 45,orderValues.get("tax"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 46,orderValues.get("finalTotal"));
					DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 47,orderValues.get("youSaved"));
					
					
				}catch(Exception e){				
					System.out.println("INFO:Failed to write captured values from Order Confirmation Page to Data Sheet");			
					
				}
				
				
			}
			
			/*Set<String> winHandles = driver.getWindowHandles();
			System.out.println("Total Windows= " + winHandles.size());
			if(winHandles.size()!=0){
				String checkoutWindow = driver.getWindowHandle();
				driver.switchTo().window(checkoutWindow);
			}else{
				test.log(LogStatus.FAIL, "check out Window is not displayed");
			}*/
			
			
			
			
			/*for (String winHandle : driver.getWindowHandles()) {
			    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}*/
			
			
	}
	
	
	
	@AfterMethod
	public void quit(){
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
