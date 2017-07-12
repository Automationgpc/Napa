package com.napa.online.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;

public class CableChainPage extends BasePage{
	
	public CableChainPage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	@FindBy(xpath="//div[@class='category-title'][contains(text(),'Cable & Chain')]")
	public WebElement CableChainSubCatLnk;
	
	@FindBy(xpath="//div[@class='category-title'][contains(text(),'Cargo, Shipping & Towing')]")
	public WebElement CargoShipTowSubCatLnk;
	
	public CableChainPLPPage clkCabChainSubCatLnk(){
		CableChainSubCatLnk.click();
		CableChainPLPPage cableChainPLPPage = new CableChainPLPPage(driver, test);
		PageFactory.initElements(driver, cableChainPLPPage);
		return cableChainPLPPage;
	}

}
