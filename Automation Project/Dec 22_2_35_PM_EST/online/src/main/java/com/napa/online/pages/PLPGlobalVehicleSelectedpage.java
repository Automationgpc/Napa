package com.napa.online.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PLPGlobalVehicleSelectedpage extends BasePage{
	
	public PLPGlobalVehicleSelectedpage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	@FindBy(xpath="(//a[@id='productTitle'])[1]")
	public WebElement Product1;
	
	
	
	public PDPpage clickProduct(){
		try{
			
			WebElement ProductEle = (new WebDriverWait(driver, 20))
					  .until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@id='productTitle'])[1]")));
			
			ProductEle.click();				
			
			//ProductEle.click();
			try{
				if(ProductEle.isDisplayed()){
					ProductEle.click();
				}
				
				
			}catch(Exception e){
				System.out.println("Trying to click Product1 2nd time. We can ignore this exception");
				
			}
			
			
			PDPpage pDPpage = new PDPpage(driver, test);
			PageFactory.initElements(driver, pDPpage);		
			return pDPpage;
			
		}catch(Exception e){
			//test.log(LogStatus.FAIL, "FAIL:Product from PLP page couldn't be clicked.");
			//Assert.fail();
			return null;
		}
	}
	
	public PDPpage clickProductInPLP(String PartNumber){
		try{
			
			//String BOLProductXp = "//a[contains(@id,'602443')][@class='button-primary yellow listing-button-buy buyonline-button']";
			//String PLPBOLBtnXpath = "//a[contains(@id,'" + PartNumber+ "')][@class='button-primary yellow listing-button-buy buyonline-button']";
			String subPart = PartNumber.substring(4);
			System.out.println("subPart="+subPart);
			String ProductPLPXpath = "//a[contains(@data-formid,'" + subPart+ "')][@id='productTitle']";
			System.out.println("ProductXpath="+ProductPLPXpath);		
			
			WebElement ProductEle = (new WebDriverWait(driver, 20))
					  .until(ExpectedConditions.elementToBeClickable(By.xpath(ProductPLPXpath)));						
			
			
			ProductEle.click();
			try{
				if(ProductEle.isDisplayed()){
					ProductEle.click();
				}
				
				
			}catch(Exception e){
				System.out.println("Trying to click Product 2nd time. We can ignore this exception");
				
			}
			
			
			PDPpage pDPpage = new PDPpage(driver, test);
			PageFactory.initElements(driver, pDPpage);		
			return pDPpage;
			
		}catch(Exception e){
			test.log(LogStatus.FAIL, "FAIL:Product from PLP page couldn't be clicked.");
			Assert.fail();
			return null;
		}
	}
	
	public PickUpInStorePage clickROLBtnInPLP(String PartNumber){
		try{
			
			//String BOLProductXp = "//a[contains(@id,'602443')][@class='button-primary yellow listing-button-buy buyonline-button']";
			//String PLPBOLBtnXpath = "//a[contains(@id,'" + PartNumber+ "')][@class='button-primary yellow listing-button-buy buyonline-button']";
			String subPart = PartNumber.substring(4);
			//System.out.println("subPart="+subPart);
			String rolBtnPLPXpath = "//a[contains(@id,'" + subPart+ "')][@class='button-primary blue listing-button-reserve rolClick']";
			//System.out.println("ProductXpath="+ProductPLPXpath);		
			
			WebElement rolBtnEle = (new WebDriverWait(driver, 20))
					  .until(ExpectedConditions.elementToBeClickable(By.xpath(rolBtnPLPXpath)));						
			
			if(rolBtnEle.isDisplayed()){
				Actions action = new Actions(driver);
				action.moveToElement(rolBtnEle).build().perform();
				rolBtnEle.click();
			}			
			
			PickUpInStorePage pickUpInStorePage = new PickUpInStorePage(driver, test);
			PageFactory.initElements(driver, pickUpInStorePage);		
			return pickUpInStorePage;
			
		}catch(Exception e){
			test.log(LogStatus.FAIL, "FAIL:ROL btn for the Product in PLP page couldn't be clicked.");
			Assert.fail();
			return null;
		}
	}

}
