package com.napacanada.hybris.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.napa.online.base.BasePage;
import com.napa.online.pages.HomePage;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HybLaunchPage extends BasePage{
	
	public HybLaunchPage(WebDriver driver, ExtentTest test){
		super(driver,test);	
		
	}
	
	public HybHomePage openHybrisStgHomePage(){
		// log
		//test.log(LogStatus.INFO, "Opening the url - "+getEnvDetails().get("url"));
		
		driver.get(RunConfig.HYBRIS_STG_URL);
		//driver.manage().deleteAllCookies();
		test.log(LogStatus.INFO, "Test Web Site - "+getEnvDetails().get("url")+" Opened");
		HybHomePage hybHomePage = new HybHomePage(driver,test);
		PageFactory.initElements(driver, hybHomePage);
		return hybHomePage;
	}

}
