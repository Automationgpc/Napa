package com.napa.online.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.napa.online.base.BasePage;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ShippingBillingPage extends BasePage{
	
	public ShippingBillingPage(WebDriver driver, ExtentTest test){
		super(driver,test);
		
	}
	
	@FindBy(xpath="//h3[@class='box-content-title'][contains(text(),'SHIPPING INFORMATION')]")
	public WebElement ShippingTitle;
	
	//Shipping Address Elements
	@FindBy(xpath="//div[@class='saved-address-icon']")
	public WebElement SavedAddressBtn;
	
	@FindBy(xpath="//p[contains(text(),'Add new address')]")
	public WebElement AddNewAddressBtn;
	
	@FindBy(xpath="//div[@class='saved-address-block']")
	public WebElement SavedAddressBlock;
	
	@FindBy(xpath="//select[@id='addressType']")
	public WebElement AddressTypeSelect;
	
	@FindBy(xpath="//input[@id='firstName']")
	public WebElement FirstName;
	
	@FindBy(xpath="//input[@id='lastname']")
	public WebElement LastName;
	
	@FindBy(xpath="//input[@id='address1']")
	public WebElement AddressOne;
	
	@FindBy(xpath="//input[@id='address2']")
	public WebElement AddressTwo;
	
	@FindBy(xpath="//input[@id='city']")
	public WebElement City;
	
	@FindBy(xpath="//select[@id='regionIso']")
	public WebElement State;
	
	@FindBy(xpath="//input[@id='postcode']")
	public WebElement ZipCode;
	
	@FindBy(xpath="//input[@id='phone']")
	public WebElement PhoneNumber;
		
	@FindBy(xpath="//input[@name='saveInAddressBook']")
	public WebElement SaveAddressChkBox;
	
	
	@FindBy(xpath="//input[@id='sameAddressCheckbox']")
	public WebElement SameAsShipChkBx;
	
	@FindBy(xpath="//input[@id='cardnumber']")
	public WebElement CardNumber;
	
	@FindBy(xpath="//input[@id='securitycode']")
	public WebElement SecurityCode;
	
	@FindBy(xpath="//select[@id='cardType']")
	public WebElement SelectCardType;
	
	@FindBy(xpath="//select[@id='expirationMonth']")
	public WebElement SelectExpMonth;
	
	@FindBy(xpath="//select[@id='expirationYear']")
	public WebElement SelectExpYear;
	
	@FindBy(xpath="//button[@id='addShippingBillingAddress']")
	public WebElement ConfirmOrderBtn;
	
	@FindBy(xpath="//button[@id='useAddressEnteredActual']")
	public WebElement UseAddressEnteredBtn;
	
	public final String UseAddressEnteredBtnXpath = "//button[@id='useAddressEnteredActual']";
	
	@FindBy(xpath="//button[@id='backtoCart']")
	public WebElement BackToCartBtn;
	
	public void addCardDetails(String paymentType){
		try{
			Select cardType = new Select(SelectCardType);
			Select expMonth = new Select(SelectExpMonth);
			Select expYear = new Select(SelectExpYear);
			
			if(paymentType.equalsIgnoreCase("Visa")){
				CardNumber.sendKeys(RunConfig.VISA);
				SecurityCode.sendKeys("123");
				cardType.selectByVisibleText("Visa");				
			}
			expMonth.selectByVisibleText("02");	
			expYear.selectByVisibleText("2019");				
			
		}catch(Exception e){
			System.out.println("Exception Occured in addCardDetails()");
			test.log(LogStatus.FAIL, "FAIL:Failed to add credit card details in Shipping Page");
			Assert.fail();
			
		}
		
		
	}
	
	public ReviewOrderPage ClickConfirmOrderInfo(){
		
		try{
			ConfirmOrderBtn.click();
			System.out.println("Confirm Order Btn in Shipping Page clicked");
			TimeUnit.SECONDS.sleep(3);
			
			try{
				WebElement UseAddressEnteredBtnEle = (new WebDriverWait(driver, 20))
						  .until(ExpectedConditions.elementToBeClickable(By.xpath(UseAddressEnteredBtnXpath)));
				if(UseAddressEnteredBtnEle.isDisplayed()){
					UseAddressEnteredBtnEle.click();
					System.out.println("Use Address Clicked Btn clicked");
				}								
				
				
			}catch(Exception e){
				
			}
			TimeUnit.SECONDS.sleep(3);
			
			ReviewOrderPage  reviewOrderPage  = new ReviewOrderPage(driver, test);
			PageFactory.initElements(driver, reviewOrderPage);
			return reviewOrderPage;			
			
		}catch(Exception e){	
			test.log(LogStatus.FAIL, "FAIL:Confirm Order Button is not Clicked");
			takeScreenShot();
			Assert.fail();
			return null;
			
		}
		
		
	}

	public void addShippingDetails(String addressType, String firstName, String lastName, String addressOne,
			String addressTwo, String city, String state, String zipCode, String phoneNumber) {
		
		Select selectAddressType = new Select(AddressTypeSelect);
		System.out.println("addressType="+addressType);
		if(addressType.equalsIgnoreCase("Residential")){
			selectAddressType.selectByVisibleText("Residential");
		}
		if(addressType.equalsIgnoreCase("Commercial")){
			selectAddressType.selectByVisibleText("Commercial");
		}
		if(addressType.contains("APO")){
			selectAddressType.selectByVisibleText("APO/AFO");
		}
		
		FirstName.sendKeys(firstName);
		LastName.sendKeys(lastName);
		AddressOne.sendKeys(addressOne);
		if(addressTwo!=null){
			AddressTwo.sendKeys(addressTwo);
		}
		City.sendKeys(city);
		State.sendKeys(state);
		ZipCode.sendKeys(zipCode);
		ZipCode.sendKeys(Keys.TAB);
		PhoneNumber.sendKeys(phoneNumber);
		PhoneNumber.sendKeys(Keys.TAB);
		
	}


}
