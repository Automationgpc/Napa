package com.napa.online.testcases;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import java.util.Hashtable;
import java.util.Map;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.napa.online.pages.LaunchPage;
import com.napa.online.util.DataUtil;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.LogStatus;

public class HTTPResponseCodeTest extends BaseTest{
	
	String testCaseName="HTTPResponseCodeTest";
	
	String testResult = "FAIL";
	
	@Test(dataProvider="getData")
	public void get_http_response_code_test(Hashtable<String,String> data) throws InterruptedException{
		
		String testDesc = data.get("TestDesc");
		test = extent.startTest(testDesc);
		
		String url = data.get("URL");
		System.out.println("url:"+url);
		
		
		
		if(!DataUtil.isTestExecutable(xls, testCaseName) || data.get(RunConfig.RUNMODE_COL).equalsIgnoreCase("No")){
			test.log(LogStatus.SKIP, "SKIP: Skipping the test case as Runmode is No");
			testResult = "SKIP";
			System.out.println("Skipping the Test: "+testDesc+" since Runmode is No");
			throw new SkipException("Skipping the test as Runmode is No");
		}
		
		/*int testValue = xls.getCellRowNum(RunConfig.TESTDATA_SHEET,"testDesc" , testDesc);
		System.out.println("testValue:"+testValue);*/
		test.log(LogStatus.INFO, "Starting "+testDesc);	
		
			
		if (RunConfig.SELECT_BROWSER_FROM_XL){
			RunConfig.BROWSER=data.get("Browser");
		}else{
			RunConfig.BROWSER=RunConfig.BROWSER_XML;
		}
		
		System.out.println("Starting the Test: "+testDesc+" on "+RunConfig.BROWSER);
		init(RunConfig.BROWSER);	
					
		LaunchPage launchPage =new LaunchPage(driver,test);
		PageFactory.initElements(driver, launchPage);
		test.log(LogStatus.INFO, "Input URL: " + url);
		
		int responseCode = launchPage.getHttpResponseCode(url);
		test.log(LogStatus.PASS, "HTTP Response Code is= "+responseCode);	
		 
		if (responseCode >= 400) 
		{ 
			 
			test.log(LogStatus.FAIL, "HTTP Response Code XXX is= "+responseCode);	
		}
	 
		
		// Prabhakar code ends here 
		 
		//Writing response code to Data Sheet
		//System.out.println("RunConfig.DATA_START_ROW_NUM: "+RunConfig.DATA_START_ROW_NUM);
		//System.out.println("RunConfig.TC_ROW_NUM: "+RunConfig.TC_ROW_NUM);
		//DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.TC_ROW_NUM, 4,ResponseCode);
		//DataUtil.setResultsData(xls, RunConfig.TESTDATA_SHEET, RunConfig.DATA_START_ROW_NUM, 36,ResponseCode);
		
		 
		
		// My writing into excel sheet -- starts 
		 
		
		
		// my writing into excel sheet -- end 
		
		
	}
		
	@AfterMethod
	public void quit(){
		
		if(extent!=null){
			extent.endTest(test);
			extent.flush();
		}
		if(driver!=null){
			driver.manage().deleteAllCookies();
			driver.quit();
		}
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
