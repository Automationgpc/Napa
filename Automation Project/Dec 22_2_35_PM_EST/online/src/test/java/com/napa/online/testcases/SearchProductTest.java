package com.napa.online.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.napa.online.base.BasePage;
import com.napa.online.pages.HomePage;
import com.napa.online.pages.LaunchPage;
import com.napa.online.pages.LoginPage;
import com.napa.online.util.DataUtil;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SearchProductTest extends BaseTest {
	
	String testCaseName="SearchProductTest";
	LoginPage loginPage;
	//HomePage homePage;
	
	//@Test(dataProvider="getData", threadPoolSize = 3, invocationCount = 1, timeOut = 10000)
	@Test(dataProvider="getData")
	public void testSearchProduct(Hashtable<String,String> data) throws InterruptedException{
		
		//test=extent.startTest("Search Product Test");
		String testDesc = data.get("TestDesc");
		//System.out.println("test Desc= "+testDesc);
		test = extent.startTest(testDesc);
		
		if(!DataUtil.isTestExecutable(xls, testCaseName) || data.get(RunConfig.RUNMODE_COL).equalsIgnoreCase("No")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is No");
			System.out.println("Skipping the Test: "+testDesc+" since Runmode is No");
			throw new SkipException("Skipping the test as Runmode is No");
		}
		
		//DataUtil.getCommonData(xls, "Visa");
		//System.out.println("Common Data Value= "+DataUtil.getCommonData(xls, "CHIDU"));
		/*if(!DataUtil.isTestExecutable(xls, testCaseName)){
			test.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}*/
		
		//RunConfig.BROWSER_NAME=data.get("Browser");
		//System.out.println("browserName passed to Test Case= "+RunConfig.BROWSER_NAME);
		
		
		//System.out.println("SearchProduct: browser from XML= "+RunConfig.BROWSER_XML);
		//System.out.println("SearchProduct: browser from XL Sheet= "+data.get("Browser"));
		
		if (RunConfig.SELECT_BROWSER_FROM_XL){
			RunConfig.BROWSER=data.get("Browser");
		}else{
			RunConfig.BROWSER=RunConfig.BROWSER_XML;
		}
		System.out.println("Starting the Test: "+testDesc+" on "+RunConfig.BROWSER);		
		init(RunConfig.BROWSER);	
		test.log(LogStatus.INFO, "Starting "+testDesc+" on "+RunConfig.BROWSER);
		
		LaunchPage launchPage =new LaunchPage(driver,test);
		PageFactory.initElements(driver, launchPage);		
		HomePage homePage = launchPage.openHomePage();
		
	
		
		/*HomePage homePage =new HomePage(driver,test);
		PageFactory.initElements(driver, homePage);	*/
		/*HomePage homePage =new HomePage(driver,test);
		homePage= homePage.goToHomePage();*/
		Thread.sleep(1000);
		//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		if (homePage.isElementPresent(homePage.header.SearchBox)){			
			homePage.header.typeInSearch(data.get("Product"));
			Thread.sleep(1000);
			test.log(LogStatus.INFO, data.get("Product")+" was typed in the Search Box");
			homePage.takeScreenShot();
			Thread.sleep(1000);
			homePage.header.clickSearchBtn();
			test.log(LogStatus.INFO, "Search Button clicked");
			homePage.takeScreenShot();
			test.log(LogStatus.PASS, "Search Product Test passed");
			
		} else{
			test.log(LogStatus.FAIL, "Search Box was not Found");
		}
		
		
		/*if (homePage.isElementPresent(homePage.header.myAccountLnk)){
			loginPage= homePage.header.goToLoginPage(homePage.header.myAccountLnk);
			Thread.sleep(1000);
			test.log(LogStatus.PASS, "Test Passed. My Account Link was Clicked");
		} else{
			test.log(LogStatus.FAIL, "My Account Link was not Found");
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
	
	//@DataProvider(name = "getData",parallel=true)
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
	
	/*@BeforeMethod(alwaysRun = true)
	public void setupBeforeMethod(ITestContext context) {
	  String seleniumHost = context.getCurrentXmlTest().getParameter("selenium.host");
	  String seleniumPort = context.getCurrentXmlTest().getParameter("selenium.port");
	  String seleniumBrowser = context.getCurrentXmlTest().getParameter("browser");
	  String seleniumUrl = context.getCurrentXmlTest().getParameter("selenium.url");
	  
	  System.out.println("browser from ITestContext= "+seleniumBrowser);
	  RunConfig.BROWSER_NAME=seleniumBrowser;
	  
	}*/
	
	/*@BeforeTest(alwaysRun = true)
	public void setupBeforeTest(ITestContext context) {
	  String seleniumHost = context.getCurrentXmlTest().getParameter("selenium.host");
	  String seleniumPort = context.getCurrentXmlTest().getParameter("selenium.port");
	  String seleniumBrowser = context.getCurrentXmlTest().getParameter("browser");
	  String seleniumUrl = context.getCurrentXmlTest().getParameter("selenium.url");
	  
	  System.out.println("browser from ITestContext= "+seleniumBrowser);
	  RunConfig.BROWSER_NAME=seleniumBrowser;
	  
	}*/


}
	
	
	
	


