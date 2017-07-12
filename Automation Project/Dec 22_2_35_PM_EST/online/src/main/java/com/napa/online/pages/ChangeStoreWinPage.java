package com.napa.online.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;

public class ChangeStoreWinPage extends BasePage{
	
	public ChangeStoreWinPage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	@FindBy(xpath="//*[@id='change-my-store-link']")
	public WebElement ChangeMyStoreLnk;
	
	public SelectStorePage ClkChangeMyStore(){
		
		if (ChangeMyStoreLnk.isDisplayed()){
			ChangeMyStoreLnk.click();
		}
		
		SelectStorePage selectStorePage = new SelectStorePage(driver, test);
		PageFactory.initElements(driver, selectStorePage);
		return selectStorePage;
		
	}
}
