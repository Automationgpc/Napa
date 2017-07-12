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
import com.napa.online.pages.RefineVehicleConditionsPage;
import com.napa.online.pages.ResContactInfoPage;
import com.napa.online.pages.ReviewOrderPage;
import com.napa.online.pages.ShoppingCartPage;
import com.napa.online.util.DataUtil;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.LogStatus;

public class RegKeySearchToolsEqMultItemMultiStrROLTest extends BaseTest{
	
	String testCaseName="RegKeySearchToolsEqMultItemMultiStrROLTest";	
	
	HomePage homePage = null;
	LoginPage loginPage = null;
	PDPpage pDPpage = null;
	RefineVehicleConditionsPage refineVehicleConditionsPage = null;
	PickUpInStorePage pickUpInStorePage = null;
	ShoppingCartPage shoppingCartPage = null;
	PLPGlobalKeywordSearchpage pLPGlobalKeywordSearchpage = null;
	PLPGlobalVehicleSelectedpage pLPGlobalVehicleSelectedpage = null;
	CheckoutSignInPage checkoutSignInPage = null;
	ResContactInfoPage resContactInfoPage = null;
	ReviewOrderPage reviewOrderPage = null;
	OrderConfirmationPage orderConfirmationPage =  null;
	
	//Input Variables
	String vehicleVin = "";
	String prod1StoreAddress="";
	String prod1StoreZip="";
	String product1Keyword="";
	String product1PartNumber="";
	String product1Qty="";
	String prod2StoreAddress="";
	String prod2StoreZip="";
	String product2Keyword="";
	String product2PartNumber = "";
	String product2Qty = "";
	
	boolean confirmFitment = false;
	
	//Test Result Variables
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
	String orderNumbers="";
	String productsOrder="";
	String partsOrder ="";		
	String unitPricesOrder = "";
	String corePricesOrder = "";
	String qtysOrder = "";
	String subtotalsOrder = "";
	String discountsOrder = "";
	String itemTotalsOrder = "";
	String fulfillmentsOrder = "";
	String pickUpLocationOrder1 = "";
	String pickUpLocationOrder2 = "";
	String pickUpPersonOrder1 = "";
	String pickUpPersonOrder2 = "";
	String rOLSubtotalsOrder = "";
	String taxesOrder = "";
	String finalTotalsOrder = "";
	String youSavedOrder = "";
	
	@Test(dataProvider="getData")
	public void Reg_Key_Search_Tools_Eq_Multi_Item_Multi_Store_ROL_Order(Hashtable<String,String> data) throws InterruptedException{
		
		vehicleVin = data.get("VehicleVIN");
		prod1StoreAddress = data.get("Prod1StoreAddress");
		prod1StoreZip = data.get("Prod1StoreZip");
		product1Keyword = data.get("Product1Keyword");
		product1PartNumber = data.get("Product1PartNumber");
		product1Qty = data.get("Product1Qty");
		prod2StoreAddress = data.get("Prod2StoreAddress");
		prod2StoreZip = data.get("Prod2StoreZip");
		product2Keyword = data.get("Product2Keyword");
		product2PartNumber = data.get("Product2PartNumber");
		product2Qty = data.get("Product2Qty");
		
		String testDesc = data.get("TestDesc");
		test = extent.startTest(testDesc);		
		
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
		//driver.manage().deleteCookieNamed("napa-cart");
		
		homePage = homePage.registerNewUser();
		//emptying shopping cart if it is not empty
		//homePage.header.emptyShoppingCart();
		
		
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
		//homePage.header.emptyShoppingCart();
		//TimeUnit.SECONDS.sleep(3);
		
		//localizing to 1st Store, then add vehicle by VIN, Add 1st Product to Cart as a Guest User			
		homePage = homePage.header.localizeStore(prod1StoreAddress, prod1StoreZip);			
		homePage.waitForPageLoad(driver);
		//test.log(LogStatus.PASS, "Store Localized to: "+data.get("StoreAddress")+","+data.get("StoreZip"));
		TimeUnit.SECONDS.sleep(3);
		homePage = homePage.header.addVehicleFromHdrByVin(vehicleVin);			
		homePage.waitForPageLoad(driver);
		TimeUnit.SECONDS.sleep(3);
		homePage.takeScreenShot();	
		
		pLPGlobalVehicleSelectedpage = homePage.header.searchKeyGlobalBarToolsEq(product1Keyword);
		pLPGlobalVehicleSelectedpage.waitForPageLoad(driver);
		
		pickUpInStorePage = pLPGlobalVehicleSelectedpage.clickROLBtnInPLP(product1PartNumber);
		pickUpInStorePage.waitForPageLoad(driver);
		
		try{
			if(pickUpInStorePage.isElementPresent(pickUpInStorePage.AtcConfirmationTxt)){
				System.out.println("AtcConfirmationTxt:"+pickUpInStorePage.AtcConfirmationTxt.getText().toString());
				if(pickUpInStorePage.AtcConfirmationTxt.getText().toString().contains("Successfully added to cart")){
					test.log(LogStatus.PASS, "PASS:Product added successfully to Cart.");
				}
			}
		}catch(Exception e){
			test.log(LogStatus.FAIL, "FAIL:Product NOT added to Cart. Exception occured");
			Assert.fail();
			System.out.println("Product NOT added to Cart. Exception occured:");
		}
		
		shoppingCartPage =pickUpInStorePage.clickCheckOutNowPU();
		
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
		
		//changing to 2nd Store, Adding 2nd Product to Cart as a Guest User
		homePage = shoppingCartPage.header.localizeStore(prod2StoreAddress, prod2StoreZip);
		//homePage = homePage.header.localizeStore(prod2StoreAddress, prod2StoreZip);			
		homePage.waitForPageLoad(driver);
		//test.log(LogStatus.PASS, "Store Localized to: "+data.get("StoreAddress")+","+data.get("StoreZip"));
		TimeUnit.SECONDS.sleep(2);	
		
		pLPGlobalVehicleSelectedpage = homePage.header.searchKeyGlobalBarToolsEq(product2Keyword);
		pLPGlobalVehicleSelectedpage.waitForPageLoad(driver);
		
		pickUpInStorePage = pLPGlobalVehicleSelectedpage.clickROLBtnInPLP(product2PartNumber);
		pickUpInStorePage.waitForPageLoad(driver);
		
		try{
			if(pickUpInStorePage.isElementPresent(pickUpInStorePage.AtcConfirmationTxt)){
				System.out.println("AtcConfirmationTxt:"+pickUpInStorePage.AtcConfirmationTxt.getText().toString());
				if(pickUpInStorePage.AtcConfirmationTxt.getText().toString().contains("Successfully added to cart")){
					test.log(LogStatus.PASS, "PASS:Product added successfully to Cart.");
				}
			}
		}catch(Exception e){
			test.log(LogStatus.FAIL, "FAIL:Product NOT added to Cart. Exception occured");
			Assert.fail();
			System.out.println("Product NOT added to Cart. Exception occured:");
		}
		
		shoppingCartPage =pickUpInStorePage.clickCheckOutNowPU();
		
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
		
		//Capturing Product 1 values from Shopping Cart Page
		try{
							
			product1Cart = shoppingCartPage.ProductName1Cart.getText().toString();
			System.out.println("product1Cart= " + product1Cart);			
			part1Cart = shoppingCartPage.Part1Cart.getText().toString();	
			System.out.println("part1Cart= " + part1Cart);			
			unitPriceCart1 = shoppingCartPage.UnitPriceCart1.getText().toString();	
			System.out.println("unitPriceCart1= " + unitPriceCart1);
			corePriceCart1 = shoppingCartPage.CorePriceCart1.getText().toString();			
			qtyCart1 = shoppingCartPage.QtyCart1.getAttribute("value");
			System.out.println("qtyCart1= " + qtyCart1);
			subtotalCart1 = shoppingCartPage.SubtotalCart1.getText().toString();
			discountCart1 = "product not on sale.no discount.";
			if (shoppingCartPage.isElementPresent(shoppingCartPage.DiscountCart1)){
				discountCart1 = shoppingCartPage.DiscountCart1.getText().toString();	
			}
						
			totalCart1 = shoppingCartPage.TotalCart1.getText().toString();	
			fulfillment1 = shoppingCartPage.FulfillmentType1.getText().toString();
			System.out.println("fulfillment1= " + fulfillment1);
			storeCart1 = shoppingCartPage.StoreAddress1.getText().toString();
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
		
		//Capturing Product 2 values form Shopping Cart Page
		try{
							
			product2Cart = shoppingCartPage.ProductName2Cart.getText().toString();
			System.out.println("product2Cart= " + product2Cart);			
			part2Cart = shoppingCartPage.Part2Cart.getText().toString();	
			System.out.println("part2Cart= " + part2Cart);
			
			//System.out.println("storeCart1= " + storeCart1);
			unitPriceCart2 = shoppingCartPage.UnitPriceCart2.getText().toString();	
			System.out.println("unitPriceCart2= " + unitPriceCart2);
			corePriceCart2 = shoppingCartPage.CorePriceCart2.getText().toString();			
			qtyCart2 = shoppingCartPage.QtyCart.getAttribute("value");
			//System.out.println("qtyCart1= " + qtyCart1);
			subtotalCart2 = shoppingCartPage.SubtotalCart2.getText().toString();
			discountCart2 = "product not on sale.no discount.";
			if (shoppingCartPage.isElementPresent(shoppingCartPage.DiscountCart2)){
				discountCart2 = shoppingCartPage.DiscountCart2.getText().toString();	
			}
						
			totalCart2 = shoppingCartPage.TotalCart2.getText().toString();	
			fulfillment2 = shoppingCartPage.FulfillmentType2.getText().toString();
			//System.out.println("fulfillment1= " + fulfillment1);
			storeCart2 = shoppingCartPage.StoreAddress2.getText().toString();	
			
			rOLSubtotalCart = shoppingCartPage.ROLSubtotal.getText().toString();			
			bOLSubtotalCart = shoppingCartPage.BOLSubtotal.getText().toString();			
			orderSubtotalCart = shoppingCartPage.OrderSubtotal.getText().toString();	
			youSavedCart = "product not on sale.no discount.";
			if (shoppingCartPage.isElementPresent(shoppingCartPage.YouSaved)){
				youSavedCart = shoppingCartPage.YouSaved.getText().toString();	
			}
			
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
				//RunConfig.ORDER_NUMBER = orderConfirmationPage.OrderNumberTxt.getText().toString();
				
				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(orderConfirmationPage.ProductName1By)).build().perform();
				
				test.log(LogStatus.PASS, "PASS:Order Placed successfully. ");
				System.out.println("PASS:Order Placed. ");
				
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
			
			DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 14,testResult);
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
				
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 24,productsCart);	
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 25,partsCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 26,unitPricesCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 27,corePricesCart);			
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 28,qtysCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 29,subtotalsCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 30,discountsCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 31,totalsCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 32,fulfillments);				
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 33,storesCart);
				
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 34,rOLSubtotalCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 35,bOLSubtotalCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 36,orderSubtotalCart);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 37,youSavedCart);
				
			}catch(Exception e){				
				System.out.println("INFO:Failed to write captured values from Shopping Cart Page to Data Sheet");			
				
			}
			
			Hashtable<String, String> orderValues = orderConfirmationPage.getOrderDetailsTwoOrdersDiffStore();
			
			try{	
				orderNumbers = "orderNumber1:"+orderValues.get("orderNumber1")+","+"orderNumber2:"+orderValues.get("orderNumber2");
				productsOrder = "product1Order:"+orderValues.get("productName1")+","+"product2Order:"+orderValues.get("productName2");
				partsOrder = "part1Order:"+ orderValues.get("partNumber1") +","+"part2Order:"+ orderValues.get("partNumber2");
				fulfillmentsOrder = "fulfillment1Order:"+ orderValues.get("fulfillmentType1") +","+"fulfillment2Order:"+ orderValues.get("fulfillmentType2");
				unitPricesOrder = "unitPrice1Order:"+ orderValues.get("unitPrice1") +","+"unitPrice2Order:"+ orderValues.get("unitPrice2");
				corePricesOrder = "corePrice1Order:"+ orderValues.get("corePrice1") + ","+ "corePrice2Order:"+ orderValues.get("corePrice2");
				qtysOrder = "qty1:"+ orderValues.get("qty1") +","+"qty2:"+ orderValues.get("qty2");
				subtotalsOrder = "subtotal1Order:"+ orderValues.get("subtotal1") +","+"subtotal2Order:"+ orderValues.get("subtotal2");
				discountsOrder = "discount1Order:"+ orderValues.get("discount1") +","+"discount2Order:"+ orderValues.get("discount2");
				itemTotalsOrder = "itemTotal1Order:"+ orderValues.get("itemTotal1") +","+"itemTotal2Order:"+ orderValues.get("itemTotal2");
				pickUpLocationOrder1 = orderValues.get("pickUpLocation1");
				pickUpPersonOrder1 = orderValues.get("pickUpPerson1");
				pickUpLocationOrder2 = orderValues.get("pickUpLocation2");
				pickUpPersonOrder2 = orderValues.get("pickUpPerson2");
				rOLSubtotalsOrder = "rOLSubtotal1Order:"+ orderValues.get("rOLSubtotal1") +","+"rOLSubtotal2Order:"+ orderValues.get("rOLSubtotal2");
				taxesOrder = "tax1Order:"+ orderValues.get("tax1") +","+"tax2Order:"+ orderValues.get("tax2");
				finalTotalsOrder = "finalTotal1Order:"+ orderValues.get("finalTotal1") +","+"finalTotal2Order:"+ orderValues.get("finalTotal2");
				youSavedOrder = "youSaved1Order:"+ orderValues.get("youSaved1") +","+"youSaved2Order:"+ orderValues.get("youSaved2");
				
				//Writing captured values from Order Confirmation Page to Test Results-Data Sheet
				
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 38,orderNumbers);
				//Product1 details
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 39,productsOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 40,partsOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 41,fulfillmentsOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 42,unitPricesOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 43,corePricesOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 44,qtysOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 45,subtotalsOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 46,discountsOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 47,itemTotalsOrder);				
				
				
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 48,pickUpLocationOrder1);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 49,pickUpPersonOrder1);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 50,pickUpLocationOrder2);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 51,pickUpPersonOrder2);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 52,rOLSubtotalsOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 53,taxesOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 54,finalTotalsOrder);
				DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 55,youSavedOrder);
				
				
			}catch(Exception e){				
				System.out.println("INFO:Failed to write captured values from Order Confirmation Page to Data Sheet");			
				
			}
			
		}
		
		
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
