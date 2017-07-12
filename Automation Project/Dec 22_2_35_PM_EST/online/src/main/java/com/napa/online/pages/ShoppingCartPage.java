package com.napa.online.pages;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ShoppingCartPage extends BasePage{
	
	public ShoppingCartPage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	public final By chatWinCloseBy = By.xpath("//img[@onclick='bt_hideAnimation();']");
	
	@FindBy(xpath="//h1[contains(text(),'SHOPPING CART')]")
	public WebElement CartHeading;
	
	@FindBy(xpath="//div[@class='product-title']")
	public WebElement ProductNameCart;
	
	@FindBy(xpath="(//div[@class='product-title'])[1]")
	public WebElement ProductName2Cart;
	
	@FindBy(xpath="(//div[@class='product-title'])[2]")
	public WebElement ProductName1Cart;
	
	@FindBy(xpath="//span[@class='text']")
	public WebElement PartNumberCart;	
	
	@FindBy(xpath="(//td[@class='product-details'])[2]/div/div[2]/span[@class='text']")
	public WebElement Part1Cart;
	
	@FindBy(xpath="(//td[@class='product-details'])[1]/div/div[2]/span[@class='text']")
	public WebElement Part2Cart;
	
	@FindBy(xpath="//span[@class='fulfillment-type-text']")
	public WebElement FulfillmentType;
	
	@FindBy(xpath="//span[@class='fulfillment-type-text']")
	public WebElement FulfillmentType1;
	
	@FindBy(xpath="(//span[@class='fulfillment-type-text'])[1]")
	public WebElement FulfillmentType2;
	
	@FindBy(xpath="//div[@class='location-address']")
	public WebElement StoreAddress;
	
	@FindBy(xpath="//div[@class='location-address']")
	public WebElement StoreAddress1;
	
	@FindBy(xpath="(//div[@class='location-address'])[1]")
	public WebElement StoreAddress2;
	
	@FindBy(xpath="//tr[1]/td[4]")
	public WebElement UnitPriceCart;
	
	@FindBy(xpath="//tr[4]/td[4]")
	public WebElement UnitPriceCart1;
	
	@FindBy(xpath="//tr[1]/td[4]")
	public WebElement UnitPriceCart2;
	
	@FindBy(xpath="//tr[1]/td[5]")
	public WebElement CorePriceCart;
	
	@FindBy(xpath="//tr[4]/td[5]")
	public WebElement CorePriceCart1;
	
	@FindBy(xpath="//tr[1]/td[5]")
	public WebElement CorePriceCart2;	
	
	
	@FindBy(xpath="//input[@id='quantity0']")//use attribute "value" to get text
	public WebElement QtyCart;
	
	@FindBy(xpath="//input[@id='quantity0']")//use attribute "value" to get text
	public WebElement QtyCart1;
	
	@FindBy(xpath="//input[@id='quantity1']")//use attribute "value" to get text
	public WebElement QtyCart2;
	
	@FindBy(xpath="//td[@class='subtotal']")
	public WebElement SubtotalCart;
	
	@FindBy(xpath="(//td[@class='subtotal'])[2]")
	public WebElement SubtotalCart1;
	
	@FindBy(xpath="(//td[@class='subtotal'])[1]")
	public WebElement SubtotalCart2;
	
	@FindBy(xpath="//td[@class='discount']/div[@class='amount']")
	public WebElement DiscountCart;
	
	@FindBy(xpath="(//td[@class='discount']/div[@class='amount'])[2]")
	public WebElement DiscountCart1;
	
	@FindBy(xpath="(//td[@class='discount']/div[@class='amount'])[1]")
	public WebElement DiscountCart2;
	
	@FindBy(xpath="//td[@class='total-price']/div[@class='amount']")
	public WebElement TotalCart;
	
	@FindBy(xpath="(//td[@class='total-price']/div[@class='amount'])[2]")
	public WebElement TotalCart1;
	
	@FindBy(xpath="(//td[@class='total-price']/div[@class='amount'])[1]")
	public WebElement TotalCart2;
	
	@FindBy(xpath="//div[@class='order-delete-icon']")
	public WebElement DeleteOrder;
	
	@FindBy(xpath="(//div[@class='order-total-content-amount'])[1]")
	public WebElement ROLSubtotal;
	
	@FindBy(xpath="(//div[@class='order-total-content-amount'])[2]")
	public WebElement BOLSubtotal;
	
	@FindBy(xpath="//div[@class='order-total-content-amount combined']")
	public WebElement OrderSubtotal;
	
	@FindBy(xpath="//div[@class='order-total-saved-amount']")
	public WebElement YouSaved;
	
	@FindBy(xpath="//div[@class='location-address']")
	public WebElement PUStoreLocation;
	
	@FindBy(xpath="//a[@class='button-primary yellow']")
	public WebElement CheckOutCartBtn;
	
	public final By CheckOutCartBtnBy = By.xpath("//div[@class='checkout-button-wrapper']/a[@class='button-primary yellow']");
	
	public void closeChatWindow(){
		
		try{
			driver.findElement(chatWinCloseBy).click();
			System.out.println("Chat Window was closed ");
			
		}catch(Exception e){
			System.out.println("Exception Occured Closing Chat Window: "+e.getMessage());
			
		}
	}
	
	public CheckoutSignInPage clkCheckoutBtnInCart(){
		
		try{
			if(CheckOutCartBtn.isDisplayed()){
				CheckOutCartBtn.click();
			}
			
			test.log(LogStatus.PASS, "PASS:Checkout Signin Page is displayed");
			
			CheckoutSignInPage checkoutSignInPage = new CheckoutSignInPage(driver, test);
			PageFactory.initElements(driver, checkoutSignInPage);
			return checkoutSignInPage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in clkCheckoutBtnInCart(): ");
			test.log(LogStatus.FAIL, "FAIL:Checkout Signin Page is NOT displayed.Failed to click Checkout Btn in Cart.");
			//Assert.fail();
			return null;
		}
		
		
		
	}
	
	public ResContactInfoPage clkCheckoutInCartSignedInUser(){
		
		try{
			/*if(CheckOutCartBtn.isDisplayed()){
				CheckOutCartBtn.click();
			}*/
			WebElement CheckoutCartEle = (new WebDriverWait(driver, 20))
					  .until(ExpectedConditions.elementToBeClickable(CheckOutCartBtnBy));
			//WebElement AddNewVehicleHdrEle = driver.findElement(By.xpath(addNewVehicleHdrXpath));
			
			if(CheckoutCartEle.isDisplayed()){
				
				CheckoutCartEle.click();	
				System.out.println("ROL Check Out as Sign In User in Cart Page is CLICKED");
				//TimeUnit.SECONDS.sleep(2);
				
			}
			
			test.log(LogStatus.PASS, "PASS:Reservation Contact Info Page is displayed");
			
			ResContactInfoPage resContactInfoPage = new ResContactInfoPage(driver, test);
			PageFactory.initElements(driver, resContactInfoPage);
			return resContactInfoPage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in clkCheckoutInCartReturningUser()");
			//test.log(LogStatus.FAIL, "FAIL:Reservation Contact Info Page is NOT displayed");
			
			return null;
		}
	}
		
		public ShippingBillingPage clkBOLCheckoutSignedInUser(){
			
			try{
				/*if(CheckOutCartBtn.isDisplayed()){
					CheckOutCartBtn.click();
				}*/
				WebElement CheckoutCartEle = (new WebDriverWait(driver, 20))
						  .until(ExpectedConditions.elementToBeClickable(CheckOutCartBtnBy));
				//WebElement AddNewVehicleHdrEle = driver.findElement(By.xpath(addNewVehicleHdrXpath));
				
				if(CheckoutCartEle.isDisplayed()){					
					CheckoutCartEle.click();	
					System.out.println("BOL Check Out as Sign In User in Cart Page is CLICKED");
					//TimeUnit.SECONDS.sleep(2);
					
				}
				
				//test.log(LogStatus.PASS, "PASS:Reservation Contact Info Page is displayed");
				
				ShippingBillingPage shippingBillingPage = new ShippingBillingPage(driver, test);
				PageFactory.initElements(driver, shippingBillingPage);
				return shippingBillingPage;
				
			}catch(Exception e){
				System.out.println("Exception Occured.Shipping&Billing Page is not displayed");
				//test.log(LogStatus.FAIL, "FAIL:Reservation Contact Info Page is NOT displayed");
				
				return null;
			}
			
		
		
		
	}
	
}
