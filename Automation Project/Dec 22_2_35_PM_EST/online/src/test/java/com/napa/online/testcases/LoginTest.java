package com.napa.online.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.napa.online.pages.HomePage;
import com.napa.online.pages.LaunchPage;
import com.napa.online.pages.LoginPage;
import com.napa.online.util.DataUtil;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends BaseTest {
	
	String testCaseName="LoginTest";
	boolean isPageDisplayed=false;
	
	//String orderNumber="none";
	boolean orderSuccess = false;
	String testResult = "FAIL";
	
	LoginPage loginPage;	
	
	@Test(dataProvider="getData")
	public void doLogin(Hashtable<String,String> data) throws InterruptedException{
		
		String testDesc = data.get("TestDesc");
		//System.out.println("test Desc= "+testDesc);
		test = extent.startTest(testDesc);
		
		if(!DataUtil.isTestExecutable(xls, testCaseName) || data.get(RunConfig.RUNMODE_COL).equalsIgnoreCase("No")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is No");
			System.out.println("Skipping the Test: "+testDesc+" since Runmode is No");
			throw new SkipException("Skipping the test as Runmode is No");
		}
		//System.out.println("LoginTest: browser from XML= "+RunConfig.BROWSER_XML);
		//System.out.println("LoginTest: browser from XL Sheet= "+data.get("Browser"));
		if (RunConfig.SELECT_BROWSER_FROM_XL){
			RunConfig.BROWSER=data.get("Browser");
		}else{
			RunConfig.BROWSER=RunConfig.BROWSER_XML;
		}
		System.out.println("Starting the Test: "+testDesc+" on "+RunConfig.BROWSER);
		init(RunConfig.BROWSER);	
		test.log(LogStatus.INFO, "Starting "+testDesc+" on "+RunConfig.BROWSER);		 
		
		/*HomePage homePage =new HomePage(driver,test);
		PageFactory.initElements(driver, homePage);			
		homePage= homePage.goToHomePage();*/
		
		LaunchPage launchPage =new LaunchPage(driver,test);
		PageFactory.initElements(driver, launchPage);		
		HomePage homePage = launchPage.openHomePage();
		
		homePage.waitForPageLoad(driver);
		//Thread.sleep(2000);
		
		//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		if (homePage.isElementPresent(homePage.header.MyAccountLnk)){
			loginPage= homePage.header.goToLoginPage(homePage.header.MyAccountLnk);
			//Thread.sleep(2000);
			if (!(loginPage==null)){
				loginPage.waitForPageLoad(driver);
				test.log(LogStatus.INFO, "Login Page is displayed");
				takeScreenShot();
			}else{
				test.log(LogStatus.FAIL, "Login Page is NOT displayed");
			}
			
		} else{
			test.log(LogStatus.FAIL, "My Account Link was not Found");
			takeScreenShot();
		}
		
		//loginPage.takeScreenShot();
		test.log(LogStatus.INFO, "Logging in");
		
		Object page = loginPage.doLogin(data.get("Username"), data.get("Password"));
		
		if(page instanceof HomePage){			
			homePage = (HomePage) page;
			
			if (homePage.isElementPresent(homePage.header.MyMenuNameDD)){
				String expectedFullName = data.get("UserFullName");
				String actualNameDisplayed = homePage.header.MyMenuNameDD.getText().toString();
				//System.out.println(expectedFullName);
				///System.out.println(actualNameDisplayed);
				if(actualNameDisplayed.contains(expectedFullName)){
					test.log(LogStatus.PASS, "User Full Name displayed. Login Successful.");
					homePage.takeScreenShot();
				}
				else{
					test.log(LogStatus.FAIL, "FAIL:Logged in.Full Name does not match.");
					Assert.fail();
				}
			} else{
				test.log(LogStatus.FAIL, "FAIL:Full Name element not Found. Login Test Failed.");
				Assert.fail();
				}
		  }
		  else{			
			loginPage.takeScreenShot();
			Thread.sleep(2000);
		  }	
		
		//Logging out
		homePage.header.doLogOut();
		if (homePage.isElementPresent(homePage.header.MyMenuNameDD)){
			test.log(LogStatus.FAIL, "Logout Failed.");
			Assert.fail();
			
		}else{
			test.log(LogStatus.PASS, "Logout Successful.");
		}
		homePage.takeScreenShot();
		
		/*String actualResult="";"
		// if i am logged in
		if(page instanceof HomePage)
			actualResult="Success";
		else
			actualResult="Unsuccessful";
		
		if(!actualResult.equals(data.get("ExpectedResult"))){
			
			reportFailure("Login was not successful");
		}*/
		
		
	}
	@AfterMethod
	public void quit(){
		if(extent!=null){
			extent.endTest(test);
			extent.flush();
		}
		if(driver!=null)
			driver.quit();
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(xls, testCaseName);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) {
	  //String seleniumHost = context.getCurrentXmlTest().getParameter("selenium.host");
	  //String seleniumPort = context.getCurrentXmlTest().getParameter("selenium.port");
	  String seleniumBrowser = context.getCurrentXmlTest().getParameter("browser");
	  //String seleniumUrl = context.getCurrentXmlTest().getParameter("selenium.url");
	  
	  //System.out.println("browser from ITestContext= "+seleniumBrowser);
	  RunConfig.BROWSER_XML=seleniumBrowser;
	  
	}
	
	 
}
