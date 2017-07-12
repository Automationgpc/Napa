package com.napa.online.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;

public class CategoryPage extends BasePage{
	
	public CategoryPage(WebDriver driver, ExtentTest test){
		super(driver,test);	
		
	}
	
	@FindBy(xpath="//a[@class='nol-left-navigation-panel-list-link navigation-panel-list-link-active'][contains(text(),'Cable & Chain')]")
	public WebElement CableChainCatLeftLnk;	
	
	
	public CableChainPage clkCableChainCatLeftLnk(){
		CableChainCatLeftLnk.click();
		CableChainPage cableChainPage = new CableChainPage(driver, test);
		PageFactory.initElements(driver, cableChainPage);
		return cableChainPage;
	}

}
