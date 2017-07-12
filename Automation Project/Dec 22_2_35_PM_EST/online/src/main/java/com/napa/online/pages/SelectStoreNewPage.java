package com.napa.online.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;

public class SelectStoreNewPage extends BasePage{
	
	public SelectStoreNewPage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	/*@FindBy(xpath="(//*[@id='header-make-my-store'])[1]")	
	public WebElement MakeMyStoreBtn;
	
	public static final By makeMyStoreBtnBy = By.xpath("(//*[@id='header-make-my-store'])[1]");*/
	
	/*public HomePage clkMakeMyStoreBtn(){		
		driver.findElement(By.xpath("(//*[@id='header-make-my-store'])[1]")).click();
		if (MakeMyStoreBtn.isDisplayed()){
			MakeMyStoreBtn.click();
		}
		HomePage homePage = new HomePage(driver, test);
		PageFactory.initElements(driver, homePage);
		return homePage;
	}*/
	
	public HomePage clkMakeMyStore(){
		String MakeMyStoreBtnsXPATH ="//*[@id='header-make-my-store']";
		List<WebElement> MakeMyStoreBtns = driver.findElements(By.xpath(MakeMyStoreBtnsXPATH));
		System.out.println("Total MakeMyStoreBtns= " + MakeMyStoreBtns.size());
		if (MakeMyStoreBtns.size()==0){
			System.out.println("Make My Store Btns could not be found");			
		}
		Actions action = new Actions(driver);
		action.moveToElement(MakeMyStoreBtns.get(0)).build().perform();
		action.click(MakeMyStoreBtns.get(0));
		//MakeMyStoreBtns.get(0).click();
		
		HomePage homePage = new HomePage(driver, test);
		PageFactory.initElements(driver, homePage);
		return homePage;
	}

}
