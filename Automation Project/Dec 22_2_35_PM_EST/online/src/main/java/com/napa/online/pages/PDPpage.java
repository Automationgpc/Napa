package com.napa.online.pages;

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

public class PDPpage extends BasePage{
	
	public PDPpage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	@FindBy(xpath="//a[@class='button-primary blue store-pickup-button productDetailRolClick'][contains(text(),'Reserve Online Pickup In Store')]")
	public WebElement ROLButton;
	
	//@FindBy(xpath="//a[@class='button-primary yellow buyonline-button'][contains(text(),'BUY ONLINE')]")
	//public WebElement BOLButton;
	
	@FindBy(xpath="//a[@class='button-primary yellow buyonline-button'][contains(text(),'BUY ONLINE')]")
	public WebElement BOLButton;
	
	@FindBy(xpath="//input[@class='product-qty-input']")
	public WebElement QtyPDP;
	
	@FindBy(xpath="//h1[@class='product-title pdp-title']")
	public WebElement ProductNamePDP;
	
	@FindBy(xpath="(//tr[@class='product-details']/td[2]/div[@class='section-desc'])[1]")
	public WebElement PartNamePDP;
	
	@FindBy(xpath="//div[@class='product-price']")
	public WebElement PricePDP;
	
	@FindBy(xpath="//div[@class='section-title']/a[contains(text(),'Confirm Fitment!')]")
	public WebElement ConfirmFitmentPDP;
	
	@FindBy(xpath="//div[@class='green-check-circle product-fitment']")
	public WebElement FitmentConfirmedPDP;
	
	@FindBy(xpath="//a[contains(text(),'Confirm Vehicle Attributes')]")
	public WebElement ConfirmVehicleAttributes;
	
	@FindBy(xpath="//a[contains(text(),'Add To Cart')]")
	public WebElement AddToCartATCConfirm;

	public PickUpInStorePage clickROLBtn(String prodQty){
		
		try{
			QtyPDP.clear();
			QtyPDP.sendKeys(prodQty);
			/*if(ConfirmFitmentPDP.isDisplayed()){
				ConfirmFitmentPDP.click();
			}*/
			//ROLButton.click();
			String rolBtnXpath = "//a[@class='button-primary blue store-pickup-button productDetailRolClick'][contains(text(),'Reserve Online Pickup In Store')]";
			WebElement ROLButtonEle = (new WebDriverWait(driver, 10))
			  .until(ExpectedConditions.elementToBeClickable(By.xpath(rolBtnXpath )));
			ROLButtonEle.sendKeys(Keys.ENTER);
			
			//Switching to Pick Up-Check Out Window 
			String atcConfirmWindow = driver.getWindowHandle();
			driver.switchTo().window(atcConfirmWindow);
			
			try{
				if(AddToCartATCConfirm.isDisplayed()){
					AddToCartATCConfirm.sendKeys(Keys.ENTER);;
				}
				
			}catch(Exception e){
				System.out.println("AddToCartATCConfirm is not displayed.We can ignore this exception ");
			}
			
			
			/*if(AddToCartATCConfirm.isDisplayed()){
				AddToCartATCConfirm.click();
			}*/
			
			PickUpInStorePage pickUpInStorePage = new PickUpInStorePage(driver, test);
			PageFactory.initElements(driver, pickUpInStorePage);		
			return pickUpInStorePage;
			
		}catch(Exception e){			
			return null;
		}
		
	}
	
	public PickUpInStorePage clickROLBtnFitmentConfirmed(String prodQty){
		
		try{
			QtyPDP.clear();
			QtyPDP.sendKeys(prodQty);
			/*if(ConfirmFitmentPDP.isDisplayed()){
				ConfirmFitmentPDP.click();
			}*/
			ROLButton.click();			
			
			PickUpInStorePage pickUpInStorePage = new PickUpInStorePage(driver, test);
			PageFactory.initElements(driver, pickUpInStorePage);		
			return pickUpInStorePage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in clickROLBtnFitmentConfirmed(String prodQty): ");
			test.log(LogStatus.FAIL, "FAIL:ROL button couldn't be clicked.");
			Assert.fail();
			return null;
		}
		
	}
	
	public RefineVehicleConditionsPage clickConfirmFitment(){
		
		try{
			if(ConfirmFitmentPDP.isDisplayed()){				
				ConfirmFitmentPDP.click();
				
				
			}	
			
			RefineVehicleConditionsPage refineVehicleConditionsPage = new RefineVehicleConditionsPage(driver, test);
			PageFactory.initElements(driver, refineVehicleConditionsPage);		
			return refineVehicleConditionsPage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in clickConfirmFitment method");
			test.log(LogStatus.FAIL, "FAIL:Confirm Fitment! button couldn't be clicked.");
			Assert.fail();
			return null;
		}
		
	}

	public BuyOnlineAtcPage clickBOLBtnFitmentConfirmed(String prodQty){
		
		try{
			//QtyPDP.clear();
			//QtyPDP.sendKeys(prodQty);
			/*if(ConfirmFitmentPDP.isDisplayed()){
				ConfirmFitmentPDP.click();
			}*/
			if(BOLButton.isEnabled()){
				BOLButton.click();
			}
			//BOLButton.click();			
			
			BuyOnlineAtcPage buyOnlineAtcPage = new BuyOnlineAtcPage(driver, test);
			PageFactory.initElements(driver, buyOnlineAtcPage);		
			return buyOnlineAtcPage;
			
		}catch(Exception e){
			System.out.println("Exception Occured while clicking BOL button in PDP page");
			test.log(LogStatus.FAIL, "FAIL:BOL button couldn't be clicked.");
			Assert.fail();
			return null;
		}
		
	}
}
