package com.napa.online.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.PageFactory;
import com.napa.online.base.BasePage;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class LaunchPage extends BasePage{
	
	public LaunchPage(WebDriver driver, ExtentTest test){
		super(driver,test);	
		
	}
	
	public HomePage openHomePage(){
		// log
		//test.log(LogStatus.INFO, "Opening the url - "+getEnvDetails().get("url"));
		
		driver.get(getEnvDetails().get("url"));
		//driver.manage().deleteAllCookies();
		test.log(LogStatus.INFO, "Test Web Site - "+getEnvDetails().get("url")+" Opened");
		System.out.println("Test Web Site - "+getEnvDetails().get("url")+" Opened");
		HomePage homePage = new HomePage(driver,test);
		PageFactory.initElements(driver, homePage);
		return homePage;
	}
	
	public int getHttpResponseCode(String url){
		
		// navigate to the page
        System.out.println("Navigate to " + url);
        driver.navigate().to(url);
        
        // and capture the last recorded url (it may be a redirect, or the
        // original url)
        String currentURL = driver.getCurrentUrl();
        
        takeScreenShot();

        // then ask for all the performance logs from this request
        // one of them will contain the Network.responseReceived method
        // and we shall find the "last recorded url" response
        LogEntries logs = driver.manage().logs().get("performance");
        //System.out.println(logs.toString());
        
        int status = -1;
        
        //System.out.println("\nList of log entries:\n");

        for (Iterator<LogEntry> it = logs.iterator(); it.hasNext();)
        {
            LogEntry entry = it.next();

            try
            {
                JSONObject json = new JSONObject(entry.getMessage());

                //System.out.println(json.toString());

                JSONObject message = json.getJSONObject("message");
                //System.out.println("message= " + message);
                String method = message.getString("method");
               //System.out.println("method= " + method);
                
                if (method != null
                        && "Network.responseReceived".equals(method))
                {
                    JSONObject params = message.getJSONObject("params");

                    JSONObject response = params.getJSONObject("response");
                    //System.out.println("Response Object= " + response.toString());
                    String messageUrl = response.getString("url");

                    if (currentURL.equals(messageUrl))
                    {
                        status = response.getInt("status");
                      
                        //System.out.println("Input URL used: " + currentURL);
                        //System.out.println("HTTP Response Code is " + status);

                        //System.out.println("Response Headers: " + response.get("headers"));
                    }
                }
            } catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Input URL used: " + currentURL);
        System.out.println("HTTP Response Code is " + status);
		return status;
	}
	
	
	

}
