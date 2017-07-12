package com.napa.online.pages;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.napa.online.base.BasePage;
import com.napa.online.util.Helper;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver, ExtentTest test){
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
	///////////////////////////////////////////////////////////////////////////////////////////
	static final By loginEmailTxtBox = By.id("loginemail");
	
	@FindBy(id="loginemail")
	public WebElement loginEmail;
	
	@FindBy(id="j_password")
	public WebElement loginPassword;
	
	/*@FindBy(id="//*[@id='loginForm']/div[3]/button[@class='button-primary yellow'][@type='submit']")
	public WebElement SignInButton;*/
	
	@FindBy(id="//button[contains(text(),'SIGN IN')]")
	public WebElement SignInButton;
	
	static final By SignInButtonBy = By.xpath("(//button[@class='button-primary yellow'][@type='submit'])[1]");
	
	public final By chatWinCloseBy = By.xpath("//img[@onclick='bt_hideAnimation();']");
	
	public MyAccount createNewAccount(){
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
			
			MyAccount myAccount = new MyAccount(driver, test);
			PageFactory.initElements(driver, myAccount);
			return myAccount;
			
		}catch(Exception e){
			System.out.println("Exception Occured while creating new user account");
			//test.log(LogStatus.FAIL, "FAIL: Reservation Contact Info Page is NOT displayed");			
			return null;
		}
		
		
		
	}
	
	public Object doLogin(String username,String password) throws InterruptedException{
		test.log(LogStatus.INFO, "Logging in -"+username+"/"+password);
		System.out.println("username= "+username);
		loginEmail.sendKeys(username);
		System.out.println("password= "+password);
		loginPassword.sendKeys(password);
		//Thread.sleep(2000);
		loginPassword.sendKeys(Keys.ENTER);
		
		//boolean loginSuccess=false;
		
		//WebElement emailTextBx= driver.findElement(loginEmailTxtBox);
		List<WebElement> elements = driver.findElements(loginEmailTxtBox);
		
		if(elements.size()==0){
			//System.out.println("We are Logged in");
			//loginSuccess=true;
			HomePage homePage = new HomePage(driver,test);
			PageFactory.initElements(driver, homePage);
			return homePage;
			
		}else {
			//System.out.println("We are still on Login Page. Login Failed");
			LoginPage loginPage = new LoginPage(driver,test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;
		}
		
	}
	
	public HomePage signIn(String username,String password) throws InterruptedException{		
		
		test.log(LogStatus.INFO, "Logging in as-"+username+"/"+password);
		System.out.println("username= "+username);		
		loginEmail.sendKeys(username);
		TimeUnit.SECONDS.sleep(1);
		//checking if Chat Window is displayed and closing it if present
		try{
			if (isElementPresent(chatWinCloseBy)){
				closeChatWindow();
			}
			
		}catch(Exception e){
			
		}

		System.out.println("password= "+password);
		loginPassword.sendKeys(password);
		TimeUnit.SECONDS.sleep(1);
		//checking if Chat Window is displayed and closing it if present
		try{
			if (isElementPresent(chatWinCloseBy)){
				closeChatWindow();
			}
			
		}catch(Exception e){
			
		}
		loginPassword.sendKeys(Keys.ENTER);
		
		
		/*if(SignInButton.isDisplayed()){
			SignInButton.click();	
			
		}*/
		
		/*WebElement SignInButtonEle = (new WebDriverWait(driver, 20))
				  .until(ExpectedConditions.elementToBeClickable(SignInButtonBy));
		//WebElement AddNewVehicleHdrEle = driver.findElement(By.xpath(addNewVehicleHdrXpath));
		
		if(SignInButtonEle.isDisplayed()){
			SignInButtonEle.click();	
			
		}*/
		//SignInButton.click();
		Thread.sleep(2000);
		
		HomePage homePage = new HomePage(driver, test);
		PageFactory.initElements(driver, homePage);
		return homePage;
		
		
	}
	
	 
	
	public void closeChatWindow(){
			
			try{
				driver.findElement(chatWinCloseBy).click();
				System.out.println("Chat Window was closed ");
				
			}catch(Exception e){
				System.out.println("Exception Occured Closing Chat Window: "+e.getMessage());
				
			}
	}
	
	
	
	/*if (homePage.isElementPresent(homePage.header.MyAccountLnk)){
	try{
		if (homePage.isElementPresent(homePage.header.MyMenuNameDD)){
			String nameDisplayed = homePage.header.MyMenuNameDD.getText().toString();
			System.out.println("nameDisplayed= "+nameDisplayed);
				if(nameDisplayed.contains("Prabhakar")){
					test.log(LogStatus.PASS, "Logged in as Prabhakar Automation");
				}
		
		}else{
			loginPage= homePage.header.goToLoginPage(homePage.header.MyAccountLnk);
			//Thread.sleep(2000);
			if (!(loginPage==null)){
				loginPage.waitForPageLoad(driver);
				test.log(LogStatus.INFO, "Login Page is displayed");
				takeScreenShot();
				Object page = loginPage.doLogin(userName, password);
				
				if(page instanceof HomePage){			
					homePage = (HomePage) page;
					homePage.waitForPageLoad(driver);
					if (homePage.isElementPresent(homePage.header.MyMenuNameDD)){
						String expectedFullName = DataUtil.getCommonData(xls, "UserFullName");
						String actualNameDisplayed = homePage.header.MyMenuNameDD.getText().toString();
						//System.out.println(expectedFullName);
						///System.out.println(actualNameDisplayed);
						if(actualNameDisplayed.contains(expectedFullName)){
							test.log(LogStatus.PASS, "User Full Name displayed. Login Successful.");
							homePage.takeScreenShot();
						}
						else{
							test.log(LogStatus.FAIL, "Logged in.Full Name does not match.");
						}
					} else{
						test.log(LogStatus.FAIL, "Full Name element not Found. Login Test Failed.");
						}
				  }
				  else{			
					loginPage.takeScreenShot();
					//Thread.sleep(2000);
				  }	
			}else{
				test.log(LogStatus.FAIL, "Login Page is NOT displayed");
			}				
		}
		
		
	}catch(Exception e){
		System.out.println("Exception occured while going to Login Page");
	}
}
	else{
		test.log(LogStatus.FAIL, "My Account Link was not Found");
		takeScreenShot();
	}*/		
	
	
}
