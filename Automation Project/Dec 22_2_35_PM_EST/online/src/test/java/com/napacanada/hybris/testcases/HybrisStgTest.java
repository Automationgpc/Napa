package com.napacanada.hybris.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.napa.online.testcases.BaseTest;
import com.napa.online.util.DataUtil;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.LogStatus;

public class HybrisStgTest extends BaseTest{
	String testCaseName="HybrisStgTest";
	
	@Test(dataProvider="getData")
	public void hybrisClickLinks(Hashtable<String,String> data) throws InterruptedException{
		
		String testDesc = data.get("TestDesc");
		//System.out.println("test Desc= "+testDesc);
		test = extent.startTest(testDesc);
		
		if(!DataUtil.isTestExecutable(xls, testCaseName) || data.get(RunConfig.RUNMODE_COL).equalsIgnoreCase("No")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is No");
			System.out.println("Skipping the Test: "+testDesc+" since Runmode is No");
			throw new SkipException("Skipping the test as Runmode is No");
		}
		
		if (RunConfig.SELECT_BROWSER_FROM_XL){
			RunConfig.BROWSER=data.get("Browser");
		}else{
			RunConfig.BROWSER=RunConfig.BROWSER_XML;
		}
		System.out.println("Starting the Test: "+testDesc+" on "+RunConfig.BROWSER);
		init(RunConfig.BROWSER);	
		test.log(LogStatus.INFO, "Starting "+testDesc+" on "+RunConfig.BROWSER);
		
	}
	

}
