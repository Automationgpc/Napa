package com.napa.online.testcases;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.napa.online.util.ExtentManager;
import com.napa.online.util.RunConfig;
import com.napa.online.util.XLS_Reader;
import com.napa.online.util.XLWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	
	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest test;
	public XLS_Reader xls = new XLS_Reader(RunConfig.DATA_XLS_PATH);
	//public XLWriter xlwriter = new XLWriter(RunConfig.RESULTS_XLS_PATH);
	
	public WebDriver driver;
	public ProfilesIni firefoxProfiles;
	//public String browser="";
	
	public void init(String browser){
		//test.log(LogStatus.INFO, "Opening browser - "+ browser);
		if(!RunConfig.GRID_RUN){
			// local machine
			if(browser.equals("Firefox")){
				/*//Create object of webdriver's inbuilt class ProfilesIni to access Its method getProfile.				  
					firefoxProfiles = new ProfilesIni();
				  //Get access of Custom firefox profile Selenium_User.
				  FirefoxProfile ffoxProfile = firefoxProfiles.getProfile("Selenium_User");
				  //Pass ffoxprofile parameter to FirefoxDriver.
				driver= new FirefoxDriver(ffoxProfile);*/
				
				driver = new FirefoxDriver();
			}
			else if(browser.equals("Chrome")){				
				System.setProperty("webdriver.chrome.driver", RunConfig.CHROME_DRIVER_EXE);	
				//DesiredCapabilities capability = DesiredCapabilities.chrome();
				//capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				
				String userAgent = "KTXN-NOLAuth";
				
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--user-agent=" + userAgent);
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				//cap.setCapability(CapabilityType.PROXY, p);
				cap.setCapability(ChromeOptions.CAPABILITY , co);
				
				//enabling Performance logging
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
				cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);					
				
				driver = new ChromeDriver(cap);
				
				
				//incognito mode
				/*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
				driver = new ChromeDriver(capabilities);*/
			}
			else if(browser.equals("IE")){				
				System.setProperty("webdriver.ie.driver", RunConfig.IE_DRIVER_EXE);									
				//driver= new InternetExplorerDriver();
				
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				ieCapabilities.setCapability("ensureCleanSession", true);
				ieCapabilities.setCapability("ignoreZoomSetting", true);				
				//ieCapabilities.setCapability("nativeEvents", false);    
				//ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
				ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
				//ieCapabilities.setCapability("disable-popup-blocking", true);
				//ieCapabilities.setCapability("enablePersistentHover", true);
				ieCapabilities.setCapability("ignore-certificate-error", true);
				ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				//ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,true);

				driver = new InternetExplorerDriver(ieCapabilities);
				
			}
			
		}else{
			// grid
			DesiredCapabilities cap=null;
			if(browser.equals("Firefox")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}else if(browser.equals("Chrome")){
				 cap = DesiredCapabilities.chrome();
				 cap.setBrowserName("chrome");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Opened browser successfully - "+ browser);

	}
	
	public void reportFailure(String failureMessage){
		test.log(LogStatus.FAIL, failureMessage);
		takeScreenShot();
		Assert.fail(failureMessage);
	}
	
	public void takeScreenShot(){
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=RunConfig.REPORTS_PATH+"screenshots//"+screenshotFile;
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

}
