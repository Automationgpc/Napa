package com.napa.online.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.napa.online.base.BasePage;
import com.napa.online.util.Helper;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CheckoutSignInPage extends BasePage{
	ShoppingCartPage shoppingCartPage;
	
	public CheckoutSignInPage(WebDriver driver, ExtentTest test){
		super(driver,test);		
	}
	/**********************New Customer Elements*******************************************/
	@FindBy(xpath="//*[@id='firstName']")
	public WebElement FirstNameTxt;
	
	@FindBy(xpath="//*[@id='lastName']")
	public WebElement LastNameTxt;
	
	@FindBy(xpath="//*[@id='CreateAccountEmail']")
	public WebElement EmailIdTxt;
	
	@FindBy(xpath="//*[@id='ConfirmEmail']")
	public WebElement ConfirmEmailIdTxt;
	
	@FindBy(xpath="//*[@id='mobileNumber']")
	public WebElement PhoneNumberTxt;
	
	@FindBy(xpath="//p[@class='error-message parsley-required'][contains(text(),'Please enter a phone number')]")
	public WebElement PhoneErrorMsgTxt;
	
	@FindBy(xpath="//*[@id='CreateAccountPassword']")
	public WebElement PasswordTxt;
	
	@FindBy(xpath="//*[@id='CreateAccountPasswordConfirm']")
	public WebElement ConfirmPasswordTxt;	
	
	@FindBy(xpath="//*[@id='registerForm']/div[9]/button")
	public WebElement CreateAccountBtn;
	
	/**********************Returning Customer Elements*******************************************/
	@FindBy(xpath="//input[@id='loginemail']")
	public WebElement LoginEmail;
	
	@FindBy(xpath="//input[@id='j_password']")
	public WebElement Password;
	
	@FindBy(xpath="//*[@id='loginForm']/div[3]/button")
	public WebElement SignInBtn;
	
	public ResContactInfoPage creatNewCustAccount(){
		try{
			Helper.createNewUserDetails();
			FirstNameTxt.sendKeys(RunConfig.FIRST_NAME);
			LastNameTxt.sendKeys(RunConfig.LAST_NAME);
			EmailIdTxt.sendKeys(RunConfig.EMAIL_ID);
			ConfirmEmailIdTxt.sendKeys(RunConfig.EMAIL_ID);
			
			ConfirmEmailIdTxt.sendKeys(Keys.TAB);
			PhoneNumberTxt.sendKeys(RunConfig.PHONE_NUMBER);
			
			//PhoneNumberTxt.sendKeys(Keys.ENTER);
			/*if(PhoneErrorMsgTxt.isDisplayed()){
				PhoneNumberTxt.sendKeys(RunConfig.PHONE_NUMBER);
				//PhoneNumberTxt.sendKeys(Keys.TAB);
				//PhoneNumberTxt.sendKeys(Keys.ENTER);
			}*/
			
			PasswordTxt.sendKeys(RunConfig.PASSWORD);
			
			ConfirmPasswordTxt.sendKeys(RunConfig.PASSWORD);
			ConfirmPasswordTxt.sendKeys(Keys.TAB);
			CreateAccountBtn.click();
			
			test.log(LogStatus.PASS, "PASS:New user has been created.Reservation Contact Info Page is displayed");
			
			ResContactInfoPage resContactInfoPage = new ResContactInfoPage(driver, test);
			PageFactory.initElements(driver, resContactInfoPage);
			return resContactInfoPage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in the Page Class Method: "+e.getMessage());
			//test.log(LogStatus.FAIL, "FAIL: Reservation Contact Info Page is NOT displayed");			
			return null;
		}
		
	}
	public ShoppingCartPage signInAsReturningUser(String username, String password){
		try{
			
			LoginEmail.sendKeys(username);
			Password.sendKeys(password);			
			SignInBtn.click();
			
			test.log(LogStatus.PASS, "PASS:Sign In as Returning User successful. Shopping Cart Page is displayed");
			
			ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, test);
			PageFactory.initElements(driver, shoppingCartPage);
			return shoppingCartPage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in signInAsReturningUser Method: ");
			test.log(LogStatus.FAIL, "FAIL:Sign In as Returning User FAILED");
			Assert.fail();
			return null;
		}
		
	}


}
