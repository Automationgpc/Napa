package com.napa.online.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.napa.online.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CableChainPLPPage extends BasePage {
	
	public CableChainPLPPage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	@FindBy(xpath="//h1[@class='search-category-name'][contains(text(),'Cable & Chain')]")
	public WebElement CabChainPLPHeadTxt;
	
	@FindBy(xpath="//a[@class='button-primary blue listing-button-reserve rolClick']")
	public WebElement RolBtn;
	
	public static final By RolBtnBy = By.xpath("//a[@class='button-primary blue listing-button-reserve rolClick']");
	
	public static final By PaginationBy = By.xpath("//a[@class='listings-pagination-page']");
	public static final By nextBy = By.xpath("//a[@class='listings-pagination-next-single'][@rel='next']");
	
	public static final By productListingsBy = By.xpath("//div[@class='grid-1-3']");
	
	public static final By prodPricePLPBy = By.xpath("(//div[@class='listing-price-amount'])[1]");
	public static final By beforePricePLPBy = By.xpath("(//div[@class='price-before-sale'])[1]");
	public static final By prodNamePLPBy = By.xpath("(//div[@class='listing-price-amount'])[1]");
	public static final By prodPartPLPBy = By.xpath("(//div[@class='listing-price-amount'])[1]");
	public static final By prodQtyPLPBy = By.xpath("(//input[@class='listing-price-quantity-input'][@type='text'])[1]");
	
	/**********************Pick Up Window Elements*******************************************/
	
	@FindBy(xpath="//span[@class='featherlight-close-icon featherlight-close']")
	public WebElement PUWindowCloseBtn;
	
	@FindBy(xpath="//div[@class='notification-title-text']")
	public WebElement AtcConfirmationTxt;
	
	@FindBy(xpath="(//div[@class='listing-item-title'])[1]")
	public WebElement ProdNamePU1;
	
	public static final By prodNamePU1By = By.xpath("(//div[@class='listing-item-title'])[1]");
	
	@FindBy(xpath="//div[@class='listing-detail-text listing-detail-text-part']")
	public WebElement PartNumberPU1;
	
	public static final By partNumberPU1By = By.xpath("//*[@id='modalROLConfirm']/div[3]/div[2]/div/div[2]/div[2]/div[2]");
	
	@FindBy(xpath="//span[@class='listing-price-value']")
	public WebElement PricePU1;
	
	public static final By pricePU1By = By.xpath("//span[@class='listing-price-value']");
	
	@FindBy(xpath="//div[@class='price-before-sale']")
	public WebElement BeforePU1;
	
	public static final By beforePU1By = By.xpath("//div[@class='price-before-sale']");
	
	@FindBy(xpath="(//input[@class='listing-price-quantity-input'][@type='text'])[1]")
	public WebElement QtyPU1;
	
	public static final By qtyPU1By = By.xpath("(//input[@class='listing-price-quantity-input'][@type='text'])[1]");
	
	@FindBy(xpath="//a[@class='button-primary clear continueShopping']")
	public WebElement PUContinueShoppingBtn;
	
	@FindBy(xpath="//a[@class='button-primary yellow'][contains(text(),'CHECKOUT NOW')]")
	public WebElement PUCheckOutNowBtn;
	
	public static final By pUCheckOutNowBtnBy = By.xpath("//a[@class='button-primary yellow'][contains(text(),'CHECKOUT NOW')]");

	public ShoppingCartPage clickCheckOutNowPU(){
		
		try{
			//driver.findElement(pUCheckOutNowBtnBy).click();
			WebElement PUCheckOut = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='button-primary yellow'][contains(text(),'CHECKOUT NOW')]")));
			//driver.findElement(pUCheckOutNowBtnBy).sendKeys(Keys.ENTER);
			PUCheckOut.sendKeys(Keys.ENTER);
			System.out.println("Check out button in Pick Up Window was clicked ");
			test.log(LogStatus.PASS, "PASS: Shopping Cart Page is displayed");
			
			ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, test);
			PageFactory.initElements(driver, shoppingCartPage);
			return shoppingCartPage;
		}catch(Exception e){
			System.out.println("Exception Message: "+e.getMessage());
			test.log(LogStatus.FAIL, "FAIL: Shopping Cart Page NOT displayed");
			return null;
		}
		
	}
}
