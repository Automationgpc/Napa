package com.napa.online.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;

public class SelectStorePage extends BasePage{
	
	public SelectStorePage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	@FindBy(xpath="//input[@class='search-input'][@id='store-search-input']")
	public WebElement StoreSearchBox;
	
	public static final By storeSearchBoxBy = By.xpath("//input[@class='search-input'][@id='store-search-input']");
	
	@FindBy(xpath="//*[@id='store-search-button']")
	public WebElement StoreSearchBtn;
	
	/*@FindBy(xpath="(//*[@id='header-make-my-store'])[1]")	
	public WebElement MakeMyStoreBtn;*/
	
	public static final By makeMyStoreBtnBy = By.xpath("(//*[@id='header-make-my-store'])[1]");
	
	/*public HomePage clkMakeMyStore(){
		String MakeMyStoreBtnsXPATH ="//*[@id='header-make-my-store']";
		List<WebElement> MakeMyStoreBtns = driver.findElements(By.xpath(MakeMyStoreBtnsXPATH));
		System.out.println("Total MakeMyStoreBtns= " + MakeMyStoreBtns.size());
		if (MakeMyStoreBtns.size()==0){
			System.out.println("Make My Store Btns could not be found");
		}
		Actions action = new Actions(driver);
		action.moveToElement(MakeMyStoreBtns.get(0)).click().release().build().perform();
		
		HomePage homePage = new HomePage(driver, test);
		PageFactory.initElements(driver, homePage);
		return homePage;
	}*/
	
	public HomePage clkMakeMyStore(String newStoreZip, String storeAddress){
		
		if (StoreSearchBox.isDisplayed()){
			StoreSearchBox.clear();
			StoreSearchBox.sendKeys(newStoreZip);
		}
		if (StoreSearchBtn.isDisplayed()){
			StoreSearchBtn.click();
		}	
		
		WebElement makeMyStoreBtnEle = (new WebDriverWait(driver, 20))
				  .until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='header-make-my-store'])[1]")));
		makeMyStoreBtnEle.click();
		
		HomePage homePage = new HomePage(driver, test);
		PageFactory.initElements(driver, homePage);
		return homePage;
	}
	
	/*public HomePage clkMakeMyStoreBtn(){		
		
		if (MakeMyStoreBtn.isDisplayed()){
			MakeMyStoreBtn.click();
		}
		HomePage homePage = new HomePage(driver, test);
		PageFactory.initElements(driver, homePage);
		return homePage;
	}*/
	
	public SelectStoreNewPage clkSearchStore(String newStoreZip, String storeAddress){
		
		if (StoreSearchBox.isDisplayed()){
			StoreSearchBox.clear();
			StoreSearchBox.sendKeys(newStoreZip);
		}
		if (StoreSearchBtn.isDisplayed()){
			StoreSearchBtn.click();
		}	
		SelectStoreNewPage selectStoreNewPage = new SelectStoreNewPage(driver, test);
		PageFactory.initElements(driver, selectStoreNewPage);
		return selectStoreNewPage;
		 
	}
}
