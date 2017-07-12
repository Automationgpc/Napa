package com.napa.online.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.napa.online.pages.Header;
import com.napa.online.pages.HomePage;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class BasePage {
	public WebDriver driver;
	public Header header;
	public ExtentTest test;
	protected String browser="";
	
	public static Hashtable<String,String> envDetailsTable;
	
	public BasePage(){
		//default constructor		
	}
	
	public BasePage(WebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
		header = new Header(driver, test);
		PageFactory.initElements(driver, header);			
		
	}
		
	public Header getHeader(){
		return header;		
	}
	
	public HomePage goToHomePage(){
		// log
		test.log(LogStatus.INFO, "Opening the url - "+getEnvDetails().get("url"));
		driver.get(getEnvDetails().get("url"));
		test.log(LogStatus.PASS, "URL Opened - "+getEnvDetails().get("url"));		
		HomePage homePage = new HomePage(driver,test);
		PageFactory.initElements(driver, homePage);
		return homePage;
	}
	
	public void takeScreenShot(){
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_")+ ".png";
		String filePath = RunConfig.REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.INFO,test.addScreenCapture(filePath));		
		
	}
	
	public static void createNewUserDetails(){
		Date d = new Date();
		//System.out.println("UnformattedDate= "+d.toString());
		String formattedDate = d.toString().replace(":", "_").replace(" ", "_");
		//System.out.println("formattedDate= "+formattedDate);
		String first = formattedDate.substring(0,11);
		//System.out.println("first= "+first);
		String last = formattedDate.substring(11);
		
		RunConfig.FIRST_NAME = "Auto_"+first;
		System.out.println("first name= "+RunConfig.FIRST_NAME);
		
		RunConfig.LAST_NAME = last;
		System.out.println("last name= "+RunConfig.LAST_NAME);
		
		RunConfig.EMAIL_ID = first+last+"@gmail.com";
		System.out.println("Email Id= "+RunConfig.EMAIL_ID);
		
		RunConfig.CONFIRM_EMAIL_ID = RunConfig.EMAIL_ID;
		System.out.println("Confirm Email Id= "+RunConfig.CONFIRM_EMAIL_ID);
		
		RunConfig.PHONE_NUMBER = "678-852-0757";
		System.out.println("Phone Number= "+RunConfig.PHONE_NUMBER);
		
		RunConfig.PASSWORD = "Auto@newuser16";
		System.out.println("Password= "+RunConfig.PASSWORD);	
			
		
	}
	
	public static Hashtable<String,String> getEnvDetails(){
		if(envDetailsTable==null){
			envDetailsTable = new Hashtable<String,String>();
			if(RunConfig.ENV.equals("PROD")){
				envDetailsTable.put("url", RunConfig.PROD_HOMEPAGE_URL);
				
			}else if(RunConfig.ENV.equals("QA")){
				envDetailsTable.put("url", RunConfig.QA_HOMEPAGE_URL);
				
			}
			if(RunConfig.ENV.equals("STG")){
				envDetailsTable.put("url", RunConfig.STG_HOMEPAGE_URL);
				
			}
			
			
		}
		return envDetailsTable;
		 
	}
	
	public void waitForPageLoad(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		//WebDriverWait wait = new WebDriverWait(driver, 0)

	    wait.until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver wdriver) {
	            return ((JavascriptExecutor) wdriver).executeScript(
	                "return document.readyState"
	            ).equals("complete");
	        }
	    });
	}
	
	public boolean isElementPresent(WebElement element){
		 
		boolean isPresent = false;				
		
		try {
			//WebDriverWait wait = new WebDriverWait(driver, 2);
			//webElement = wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
			boolean status=element.isDisplayed();
			System.out.println("element status from isElementPresent= "+status);
			Assert.assertEquals(element.isDisplayed(),true);
			isPresent = true;
		}catch(Exception e){
			isPresent = false;			
		}
		
		return isPresent;
		
	}
	public boolean isElementPresent(By elementLocation){
		WebElement element; 
		boolean isPresent = false;				
		
		try {
			//WebDriverWait wait = new WebDriverWait(driver, 2);
			//webElement = wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
			element = driver.findElement(elementLocation);
			isPresent  =element.isDisplayed();
			System.out.println("element status from isElementPresent= "+isPresent );
			//Assert.assertEquals(actualStatus,true);
			//isPresent = true;
		}catch(Exception e){
			isPresent = false;			
		}
		
		return isPresent;
		
	}
	/*public static WebElement findElement(final WebDriver driver, final By locator, final int timeoutSeconds) {
	    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(timeoutSeconds, TimeUnit.SECONDS)
	            .pollingEvery(500, TimeUnit.MILLISECONDS)
	            .ignoring(NoSuchElementException.class);

	    return wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver webDriver) {
	            return driver.findElement(locator);
	        }
	    });
	}*/
	
	public void waitUntilClickable(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void clickButton(WebDriver driver, String identifyBy,
			String locator, String alt) throws InterruptedException {
		
	}
	public boolean syncObject(By locator) throws TimeoutException {
		boolean IsDisplayed = false;
		WebElement element = null;
		try { 
			WebDriverWait wait = new WebDriverWait(driver, 3);			
			element=wait.until(ExpectedConditions.presenceOfElementLocated(locator));	
			System.out.println("element status from isElementPresentExplict= "+IsDisplayed); 
			IsDisplayed = true;
			return IsDisplayed;
			// currentTime();
		} catch (TimeoutException e) {
			System.out.println("element status from isElementPresentExplict= "+IsDisplayed);
			return IsDisplayed;
			
		}


	}
	
	public boolean isPageDisplayed(WebElement element){
		 
		boolean isDisplayed = false;				
		
		try {
			//WebDriverWait wait = new WebDriverWait(driver, 2);
			//webElement = wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
			Assert.assertEquals(element.isDisplayed(),true);
			isDisplayed = true;
		}catch(Exception e){
			isDisplayed = false;			
		}
		
		return isDisplayed;
		
	}
	
	public void typeText(WebElement element, String text){
		element.sendKeys(text);
	}
	
	public void clickBtn(WebElement element){
		element.click();
	}
	
	

}
