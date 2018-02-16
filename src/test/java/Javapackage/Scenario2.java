package Javapackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import net.bytebuddy.jar.asm.commons.TryCatchBlockSorter;

import java.io.File;


public class Scenario2 extends ExtendReportsClass{
  
	  
	FileInputStream File;
	  Properties pro;
	  
	  WebDriver driver = null;
	  String str1;
	  String url;
	 
	  
	  @BeforeClass
          public void f() throws Exception {
		  File=new FileInputStream(new File("D:\\Selenium Docs\\Selenium Eclipse\\Test1\\Repo.properties"));
		  pro=new Properties();
		  pro.load(File);
		  
		  url = "http://10.159.34.113:4444/wd/hub";
          try {
              DesiredCapabilities capabilities = new DesiredCapabilities();
              capabilities.setBrowserName("chrome");
              capabilities.setPlatform(Platform.WINDOWS);
              driver = new RemoteWebDriver(new URL(url), capabilities);
          }catch(Exception e){
              e.printStackTrace();
          }
		/*  System.setProperty("webdriver.chrome.driver", "D:\\Selenium Docs\\Selenium Drivers\\chromedriver.exe");
		   driver=new ChromeDriver();*/
		   driver.manage().window().maximize();
		  
	  }
	  
	  @Test(description="Login",priority=1)
	  public void Login() throws Exception{
		  
		  //ExtentReports line
		  logger = extent.startTest("Login"); 
		 
		  
		  //Step1-launch opencart
		  driver.get(pro.getProperty("opencart.url"));
		  
		  //Step2-login
		  Thread.sleep(2000);
		  driver.findElement(By.linkText(pro.getProperty("Login.clicklogin.Linktext"))).click();
		  
		  //step3-credentials
		  driver.findElement(By.name(pro.getProperty("Login.email.name"))).sendKeys("reshma.2@gmail.com");
		  driver.findElement(By.name(pro.getProperty("Login.password.name"))).sendKeys("Abc@12345");
		  driver.findElement(By.xpath(pro.getProperty("Login.Loginclick.xpath"))).click();
		  System.out.println("Login successfull");
		  
		  //------------Validation of Step17--------------------------------
		  Thread.sleep(2000);
		  try {
			  driver.findElement(By.linkText(pro.getProperty("orderhistory.click.linktext"))).click();
			  str1=driver.findElement(By.xpath(pro.getProperty("orderhistory.orderid.xpath"))).getText();
			  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		  
		  //----------------------------------------------------------------
		  
		  
		//Validation
	       Thread.sleep(2000);
	       String link=driver.findElement(By.xpath(".//*[@id='welcome']/a[1]")).getText();
	       System.out.println(link);
	       if(link.equals("Reshma"))
	       {
	    	   System.out.println("Validation is passed");
	    	  
	       }
	       else{
	    	   System.out.println("Validation is failed");
	       }
		  
		  //step4-click on home
		  driver.findElement(By.linkText(pro.getProperty("Home.ClickHome.linktext"))).click();
		  
		  //step5-click on any product
		  driver.findElement(By.linkText(pro.getProperty("Home.AppleHD.linktext"))).click();
		  
		  
		  //step6-click on related tab
		  driver.findElement(By.linkText(pro.getProperty("Home.RelatedTab.linktext"))).click();
		  driver.findElement(By.linkText("Home")).click();
		  
		  List<WebElement> list=driver.findElements(By.xpath(".//*[@id='content']/div[2]/div[2]/div/div"));
		  int Listsize =list.size();
		  System.out.println(Listsize);
		  int Count=0;
		  for(int i=0;i<Listsize;i++)
		  {
			  driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/div/div["+(i+1)+"]/div[1]/a/img")).click();
			  
			  
			  List<WebElement> tabData=driver.findElements(By.xpath(".//*[@id='tabs']"));
			  
			  for(int j=0;j<tabData.size();j++)
			  {
				  String data=tabData.get(j).getText();
				  
				  if(data.contains("Related Products"))
				  {
					  
					  Count++;
					  System.out.println("Related Product Tab is Present");
					  driver.findElement(By.xpath("//a[contains(text(),'Related Products ')]")).click();
					  //driver.findElement(By.linkText("Add to Cart")).click();
					  driver.findElement(By.xpath("//a[contains(text(),'Add to Cart')]")).click();
					  System.out.println("Add to cart is clicked");
					  Thread.sleep(2000);
			      }
				  
				  else{
					  System.out.println("Related Product Tab is not Present");
				       }
			  }
			  
			  driver.findElement(By.linkText("Home")).click(); 
			  
			  
		  }
		  
		  System.out.println("No of Related Product Tabs Are  :"+Count);
		  
		 
		  
		  //step8-Shopping cart
		  driver.findElement(By.xpath(pro.getProperty("Home.shoppingcart.xpath"))).click();
		  
		  //step9-Change the quantity of the product
		  driver.findElement(By.name(pro.getProperty("shoppingcart.quantity.name"))).clear();
		  driver.findElement(By.name(pro.getProperty("shoppingcart.quantity.name"))).sendKeys("3");
		  System.out.println("Changed the quantity");
		  
		  //step10-Click on the update
		  driver.findElement(By.xpath(pro.getProperty("shoppincart.update.xpath"))).click();
		  System.out.println("cart is updated");
		  
		  //-----------Validation of step14-----------------------------------
		  
		//validation in shopping cart
	       
	       String Productname=driver.findElement(By.linkText(pro.getProperty("shoppingcart.productname.linktext"))).getText();
	       //System.out.println("pRODUCT NAME IS" +Productname);
	       String Model=driver.findElement(By.xpath(pro.getProperty("shoppingcart.model.xpath"))).getText();
	       String Quantity=driver.findElement(By.xpath(pro.getProperty("shoppingcart.quantity.xpath"))).getAttribute("value");
	       String Unitprice=driver.findElement(By.xpath(pro.getProperty("shoppingcart.unitprice.xpath"))).getText();
	       String Total=driver.findElement(By.xpath(pro.getProperty("shoppingcart.total.xpath"))).getText();
	       
	       List<String> Shoppingcart=new ArrayList<String>();
	       Shoppingcart.add(Productname);
	       Shoppingcart.add(Model);
	       Shoppingcart.add(Quantity);
	       Shoppingcart.add(Unitprice);
	       Shoppingcart.add(Total);
	       
	      System.out.println("The list of shoppingcart is " +Shoppingcart);
	      
	      //--------------------------------------------------------------------
		  
		//step11 Click on Checkout
	       Thread.sleep(2000);
	       driver.findElement(By.linkText(pro.getProperty("checkout.click.linktext"))).click();
	       
	       //step12 Click on Continue buttons (2nd, 3rd and 4th)
	           // 2nd continue
	       Thread.sleep(2000);
	       driver.findElement(By.cssSelector(pro.getProperty("checkout.continue.cssSelector"))).click();
	       
	           // 3rd continue
	       Thread.sleep(1000);
	       driver.findElement(By.cssSelector(pro.getProperty("checkout.continue1.cssSelector"))).click();
	       
	           // 4th continue
	       Thread.sleep(1000);
	       driver.findElement(By.cssSelector(pro.getProperty("checkout.continue2.cssSelector"))).click();
	       
	       //step13 Check the Terms and Conditions Checkbox and click Continue
	       Thread.sleep(1000);
	       driver.findElement(By.name(pro.getProperty("checkout.termsndconditions.checkbox.name"))).click();
	       driver.findElement(By.cssSelector(pro.getProperty("checkout.continue3.cssSelector"))).click();
	       Thread.sleep(2000);
	       
	       //step14 Verify the product details is valid
	       
	       
	     //-----------Validation of step14-----------------------------------
			  
			//validation in Checkout
		       
		       String CheckoutProductname=driver.findElement(By.xpath(pro.getProperty("Checkout.productname.xpath"))).getText();
		       String CheckoutModel=driver.findElement(By.xpath(pro.getProperty("Checkout.model.xpath"))).getText();
		       String CheckoutQuantity=driver.findElement(By.xpath(pro.getProperty("Checkout.quantity.xpath"))).getText();
		       String CheckoutUnitprice=driver.findElement(By.xpath(pro.getProperty("Checkout.unitprice.xpath"))).getText();
		       String CheckoutTotal=driver.findElement(By.xpath(pro.getProperty("Checkout.total.xpath"))).getText();
		       
		       List<String> Checkout=new ArrayList<String>();
		       Checkout.add(CheckoutProductname);
		       Checkout.add(CheckoutModel);
		       Checkout.add(CheckoutQuantity);
		       Checkout.add(CheckoutUnitprice);
		       Checkout.add(CheckoutTotal);
		       
		      System.out.println("The list of Checkout is " +Checkout);
		      
		      //--------------------------------------------------------------------
			  
	       //writing the list of Shopingcart and checkout to Excel
		      
		      FileOutputStream output=new FileOutputStream("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\TC04ExcelOutPut.xlsx");
		      XSSFWorkbook wk=new XSSFWorkbook();
		      XSSFSheet sh=wk.createSheet();
		      for(int i=0;i<Shoppingcart.size();i++)
		      {
		    	   sh.createRow(i).createCell(0).setCellValue(Shoppingcart.get(i));
		    	   sh.getRow(i).createCell(1).setCellValue(Checkout.get(i));
		    	  
		    
		    	   }
		    	   
		      for(int i=0;i<Shoppingcart.size();i++)
		      {
		      if(Shoppingcart.get(i).equals(Checkout.get(i)))
		      {
		    	  sh.getRow(i).createCell(2).setCellValue("True");  
		    	  
		      }
		      else{
		    	  sh.getRow(i).createCell(2).setCellValue("False");
		      }
		        
		      }
		      
		      wk.write(output);
		      wk.close();
		      
	  //---------------------------------------------------------------------
	      
	       //Step15 Confirm the order
	       Thread.sleep(1000);
	       driver.findElement(By.cssSelector(pro.getProperty("checkout.confirm.cssSelector"))).click();
	       System.out.println("Order has been placed");
	       
	       //step16 Click on order history of my account
	       Thread.sleep(2000);
	       driver.findElement(By.linkText(pro.getProperty("orderhistory.click.linktext"))).click();
	       System.out.println("Clicked on order history");
	       
	       
	       //------------Validation of Step17--------------------------------
			  Thread.sleep(1000);
			 
			  String str2=driver.findElement(By.xpath(pro.getProperty("orderhistory.orderid.xpath"))).getText();
			  try {
				
				  Assert.assertNotEquals(str1, str2);
				  System.out.println("Previous order is displayed");
				  
			} catch (Exception e) {
				
				driver.findElement(By.linkText(pro.getProperty("Logout.linktext"))).click();
				
			}
			 
			  
			  //----------------------------------------------------------------
	       
	       //step17 Click on view
	       Thread.sleep(1000);
	       driver.findElement(By.cssSelector(pro.getProperty("orderhistory.view.cssSelector"))).click();
	       System.out.println("Clicked on view");
	 
	       
	       
	       //step18 Click on return
	       Thread.sleep(1000);
	       driver.findElement(By.cssSelector(pro.getProperty("orderhistory.return.cssSelector"))).click();
	       System.out.println("Clicked on return");
	       
	       //step19 Return page details filling
	       Thread.sleep(2000);
	       driver.findElement(By.id(pro.getProperty("productreturns.reasonforreturn.id"))).click();
	       System.out.println("Clicked on order error");
	       
	         //captua
	       /*System.out.println("Please enter the captcha:");
	       Scanner scr = new Scanner(System.in);
	       String Captcha = scr.nextLine();
	       driver.findElement(By.name("captcha")).clear();
	       driver.findElement(By.name("captcha")).sendKeys(Captcha);*/
	       
	       Thread.sleep(9000);
	       
	       driver.findElement(By.xpath(pro.getProperty("productreturns.continue.xpath"))).click();
	       System.out.println("Clicked on continue in product returns");
	       
	       //step20  Click on continue to goto homepage
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(pro.getProperty("productreturns.continuetohomepage.xpath"))).click();
	       System.out.println("Clicked on continue togo to home page");
	       
	       //step21 Logout
	       Thread.sleep(1000);
	       driver.findElement(By.linkText(pro.getProperty("Logout.linktext"))).click();
	      
	       
	     //validation of checkpoint
	        //Logout message
	       String logout=driver.findElement(By.xpath(pro.getProperty("Logout.gettext.xpath"))).getText();
	       System.out.println(logout);
	       if (logout.contains("Account Logout")) {
	    	   System.out.println("Checkpoint is passed");
	       }
	       else {
	    	   System.out.println("Checkpoint is Failed");
	       }
	       
	     //ExtentReports line
			  logger.log(LogStatus.PASS, "Loggedin Successfully" ,"Method \"Login\" is passed"); 
	       
	}

		  
		  
	
		  
	  }
	    

