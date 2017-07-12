package com.napa.online.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;

public class MyAccount extends BasePage{
	
	public MyAccount(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	@FindBy(xpath="//*[@id='updateContactForm']/button")
	public WebElement SaveAccountInfo;

}
