package com.napa.online.pages;

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

public class BuyOnlineCheckoutPage extends BasePage{
	
	
	public BuyOnlineCheckoutPage(WebDriver driver, ExtentTest test){
		super(driver,test);		
	}
	
	
	@FindBy(xpath="//a[@class='button-primary yellow'][contains(text(),'CHECKOUT NOW')]")
	public WebElement CheckOutNowBtn;
	
	public final By CheckOutNowBtnBy = By.xpath("//a[@class='button-primary yellow'][contains(text(),'CHECKOUT NOW')]");
	
	public ShoppingCartPage clkCheckOutNow(){
		
		try{
			
			WebElement CheckOutNow = (new WebDriverWait(driver, 60))
					  .until(ExpectedConditions.elementToBeClickable(CheckOutNowBtnBy));
			//driver.findElement(pUCheckOutNowBtnBy).sendKeys(Keys.ENTER);
			//CheckOutNow.sendKeys(Keys.ENTER);
			if(CheckOutNow.isDisplayed()){
				CheckOutNow.click();
				//AddToCartBtn.sendKeys(Keys.ENTER);
				System.out.println("Checkout Now button in BOL Check out Window is clicked ");
			}
			
			
			ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, test);
			PageFactory.initElements(driver, shoppingCartPage);
			return shoppingCartPage;
			
		}catch(Exception e){
			System.out.println("Checkout Now button in BOL Check out Window was not Clicked. Exception Occured");
			test.log(LogStatus.FAIL, "FAIL:Checkout Now button in BOL Check out Window was not Clicked");
			Assert.fail();
			return null;
		}
		
	}

}
