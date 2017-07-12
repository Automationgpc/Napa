package com.napa.online.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReviewOrderPage extends BasePage{
	
	public ReviewOrderPage(WebDriver driver, ExtentTest test){
		super(driver,test);		
	}
	
	@FindBy(xpath="//h2[@class='checkout-steps-4-header']")
	public WebElement OrderHeader;
	
	@FindBy(xpath="//div[@class='location-phone']")
	public WebElement LocationPhone;
	
	/////////////////////////BOL Review Order///////////////////
	@FindBy(xpath="(//div[@class='order-total-info-content'])[1]")
	public WebElement ShippingAddress;
	////////////////////////////////////////////////////////////
	
	
	@FindBy(xpath="//input[@class='checkbox-input'][@type='checkbox']")
	public WebElement TermsCondChkBx;
	
	@FindBy(xpath="//p[@class='error-message parsley-custom-error-message']")
	public WebElement TermsErrorMsg;
	
	@FindBy(xpath="//*[@class='button-primary yellow order-submit-button-class'][contains(text(),'SUBMIT ORDER')]")
	public WebElement SubmitOrderBtn;
	
	public OrderConfirmationPage SubmitOrder(){
		try{
			if(TermsCondChkBx.isDisplayed()){
				TermsCondChkBx.click();				
			}	
			
			if(SubmitOrderBtn.isDisplayed()){
				SubmitOrderBtn.click();
			}
			
			
			try{
				if(TermsErrorMsg.isDisplayed()){
					TermsCondChkBx.click();
					SubmitOrderBtn.click();
				}
			}catch(Exception e){
				System.out.println("Exception Occured while trying to click Terms&Conditions Check Box 2nd time. We can ignore.");
				//test.log(LogStatus.FAIL, "Reservation Contact Info Page is NOT displayed");			
				
			}			
			
		}catch(Exception e){
			System.out.println("Exception Occured When Submit Order Button is clicked: "+e.getMessage());
			//test.log(LogStatus.FAIL, "Reservation Contact Info Page is NOT displayed");			
			
		}
		OrderConfirmationPage  orderConfirmationPage  = new OrderConfirmationPage (driver, test);
		PageFactory.initElements(driver, orderConfirmationPage );
		return orderConfirmationPage;
		
	}
}
