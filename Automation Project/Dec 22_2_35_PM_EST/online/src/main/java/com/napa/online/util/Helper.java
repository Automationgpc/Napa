package com.napa.online.util;

import java.util.Date;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Helper {
	
	public boolean isElementPresent(WebElement element){
		 
		boolean isPresent = false;				
		
		try {
			//WebDriverWait wait = new WebDriverWait(driver, 2);
			//webElement = wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
			boolean status=element.isDisplayed();
			System.out.println("element status= "+status);
			Assert.assertEquals(element.isDisplayed(),true);
			isPresent = true;
		}catch(Exception e){
			isPresent = false;			
		}
		
		return isPresent;
		
	}
	
	public static void createNewUserDetails(){
		Date d = new Date();
		//System.out.println("UnformattedDate= "+d.toString());
		String formattedDate = d.toString().replace(":", "_").replace(" ", "_");
		//System.out.println("formattedDate= "+formattedDate);
		//String first = formattedDate.substring(0,11);
		String first = "John";
		//System.out.println("first= "+first);
		//String last = formattedDate.substring(11);
		String last = "Doe";
		
		RunConfig.FIRST_NAME = "Auto_"+first;
		//System.out.println("first name= "+RunConfig.FIRST_NAME);
		
		RunConfig.LAST_NAME = last;
		//System.out.println("last name= "+RunConfig.LAST_NAME);
		
		RunConfig.EMAIL_ID = formattedDate+"@gmail.com";
		//System.out.println("Email Id= "+RunConfig.EMAIL_ID);
		
		RunConfig.CONFIRM_EMAIL_ID = RunConfig.EMAIL_ID;
		//System.out.println("Confirm Email Id= "+RunConfig.CONFIRM_EMAIL_ID);
		
		RunConfig.PHONE_NUMBER = "678-852-0757";
		//System.out.println("Phone Number= "+RunConfig.PHONE_NUMBER);
		
		RunConfig.PASSWORD = "napa@Auto77";
		//System.out.println("Password= "+RunConfig.PASSWORD);	
			
		
	}

}
