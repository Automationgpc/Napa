package com.napa.online.testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.napa.online.pages.HomePage;
import com.napa.online.pages.LaunchPage;
import com.napa.online.util.DataUtil;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.LogStatus;

public class SampleTest extends BaseTest {
	String testCaseName="SampleTest";
	
	@Test(dataProvider="getData")
	public void sampleRun(Hashtable<String,String> data) throws InterruptedException{
		test = extent.startTest("Sample Test X");
		
		if(!DataUtil.isTestExecutable(xls, testCaseName) || data.get(RunConfig.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
			throw new SkipException("Skipping the test as Runmode is N");
		}
		
		test.log(LogStatus.INFO, "Starting Sample Test");
		init("Mozilla");		 
		
		/*HomePage homePage =new HomePage(driver,test);
		PageFactory.initElements(driver, homePage);			
		homePage= homePage.goToHomePage();*/
		
		LaunchPage launchPage =new LaunchPage(driver,test);
		PageFactory.initElements(driver, launchPage);		
		HomePage homePage = launchPage.openHomePage();
		
		homePage.waitForPageLoad(driver);
		Thread.sleep(2000);
		
		homePage.typeInHeroSearch("brake pads");
		homePage.takeScreenShot();
		Thread.sleep(10000);
		homePage.selectHeroYear("2016");
		homePage.takeScreenShot();
		Thread.sleep(50000);
		/*homePage.HeroAutoMake.click();
		homePage.selectHeroMake("Acura");
		homePage.takeScreenShot();
		Thread.sleep(50000);*/
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
}


