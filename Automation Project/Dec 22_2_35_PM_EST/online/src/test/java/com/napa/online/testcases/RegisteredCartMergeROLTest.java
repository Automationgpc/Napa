package com.napa.online.testcases;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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



public class RegisteredCartMergeROLTest extends BaseTest{
	
	String testCaseName="RegisteredCartMergeROLTest";	
	
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
	
	
	
	String year = "";
	String make = "";
	String model = "";
	String vehicle = "";
	
	String product1="";
	String product1Qty="";
	String product2 = "";
	String product2Qty = "";
	
	
	boolean orderSuccess = false;
	String testResult = "FAIL";

	String product1Cart="";
	String part1Cart ="";
	String fulfillment1 ="";
	String storeCart1 = "";
	String unitPriceCart1 = "";
	String corePriceCart1 = "";
	String qtyCart1 = "";
	String subtotalCart1 = "";
	String discountCart1 = "";
	String totalCart1 = "";			
	
	String product2Cart="";
	String part2Cart ="";
	String fulfillment2 ="";
	String storeCart2 = "";
	String unitPriceCart2 = "";	
	String corePriceCart2 = "";
	String qtyCart2 = "";
	String subtotalCart2 = "";
	String discountCart2 = "";
	String totalCart2 = "";
	
	String productsCart="";
	String partsCart ="";
	String fulfillments ="";
	String storesCart = "";
	String unitPricesCart = "";
	String corePricesCart = "";
	String qtysCart = "";
	String subtotalsCart = "";
	String discountsCart = "";
	String totalsCart = "";
	String rOLSubtotalCart = "";
	String bOLSubtotalCart = "";
	String orderSubtotalCart= "";
	String youSavedCart = "";
	
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
	public void Guest_Cart_Merge_ROL_Order(Hashtable<String,String> data) throws InterruptedException{
		
		String testDesc = data.get("TestDesc");
		//System.out.println("test Desc= "+testDesc);
		test = extent.startTest(testDesc);
		
		year = data.get("VehicleYear");
		make = data.get("VehicleMake");
		model = data.get("VehicleModel");
		vehicle = year+" "+make+" "+model;
		
		String newStoreAddress=data.get("StoreAddress");
		String newStoreZip=data.get("StoreZip");		
		
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
		
		//emptying shopping cart if it is not empty
		//homePage.header.emptyShoppingCart();
		
		homePage = homePage.registerNewUser();
		
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
					Assert.fail();
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
							test.log(LogStatus.PASS, "Successfully logged in as: "+userFullName);
						}					
					
				}else{
					test.log(LogStatus.FAIL, "FAIL:User Login Failed");
					Assert.fail();
				}
				
				takeScreenShot();				
				
			}catch(Exception e){				
				test.log(LogStatus.FAIL, "FAIL:User Login Failed");
				takeScreenShot();
				Assert.fail();
				
			}				
			
			
		}		
				
		//Emptying ShoppingCart if there are any items in Cart
		homePage.header.emptyShoppingCart();
		TimeUnit.SECONDS.sleep(3);
		
		//homePage.header.NapaLogoHdr.click();
		
		//homePage.waitForPageLoad(driver);
			
		//homePage.waitForPageLoad(driver);
		homePage = homePage.header.localizeStore(newStoreAddress, newStoreZip);
		TimeUnit.SECONDS.sleep(3);
		homePage.waitForPageLoad(driver);
		homePage = homePage.header.addVehicleFromHdr(year, make, model);		
		homePage.waitForPageLoad(driver);	
		TimeUnit.SECONDS.sleep(3);
			
		//homePage.takeScreenShot();		
		
		product1 = data.get("Product1");
		product1Qty = data.get("Product1Qty");
		
		//pDPpage = homePage.header.searchAllProductsWithPart(product1);
		pDPpage = homePage.header.searchVehSpecificWithPart(product1);
		pDPpage.waitForPageLoad(driver);
		pickUpInStorePage=pDPpage.clickROLBtn(product1Qty);
		//pickUpInStorePage.waitForPageLoad(driver);
		TimeUnit.SECONDS.sleep(3);
		//Switching to Pick Up-Check Out Window 
		String puCheckoutWindow = driver.getWindowHandle();
		driver.switchTo().window(puCheckoutWindow);
		
		shoppingCartPage = pickUpInStorePage.clickCheckOutNowPU();
		
		shoppingCartPage.waitForPageLoad(driver);		
		test.log(LogStatus.PASS, "Shopping Cart page is displayed");
		takeScreenShot();
		
		//checking if Chat Window is displayed and closing it if present
		try{
			if (shoppingCartPage.isElementPresent(shoppingCartPage.chatWinCloseBy)){
				shoppingCartPage.closeChatWindow();
			}
			
		}catch(Exception e){
			
		}
		try{
			//Capturing values from Shopping Cart Page				
			product1Cart = shoppingCartPage.ProductNameCart.getText().toString();
			System.out.println("product1Cart= " + product1Cart);			
			part1Cart = shoppingCartPage.PartNumberCart.getText().toString();	
			System.out.println("part1Cart= " + part1Cart);			
			unitPriceCart1 = shoppingCartPage.UnitPriceCart.getText().toString();	
			System.out.println("unitPriceCart1= " + unitPriceCart1);
			corePriceCart1 = shoppingCartPage.CorePriceCart.getText().toString();			
			qtyCart1 = shoppingCartPage.QtyCart.getAttribute("value");
			System.out.println("qtyCart1= " + qtyCart1);
			subtotalCart1 = shoppingCartPage.SubtotalCart.getText().toString();
			discountCart1 = "product not on sale.no discount.";
			if (shoppingCartPage.isElementPresent(shoppingCartPage.DiscountCart)){
				discountCart1 = shoppingCartPage.DiscountCart.getText().toString();	
			}
						
			totalCart1 = shoppingCartPage.TotalCart.getText().toString();	
			fulfillment1 = shoppingCartPage.FulfillmentType.getText().toString();
			System.out.println("fulfillment1= " + fulfillment1);
			storeCart1 = shoppingCartPage.StoreAddress.getText().toString();
			System.out.println("storeCart1= " + storeCart1);			
			
			
		}catch(Exception e){
			System.out.println("Exception occured while capturing values from Cart: "+e.getMessage());
		}
				
		//checking if Chat Window is displayed and closing it if present
		try{
			if (shoppingCartPage.isElementPresent(shoppingCartPage.chatWinCloseBy)){
				shoppingCartPage.closeChatWindow();
			}
			
		}catch(Exception e){
			
		}			
		
		
		//Logging out
		homePage = shoppingCartPage.header.doLogOut();
		
		if (homePage.isElementPresent(homePage.header.MyMenuNameDD)){
			test.log(LogStatus.FAIL, "Logout Failed.");
			
		}else{
			test.log(LogStatus.PASS, "Logout Successful.");
		}
		
		//shoppingCartPage.takeScreenShot();
		
		homePage = homePage.header.localizeStore(newStoreAddress, newStoreZip);
		//boolean isStoreLocalized2 = homePage.header.localizeStoreFromHeader(newStoreAddress, newStoreZip);
		homePage.waitForPageLoad(driver);
		TimeUnit.SECONDS.sleep(3);
		test.log(LogStatus.PASS, "Store Localized to: "+data.get("StoreAddress")+","+data.get("StoreZip"));
		
		homePage = homePage.header.addVehicleFromHdr(year, make, model);		
		
		homePage.waitForPageLoad(driver);	 
		TimeUnit.SECONDS.sleep(3);
		product2 = data.get("Product2");
		product2Qty = data.get("Product2Qty");
		
		//searching for Motor Oil in Global search bar
		//pLPGlobalKeywordSearchpage = homePage.header.searchAllProductsWithKeyword(product2);		
		//pLPGlobalVehicleSelectedpage = pLPGlobalKeywordSearchpage.selectVehfrmListinHdrOnPLP();	
		
		pLPGlobalVehicleSelectedpage = homePage.header.searchVehSpecificWithKeyword(product2);
		pLPGlobalVehicleSelectedpage.waitForPageLoad(driver);
		
		//Navigating to PDP page by clicking Product2 from PLP page
		try{				
			pDPpage = pLPGlobalVehicleSelectedpage.clickProduct();
			pDPpage.waitForPageLoad(driver);
			test.log(LogStatus.PASS, "PASS:"+product2+" PDP page is displayed");
			takeScreenShot();				
			
		}catch(Exception e){				
			test.log(LogStatus.FAIL, "FAIL:"+product2+" PDP page is NOT displayed");	
			takeScreenShot();
			Assert.fail();
			
		}
		
		
		/*//capturing Product2 details from PDP page
		try{
			if(pDPpage.isElementPresent(pDPpage.PricePDP)){
				product2PDP = pDPpage.ProductNamePDP.getText().toString();
				System.out.println("product2PDP:"+product2PDP);
				part2PDP = pDPpage.PartNamePDP.getText().toString();
				System.out.println("part2PDP:"+part2PDP);
				price2FullPDP = pDPpage.PricePDP.getText().toString();
				System.out.println("price2FullPDP:"+price2FullPDP);
				Fitment2PDP = pDPpage.FitmentConfirmedPDP.getText().toString();
				System.out.println("Fitment2PDP:"+Fitment2PDP);
			}
			
		}catch(Exception e){
			test.log(LogStatus.FAIL, "Failed to capture Product2 values from PDP page");
			System.out.println("Failed to capture Product2 values from PDP page:"+e.getMessage());
		}*/
		
		pickUpInStorePage = pDPpage.clickROLBtnFitmentConfirmed(product2Qty);
		pickUpInStorePage.waitForPageLoad(driver);
		
		TimeUnit.SECONDS.sleep(3);
		//Switching to Pick Up-Check Out Window 
		String puCheckoutWindow1 = driver.getWindowHandle();
		driver.switchTo().window(puCheckoutWindow1);
		
		try{
			if(pickUpInStorePage.isElementPresent(pickUpInStorePage.AtcConfirmationTxt)){
				System.out.println("AtcConfirmationTxt:"+pickUpInStorePage.AtcConfirmationTxt.getText().toString());
				if(pickUpInStorePage.AtcConfirmationTxt.getText().toString().contains("Successfully added to cart")){
					test.log(LogStatus.PASS, product2+" added successfully to Cart.");
				}
			}
		}catch(Exception e){
			test.log(LogStatus.FAIL, product2+" NOT added to Cart. Exception occured");
			Assert.fail();
			System.out.println(product2+" NOT added to Cart. Exception occured:");
		}
		
		shoppingCartPage =pickUpInStorePage.clickCheckOutNowPU();
		
		shoppingCartPage.waitForPageLoad(driver);
		
		//Capturing Product 2 values form Shopping Cart Page
		try{
							
			product2Cart = shoppingCartPage.ProductNameCart.getText().toString();
			System.out.println("product2Cart= " + product2Cart);			
			part2Cart = shoppingCartPage.PartNumberCart.getText().toString();	
			System.out.println("part2Cart= " + part2Cart);
			
			//System.out.println("storeCart1= " + storeCart1);
			unitPriceCart2 = shoppingCartPage.UnitPriceCart.getText().toString();	
			System.out.println("unitPriceCart2= " + unitPriceCart2);
			corePriceCart2 = shoppingCartPage.CorePriceCart.getText().toString();			
			qtyCart2 = shoppingCartPage.QtyCart.getAttribute("value");
			//System.out.println("qtyCart1= " + qtyCart1);
			subtotalCart2 = shoppingCartPage.SubtotalCart.getText().toString();
			discountCart2 = "product not on sale.no discount.";
			if (shoppingCartPage.isElementPresent(shoppingCartPage.DiscountCart)){
				discountCart2 = shoppingCartPage.DiscountCart.getText().toString();	
			}
						
			totalCart2 = shoppingCartPage.TotalCart.getText().toString();	
			fulfillment2 = shoppingCartPage.FulfillmentType.getText().toString();
			//System.out.println("fulfillment1= " + fulfillment1);
			storeCart2 = shoppingCartPage.StoreAddress.getText().toString();		
			//rOLSubtotal1 = shoppingCartPage.ROLSubtotal.getText().toString();			
			/*bOLSubtotal1 = shoppingCartPage.BOLSubtotal.getText().toString();			
			orderSubtotal1 = shoppingCartPage.OrderSubtotal.getText().toString();	
			youSaved1 = "product not on sale.no discount.";
			if (shoppingCartPage.isElementPresent(shoppingCartPage.YouSaved)){
				youSaved1 = shoppingCartPage.YouSaved.getText().toString();	
			}*/
			
		}catch(Exception e){
			System.out.println("Exception occured while capturing Product2 values from Cart: "+e.getMessage());
		}
		
		//checking if Chat Window is displayed and closing it if present
		try{
			if (shoppingCartPage.isElementPresent(shoppingCartPage.chatWinCloseBy)){
				shoppingCartPage.closeChatWindow();
			}
			
		}catch(Exception e){
			
		}
						 
		checkoutSignInPage = shoppingCartPage.clkCheckoutBtnInCart();
		checkoutSignInPage.waitForPageLoad(driver);
		shoppingCartPage = checkoutSignInPage.signInAsReturningUser(RunConfig.EMAIL_ID, RunConfig.PASSWORD);
		shoppingCartPage.waitForPageLoad(driver);	
		
		//Capturing values from Merged Shopping Cart
		rOLSubtotalCart = shoppingCartPage.ROLSubtotal.getText().toString();			
		bOLSubtotalCart = shoppingCartPage.BOLSubtotal.getText().toString();			
		orderSubtotalCart = shoppingCartPage.OrderSubtotal.getText().toString();	
		youSavedCart = "product not on sale.no discount.";
		if (shoppingCartPage.isElementPresent(shoppingCartPage.YouSaved)){
			youSavedCart = shoppingCartPage.YouSaved.getText().toString();	
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
			takeScreenShot();				
			
		}catch(Exception e){				
			test.log(LogStatus.FAIL, "FAIL:Check out as Returning User failed. Reservation Contact Info Page is NOT displayed");	
			Assert.fail();
			takeScreenShot();
		}
		
		//clicking Review Order Button from Reservation Contact Info Page for ROL order
		try{				
			reviewOrderPage = resContactInfoPage.ClkReviewOrder();	
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
				action.moveToElement(driver.findElement(orderConfirmationPage.ProductName1By)).build().perform();
				
				test.log(LogStatus.PASS, "PASS:Order Placed. "+ RunConfig.ORDER_NUMBER);
				System.out.println("PASS:Order Placed. "+ RunConfig.ORDER_NUMBER);
				
				orderSuccess=true;
				testResult = "PASS";
				System.out.println("testResult= "+ testResult);
				takeScreenShot();
			} else{
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
				productsCart = "Product1Cart:"+product1Cart+","+"Product2Cart:"+product2Cart;
				partsCart = "part1Cart:"+part1Cart+","+"part2Cart:"+part2Cart;
				unitPricesCart = "unitPriceCart1:"+unitPriceCart1+","+"unitPriceCart2:"+unitPriceCart2;
				corePricesCart = "corePriceCart1:"+corePriceCart1+ ","+ "corePriceCart2:"+corePriceCart2;
				qtysCart = "qtyCart1:"+qtyCart1+","+"qtyCart2:"+qtyCart2;
				subtotalsCart = "subtotalCart1:"+subtotalCart1+","+"subtotalCart2:"+subtotalCart2;
				discountsCart = "discountCart1:"+discountCart1+","+"discountCart2:"+discountCart2;
				totalsCart = "totalCart1:"+totalCart1+","+"totalCart2:"+totalCart2;
				fulfillments = "fulfillment1:"+fulfillment1+","+"fulfillment2:"+fulfillment2;
				storesCart = "storeCart1:"+storeCart1+","+"storeCart2:"+storeCart2;
				
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 23,productsCart);	
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 24,partsCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 25,unitPricesCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 26,corePricesCart);			
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 27,qtysCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 28,subtotalsCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 29,discountsCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 30,totalsCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 31,fulfillments);				
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 32,storesCart);
				
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 33,rOLSubtotalCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 34,bOLSubtotalCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 35,orderSubtotalCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 36,youSavedCart);
				
			}catch(Exception e){				
				System.out.println("INFO:Failed to write captured values from Shopping Cart Page to Data Sheet");			
				
			}
			
			
			Hashtable<String, String> orderValues = orderConfirmationPage.getOrderDetailsTwoItemsSameStore();
			
			try{	
				productsOrder = "product1Order:"+orderValues.get("productName1")+","+"product2Order:"+orderValues.get("productName2");
				partsOrder = "part1Order:"+ orderValues.get("partNumber1") +","+"part2Order:"+ orderValues.get("partNumber2");
				unitPricesOrder = "unitPrice1Order:"+ orderValues.get("unitPrice1") +","+"unitPrice2Order:"+ orderValues.get("unitPrice2");
				corePricesOrder = "corePrice1Order:"+ orderValues.get("corePrice1") + ","+ "corePrice2Order:"+ orderValues.get("corePrice2");
				qtysOrder = "qty1Order:"+ orderValues.get("qty1") +","+"qty2Order:"+ orderValues.get("qty2");
				subtotalsOrder = "subtotal1Order:"+ orderValues.get("subtotal1") +","+"subtotal2Order:"+ orderValues.get("subtotal2");
				discountsOrder = "discount1Order:"+ orderValues.get("discount1") +","+"discount2Order:"+ orderValues.get("discount2");
				itemTotalsOrder = "itemTotal1Order:"+ orderValues.get("itemTotal1") +","+"itemTotal2Order:"+ orderValues.get("itemTotal2");
				
				//Writing captured values from Order Confirmation Page to Test Results-Data Sheet
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 13,testResult);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 37,orderValues.get("orderNumber"));
				//Product1 details
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 38,productsOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 39,partsOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 40,orderValues.get("fulfillmentType"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 41,unitPricesOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 42,corePricesOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 43,qtysOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 44,subtotalsOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 45,discountsOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 46,itemTotalsOrder);				
				
				
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 47,orderValues.get("pickUpLocation"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 48,orderValues.get("pickUpPerson"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 49,orderValues.get("rOLSubtotal"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 50,orderValues.get("tax"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 51,orderValues.get("finalTotal"));
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 52,orderValues.get("youSaved"));
				
				
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
