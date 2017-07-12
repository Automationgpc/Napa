package com.napa.online.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.napa.online.base.BasePage;
import com.napa.online.util.Helper;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	HomePage homePage;
	//static final By searchTxtBox = By.id("textSearch");	
	//static final By searchBtn = By.xpath("//*[@id='search_form']/button");*/
	
	@FindBy(xpath="//div[@class='header-branding-logo']")
	public WebElement NapaLogo;
	
	/******************Hero Fitment Elements******************************************/
	
	@FindBy(id="herofitmentSearchText")
	public WebElement HeroSearchTextBox;
	
	@FindBy(id="hero-year")
	public WebElement HeroAutoYear;
	
	@FindBy(xpath="//div[@class='tt-suggestion tt-selectable'][contains(text(),'2017')]")
	public WebElement HeroYear2017;
	
	@FindBy(id="hero-make")
	public WebElement HeroAutoMake;
	
	@FindBy(id="hero-model")
	public WebElement HeroAutoModel;
	
	@FindBy(xpath="//input[@class='button-primary yellow button-enable gs-find-button'][@value='FIND']")
	public WebElement HeroFindBtn;
	
	public void typeInHeroSearch(String text){
		
		HeroSearchTextBox.sendKeys(text);
		HeroSearchTextBox.sendKeys(Keys.TAB);
	}
	
	public void selectHeroYear(String year) throws InterruptedException{		
		//Select listbox = new Select(HeroAutoYearList);
		//listbox.selectByVisibleText(visibleText);
		/*List<WebElement> selectableItems = driver.findElements(By.xpath("//div[@class='tt-suggestion tt-selectable']")); 
		System.out.println("Total items= "+selectableItems.size());
		System.out.println("First item= "+selectableItems.get(2).toString());*/
		
		//HeroAutoYear.click();
		HeroAutoYear.sendKeys(year);
		HeroAutoYear.sendKeys(Keys.TAB);
		//HeroAutoYear.click();
		/*WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='tt-suggestion tt-selectable'][contains(text(),'2016')]")));
	    myDynamicElement.click();*/
		/*String strYearXpath= "//div[@class='tt-suggestion tt-selectable'][contains(text(),'"+year+"')]";
		WebElement HeroYrToSelect = driver.findElement(By.xpath(strYearXpath));
		HeroYrToSelect.click();
		HeroYrToSelect.sendKeys(Keys.ENTER);*/
		//Thread.sleep(20000);
		
		//HeroYear2017.click();
		//homePage.waitUntilClickable(element);
	}
	
	public void selectHeroMake(String make) throws InterruptedException{		
		//Select listbox = new Select(HeroAutoYearList);
		//listbox.selectByVisibleText(visibleText);
		HeroAutoMake.click();
		String strMakeXpath= "//div[@class='tt-suggestion tt-selectable'][contains(text(),'"+make+"')]";
		WebElement HeroMakeToSelect = driver.findElement(By.xpath(strMakeXpath));
		HeroMakeToSelect.click();
		HeroMakeToSelect.sendKeys(Keys.ENTER);
		//Thread.sleep(20000);
	}
	
	public HomePage registerNewUser(){			
		
		try{
			LoginPage loginPage = header.goToLoginPage();		
			//System.out.println("on LoginPage");
			
			MyAccount myAccount = loginPage.createNewAccount();
			myAccount.SaveAccountInfo.click();
			myAccount.header.doLogOut();
			
			HomePage homePage = new HomePage(driver, test);
			PageFactory.initElements(driver, homePage);
			return homePage;
			
		}catch(Exception e){
			System.out.println("Exception Occured while creating registering new user");
			//test.log(LogStatus.FAIL, "FAIL: Reservation Contact Info Page is NOT displayed");			
			return null;
		}
	
	}
	
	
}
 