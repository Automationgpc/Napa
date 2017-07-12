package com.napa.online.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PLPGlobalKeywordSearchpage extends BasePage{
	
	public PLPGlobalKeywordSearchpage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	@FindBy(xpath="(//a[@id='productTitle'])[1]")
	public WebElement Product1;
	
	public PLPGlobalVehicleSelectedpage selectVehfrmListinHdrOnPLP(){
		try{
			header.SelectVehicleHdr.click();
			TimeUnit.SECONDS.sleep(3);;
			
			try{
				if(!header.SelectVehiclefromList.isDisplayed()){
					header.SelectVehicleHdr.click();	
				}
				
			}catch(Exception e){
				System.out.println("Exception Occured 2nd time clicking SelectVehicleHdr.click(): "+e.getMessage());
				
			}
						
			if(header.SelectVehiclefromList.isDisplayed()){
				header.SelectVehiclefromList.sendKeys(Keys.ENTER);
			}
			PLPGlobalVehicleSelectedpage pLPGlobalVehicleSelectedpage = new PLPGlobalVehicleSelectedpage(driver, test);
			PageFactory.initElements(driver, pLPGlobalVehicleSelectedpage);		
			return pLPGlobalVehicleSelectedpage;
			
		}catch(Exception e){
			System.out.println("Exception Occured while selecting Vehicle from List(): ");
			test.log(LogStatus.FAIL, "FAIL:Vehicle from Vehicle List in Header couldn't be Selected.");
			Assert.fail();
			return null;
		}
	}

}
