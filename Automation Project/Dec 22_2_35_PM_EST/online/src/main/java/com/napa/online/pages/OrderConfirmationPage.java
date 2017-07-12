package com.napa.online.pages;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.napa.online.base.BasePage;
import com.napa.online.util.RunConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class OrderConfirmationPage extends BasePage{
	
	public OrderConfirmationPage(WebDriver driver, ExtentTest test){
		super(driver,test);		
	}
	
	/////////////////////////BOL elements////////////////////////////////
	@FindBy(xpath="//div[@class='order-confirm-content-header']/h3")
	public WebElement OrderTypeHeader;
	
	@FindBy(xpath="//div[@class='shipping-info']")
	public WebElement ShippingInfo;
	
	@FindBy(xpath="//li[1]/div[@class='amount']")
	public WebElement BOLSubtotal;
	
	@FindBy(xpath="//li[3]/div[@class='amount']")
	public WebElement ShippingCharge;
	
	@FindBy(xpath="//li[4]/div[@class='amount']")
	public WebElement FinalTotalBOL;
	
	/////////////////////////////////////////////////////////////////
	
	
	
	@FindBy(xpath="//h5[@class='checkout-step-title']")
	public WebElement OrderSubmittedTxt;
	
	public static final By orderSubmittedBy = By.xpath("//h5[@class='checkout-step-title']");
	
	@FindBy(xpath="//h3[contains(text(),'Reserve Online Order Details')]")
	public WebElement ROLOrderDetailsTxt;
	
	@FindBy(xpath="(//div[@class='order-confirm-content-number'])[1]")
	public WebElement OrderNumberTxt;	
	
	public final By OrderNumberTxtBy = By.xpath("(//div[@class='order-confirm-content-number'])[1]");
	
	@FindBy(xpath="(//div[@class='product-title'])[1]")
	public WebElement ProductName;
	
	public final By ProductNameBy = By.xpath("(//div[@class='product-title'])[1]");
	
	@FindBy(xpath="(//span[@class='text'])[1]")
	public WebElement PartNumber;
	
	@FindBy(xpath="//div[@class='order-confirm-content-header']/h3")
	public WebElement FulfillmentType;
		
	@FindBy(xpath="//tr[1]/td[3]")
	public WebElement UnitPrice;
	
	@FindBy(xpath="//tr[1]/td[4]")
	public WebElement CorePrice;
	
	@FindBy(xpath="//tr[1]/td[5]")//use attribute "value" to get text
	public WebElement Qty;
	
	@FindBy(xpath="//tr[1]/td[6]")
	public WebElement Subtotal;
	
	@FindBy(xpath="//td[@class='discount']/div[@class='amount']")
	public WebElement Discount;
	
	@FindBy(xpath="//td[@class='total-price']/div[@class='amount']")
	public WebElement ItemTotal;
	
	@FindBy(xpath="//div[@class='grid-10']")
	public WebElement PickUpLocation;
	
	@FindBy(xpath="//div[@class='grid-3']")
	public WebElement PickUpPerson;
	
	//////////////////////for 2 products added to 2 different stores////////////////////////////
	
	@FindBy(xpath="(//div[@class='order-confirm-content-number'])[1]")
	public WebElement OrderNumDiffStr1;
	
	@FindBy(xpath="(//div[@class='order-confirm-content-number'])[2]")
	public WebElement OrderNumDiffStr2;
	
	@FindBy(xpath="(//div[@class='product-title'])[1]")
	public WebElement ProductDiffStr1;
	
	@FindBy(xpath="(//div[@class='product-title'])[2]")
	public WebElement ProductDiffStr2;
	
	@FindBy(xpath="(//tr/td[2]/div/div[2]/span[2])[1]")
	public WebElement PartDiffStr1;
	
	@FindBy(xpath="(//tr/td[2]/div/div[2]/span[2])[2]")
	public WebElement PartDiffStr2;	
	
	@FindBy(xpath="(//tr/td[3])[1]")
	public WebElement UnitPriceDiffStr1;
	
	@FindBy(xpath="(//tr/td[3])[2]")
	public WebElement UnitPriceDiffStr2;
	
	@FindBy(xpath="(//tr/td[4])[1]")
	public WebElement CorePriceDiffStr1;
	
	@FindBy(xpath="(//tr/td[4])[2]")
	public WebElement CorePriceDiffStr2;
	
	@FindBy(xpath="(//tr/td[5])[1]")
	public WebElement QtyDiffStr1;
	
	@FindBy(xpath="(//tr/td[5])[2]")
	public WebElement QtyDiffStr2;
	
	@FindBy(xpath="(//tr/td[6])[1]")
	public WebElement SubtotalDiffStr1;
	
	@FindBy(xpath="(//tr/td[6])[2]")
	public WebElement SubtotalDiffStr2;
	
	@FindBy(xpath="(//tr/td[7])[1]")
	public WebElement DiscountDiffStr1;
	
	@FindBy(xpath="(//tr/td[7])[2]")
	public WebElement DiscountDiffStr2;
	
	@FindBy(xpath="(//tr/td[8])[1]")
	public WebElement ItemTotalDiffStr1;
	
	@FindBy(xpath="(//tr/td[8])[2]")
	public WebElement ItemTotalDiffStr2;
	
	@FindBy(xpath="(//div[@class='order-confirm-content-header']/h3)[1]")
	public WebElement FulfillmentDiffStr1;
	
	@FindBy(xpath="(//div[@class='order-confirm-content-header']/h3)[2]")
	public WebElement FulfillmentDiffStr2;
	
	@FindBy(xpath="(//div[@class='grid-10'])[1]")
	public WebElement PickUpLocationDiffStr1;
	
	@FindBy(xpath="(//div[@class='grid-10'])[2]")
	public WebElement PickUpLocationDiffStr2;
	
	@FindBy(xpath="(//div[@class='grid-3'])[1]")
	public WebElement PickUpPersonDiffStr1;
	
	@FindBy(xpath="(//div[@class='grid-3'])[2]")
	public WebElement PickUpPersonDiffStr2;
	
	@FindBy(xpath="(//li[1]/div[@class='amount'])[1]")
	public WebElement ROLSubtotalDiffStr1;
	
	@FindBy(xpath="(//li[1]/div[@class='amount'])[2]")
	public WebElement ROLSubtotalDiffStr2;
	
	@FindBy(xpath="(//li[2]/div[@class='amount'])[1]")
	public WebElement TaxDiffStr1;
	
	@FindBy(xpath="(//li[2]/div[@class='amount'])[2]")
	public WebElement TaxDiffStr2;
	
	@FindBy(xpath="(//li[3]/div[@class='amount'])[1]")
	public WebElement FinalTotalDiffStr1;
	
	@FindBy(xpath="(//li[3]/div[@class='amount'])[2]")
	public WebElement FinalTotalDiffStr2;
	
	@FindBy(xpath="(//div[@class='amount discount'])[1]")
	public WebElement YouSavedDiffStr1;
	
	@FindBy(xpath="(//div[@class='amount discount'])[2]")
	public WebElement YouSavedDiffStr2;
	
	
	/////////////////////////////////////////////////////////////////////
	
	//////////////////////for same store and 2 products////////////////////
	@FindBy(xpath="(//div[@class='product-title'])[2]")
	public WebElement ProductName1;
	
	@FindBy(xpath="(//div[@class='product-title'])[1]")
	public WebElement ProductName2;
		
	public final By ProductName1By = By.xpath("(//div[@class='product-title'])[2]");	
	
	@FindBy(xpath="//tr[2]/td[2]/div/div[2]/span[2]")
	public WebElement PartNumber1;
	
	@FindBy(xpath="//tr[1]/td[2]/div/div[2]/span[2]")
	public WebElement PartNumber2;
	
	
	@FindBy(xpath="//tr[2]/td[3]")
	public WebElement UnitPrice1;
	
	@FindBy(xpath="//tr[1]/td[3]")
	public WebElement UnitPrice2;
	
	
	@FindBy(xpath="//tr[2]/td[4]")
	public WebElement CorePrice1;
	
	@FindBy(xpath="//tr[1]/td[4]")
	public WebElement CorePrice2;
	
	
	@FindBy(xpath="//tr[2]/td[5]")//use attribute "value" to get text
	public WebElement Qty1;
	
	@FindBy(xpath="//tr[1]/td[5]")//use attribute "value" to get text
	public WebElement Qty2;	
	
	@FindBy(xpath="//tr[2]/td[6]")
	public WebElement Subtotal1;
	
	@FindBy(xpath="//tr[1]/td[6]")
	public WebElement Subtotal2;	
	
	
	@FindBy(xpath="//tr[2]/td[7]/div[1]")
	public WebElement Discount1;
	
	@FindBy(xpath="//tr[1]/td[7]/div[1]")
	public WebElement Discount2;
	
	
	@FindBy(xpath="(//td[@class='total-price']/div[@class='amount'])[2]")
	public WebElement ItemTotal1;
	
	@FindBy(xpath="(//td[@class='total-price']/div[@class='amount'])[1]")
	public WebElement ItemTotal2;	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		
	@FindBy(xpath="//li[1]/div[@class='amount']")
	public WebElement ROLSubtotal;
	
	@FindBy(xpath="//li[2]/div[@class='amount']")
	public WebElement Tax;
	
	public final By TaxBy = By.xpath("//li[2]/div[@class='amount']");
	
	@FindBy(xpath="//li[3]/div[@class='amount']")
	public WebElement FinalTotal;
	
	@FindBy(xpath="//div[@class='amount discount']")
	public WebElement YouSaved;
	
	public boolean isOrderPlaced(){	
			 
			boolean orderPlaced = false;				
			
			try {
				//WebDriverWait wait = new WebDriverWait(driver, 2);
				//webElement = wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
				
				if (OrderSubmittedTxt.getText().toString().contains("Order Submitted")){
					RunConfig.ORDER_NUMBER = OrderNumberTxt.getText().toString();
					test.log(LogStatus.PASS, "PASS:Order Placed.Capturing from isOrderPlaced() "+ RunConfig.ORDER_NUMBER);
					System.out.println("PASS:Order Placed. Capturing from isOrderPlaced()"+ RunConfig.ORDER_NUMBER);
					
				} 
				
				orderPlaced = true;
			}catch(Exception e){
				orderPlaced = false;			
			}
			
			return orderPlaced;
			
		}
	
	public Hashtable<String, String> getOrderDetails(){
		
		Hashtable<String, String> orderDetails = new Hashtable<String, String>();
		
		String orderNumber = OrderNumberTxt.getText().toString();
		System.out.println("orderNumber= " + orderNumber);
		
		String productName = ProductName.getText().toString();
		System.out.println("ProductName= " + productName);
		
		String partNumber = PartNumber.getText().toString();
		System.out.println("PartNumber= " + partNumber);
		
		String fulfillmentType = FulfillmentType.getText().toString();
		System.out.println("FulfillmentType= " + fulfillmentType);
		
		String unitPrice = UnitPrice.getText().toString();
		System.out.println("UnitPrice= " + unitPrice);
		
		String corePrice = CorePrice.getText().toString();
		System.out.println("CorePrice= " + corePrice);
		
		String qty = Qty.getText().toString();
		System.out.println("qty= " + qty);
		
		String subtotal = Subtotal.getText().toString();
		System.out.println("Subtotal= " + subtotal);
		
		String discount = "product not on sale.no discount.";
		if (Discount.isDisplayed()){
			discount = Discount.getText().toString();	
		}		
		System.out.println("Discount= " + discount);
		
		String itemTotal = ItemTotal.getText().toString();
		System.out.println("ItemTotal= " + itemTotal);
		
		String pickUpLocation = PickUpLocation.getText().toString();
		System.out.println("PickUpLocation= " + pickUpLocation);
		
		String pickUpPerson = PickUpPerson.getText().toString();
		System.out.println("PickUpPerson= " + pickUpPerson);
		
		String rOLSubtotal = ROLSubtotal.getText().toString();
		System.out.println("ROLSubtotal= " + rOLSubtotal);
		
		String tax = Tax.getText().toString();
		System.out.println("Tax= " + tax);
		
		String finalTotal = FinalTotal.getText().toString();
		System.out.println("FinalTotal= " + finalTotal);
		
		String youSaved = "product not on sale.no discount.";
		try{				
			if (YouSaved.isDisplayed()){
				youSaved = YouSaved.getText().toString();	
			}			
			
			
		}catch(Exception e){				
			System.out.println("Exception occured for youSaved:"+e.getMessage());			
			
		}
				
		System.out.println("YouSaved= " + youSaved);		
		
		orderDetails.put("orderNumber", orderNumber);
		orderDetails.put("productName", productName);
		orderDetails.put("partNumber", partNumber);
		orderDetails.put("fulfillmentType", fulfillmentType);
		orderDetails.put("unitPrice", unitPrice);
		orderDetails.put("corePrice", corePrice);
		orderDetails.put("qty", qty);
		orderDetails.put("subtotal", subtotal);
		orderDetails.put("discount", discount);
		orderDetails.put("itemTotal", itemTotal);
		orderDetails.put("pickUpLocation", pickUpLocation);
		orderDetails.put("pickUpPerson", pickUpPerson);
		orderDetails.put("rOLSubtotal", rOLSubtotal);
		orderDetails.put("tax", tax);
		orderDetails.put("finalTotal", finalTotal);
		orderDetails.put("youSaved", youSaved);
		
		return orderDetails;
	}
	
public Hashtable<String, String> getOrderDetailsBOL(){
		
		Hashtable<String, String> orderDetails = new Hashtable<String, String>();
		
		String orderNumber = OrderNumberTxt.getText().toString();
		System.out.println("orderNumber= " + orderNumber);
		
		String productName = ProductName.getText().toString();
		//System.out.println("ProductName= " + productName);
		
		String partNumber = PartNumber.getText().toString();
		//System.out.println("PartNumber= " + partNumber);
		
		String fulfillmentType = OrderTypeHeader.getText().toString();
		//System.out.println("FulfillmentType= " + fulfillmentType);
		
		String unitPrice = UnitPrice.getText().toString();
		//System.out.println("UnitPrice= " + unitPrice);
		
		String corePrice = CorePrice.getText().toString();
		//System.out.println("CorePrice= " + corePrice);
		
		String qty = Qty.getText().toString();
		//System.out.println("qty= " + qty);
		
		String subtotal = Subtotal.getText().toString();
		//System.out.println("Subtotal= " + subtotal);
		
		String discount = "no discount available";
		if (Discount.isDisplayed()){
			discount = Discount.getText().toString();	
		}		
		//System.out.println("Discount= " + discount);
		
		String itemTotal = ItemTotal.getText().toString();
		//System.out.println("ItemTotal= " + itemTotal);
		
		String shippingInfo = ShippingInfo.getText().toString();
		//System.out.println("shippingInfo= " + shippingInfo);		
		
		String bOLSubtotal = BOLSubtotal.getText().toString();
		//System.out.println("BOLSubtotal= " + bOLSubtotal);
		
		String tax = Tax.getText().toString();
		//System.out.println("Tax= " + tax);
		
		String shippingCharge = ShippingCharge.getText().toString();
		//System.out.println("Tax= " + tax);
		
		String finalTotalBOL = FinalTotalBOL.getText().toString();
		//System.out.println("FinalTotalBOL= " + finalTotalBOL);
		
		String youSaved = "no discount available";
		try{				
			if (YouSaved.isDisplayed()){
				youSaved = YouSaved.getText().toString();	
			}			
			
			
		}catch(Exception e){				
			System.out.println("Exception occured for youSaved:"+e.getMessage());			
			
		}
				
		//System.out.println("YouSaved= " + youSaved);		
		
		orderDetails.put("orderNumber", orderNumber);
		orderDetails.put("productNameOrder", productName);
		orderDetails.put("partNumberOrder", partNumber);
		orderDetails.put("fulfillmentTypeOrder", fulfillmentType);
		orderDetails.put("unitPriceOrder", unitPrice);
		orderDetails.put("corePriceOrder", corePrice);
		orderDetails.put("qtyOrder", qty);
		orderDetails.put("subtotalOrder", subtotal);
		orderDetails.put("discountOrder", discount);
		orderDetails.put("itemTotalOrder", itemTotal);
		orderDetails.put("shippingInfoOrder", shippingInfo);		
		orderDetails.put("bOLSubtotalOrder", bOLSubtotal);
		orderDetails.put("taxOrder", tax);
		orderDetails.put("shippingCharge", shippingCharge);
		orderDetails.put("finalTotalBOLOrder", finalTotalBOL);
		orderDetails.put("youSavedOrder", youSaved);
		
		return orderDetails;
	}
	
	public Hashtable<String, String> getOrderDetailsTwoItemsSameStore(){
		
		Hashtable<String, String> orderDetails = new Hashtable<String, String>();		
		
		String orderNumber = OrderNumberTxt.getText().toString();
		System.out.println("orderNumber= " + orderNumber);
		
		String productName1 = ProductName1.getText().toString();
		//System.out.println("ProductName1= " + productName1);
		
		String productName2 = ProductName2.getText().toString();
		//System.out.println("ProductName2= " + productName2);
		
		String partNumber1 = PartNumber1.getText().toString();
		//System.out.println("PartNumber= " + partNumber);
		
		String partNumber2 = PartNumber2.getText().toString();
		//System.out.println("PartNumber= " + partNumber);		
		
		String unitPrice1 = UnitPrice1.getText().toString();
		//System.out.println("UnitPrice= " + unitPrice);
		
		String unitPrice2 = UnitPrice2.getText().toString();
		//System.out.println("UnitPrice= " + unitPrice);
		
		String corePrice1 = CorePrice1.getText().toString();
		//System.out.println("CorePrice= " + corePrice);
		
		String corePrice2 = CorePrice2.getText().toString();
		//System.out.println("CorePrice= " + corePrice);
		
		String qty1 = Qty1.getText().toString();
		//System.out.println("qty= " + qty);
		
		String qty2 = Qty2.getText().toString();
		//System.out.println("qty= " + qty);
		
		String subtotal1 = Subtotal1.getText().toString();
		//System.out.println("Subtotal= " + subtotal);
		
		String subtotal2 = Subtotal2.getText().toString();
		//System.out.println("Subtotal= " + subtotal);
		
		String discount1 = "product not on sale.no discount.";
		try{				
			if (Discount1.isDisplayed()){
				discount1 = Discount1.getText().toString();	
			}			
		}catch(Exception e){				
			System.out.println("Exception occured for Discount1 Text");			
			
		}
				
		String discount2 = "product not on sale.no discount.";
		try{				
			if (Discount2.isDisplayed()){
				discount2 = Discount2.getText().toString();	
			}			
		}catch(Exception e){				
			System.out.println("Exception occured for Discount2 Text");			
			
		}
			
		//System.out.println("Discount= " + discount);
		
		String itemTotal1 = ItemTotal1.getText().toString();
		//System.out.println("ItemTotal= " + itemTotal);
		
		String itemTotal2 = ItemTotal2.getText().toString();
		//System.out.println("ItemTotal= " + itemTotal);
		
		String fulfillmentType = FulfillmentType.getText().toString();
		//System.out.println("FulfillmentType= " + fulfillmentType);
		
		String pickUpLocation = PickUpLocation.getText().toString();
		//System.out.println("PickUpLocation= " + pickUpLocation);
		
		String pickUpPerson = PickUpPerson.getText().toString();
		//System.out.println("PickUpPerson= " + pickUpPerson);
		
		String rOLSubtotal = ROLSubtotal.getText().toString();
		//System.out.println("ROLSubtotal= " + rOLSubtotal);
		
		String tax = Tax.getText().toString();
		//System.out.println("Tax= " + tax);
		
		String finalTotal = FinalTotal.getText().toString();
		//System.out.println("FinalTotal= " + finalTotal);
		
		String youSaved = "product not on sale.no discount.";
		try{				
			if (YouSaved.isDisplayed()){
				youSaved = YouSaved.getText().toString();	
			}			
			
			
		}catch(Exception e){				
			System.out.println("Exception occured for youSaved:");			
			
		}
				
		//System.out.println("YouSaved= " + youSaved);		
		
		orderDetails.put("orderNumber", orderNumber);
		
		//Product 1 details from Order
		orderDetails.put("productName1", productName1);
		orderDetails.put("partNumber1", partNumber1);		
		orderDetails.put("unitPrice1", unitPrice1);
		orderDetails.put("corePrice1", corePrice1);
		orderDetails.put("qty1", qty1);
		orderDetails.put("subtotal1", subtotal1);
		orderDetails.put("discount1", discount1);
		orderDetails.put("itemTotal1", itemTotal1);
		
		//Product 2 details from Order		
		orderDetails.put("productName2", productName2);
		orderDetails.put("partNumber2", partNumber2);		
		orderDetails.put("unitPrice2", unitPrice2);
		orderDetails.put("corePrice2", corePrice2);
		orderDetails.put("qty2", qty2);
		orderDetails.put("subtotal2", subtotal2);
		orderDetails.put("discount2", discount2);
		orderDetails.put("itemTotal2", itemTotal2);
		
		orderDetails.put("fulfillmentType", fulfillmentType);
		orderDetails.put("pickUpLocation", pickUpLocation);
		orderDetails.put("pickUpPerson", pickUpPerson);
		orderDetails.put("rOLSubtotal", rOLSubtotal);
		orderDetails.put("tax", tax);
		orderDetails.put("finalTotal", finalTotal);
		orderDetails.put("youSaved", youSaved);
		
		return orderDetails;
	}
	
public Hashtable<String, String> getOrderDetailsTwoOrdersDiffStore(){
		
		Hashtable<String, String> orderDetails = new Hashtable<String, String>();		
		
		String orderNumber1 = OrderNumDiffStr1.getText().toString();
		System.out.println("orderNumber1= " + orderNumber1);
		
		String orderNumber2 = OrderNumDiffStr2.getText().toString();
		System.out.println("orderNumber2= " + orderNumber2);
		
		String productName1 = ProductDiffStr1.getText().toString();
		//System.out.println("ProductName1= " + productName1);
		
		String productName2 = ProductDiffStr2.getText().toString();
		//System.out.println("ProductName2= " + productName2);
		
		String partNumber1 = PartDiffStr1.getText().toString();
		//System.out.println("PartNumber= " + partNumber);
		
		String partNumber2 = PartDiffStr2.getText().toString();
		//System.out.println("PartNumber= " + partNumber);		
		
		String unitPrice1 = UnitPriceDiffStr1.getText().toString();
		//System.out.println("UnitPrice= " + unitPrice);
		
		String unitPrice2 = UnitPriceDiffStr2.getText().toString();
		//System.out.println("UnitPrice= " + unitPrice);
		
		String corePrice1 = CorePriceDiffStr1.getText().toString();
		//System.out.println("CorePrice= " + corePrice);
		
		String corePrice2 = CorePriceDiffStr2.getText().toString();
		//System.out.println("CorePrice= " + corePrice);
		
		String qty1 = QtyDiffStr1.getText().toString();
		//System.out.println("qty= " + qty);
		
		String qty2 = QtyDiffStr2.getText().toString();
		//System.out.println("qty= " + qty);
		
		String subtotal1 = SubtotalDiffStr1.getText().toString();
		//System.out.println("Subtotal= " + subtotal);
		
		String subtotal2 = SubtotalDiffStr2.getText().toString();
		//System.out.println("Subtotal= " + subtotal);
		
		String discount1 = "no discount displayed";
		try{				
			if (DiscountDiffStr1.isDisplayed()){
				discount1 = DiscountDiffStr1.getText().toString();	
			}			
		}catch(Exception e){				
			System.out.println("Exception occured for Discount1 Text");			
			
		}
				
		String discount2 = "no discount displayed";
		try{				
			if (DiscountDiffStr2.isDisplayed()){
				discount2 = DiscountDiffStr2.getText().toString();	
			}			
		}catch(Exception e){				
			System.out.println("Exception occured for Discount2 Text");			
			
		}
			
		//System.out.println("Discount= " + discount);
		
		String itemTotal1 = ItemTotalDiffStr1.getText().toString();
		//System.out.println("ItemTotal= " + itemTotal);
		
		String itemTotal2 = ItemTotalDiffStr2.getText().toString();
		//System.out.println("ItemTotal= " + itemTotal);
		
		String fulfillmentType1 = FulfillmentDiffStr1.getText().toString();
		//System.out.println("FulfillmentType1= " + fulfillmentType1);
		
		String fulfillmentType2 = FulfillmentDiffStr2.getText().toString();
		//System.out.println("FulfillmentType2= " + fulfillmentType2);
		
		String pickUpLocation1 = PickUpLocationDiffStr1.getText().toString();
		//System.out.println("PickUpLocation1= " + pickUpLocation1);
		
		String pickUpLocation2 = PickUpLocationDiffStr2.getText().toString();
		//System.out.println("PickUpLocation2= " + pickUpLocation2);
		
		String pickUpPerson1 = PickUpPersonDiffStr1.getText().toString();
		//System.out.println("PickUpPerson1= " + pickUpPerson1);
		
		String pickUpPerson2 = PickUpPersonDiffStr2.getText().toString();
		//System.out.println("PickUpPerson2= " + pickUpPerson2);
		
		String rOLSubtotal1 = ROLSubtotalDiffStr1.getText().toString();
		//System.out.println("ROLSubtotal1= " + rOLSubtotal1);
		
		String rOLSubtotal2 = ROLSubtotalDiffStr2.getText().toString();
		//System.out.println("ROLSubtotal1= " + rOLSubtotal2);
		
		String tax1 = TaxDiffStr1.getText().toString();
		//System.out.println("Tax1= " + tax1);
		
		String tax2 = TaxDiffStr2.getText().toString();
		//System.out.println("Tax2= " + tax2);
		
		String finalTotal1 = FinalTotalDiffStr1.getText().toString();
		//System.out.println("FinalTotal1= " + finalTotal1);
		
		String finalTotal2 = FinalTotalDiffStr2.getText().toString();
		//System.out.println("FinalTotal2= " + finalTotal2);
		
		String youSaved1 = "no discount available.";
		try{				
			if (YouSavedDiffStr1.isDisplayed()){
				youSaved1 = YouSavedDiffStr1.getText().toString();	
			}			
			
			
		}catch(Exception e){				
			System.out.println("Exception occured for youSaved1");			
			
		}
		
		String youSaved2 = "no discount available.";
		try{				
			if (YouSavedDiffStr2.isDisplayed()){
				youSaved2 = YouSavedDiffStr2.getText().toString();	
			}			
			
			
		}catch(Exception e){				
			System.out.println("Exception occured for youSaved2");			
			
		}		
		
		//Product 1 details from Order
		orderDetails.put("orderNumber1", orderNumber1);
		orderDetails.put("productName1", productName1);
		orderDetails.put("partNumber1", partNumber1);		
		orderDetails.put("unitPrice1", unitPrice1);
		orderDetails.put("corePrice1", corePrice1);
		orderDetails.put("qty1", qty1);
		orderDetails.put("subtotal1", subtotal1);
		orderDetails.put("discount1", discount1);
		orderDetails.put("itemTotal1", itemTotal1);
		orderDetails.put("fulfillmentType1", fulfillmentType1);
		orderDetails.put("pickUpLocation1", pickUpLocation1);
		orderDetails.put("pickUpPerson1", pickUpPerson1);
		orderDetails.put("rOLSubtotal1", rOLSubtotal1);
		orderDetails.put("tax1", tax1);
		orderDetails.put("finalTotal1", finalTotal1);
		orderDetails.put("youSaved1", youSaved1);
		
		//Product 2 details from Order	
		orderDetails.put("orderNumber2", orderNumber2);
		orderDetails.put("productName2", productName2);
		orderDetails.put("partNumber2", partNumber2);		
		orderDetails.put("unitPrice2", unitPrice2);
		orderDetails.put("corePrice2", corePrice2);
		orderDetails.put("qty2", qty2);
		orderDetails.put("subtotal2", subtotal2);
		orderDetails.put("discount2", discount2);
		orderDetails.put("itemTotal2", itemTotal2);
		orderDetails.put("fulfillmentType2", fulfillmentType2);
		orderDetails.put("pickUpLocation2", pickUpLocation2);
		orderDetails.put("pickUpPerson2", pickUpPerson2);
		orderDetails.put("rOLSubtotal2", rOLSubtotal2);
		orderDetails.put("tax2", tax2);
		orderDetails.put("finalTotal2", finalTotal2);
		orderDetails.put("youSaved2", youSaved2);
		
		
		return orderDetails;
	}
	
	
	

}
