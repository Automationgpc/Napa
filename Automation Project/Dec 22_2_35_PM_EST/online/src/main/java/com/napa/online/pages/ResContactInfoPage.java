package com.napa.online.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.napa.online.base.BasePage;
import com.napa.online.util.Helper;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ResContactInfoPage extends BasePage {
	
	public ResContactInfoPage(WebDriver driver, ExtentTest test){
		super(driver,test);		
	}
	
	@FindBy(xpath="//h3[@class='box-content-title']")
	public WebElement ResContactInfoTitle;
	
	@FindBy(xpath="//*[@class='button-primary yellow rew-order-btn'][contains(text(),'REVIEW ORDER')]")
	public WebElement ReviewOrderBtn;
	
	public ReviewOrderPage ClkReviewOrder(){
		
		try{
			
			ReviewOrderBtn.click();
			
			test.log(LogStatus.PASS, "PASS:Review Order Page is displayed");
			
			ReviewOrderPage reviewOrderPage = new ReviewOrderPage(driver, test);
			PageFactory.initElements(driver, reviewOrderPage);
			return reviewOrderPage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in the Reservation Contact Page Class Btn Click Method: "+e.getMessage());
			//test.log(LogStatus.FAIL, "Reservation Contact Info Page is NOT displayed");			
			return null;
		}
		
		
	}

}
