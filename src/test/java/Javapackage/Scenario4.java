package Javapackage;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class Scenario4 extends ExtendReportsClass {
  
	FileInputStream File;
	  Properties pro;
	  String url;
	WebDriver driver;
	
	
  @BeforeClass
  public void beforeClass() throws InterruptedException, Exception {
	  
	  File=new FileInputStream(new File("D:\\Selenium Docs\\Selenium Eclipse\\Test1\\Repo.properties"));
	  pro=new Properties();
	  pro.load(File);
	  System.setProperty("webdriver.ie.driver", "D:\\Selenium Docs\\Selenium Drivers\\IEDriverServer.exe");
	  url = "http://10.159.34.113:8888/wd/hub";
      try {
          DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
          //capabilities.setBrowserName("chrome");
          capabilities.setPlatform(Platform.WINDOWS);
          driver = new RemoteWebDriver(new URL(url), capabilities);
      }catch(Exception e){
          e.printStackTrace();
      }
	  
	/*  System.setProperty("webdriver.chrome.driver", "D:\\Selenium Docs\\Selenium Drivers\\chromedriver.exe");
	   driver=new ChromeDriver();*/
	   driver.manage().window().maximize();
	  
 
	  
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
	  
	  //---------------------------------
	  
	  //validation
	  
	    String Loginvalue= driver.findElement(By.xpath("//a[@href='http://10.207.182.108:81/opencart/index.php?route=account/account']")).getText();
	    //System.out.println(Loginvalue);
	    Assert.assertEquals(Loginvalue,"Reshma");
	    System.out.println("Logged in with the Reshma");
	    
	   //--------------------------------
	    
  }
	@Test(priority=1)
	
	public void scenario4() throws InterruptedException, Exception{
		
		//ExtentReports line
		  logger = extent.startTest("scenario4"); 
       
	
	  //step4-click on home
		  driver.findElement(By.linkText(pro.getProperty("Home.ClickHome.linktext"))).click();
		  System.out.println("Clicked on home");

	  //step5-Samsung galaxy
          driver.findElement(By.xpath(pro.getProperty("Samsunggalaxy.xpath"))).click();
          System.out.println("Clicked on Samsung galaxy tab");
          Thread.sleep(3000);
	    
	  //step6-Samsung galaxy image
	    driver.findElement(By.xpath(pro.getProperty("Samsunggalaxy.image.xpath"))).click();
	    System.out.println("Clicked on image");
	    Thread.sleep(3000);
	    
	    //--------------------------------
	    
	     //Validation
	       String image=driver.findElement(By.xpath("//div[@id='cboxCurrent']")).getText();
	       System.out.println(image);
	       String image1=image.substring(11);
	       System.out.println(image1);
	       int imageint=Integer.parseInt(image1);
	       System.out.println(imageint);
	       
	       BufferedWriter imageoutput=new BufferedWriter(new FileWriter("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\Tc03_step6.txt"));
	       imageoutput.write(" Image count is " +imageint);
	       imageoutput.close();
	    
	    //--------------------------------
	    
	  //step7-image count
	    for(int i=1;i<=7;i++)
	    {
	    driver.findElement(By.xpath(pro.getProperty("Samsunggalaxy.imagecount.xpath"))).click();
	    int count = 0;
	    count= count+i;
	    System.out.println("image  "+count);
	    }
	    
	  //step8-close
	    driver.findElement(By.xpath(pro.getProperty("Samsunggalaxy.imageclose.xpath"))).click();
	    System.out.println("Closed");
	    Thread.sleep(3000);
	    
	    
	  //step9-Add to cart
	    driver.findElement(By.id(pro.getProperty("Samsunggalaxy.addtocart.id"))).click();
	    System.out.println("Added to cart");
	    Thread.sleep(3000);
	    
	  //step10-click on Shopping cart
	          driver.findElement(By.xpath(pro.getProperty("Home.shoppingcart.xpath"))).click();
	          System.out.println("Shopping cart link");
	          Thread.sleep(3000);
	          
	  //step11-Radio button estimate
	          driver.findElement(By.id(pro.getProperty("shoppingcart.estimatebutton.id"))).click();
	          System.out.println("Estimate Shipping & Taxes");
	          Thread.sleep(3000);
	          
	  //step12-Get Quotes
	          driver.findElement(By.id(pro.getProperty("shoppingcart.getcote.id"))).click();
	          System.out.println("Get Quotes");
	          Thread.sleep(3000);
	         
	          
	          //Flat radio button
	          driver.findElement(By.xpath(pro.getProperty("shoppingcart.flatbutton.xpath"))).click();
	          Thread.sleep(2000);
	          driver.findElement(By.id(pro.getProperty("shoppingcart.applyshippingbutton.id"))).click();
	          System.out.println("clicked on Flat radio button");
	          Thread.sleep(3000);
	          
	          //---------------------------------
	          
	          //--Validation----------
	          
	          BufferedWriter flatshipping=new BufferedWriter(new FileWriter("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\Tc03_step12.txt"));
	    	  String amt=driver.findElement(By.xpath(".//*[@id='total']/tbody/tr[3]/td[2]")).getText();
	    	  System.out.println("Total Amount is "  +amt);
	    	  flatshipping.write("Total Amount is " +amt);
	    	  flatshipping.close();
	    	  
	          //---------------------------------
	          
	  //step13-Coupon radio button
	          driver.findElement(By.id("use_coupon")).click();
	          Thread.sleep(3000);
	          
	  //step14-Enter the Random Coupon Number
	          driver.findElement(By.name("coupon")).sendKeys("Abc");
	          driver.findElement(By.xpath(".//*[@id='coupon']/form/input[3]")).click();
	          System.out.println("Clicked on Coupon radio button");
	          Thread.sleep(3000);
	          
	          //---------------------------------
	          
	          //-----validation----------
	          
	          BufferedWriter warning=new BufferedWriter(new FileWriter("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\Tc03_step14.txt"));
	    	  String warningmsg=driver.findElement(By.xpath(".//*[@id='container']/div[4]")).getText();
	    	  System.out.println("Warning msg is "  +warningmsg);
	    	  warning.write("Warning msg is " +warningmsg);
	    	  warning.close();
	    	  
	          //---------------------------------
	  //step15-Checkout
	          driver.findElement(By.xpath(".//*[@id='content']/div[5]/div[1]/a")).click();
	          Thread.sleep(3000);
	      
	  //step16-Select Billing Details -> I want to use new address option
	          driver.findElement(By.id("payment-address-new")).click();
	          Thread.sleep(3000);
	          
	  //step17-Fill in the mandatory details and click continue
	          driver.findElement(By.name("firstname")).sendKeys("Demo11");
	  driver.findElement(By.name("lastname")).sendKeys("User");
	  driver.findElement(By.name("address_1")).sendKeys("Plot No:C-55,Kakatiya Nagar");
	  driver.findElement(By.name("city")).sendKeys("Hyderabad");
	  WebElement drop = driver.findElement(By.name("country_id"));
	  Select E = new Select(drop);
	    E.selectByVisibleText("India");
	    WebElement drop1 = driver.findElement(By.name("zone_id"));
	    Thread.sleep(3000);
	    Select F = new Select(drop1);
	    F.selectByVisibleText("Andhra Pradesh");
	    
	    //Continue1
	         driver.findElement(By.id("button-payment-address")).click();
	         System.out.println("clicked on continue1");
	         Thread.sleep(3000);
	         
	  //step18-delivery address change
	         
	      WebElement deliverydetails = driver.findElement(By.xpath("//div[@id='shipping-existing']/select"));
	  Select newadd = new Select(deliverydetails);
	  newadd.selectByIndex(newadd.getOptions().size()-1);
	       
	       //Continue2
	       driver.findElement(By.id("button-shipping-address")).click();
	       System.out.println("clicked on continue2");
	       Thread.sleep(3000);
	       
	 //step19-Continue3
	       driver.findElement(By.xpath(".//*[@id='shipping-method']/div[2]/textarea")).sendKeys("abc");
	       Thread.sleep(3000);
	       
	      //continue3
	        driver.findElement(By.id("button-shipping-method")).click();
	         System.out.println("clicked on continue3");
	         Thread.sleep(3000);
	         
	      //Checkbox
	       driver.findElement(By.name("agree")).click();
	       System.out.println("checkbox selected");
	       Thread.sleep(3000);
	       
	 //step20-Terms and Conditions link
	       driver.findElement(By.xpath(".//*[@id='payment-method']/div[2]/div/div/a/b")).click();
	       System.out.println("Terms and Conditions link clicked");
	       Thread.sleep(2000);
	       
	       //---------------------------------
	       
	       //---validation
	       BufferedWriter Terms=new BufferedWriter(new FileWriter("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\Tc03_step20.txt"));
	    	  String termsndcond=driver.findElement(By.xpath(".//*[@id='cboxLoadedContent']/p")).getText();
	    	  System.out.println("No of charectors in Warning msg is "  +termsndcond.length());
	    	  Terms.write("No of charectors in Warning msg is " +termsndcond.length());
	    	  Terms.close();
	      
	       //---------------------------------
	       
	 //step21-close
	       driver.findElement(By.xpath(".//*[@id='cboxClose']")).click();
	       System.out.println("Close button");
	       Thread.sleep(3000);
	       
	        //continue order
	       driver.findElement(By.id("button-payment-method")).click();
	       System.out.println("Click on continue");
	       Thread.sleep(3000);
	       
	       //----------------------------------
	       
	       //Validation
	       String Price=driver.findElement(By.xpath(".//*[@id='confirm']/div[2]/div[1]/table/tbody/tr/td[4]")).getText();
	       String Total=driver.findElement(By.xpath(".//*[@id='confirm']/div[2]/div[1]/table/tfoot/tr[3]/td[2]")).getText();
	       
	       List<String> Order = new ArrayList<String>();
	       Order.add(Price);
	       Order.add(Total);
	       
	       System.out.println("The list of Order is " +Order);
	       
	       FileOutputStream output=new FileOutputStream("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\TC04ExcelOutPut.xlsx");
		      XSSFWorkbook wk=new XSSFWorkbook();
		      XSSFSheet sh=wk.createSheet();
		      for(int i=0;i<Order.size();i++)
		      {
		    	   sh.createRow(i).createCell(0).setCellValue(Order.get(i));
		    	   		    	  		    
		    	   }
	     
		      wk.write(output);
		      wk.close();
		      
	       //----------------------------------
	       
	  //step22-Billing details rollup
	       driver.findElement(By.xpath(".//*[@id='payment-address']/div[1]/a")).click();
	       Thread.sleep(2000);
	       
	  //step23-Select the first address in the list box and click on continue until you reach confirm order
	       driver.findElement(By.xpath(".//*[@id='payment-existing']/select/option[1]")).click();
	       
	       System.out.println("");
	       
	         //Continue1
	            driver.findElement(By.id("button-payment-address")).click();
	            System.out.println("clicked on continue1");
	            Thread.sleep(3000);
	            
	         //Continue2
	         driver.findElement(By.id("button-shipping-address")).click();
	         System.out.println("clicked on continue2");
	         Thread.sleep(3000);
	         
	         //continue3
	           driver.findElement(By.id("button-shipping-method")).click();
	            System.out.println("clicked on continue3");
	            Thread.sleep(3000);
	            
	         //Checkbox
	         driver.findElement(By.name("agree")).click();
	         System.out.println("checkbox selected");
	         Thread.sleep(3000);
	         
	         //Continue
	         driver.findElement(By.id("button-payment-method")).click();
	         System.out.println("continue");
	         Thread.sleep(3000);
	      
	      
	         
	       
	    //step24-confirm order
	       driver.findElement(By.id("button-confirm")).click();
	       System.out.println("confirm button");
	       Thread.sleep(3000);
	       
	       
	    //step25-Logout
	       driver.findElement(By.linkText("Logout")).click();
	       System.out.println("Logout successful");
	     
	     //ExtentReports line
		      logger.log(LogStatus.PASS, "Scenario4 is successfull" ,"Method \"scenario4\" is passed"); 
	         
	}
	          
	          
	    


	  }




  
