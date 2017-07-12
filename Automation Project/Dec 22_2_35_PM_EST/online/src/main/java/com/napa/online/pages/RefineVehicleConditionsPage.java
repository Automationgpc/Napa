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

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;

public class RefineVehicleConditionsPage extends BasePage{
	
	public RefineVehicleConditionsPage(WebDriver driver, ExtentTest test){
		super(driver,test);		
	}
	
	@FindBy(xpath="//div[@class='applied-conditions-item']/span[@class='applied-vehicle-edit']")
	public WebElement RefineVehicleConditions;
	
	@FindBy(xpath="//select[@class='pure-input-1']")
	public WebElement SelectEngines;
	
	@FindBy(xpath="//input[@class='listing-price-quantity-input']")
	public WebElement Qty;
	
	public PickUpInStorePage clickROLBtn(String prodQty){
		
		try{
			Qty.clear();
			Qty.sendKeys(prodQty);
			TimeUnit.SECONDS.sleep(3);
			
			String rolBtnXpath = "//a[@class='button-primary blue listing-button-reserve rolClick']";
			
			WebElement ROLButtonEle = (new WebDriverWait(driver, 10))
			  .until(ExpectedConditions.elementToBeClickable(By.xpath(rolBtnXpath )));
			
			if (ROLButtonEle.isDisplayed()){
				//ROLButtonEle.sendKeys(Keys.ENTER);
				ROLButtonEle.click();
				TimeUnit.SECONDS.sleep(3);
				System.out.println("ROL button in Refine Vehicle Conditions Page is clicked ");
			}else{
				System.out.println("ROL button in Refine Vehicle Conditions Page is NOT FOUND");
			}
						 
			
			PickUpInStorePage pickUpInStorePage = new PickUpInStorePage(driver, test);
			PageFactory.initElements(driver, pickUpInStorePage);		
			return pickUpInStorePage;
			
		}catch(Exception e){			
			return null;
		}
		
	}
	

}
