package com.napa.online.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class BuyOnlineAtcPage extends BasePage{
	
	public BuyOnlineAtcPage(WebDriver driver, ExtentTest test){
		super(driver,test);
		
	}
	
	
	@FindBy(xpath="//a[@class='button-primary yellow bolAddToCart'][contains(text(),'ADD TO CART')]")
	public WebElement AddToCartBtn;
	
	public final By AddToCartBtnBy = By.xpath("//a[@class='button-primary yellow bolAddToCart'][contains(text(),'ADD TO CART')]");
	
	public BuyOnlineCheckoutPage clickAddToCart(){
		
		try{
			
			WebElement ATC = (new WebDriverWait(driver, 60))
					  .until(ExpectedConditions.elementToBeClickable(AddToCartBtnBy));
			//driver.findElement(pUCheckOutNowBtnBy).sendKeys(Keys.ENTER);
			//PUCheckOutNowBtn.sendKeys(Keys.ENTER);
			if(ATC.isDisplayed()){
				ATC.sendKeys(Keys.ENTER);
				//AddToCartBtn.sendKeys(Keys.ENTER);
				System.out.println("ATC button in BOL ATC modal is clicked ");
				TimeUnit.SECONDS.sleep(2);
			}
			
			
			BuyOnlineCheckoutPage buyOnlineCheckoutPage = new BuyOnlineCheckoutPage(driver, test);
			PageFactory.initElements(driver, buyOnlineCheckoutPage);
			return buyOnlineCheckoutPage;
		}catch(Exception e){
			System.out.println("ATC button in BOL ATC modal was not Clicked. Exception Occured");
			test.log(LogStatus.FAIL, "ATC button in BOL ATC modal was not Clicked");
			Assert.fail();
			return null;
		}
		
	}

}
