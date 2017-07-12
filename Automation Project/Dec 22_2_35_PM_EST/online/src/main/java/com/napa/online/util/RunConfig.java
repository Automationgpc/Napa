package com.napa.online.util;

import java.io.File;

public class RunConfig {
	
	public static final boolean GRID_RUN = false;	
	
	public static final String ENV = "QA"; //PROD,QA,STG,HYBRIS_STG
	
	public static boolean SELECT_BROWSER_FROM_XL = false;//false, true
	
	public static String BROWSER_XML="";
	
	public static String BROWSER="";
	//public static String BROWSER_NAME=System.getProperty("browser");
	
	// URL-prod-napaonline
	public static final String PROD_HOMEPAGE_URL = "http://www.napaonline.com";
	
	// URL-QA-napaonline
	public static final String QA_HOMEPAGE_URL = "https://akamai.disqa.napaonline.com/";	
	//public static final String QA_HOMEPAGE_URL = "https://attqa.napaonline.com/";
	
	// URL-STG-Napaonline
	//public static final String STG_HOMEPAGE_URL = "https://attstg.napaonline.com/";
	public static final String STG_HOMEPAGE_URL = "https://akamai.disstaging.napaonline.com/";	
	
	// URL-HYBRIS_STG
	public static final String HYBRIS_STG_URL = "http://attstgadmin.napacanada.com/cmscockpit/index.zul";	
	
	//paths
	
	//public static final String CHROME_DRIVER_EXE_WIN = System.getProperty("user.dir")+ "\\chromedriver.exe";
	public static final String CHROME_DRIVER_EXE= System.getProperty("user.dir")+ File.separator+"chromedriver32_2_25.exe";	
	//public static final String CHROME_DRIVER_EXE= System.getProperty("user.dir")+ File.separator+"chromedriver_win32_2_0.exe";	
	//public static final String IE_DRIVER_EXE = System.getProperty("user.dir")+ File.separator+"IEDriverServer.exe";
	public static final String IE_DRIVER_EXE = System.getProperty("user.dir")+ File.separator+"IEDriverServer32_2_53_1.exe";
	
	public static final String REPORTS_PATH = System.getProperty("user.dir")+ File.separator+"test_reports"+File.separator+"test_report_";	
	public static final String REPORTS_CONFIG_PATH = System.getProperty("user.dir")+File.separator+"ReportsConfig.xml";		
	
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+ File.separator+"data"+File.separator+"Data.xls";
	//public static final String RESULTS_XLS_PATH = System.getProperty("user.dir")+ File.separator+"data"+File.separator+"Data_Output.xls";
	
	public static final String TESTCASES_SHEET = "TestCases";
	public static final String TESTDATA_SHEET = "TestData";
	public static final Object RUNMODE_COL = "Runmode";
	public static final String COMMONDATA_SHEET = "CommonData";
	
	//Global Variables
	public static String FIRST_NAME="";
	public static String LAST_NAME="";
	public static String EMAIL_ID="";
	public static String CONFIRM_EMAIL_ID="";
	public static String PHONE_NUMBER="";
	public static String PASSWORD="";
	public static String CONFIRM_PASSWORD="";

	public static String ORDER_NUMBER="";

	public static int DATA_START_ROW_NUM;

	public static boolean NOT_LOCALIZED = false;

	public static String VISA="";

	public static int TC_ROW_NUM;

	

	
		
	

}
