package com.napa.online.pages;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.napa.online.base.BasePage;
import com.napa.online.util.RunConfig;
import com.napa.online.util.Helper;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

public class Header{
	
	WebDriver driver;
	ExtentTest test;
	
	

	public Header(WebDriver driver, ExtentTest test) {
		this.driver =  driver;
		this.test = test;
	}	
	
	
	/****************My Account Elements*************************************************/
	/*@FindBy(xpath="//span[contains(@class,'icon-napa-user')]")
	public WebElement MyAccountLnk;*/
	
	@FindBy(xpath="//span[@class='icon-napa-user']")	
	public WebElement MyAccountLnk;
		
	@FindBy(xpath="(//div[@class='my-menu-value'])[3]")
	public WebElement MyMenuNameDD;
	
	@FindBy(xpath="//a[@class='button-primary yellow'][@href='/napa/en/logout']")
	public WebElement LogOutBtn;
	
	/****************My Vehicle Elements*************************************************/
	@FindBy(xpath="(//div[@class='my-menu-value'])[1]")
	public WebElement SelectVehicleHdr;
	
	
	
	@FindBy(xpath="//a[@id='deselectallvehicles']")
	public WebElement DeselectAllVehiclesHdr;
	
	@FindBy(xpath="//a[@class='my-menu-panel-list-link']")
	public WebElement SelectVehiclefromList;
	
	@FindBy(xpath="//a[@class='button-primary yellow add-new-vehicle']")
	public WebElement AddNewVehicleHdr;
	
	@FindBy(xpath="//*[@id='modalMyVehicleAdd']/ul/li[2]")
	public WebElement AddNewVehicleFromHdrByVin;
	
	public final By AddNewVehicleFromHdrByVinBy = By.xpath("//*[@id='modalMyVehicleAdd']/ul/li[2]");
	public final By VinTextBoxBy = By.xpath("//*[@id='fitment-vin']");
	public final By AddVehicleVinBtnBy = By.xpath("//*[@id='vin']/div[2]/input[2]");
	
	
	public void clkSelectVehicleHdr(){
		SelectVehicleHdr.click();		
	}
	@FindBy(xpath="//li[@class='fitment-modal-tab-new active']")
	public WebElement AddNewVehicleTab;
	
	@FindBy(xpath="//input[@id='modal-year']")
	public WebElement VehicleYearHdr;
	
	
	
	public void clkSelectVehicleYearHdr(){
		VehicleYearHdr.click();		
	}
	
	public void selectYearHdr(String year) throws InterruptedException{
		//VehicleYearHdr.click();
		//Thread.sleep(3000);
		if(VehicleYearHdr.isDisplayed()){
			//VehicleYearHdr.click();
			//TimeUnit.SECONDS.sleep(2);
			VehicleYearHdr.sendKeys(year);
			TimeUnit.SECONDS.sleep(2);
			VehicleYearHdr.sendKeys(Keys.TAB);
			TimeUnit.SECONDS.sleep(2);			
		}else{
			System.out.println("VehicleYearHdr was NOT FOUND");
			Assert.fail();
		}
		
	}
		
	@FindBy(xpath="//input[@id='modal-make']")
	public WebElement VehicleMakeHdr;
	
	//*[@id='new']/div[2]/span[2]/div/div/div[14]
	//static final By focusBy = By.xpath("//*[@id='new']/div[2]/span[2]/div/div/div[14]");
	static final By focusBy = By.xpath("//div[@class='tt-suggestion tt-selectable'][contains(text(),'Ford')]");
	
	public void selectMakeHdr(String make) throws InterruptedException{
		if(VehicleMakeHdr.isDisplayed()){
			VehicleMakeHdr.sendKeys(make);
			TimeUnit.SECONDS.sleep(2);
			VehicleMakeHdr.sendKeys(Keys.TAB);
			TimeUnit.SECONDS.sleep(2);			
		}else{
			System.out.println("VehicleMakeHdr was NOT FOUND");
			Assert.fail();
		}
		
		
	}
	
	@FindBy(xpath="//input[@id='modal-model']")
	public WebElement VehicleModelHdr;
	
	@FindBy(xpath="//*[@id='new']/div[2]/span[1]/div/div/div[2]")
	public WebElement VehicleYear2016;
	
	
	static final By vehicleYrBy = By.xpath("//input[@id='modal-year']");
	static final By year2016 = By.xpath("//*[@id='new']/div[2]/span[1]/div/div/div[2]");
	static final By selectYear = By.xpath("//*[@id='new']/div[2]/span[1]/div/div/div[2]");
	
	static final By addVehicleHdrBtnLoc = By.xpath("//input[@class='button-primary yellow button-enable fitment-select-button'][@value='Add Vehicle']");
	
	@FindBy(xpath="//input[@class='button-primary yellow button-enable fitment-select-button'][@value='Add Vehicle']")
	public WebElement AddVehicleHdrBtn;
	
	//*[@id='vin']/div[2]/input[2]
	
	
	public void selectModelHdr(String model) throws InterruptedException{
		VehicleModelHdr.sendKeys(model);
		TimeUnit.SECONDS.sleep(2);
		VehicleModelHdr.sendKeys(Keys.TAB);
		//VehicleModelHdr.sendKeys(Keys.ENTER);
	}
	
	public HomePage addVehicleFromHdr(String year, String make, String model) throws InterruptedException{
		
		String vehicle = year+" "+make+" "+model;		
		String yearXpath="//div[@class='tt-suggestion tt-selectable'][contains(text(),'"+year+"')]";		
		String makeXpath="//div[@class='tt-suggestion tt-selectable'][contains(text(),'"+make+"')]";		
		String modelXpath="//div[@class='tt-suggestion tt-selectable'][contains(text(),'"+model+"')]";
		
		String selectVehicleHdrXpath = "(//div[@class='my-menu-value'])[1]";
		String addNewVehicleHdrXpath = "//a[@class='button-primary yellow add-new-vehicle']";
		String yearDDXpath = "//input[@id='modal-year']";
		String makeDDXpath = "//input[@id='modal-make']";
		String modelDDXpath = "//input[@id='modal-model']";
		String addVehicleXpath = "//input[@class='button-primary yellow button-enable fitment-select-button'][@value='Add Vehicle']";
		WebElement YearDDEle=null;
		
		
		try{				
			
			WebElement SelectVehicleHdrEle = (new WebDriverWait(driver, 5))
					  .until(ExpectedConditions.elementToBeClickable(By.xpath(selectVehicleHdrXpath)));
			
			if(SelectVehicleHdrEle.isDisplayed()){
				if(SelectVehicleHdrEle.getText().toString().contains(vehicle)){
					test.log(LogStatus.PASS, "PASS: Vehicle "+vehicle+" added from Header");
				}else{
					SelectVehicleHdrEle.click();
					TimeUnit.SECONDS.sleep(1);
					try{
						YearDDEle = (new WebDriverWait(driver, 5))
								  .until(ExpectedConditions.elementToBeClickable(By.xpath(yearDDXpath)));
						if(!YearDDEle.isDisplayed()){
							SelectVehicleHdrEle.click();				
						}
						
					}catch(Exception e){
						System.out.println("Exception occured while trying to click SelectVehicleHDR 2nd time.We can ignore this exception ");
					}
					//Thread.sleep(5000);
					try{
						WebElement AddNewVehicleHdrEle = (new WebDriverWait(driver, 5))
								  .until(ExpectedConditions.elementToBeClickable(By.xpath(addNewVehicleHdrXpath)));
						//WebElement AddNewVehicleHdrEle = driver.findElement(By.xpath(addNewVehicleHdrXpath));
						
						if(AddNewVehicleHdrEle.isDisplayed()){
							System.out.println("AddNewVehicleHdrEle is DISPLAYED");
							AddNewVehicleHdrEle.click();	
							TimeUnit.SECONDS.sleep(2);
							
						}
						
					}catch(Exception e){
						System.out.println("AddNewVehicleHdrEle is not displayed.We can ignore this exception");
					}
								
					if (RunConfig.BROWSER.equals("IE")|| RunConfig.BROWSER.equals("Firefox")){	
						
						if(driver.findElement(By.xpath(yearDDXpath)).isDisplayed()){
							driver.findElement(By.xpath(yearDDXpath)).sendKeys(year);
							//Thread.sleep(5000);
							TimeUnit.SECONDS.sleep(5);
							driver.findElement(By.xpath(yearDDXpath)).sendKeys(Keys.TAB);
							Thread.sleep(5000);
						}
						if(driver.findElement(By.xpath(makeDDXpath)).isDisplayed()){
							driver.findElement(By.xpath(makeDDXpath)).sendKeys(make);
							Thread.sleep(5000);
							driver.findElement(By.xpath(makeDDXpath)).sendKeys(Keys.TAB);
							Thread.sleep(5000);
						}
						if(driver.findElement(By.xpath(modelDDXpath)).isDisplayed()){
							driver.findElement(By.xpath(modelDDXpath)).sendKeys(model);
							Thread.sleep(5000);
							driver.findElement(By.xpath(modelDDXpath)).sendKeys(Keys.TAB);
							//Thread.sleep(3);
						}	
						if(driver.findElement(By.xpath(addVehicleXpath)).isEnabled()){			
							driver.findElement(By.xpath(addVehicleXpath)).click();
						}
						
							 
							//WebElement yearEle = driver.findElement(By.xpath(yearXpath));
							//action.moveToElement(yearEle).click().release().build().perform();
							
							//action.clickAndHold(VehicleYearHdr).moveToElement(driver.findElement(By.xpath(yearXpath))).click().release().build().perform();
							//action.clickAndHold(VehicleMakeHdr).moveToElement(driver.findElement(By.xpath(makeXpath))).click().release().build().perform();
							//action.clickAndHold(VehicleModelHdr).moveToElement(driver.findElement(By.xpath(modelXpath))).click().release().build().perform();
							//action.clickAndHold(VehicleYearHdr);
							//driver.findElement(year2016);
							//action.moveToElement(driver.findElement(By.xpath(yearXpath))).click();
							
							//AddVehicleHdrBtn.sendKeys(Keys.ENTER);
							//test.log(LogStatus.INFO, "Vehicle "+vehicle+" added from Header");
						
					}
					if (RunConfig.BROWSER.equals("Chrome")){
						selectYearHdr(year);
						selectMakeHdr(make);
						selectModelHdr(model);			
						AddVehicleHdrBtn.sendKeys(Keys.ENTER);
					}									
					test.log(LogStatus.PASS, "PASS: Vehicle "+vehicle+" added from Header");
				}
								
			}
			
			HomePage homePage = new HomePage(driver, test);
			PageFactory.initElements(driver, homePage);		
			return homePage;
		}catch(Exception e){				
			System.out.println("FAIL: Failed to add Vehicle from Header");
			test.log(LogStatus.FAIL, "FAIL: Failed to add Vehicle from Header");	
			Assert.fail();				
			return null;
		}
			
			/*if(SelectVehicleHdrEle.isDisplayed()){
				SelectVehicleHdrEle.click();				
			}else{	
				System.out.println("Vehicle Header not displayed");
			}
			TimeUnit.SECONDS.sleep(2);
			try{
				YearDDEle = (new WebDriverWait(driver, 20))
						  .until(ExpectedConditions.elementToBeClickable(By.xpath(yearDDXpath)));
				if(!YearDDEle.isDisplayed()){
					SelectVehicleHdrEle.click();				
				}
				
			}catch(Exception e){
				System.out.println("Exception occured while trying to click SelectVehicleHDR 2nd time.We can ignore this exception ");
			}
			//Thread.sleep(5000);
			try{
				WebElement AddNewVehicleHdrEle = (new WebDriverWait(driver, 20))
						  .until(ExpectedConditions.elementToBeClickable(By.xpath(addNewVehicleHdrXpath)));
				//WebElement AddNewVehicleHdrEle = driver.findElement(By.xpath(addNewVehicleHdrXpath));
				
				if(AddNewVehicleHdrEle.isDisplayed()){
					System.out.println("AddNewVehicleHdrEle is DISPLAYED");
					AddNewVehicleHdrEle.click();	
					TimeUnit.SECONDS.sleep(2);
					
				}
				
			}catch(Exception e){
				System.out.println("AddNewVehicleHdrEle is not displayed.We can ignore this exception");
			}
						
			if (RunConfig.BROWSER.equals("IE")|| RunConfig.BROWSER.equals("Firefox")){	
				
				if(driver.findElement(By.xpath(yearDDXpath)).isDisplayed()){
					driver.findElement(By.xpath(yearDDXpath)).sendKeys(year);
					//Thread.sleep(5000);
					TimeUnit.SECONDS.sleep(5);
					driver.findElement(By.xpath(yearDDXpath)).sendKeys(Keys.TAB);
					Thread.sleep(5000);
				}
				if(driver.findElement(By.xpath(makeDDXpath)).isDisplayed()){
					driver.findElement(By.xpath(makeDDXpath)).sendKeys(make);
					Thread.sleep(5000);
					driver.findElement(By.xpath(makeDDXpath)).sendKeys(Keys.TAB);
					Thread.sleep(5000);
				}
				if(driver.findElement(By.xpath(modelDDXpath)).isDisplayed()){
					driver.findElement(By.xpath(modelDDXpath)).sendKeys(model);
					Thread.sleep(5000);
					driver.findElement(By.xpath(modelDDXpath)).sendKeys(Keys.TAB);
					//Thread.sleep(3);
				}	
				if(driver.findElement(By.xpath(addVehicleXpath)).isEnabled()){			
					driver.findElement(By.xpath(addVehicleXpath)).click();
				}
				
					 
					//WebElement yearEle = driver.findElement(By.xpath(yearXpath));
					//action.moveToElement(yearEle).click().release().build().perform();
					
					//action.clickAndHold(VehicleYearHdr).moveToElement(driver.findElement(By.xpath(yearXpath))).click().release().build().perform();
					//action.clickAndHold(VehicleMakeHdr).moveToElement(driver.findElement(By.xpath(makeXpath))).click().release().build().perform();
					//action.clickAndHold(VehicleModelHdr).moveToElement(driver.findElement(By.xpath(modelXpath))).click().release().build().perform();
					//action.clickAndHold(VehicleYearHdr);
					//driver.findElement(year2016);
					//action.moveToElement(driver.findElement(By.xpath(yearXpath))).click();
					
					//AddVehicleHdrBtn.sendKeys(Keys.ENTER);
					//test.log(LogStatus.INFO, "Vehicle "+vehicle+" added from Header");
				
			}
			if (RunConfig.BROWSER.equals("Chrome")){
				selectYearHdr(year);
				selectMakeHdr(make);
				selectModelHdr(model);			
				AddVehicleHdrBtn.sendKeys(Keys.ENTER);
			}			
		
			test.log(LogStatus.PASS, "PASS: Vehicle "+vehicle+" added from Header");
			HomePage homePage = new HomePage(driver, test);
			PageFactory.initElements(driver, homePage);		
			return homePage;
		}catch(Exception e){				
			System.out.println("FAIL: Failed to add Vehicle from Header");
			test.log(LogStatus.FAIL, "FAIL: Failed to add Vehicle from Header");	
			Assert.fail();
			HomePage homePage = new HomePage(driver, test);
			PageFactory.initElements(driver, homePage);		
			return homePage;
		}*/
		
	}	
	
	public HomePage addVehicleFromHdrByVin(String vehicleVin) throws InterruptedException{			
		
		
		String selectVehicleHdrXpath = "(//div[@class='my-menu-value'])[1]";
		String addNewVehicleHdrXpath = "//a[@class='button-primary yellow add-new-vehicle']";		
		
		try{				
			
			WebElement SelectVehicleHdrEle = (new WebDriverWait(driver, 5))
					  .until(ExpectedConditions.elementToBeClickable(By.xpath(selectVehicleHdrXpath)));
			
			if(SelectVehicleHdrEle.isDisplayed()){				
					SelectVehicleHdrEle.click();
					TimeUnit.SECONDS.sleep(1);
					
					try{
						WebElement AddNewVehicleHdrEle = (new WebDriverWait(driver, 5))
								  .until(ExpectedConditions.elementToBeClickable(By.xpath(addNewVehicleHdrXpath)));
						
						
						if(AddNewVehicleHdrEle.isDisplayed()){
							System.out.println("AddNewVehicleHdrEle is DISPLAYED");
							AddNewVehicleHdrEle.click();	
							TimeUnit.SECONDS.sleep(2);
							
						}
						
					}catch(Exception e){
						System.out.println("AddNewVehicleHdrEle is not displayed.We can ignore this exception");
					}
								
					if (RunConfig.BROWSER.equals("IE")|| RunConfig.BROWSER.equals("Firefox")){				 
							
						//to do
					}
					
					if (RunConfig.BROWSER.equals("Chrome")){
						
						try{
							WebElement AddNewVehicleFromHdrByVinEle = (new WebDriverWait(driver, 10))
									  .until(ExpectedConditions.elementToBeClickable(AddNewVehicleFromHdrByVinBy));
							
							
							if(AddNewVehicleFromHdrByVinEle.isDisplayed()){
								System.out.println("AddNewVehicleFromHdrByVinEle is DISPLAYED");
								AddNewVehicleFromHdrByVinEle.click();	
								TimeUnit.SECONDS.sleep(2);
								
							}
							
						}catch(Exception e){
							System.out.println("AddNewVehicleFromHdrByVinEle is not displayed");
						}
						try{
							WebElement VinTextBoxEle = (new WebDriverWait(driver, 10))
									  .until(ExpectedConditions.elementToBeClickable(VinTextBoxBy));
							
							
							if(VinTextBoxEle.isDisplayed()){
								System.out.println("VinTextBoxEle is DISPLAYED");
								VinTextBoxEle.sendKeys(vehicleVin);								
								
							}
							
						}catch(Exception e){
							System.out.println("AddNewVehicleFromHdrByVinEle is not displayed");
						}
									
						driver.findElement(AddVehicleVinBtnBy).sendKeys(Keys.ENTER);
					}									
					test.log(LogStatus.PASS, "PASS: Vehicle with "+vehicleVin+" added from Header");
				}			
			
		}catch(Exception e){				
			System.out.println("FAIL: Failed to add Vehicle from Header using VIN# "+vehicleVin);
			test.log(LogStatus.FAIL, "FAIL: Failed to add Vehicle from Header using VIN# "+vehicleVin);	
			Assert.fail();				
			
		}
		HomePage homePage = new HomePage(driver, test);
		PageFactory.initElements(driver, homePage);		
		return homePage;
	}
	
	@FindBy(xpath="//li[@class='fitment-modal-tab-vin']")
	public WebElement AddVehicleByVINTab;
	
	
	/**********************My Store/Change Store Elements*******************************************/
	
	@FindBy(xpath="//*[@id='defaultStoreName']")
	public WebElement ChangeStoreHdr;
	
	@FindBy(xpath="//*[@id='defaultStoreName']/span")
	public WebElement DefaultStoreName;
	
	static final By defaultStoreNameBy = By.xpath("//*[@id='defaultStoreName']/span");
	
	@FindBy(xpath="//*[@id='change-my-store-link']")
	public WebElement ChangeMyStoreLnk;
	
	/*@FindBy(xpath="//input[@class='search-input'][@id='store-search-input']")
	public WebElement StoreSearchBox;*/
	
	/*public static final By storeSearchBoxBy = By.xpath("//input[@class='search-input'][@id='store-search-input']");
	
	@FindBy(xpath="//*[@id='store-search-button']")
	public WebElement StoreSearchBtn;
	
	@FindBy(xpath="(//*[@id='header-make-my-store'])[1]")	
	public WebElement MakeMyStoreBtn;
	
	public static final By makeMyStoreBtnBy = By.xpath("(//*[@id='header-make-my-store'])[1]");*/
	
	@FindBy(xpath="//div[@class='address-2']")	
	public WebElement StoreAddressTxt;
	
	/**********************mini cart Elements*******************************************/
	@FindBy(xpath="//span[@class='icon-napa-cart']")	
	public WebElement MiniCart;
	
	@FindBy(xpath="//div[@class='my-menu-icon-number']")	
	public WebElement MiniCartIconNumber;
	
	@FindBy(xpath="//a[@class='button-primary yellow'][contains(text(),'View Shopping Cart')]")	
	public WebElement viewShoppingCartMini;
	
	public static final By viewShoppingCartMiniBy = By.xpath("//a[@class='button-primary yellow'][contains(text(),'View Shopping Cart')]");
	
	public static final By deleteOrderBy = By.xpath("//div[@class='order-delete-icon']");
	public static final By napaLogoBy = By.xpath("//div[@class='header-branding-logo']");
	
	@FindBy(xpath="//div[@class='header-branding-logo']")	
	public WebElement NapaLogoHdr;
	
	public void emptyShoppingCart(){
		
		try{				
			if(MiniCartIconNumber.isDisplayed()){			
				String totalItems = MiniCartIconNumber.getText().toString();
				System.out.println("totalItems ="+totalItems);
				
				if(!totalItems.contains("0")){
					//Actions action = new Actions(driver);
					//action.clickAndHold(MiniCart).build().perform();
					//action.clickAndHold(MiniCart).moveToElement(driver.findElement(viewShoppingCartMiniBy)).click().release().build().perform();
					MiniCartIconNumber.click();
					
					WebElement viewShoppingCartMiniEle = (new WebDriverWait(driver, 10))
							  .until(ExpectedConditions.elementToBeClickable((viewShoppingCartMiniBy)));
					
					while(!viewShoppingCartMiniEle.isDisplayed()){
						MiniCartIconNumber.click();
					}
					viewShoppingCartMiniEle.sendKeys(Keys.ENTER);
					/*viewShoppingCartMini.click();
					while(viewShoppingCartMini.isDisplayed()){
						viewShoppingCartMini.click();
					}*/
					//MiniCartIconNumber.click();
					/*if(viewShoppingCartMini.isDisplayed()){
						viewShoppingCartMini.click();
					}*/
					//viewShoppingCartMini.click();
					List<WebElement> DeleteOrderElements = driver.findElements(deleteOrderBy);
					//System.out.println("DeleteOrderElements.size before for loop ="+DeleteOrderElements.size());
					for(int i=0;i<DeleteOrderElements.size();i=i++){
						//System.out.println("DeleteOrderElements.size inside for loop ="+DeleteOrderElements.size());
						if(DeleteOrderElements.get(i).isDisplayed()){
							DeleteOrderElements.get(i).click();
						}
						Thread.sleep(2000);
						
						/*WebElement deleteEle = (new WebDriverWait(driver, 20))
								  .until(ExpectedConditions.elementToBeClickable((deleteOrderBy)));
										 
						DeleteOrderElements = driver.findElements(deleteOrderBy);
						System.out.println("DeleteOrderElements.size after first delete ="+DeleteOrderElements.size());*/
					}
					driver.findElement(napaLogoBy).click();
				}
			}
			
		}catch(Exception e){				
			//test.log(LogStatus.FAIL, "FAIL:Exception occured while emptying Cart");			
			
		}
		
		try{				
			if(MiniCartIconNumber.isDisplayed()){			
				String totalItems = MiniCartIconNumber.getText().toString();
				System.out.println("totalItems ="+totalItems);
				
				if(!totalItems.contains("0")){
					//Actions action = new Actions(driver);
					//action.clickAndHold(MiniCart).build().perform();
					//action.clickAndHold(MiniCart).moveToElement(driver.findElement(viewShoppingCartMiniBy)).click().release().build().perform();
					MiniCartIconNumber.click();
					
					WebElement viewShoppingCartMiniEle = (new WebDriverWait(driver, 10))
							  .until(ExpectedConditions.elementToBeClickable((viewShoppingCartMiniBy)));
					
					while(!viewShoppingCartMiniEle.isDisplayed()){
						MiniCartIconNumber.click();
					}
					viewShoppingCartMiniEle.sendKeys(Keys.ENTER);
					
					/*viewShoppingCartMini.click();
					while(viewShoppingCartMini.isDisplayed()){
						viewShoppingCartMini.click();
					}*/
					//MiniCartIconNumber.click();
					/*if(viewShoppingCartMini.isDisplayed()){
						viewShoppingCartMini.click();
					}*/
					//viewShoppingCartMini.click();
					List<WebElement> DeleteOrderElements = driver.findElements(deleteOrderBy);
					//System.out.println("DeleteOrderElements.size before for loop ="+DeleteOrderElements.size());
					for(int i=0;i<DeleteOrderElements.size();i=i++){
						//System.out.println("DeleteOrderElements.size inside for loop ="+DeleteOrderElements.size());
						if(DeleteOrderElements.get(i).isDisplayed()){
							DeleteOrderElements.get(i).click();
						}
						Thread.sleep(5000);
						
						/*WebElement deleteEle = (new WebDriverWait(driver, 20))
								  .until(ExpectedConditions.elementToBeClickable((deleteOrderBy)));
										 
						DeleteOrderElements = driver.findElements(deleteOrderBy);
						System.out.println("DeleteOrderElements.size after first delete ="+DeleteOrderElements.size());*/
					}
					driver.findElement(napaLogoBy).click();
				}
			}
			
		}catch(Exception e){				
			System.out.println("Trying to empty Cart 2nd time.We can ignore this exception");			
			
		}
	}
	
	
	/*public void changeStore(String newStoreZip, String storeAddress) throws InterruptedException{
		//ChangeStoreHdr.click();
		String mainWindow = driver.getWindowHandle();
		String defaultStore="none";
		
		if(DefaultStoreName.isDisplayed()){
			//DefaultStoreName.click();
			defaultStore = DefaultStoreName.getText().toString();
		}
		//String defaultStore = DefaultStoreName.getText().toString();
		System.out.println("defaultStore = "+defaultStore);
		
		if (!defaultStore.contains(storeAddress)){
			
			if (ChangeStoreHdr.isDisplayed()){
				ChangeStoreHdr.click();
				System.out.println("Change Store Clicked");
				Thread.sleep(2000);
				if (ChangeMyStoreLnk.isDisplayed()){
					ChangeMyStoreLnk.click();
					System.out.println("Change my Store link Clicked");
					
					String changeStoreWindow = driver.getWindowHandle();
					driver.switchTo().window(changeStoreWindow);
					
					//driver.findElement(storeSearchBoxBy).sendKeys(Keys.BACK_SPACE);
					//driver.findElement(storeSearchBoxBy).clear();
					//driver.findElement(storeSearchBoxBy).sendKeys(newStoreZip);
					if (StoreSearchBox.isDisplayed()){
						StoreSearchBox.clear();
						StoreSearchBox.sendKeys(newStoreZip);
						if (StoreSearchBtn.isDisplayed()){
							StoreSearchBtn.click();
							//Thread.sleep(2000);
							WebElement makeMyStoreBtnEle = (new WebDriverWait(driver, 20))
									  .until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='header-make-my-store'])[1]")));
							makeMyStoreBtnEle.click();
							//MakeMyStoreBtn.click();
							String currentWindow = driver.getWindowHandle();
							driver.switchTo().window(currentWindow);
							
							WebDriverWait wait = new WebDriverWait(driver, 60);
							wait.until(ExpectedConditions.visibilityOfElementLocated(makeMyStoreBtnBy));
							
							if(driver.findElement(makeMyStoreBtnBy).isDisplayed()){
								driver.findElement(makeMyStoreBtnBy).sendKeys(Keys.ENTER);
								
							}
							
							//Thread.sleep(3000);
							WebElement NewDefaultStore = driver.findElement(defaultStoreNameBy);
							if (NewDefaultStore.getText().contains(storeAddress)){
								System.out.println("Store Localized successfully");
								test.log(LogStatus.INFO, "Store localized to "+storeAddress+" "+newStoreZip);
								
							}else{
								test.log(LogStatus.FAIL, "Failed to localize Store to "+storeAddress+" "+newStoreZip);
							}
							
						}else{
							test.log(LogStatus.FAIL, "Store Search button not found");
						}
					}else{
						test.log(LogStatus.FAIL, "Store Search box not found");
					}
				}else{
					test.log(LogStatus.FAIL, "Change My Store Link not found");
				}
			}else{
				test.log(LogStatus.FAIL, "Change Store in Header not found");
			}
					
			
		}
		else {
			test.log(LogStatus.INFO, "Store is localized to "+storeAddress+" "+newStoreZip);
		}
		
	}*/
	/*public HomePage changeStore(String newStoreZip, String storeAddress) throws InterruptedException{
		//ChangeStoreHdr.click();
		String mainWindow = driver.getWindowHandle();
		String defaultStore="none";
		
		if(DefaultStoreName.isDisplayed()){
			//DefaultStoreName.click();
			defaultStore = DefaultStoreName.getText().toString();
		}
		//String defaultStore = DefaultStoreName.getText().toString();
		System.out.println("defaultStore = "+defaultStore);
		
		if (!defaultStore.contains(storeAddress)){
			
			if (ChangeStoreHdr.isDisplayed()){
				ChangeStoreHdr.click();
				System.out.println("Change Store Clicked");
				Thread.sleep(2000);
				if (ChangeMyStoreLnk.isDisplayed()){
					ChangeMyStoreLnk.click();
					System.out.println("Change my Store link Clicked");
					
					String changeStoreWindow = driver.getWindowHandle();
					driver.switchTo().window(changeStoreWindow);
					
					//driver.findElement(storeSearchBoxBy).sendKeys(Keys.BACK_SPACE);
					//driver.findElement(storeSearchBoxBy).clear();
					//driver.findElement(storeSearchBoxBy).sendKeys(newStoreZip);
					if (StoreSearchBox.isDisplayed()){
						StoreSearchBox.clear();
						StoreSearchBox.sendKeys(newStoreZip);
					}
					if (StoreSearchBtn.isDisplayed()){
						StoreSearchBtn.click();
					}	
					if (MakeMyStoreBtn.isDisplayed()){
						MakeMyStoreBtn.click();
					}else if (MakeMyStoreBtn.isEnabled()){
						MakeMyStoreBtn.click();
					}		
							
					WebDriverWait wait = new WebDriverWait(driver, 60);
					wait.until(ExpectedConditions.visibilityOfElementLocated(makeMyStoreBtnBy));
					
					if(driver.findElement(makeMyStoreBtnBy).isDisplayed()){
						driver.findElement(makeMyStoreBtnBy).sendKeys(Keys.ENTER);
						
					}
					
					WebElement
					if(driver.findElement(By.xpath("(//*[@id='header-make-my-store'])[1]")).isDisplayed()){
						driver.findElement(By.xpath("(//*[@id='header-make-my-store'])[1]")).sendKeys(Keys.ENTER);
						
					}
							
				  }
				}				
		}
		HomePage homePage = new HomePage(driver, test);
		PageFactory.initElements(driver, homePage);
		return homePage;
	}*/
	/*****************************************************************/
	
	@FindBy(id="textSearch")
	public WebElement SearchBox;	
	
	@FindBy(xpath="//*[@id='search_form']/div[2]/div[1]")
	public WebElement InAllProducts;
	
	@FindBy(xpath="//*[@id='search_form']/div[2]/div[2]")
	public WebElement TAVehicleSpecific;
	
	//@FindBy(xpath="//div[@class='search-typeahead-option search-tools search-typeahead-active']/span[@class='search-typeahead-type']")
	//public WebElement TypeAheadToolsEq;
	
	@FindBy(xpath="//*[@id='search_form']/div[2]/div[5]")
	public WebElement TypeAheadToolsEq;
	
	public final By TypeAheadToolsEqBy = By.xpath("//div[@class='search-typeahead']/div[@class='search-typeahead-option search-tools search-typeahead-active']");
	
	@FindBy(xpath="//*[@id='search_form']/button")
	public WebElement SearchBtn;
	
	@FindBy(xpath="(//a[@class='navigation-link'])[1]")
	public WebElement PartsAccessoriesDD;
	
	/****************Tools&Equipment Elements*************************************************/
	@FindBy(xpath="(//a[@class='navigation-link'])[2]")
	public WebElement ToolsEquipSuppliesDD;	
	
	@FindBy(xpath="//a[@class='navigation-panel-list-link'][contains(text(),'Cable & Chain')]")
	public WebElement CableChainTELnk;
	
	public static final By cableChainTELnkBy = By.xpath("//a[@class='navigation-panel-list-link'][contains(text(),'Cable & Chain')]");
	
	@FindBy(xpath="(//a[@class='navigation-link'])[3]")
	public WebElement DiscoverLearnDD;
	
	@FindBy(xpath="(//h4[@class='navigation-panel-selector-title'])[1]")
	public WebElement BrowseByVehicle;
	
	@FindBy(xpath="(//h4[@class='navigation-panel-selector-title'])[2]")
	public WebElement BrowseAllParts;
	/******************Browse By Vehicle(BBV) Elements******************************************/
	
		
	
	public void typeInSearch(String text){
		SearchBox.sendKeys(text);
	}
	
	public void clickSearchBtn(){
		SearchBtn.click();
	}
	public HomePage doLogOut(){
		try{
			
			if(MyMenuNameDD.isDisplayed()){
				MyMenuNameDD.click();
			}
			
			WebElement LogoutBtnEle = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[3]/div/div/a")));
			if(LogoutBtnEle.isDisplayed()){
				LogoutBtnEle.click();
				System.out.println("Logout button clicked");
			}
			HomePage homePage = new HomePage(driver, test);
			PageFactory.initElements(driver, homePage);
			return homePage;
			
		}catch(Exception e){
			System.out.println("Exception occured in doLogout() ");
			Assert.fail();
			return null;
		}
		
		//LogOutBtn.click();
	}
	public void clickNameFromHeader(){
		MyMenuNameDD.click();
	}
	
	
	
	/****************Mega Menu-Tools&Equipment Element Methods*************************************************/
	
	public void clkToolsEquipSuppLnk(){
		ToolsEquipSuppliesDD.click();
		
	}
	
	public CategoryPage clickCableChainTELnk() throws InterruptedException{
		
		if (RunConfig.BROWSER.equals("Chrome")){
			
			try{
				WebElement toolsEquipEle = (new WebDriverWait(driver, 10))
						  .until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='navigation-link'])[2]")));
				if(toolsEquipEle.isEnabled()){
					toolsEquipEle.click();
				}
				TimeUnit.SECONDS.sleep(5);
				
			}catch(Exception e){			
				test.log(LogStatus.FAIL, "FAIL:Failed to click tools,equipment&supplies link in Mega menu");
				Assert.fail();
				
			}
			
			try{
				
				WebElement cableChainLnkEle = (new WebDriverWait(driver, 10))
						  .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='navigation-panel-list-link'][contains(text(),'Cable & Chain')]")));
				if(cableChainLnkEle.isEnabled()){
					cableChainLnkEle.click();
				}
				
			}catch(Exception e){			
				test.log(LogStatus.FAIL, "FAIL:Failed to click Cable&Chain link");
				Assert.fail();
				
			}			
			
		}	
		if (RunConfig.BROWSER.equals("IE")){
			try{					
				
				WebElement toolsEquipEle = (new WebDriverWait(driver, 10))
						  .until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='navigation-link'])[2]")));
				
				Actions action = new Actions(driver);
				action.clickAndHold(toolsEquipEle).moveToElement(driver.findElement(cableChainTELnkBy)).release().build().perform();			
				
				WebElement cableChainLnkEle = (new WebDriverWait(driver, 10))
						  .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='navigation-panel-list-link'][contains(text(),'Cable & Chain')]")));
				cableChainLnkEle.click();				
				
			}catch(Exception e){			
				test.log(LogStatus.FAIL, "FAIL:Failed to click Cable&Chain link");
				Assert.fail();				
				
			}
		}
		
		CategoryPage categoryPage = new CategoryPage(driver, test);
		PageFactory.initElements(driver, categoryPage);
		return categoryPage;
	}
	
	
	
	//Header header = new Header(driver)
	
	public LoginPage goToLoginPage(WebElement loginLink){
		
		try{
			loginLink.click();
			loginLink.click();
			try{
				if(LogOutBtn.isDisplayed()){
					LogOutBtn.click();
				}
				if(LogOutBtn.isDisplayed()){
					LogOutBtn.click();
				}
				if(loginLink.isDisplayed()){
					loginLink.click();
				}
				
			}catch(Exception e){
				System.out.println("Exception Message: "+e.getMessage());
			}
			
			LoginPage loginPage = new LoginPage(driver, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;
		}catch(Exception e){
			System.out.println("Exception Message: "+e.getMessage());
			
			return null;
		}
		//myAccountLnk.click();
		
	}
	
	public LoginPage goToLoginPage(){			
		
			if(MyAccountLnk.isDisplayed()){
				MyAccountLnk.click();
			}	
			
			LoginPage loginPage = new LoginPage(driver, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;		
		
	}
	
/*public HomePage signIn(WebElement loginLink){
		
		try{
			if(MyAccountLnk.isDisplayed()){
				MyAccountLnk.click();
			}
			try{
				if(LogOutBtn.isDisplayed()){
					LogOutBtn.click();
				}
				if(LogOutBtn.isDisplayed()){
					LogOutBtn.click();
				}
				if(loginLink.isDisplayed()){
					loginLink.click();
				}
				
			}catch(Exception e){
				System.out.println("Exception Message: "+e.getMessage());
			}
			
			HomePage homePage = new HomePage(driver, test);
			PageFactory.initElements(driver, homePage);
			return homePage;
		}catch(Exception e){
			System.out.println("Exception Occured in signIn() method: "+e.getMessage());			
			return null;
		}
		//myAccountLnk.click();
		
	}*/
	
	public boolean addVehicleFromHeader(){
		
		try{
			
			return true;
		}catch(Exception e){
			System.out.println("Exception Message: "+e.getMessage());
			
			return false;
		}
		//myAccountLnk.click();
		
	}
	
	public HomePage localizeStore(String newStoreAddress, String newStoreZip){
		
		String defaultStore="none";
		String zipXPATH = "//input[@class='search-input'][@id='store-search-input']";
		String searchbtnXPATH="//*[@id='store-search-button']";
		String makeMyStoreXPATH1= "(//a[@class='button-primary yellow select-store'])[1]";
		String makeMyStoreXPATH2= "//a[@class='button-primary yellow select-store'][@data-store-facilityid='99993']";
		String makeMyStoreXPATH="";
		String changeMyStoreXpath="//*[@id='change-my-store-link']";
		
		
		String defaultStoreXpath = "//*[@id='defaultStoreName']";
		
		try{
			WebElement defaultStoreEle = (new WebDriverWait(driver, 40))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(defaultStoreXpath)));
			if(defaultStoreEle.isEnabled()){
				defaultStore = defaultStoreEle.getText().toString();
				System.out.println("found default store text");
			}
			
			System.out.println("defaultStore = "+defaultStore);			
			
			if (!defaultStore.contains(newStoreAddress)){	
				
				if(newStoreAddress.contains("5260C Atlanta Hwy")){
					makeMyStoreXPATH=makeMyStoreXPATH2;
				}else{
					makeMyStoreXPATH=makeMyStoreXPATH1;
				}
								
				ChangeStoreHdr.click();	
				if(!ChangeMyStoreLnk.isDisplayed()){
					ChangeStoreHdr.click();
				}
				System.out.println("change store header clicked");	
				
				WebElement ChangeMyStoreLnkEle = (new WebDriverWait(driver, 20))
						  .until(ExpectedConditions.elementToBeClickable(By.xpath(changeMyStoreXpath)));
				ChangeMyStoreLnkEle.click();				
				System.out.println("change my store link clicked");
				WebElement ZipEle = (new WebDriverWait(driver, 20))
						  .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(zipXPATH)));
				//driver.findElement(By.xpath(zipXPATH)).clear();
				ZipEle.clear();
				System.out.println("zip cleared");
				
				//TimeUnit.SECONDS.sleep(3);
				//driver.findElement(By.xpath(zipXPATH)).sendKeys(newStoreZip);
				ZipEle.sendKeys(newStoreZip);
				System.out.println("new zip entered");
				//Thread.sleep(2000);
				//TimeUnit.SECONDS.sleep(20);
				//driver.findElement(By.xpath(zipXPATH)).sendKeys(Keys.ENTER);				
				
				//driver.findElement(By.xpath(searchbtnXPATH)).sendKeys(Keys.ENTER);
				
				System.out.println("before clicking search button");
				//driver.findElement(By.xpath(searchbtnXPATH)).click();
				WebElement SearchBtnEle = (new WebDriverWait(driver, 20))
						  .until(ExpectedConditions.elementToBeClickable(By.xpath(searchbtnXPATH)));
				
				SearchBtnEle .click();
				//Thread.sleep(3000);
				System.out.println("after clicking search button");
				TimeUnit.SECONDS.sleep(4);
				
				
				
				WebElement makeMyStoreBtnEle = (new WebDriverWait(driver, 20))
						  .until(ExpectedConditions.elementToBeClickable(By.xpath(makeMyStoreXPATH)));
				if(makeMyStoreBtnEle.isDisplayed()){
					makeMyStoreBtnEle.click();
					//TimeUnit.SECONDS.sleep(3);
					System.out.println("makemystore button was clicked 1st time");
				}	
				/*try{
					if(makeMyStoreBtnEle.isDisplayed()){
						makeMyStoreBtnEle.click();
						System.out.println("makemystore button was clicked on 2nd attempt");
					}	
					
				}catch(Exception e){
					System.out.println("Exception Occured while trying to click makemystore button 2nd time");
					
					
				}*/
			}
			test.log(LogStatus.PASS, "PASS:Store Localized to: "+newStoreAddress+","+newStoreZip);
			
			HomePage homePage = new HomePage(driver, test);
			PageFactory.initElements(driver, homePage);		
			return homePage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in localizeStore(): ");
			test.log(LogStatus.FAIL, "FAIL:Store Localization FAILED. ");
			Assert.fail();
			
			HomePage homePage = new HomePage(driver, test);
			PageFactory.initElements(driver, homePage);		
			return homePage;		
			
		}		

	}
	
	public boolean localizeStoreFromHeader(String newStoreAddress, String newStoreZip){
		String defaultStore="none";
		String zipXPATH = "//input[@class='search-input'][@id='store-search-input']";
		String searchbtnXPATH="//*[@id='store-search-button']";
		String makeMyStoreXPATH1= "(//a[@class='button-primary yellow select-store'])[1]";
		String makeMyStoreXPATH2= "(//a[@class='button-primary yellow select-store'])[2]";
		String makeMyStoreXPATH="";
		
		try{
			if(DefaultStoreName.isDisplayed()){				
				defaultStore = DefaultStoreName.getText().toString();
			}
			//String defaultStore = DefaultStoreName.getText().toString();
			System.out.println("defaultStore = "+defaultStore);			
			
			if (!defaultStore.contains(newStoreAddress)){
				//changeStoreWinPage = homePage.header.clkMyStoreHdr();
				//selectStorePage = changeStoreWinPage.ClkChangeMyStore();
				//homePage = selectStorePage.clkMakeMyStore(data.get("StoreZip"), data.get("StoreAddress"));
				ChangeStoreHdr.click();	
				if(!ChangeMyStoreLnk.isDisplayed()){
					ChangeStoreHdr.click();
				}
				ChangeMyStoreLnk.click();
				/*String zipXPATH = "//input[@class='search-input'][@id='store-search-input']";
				String searchbtnXPATH="//*[@id='store-search-button']";
				String makeMyStoreXPATH1= "(//a[@class='button-primary yellow select-store'])[1]";
				String makeMyStoreXPATH2= "(//a[@class='button-primary yellow select-store'])[2]";
				String makeMyStoreXPATH="";*/
				//selectStoreNewPage = selectStorePage.clkSearchStore(data.get("StoreZip"), data.get("StoreAddress"));				
				//homePage = selectStoreNewPage.clkMakeMyStore();
				driver.findElement(By.xpath(zipXPATH)).clear();
				driver.findElement(By.xpath(zipXPATH)).sendKeys(newStoreZip);
				driver.findElement(By.xpath(zipXPATH)).sendKeys(Keys.ENTER);
				//driver.findElement(By.xpath(searchbtnXPATH)).click();
				if(newStoreAddress.contains("5260C")){
					makeMyStoreXPATH=makeMyStoreXPATH2;
				}else{
					makeMyStoreXPATH=makeMyStoreXPATH1;
				}
				//Scroll till element.
				JavascriptExecutor je = (JavascriptExecutor) driver;
				WebElement element = driver.findElement(By.xpath(makeMyStoreXPATH));
				//je.executeScript("arguments[0].scrollIntoView(true);",element);
				//je.wait();
				//je.executeScript("arguments[0].click();", element);
				
				if(element.isDisplayed()){
					//je.executeScript("arguments[0].scrollIntoView(true);",element);
					element.sendKeys(Keys.ENTER);
				}
				
				if(driver.findElement(By.xpath(makeMyStoreXPATH)).isDisplayed()){
					driver.findElement(By.xpath(makeMyStoreXPATH)).sendKeys(Keys.ENTER);;
				}
				
				/*if(driver.findElement(By.xpath(makeMyStoreXPATH)).isDisplayed()){
					driver.findElement(By.xpath(makeMyStoreXPATH)).sendKeys(Keys.ENTER);
				}
				
				if(driver.findElement(By.xpath(makeMyStoreXPATH)).isDisplayed()){
					driver.findElement(By.xpath(makeMyStoreXPATH)).sendKeys(Keys.ENTER);;
				}*/
				
				//System.out.println("---------------------");				
				//homePage = selectStorePage.clkMakeMyStore(data.get("StoreZip"), data.get("StoreAddress"));
			}
			
			return true;
		}catch(Exception e){
			System.out.println("Exception Message: "+e.getMessage());
			
			return false;
		}
		//myAccountLnk.click();
		
	}
	
	public ChangeStoreWinPage clkMyStoreHdr(){
		if (ChangeStoreHdr.isDisplayed()){
			ChangeStoreHdr.click();
		}
		
		ChangeStoreWinPage changeStoreWinPage = new ChangeStoreWinPage(driver, test);
		PageFactory.initElements(driver, changeStoreWinPage);		
		return changeStoreWinPage;
	}
	
	public PDPpage searchAllProductsWithPart(String searchItem) throws InterruptedException{
		try{
			SearchBox.sendKeys(searchItem);			
			TimeUnit.SECONDS.sleep(3);
			while(!InAllProducts.isDisplayed()){
				SearchBox.clear();
				SearchBox.sendKeys(searchItem);
			}
			InAllProducts.click();
			TimeUnit.SECONDS.sleep(3);
			PDPpage pDPpage = new PDPpage(driver, test);
			PageFactory.initElements(driver, pDPpage);		
			return pDPpage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in searchAllProducts(String searchItem): "+e.getMessage());
			return null;
		}
	}
	
	public PDPpage searchVehSpecificWithPart(String searchItem) throws InterruptedException{
		try{
			SearchBox.sendKeys(searchItem);			
			TimeUnit.SECONDS.sleep(3);
			while(!InAllProducts.isDisplayed()){
				SearchBox.clear();
				SearchBox.sendKeys(searchItem);
			}
			TAVehicleSpecific.click();
			TimeUnit.SECONDS.sleep(3);
			PDPpage pDPpage = new PDPpage(driver, test);
			PageFactory.initElements(driver, pDPpage);		
			return pDPpage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in searchAllProducts(String searchItem): "+e.getMessage());
			return null;
		}
	}
	public PLPGlobalKeywordSearchpage searchAllProductsWithKeyword(String keyword) throws InterruptedException{
		try{	
			if(SearchBox.isDisplayed()){
				SearchBox.sendKeys(keyword);
				
			}
			/*WebElement GlobarSearchEle = (new WebDriverWait(driver, 40))
					  .until(ExpectedConditions.presenceOfElementLocated(By.id("textSearch")));
			if(GlobarSearchEle.isDisplayed()){
				System.out.println("Global Search bar for Keyword is displayed");
				GlobarSearchEle.sendKeys(keyword);
			}
					*/	
			Thread.sleep(2000);
			while(!InAllProducts.isDisplayed()){
				SearchBox.clear();
				SearchBox.sendKeys(keyword);
			}
			/*while(!InAllProducts.isDisplayed()){
				GlobarSearchEle.clear();
				GlobarSearchEle.sendKeys(keyword);
			}*/
			InAllProducts.click();
			
			PLPGlobalKeywordSearchpage pLPGlobalKeywordSearchpage= new PLPGlobalKeywordSearchpage(driver, test);
			PageFactory.initElements(driver, pLPGlobalKeywordSearchpage);		
			return pLPGlobalKeywordSearchpage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in searchAllProductsWithKeyword(String searchItem): ");
			
			return null;
		}	
		
	}
	
	public PLPGlobalVehicleSelectedpage searchVehSpecificWithKeyword(String keyword) throws InterruptedException{
		try{	
			if(SearchBox.isDisplayed()){
				SearchBox.sendKeys(keyword);
				
			}
			/*WebElement GlobarSearchEle = (new WebDriverWait(driver, 40))
					  .until(ExpectedConditions.presenceOfElementLocated(By.id("textSearch")));
			if(GlobarSearchEle.isDisplayed()){
				System.out.println("Global Search bar for Keyword is displayed");
				GlobarSearchEle.sendKeys(keyword);
			}
					*/	
			Thread.sleep(2000);
			while(!InAllProducts.isDisplayed()){
				SearchBox.clear();
				SearchBox.sendKeys(keyword);
			}
			/*while(!InAllProducts.isDisplayed()){
				GlobarSearchEle.clear();
				GlobarSearchEle.sendKeys(keyword);
			}*/
			TAVehicleSpecific.click();
			
			PLPGlobalVehicleSelectedpage pLPGlobalVehicleSelectedpage= new PLPGlobalVehicleSelectedpage(driver, test);
			PageFactory.initElements(driver, pLPGlobalVehicleSelectedpage);		
			return pLPGlobalVehicleSelectedpage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in searchVehSpecificWithKeyword(String keyword): "+e.getMessage());
			return null;
		}	
		
	}
	
	public PLPGlobalVehicleSelectedpage searchKeyGlobalBarToolsEq(String keyword) throws InterruptedException{
		try{	
			if(SearchBox.isDisplayed()){
				SearchBox.sendKeys(keyword);
				
			}			
					
			Thread.sleep(2000);
			while(!InAllProducts.isDisplayed()){
				SearchBox.clear();
				SearchBox.sendKeys(keyword);
			}
			
			
			/*WebElement TypeAheadToolsEqEle = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.elementToBeClickable(TypeAheadToolsEqBy));
			if(TypeAheadToolsEqEle.isDisplayed()){
				TypeAheadToolsEq.click();
				System.out.println("Type Ahead Tools Equipment clicked");
			}*/
			
			//TAVehicleSpecific.click();
			TypeAheadToolsEq.click();
			
			PLPGlobalVehicleSelectedpage pLPGlobalVehicleSelectedpage= new PLPGlobalVehicleSelectedpage(driver, test);
			PageFactory.initElements(driver, pLPGlobalVehicleSelectedpage);		
			return pLPGlobalVehicleSelectedpage;
			
		}catch(Exception e){
			System.out.println("Exception Occured in searchVehSpecificWithKeyword(String keyword): "+e.getMessage());
			return null;
		}	
		
	}
	
	
	
}
