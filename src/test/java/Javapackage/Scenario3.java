package Javapackage;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class Scenario3 extends ExtendReportsClass{
	
	FileInputStream File;
	  Properties pro;
	  
	  WebDriver driver;
	  String str1;
	  String url;
	  @BeforeClass

	  
	  public void p() throws Exception {
		  File=new FileInputStream(new File("D:\\Selenium Docs\\Selenium Eclipse\\Test1\\Repo.properties"));
		  pro=new Properties();
		  pro.load(File);
		  
		  url = "http://10.159.34.113:4444/wd/hub";
          try {
              DesiredCapabilities capabilities =new DesiredCapabilities();
              
              capabilities.setBrowserName("chrome");
              capabilities.setPlatform(Platform.WINDOWS);
              driver = new RemoteWebDriver(new URL(url), capabilities);
              
          }catch(Exception e){
              e.printStackTrace();
          }
		  /*System.setProperty("webdriver.chrome.driver", "D:\\Selenium Docs\\Selenium drivers\\chromedriver.exe");
		   driver=new ChromeDriver();*/
		   driver.manage().window().maximize();
		  
	  }
  @Test
  public void aLogin() throws Exception {
	  
	//ExtentReports line
	  logger = extent.startTest("aLogin"); 
	  
	  
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
	  
	  //------------------------------------------------
	  
	//validation
	  String Loginvalue= driver.findElement(By.xpath("//a[@href='http://10.207.182.108:81/opencart/index.php?route=account/account']")).getText();
	  //System.out.println(Loginvalue);
	  Assert.assertEquals(Loginvalue,"Reshma");
	  System.out.println("Logged in with the User Reshma");
	  
	//ExtentReports line
	  logger.log(LogStatus.PASS, "Loggedin Successfully" ,"Method \"aLogin\" is passed"); 
  }
	  
	  /*-----------------------------------------------*/
	  @DataProvider(name="Search")
	  public static Object[][] Search() throws IOException{
		  Object[][] obj=ExcelLogic.readExcel("SearchSheet","D:\\Selenium Docs\\InputFiles of Testng\\EclipseInputSheet.xlsx");
		 // System.out.println(obj);
		  return obj;
	  }

	  /*-----------------------------------------------*/
	  
	  @Test(dataProvider="Search")
	  
	  public void bApple(String product) throws AWTException, Exception
	  {
		  
		//ExtentReports line
		  logger = extent.startTest("Apple"); 
		  
	  //step4-Search
	  driver.findElement(By.xpath(pro.getProperty("search.click.xpath"))).sendKeys(product);
	  Thread.sleep(1000);
	  Robot rb=new Robot();
	  rb.keyPress(KeyEvent.VK_ENTER);
	  rb.keyRelease(KeyEvent.VK_ENTER);
	  
	  //----------------------------------------------
	  
	    //Validation [Get the number of items in the grid and output to flat file]
	  
	  try {
		  
		driver.findElement(By.xpath(pro.getProperty("search.gridpath.xpath"))).click();
		
	} catch (Exception e) {
		
		// TODO: handle exception
	}
	  
	  //Flat file
	  
	  BufferedWriter bw1=new BufferedWriter(new FileWriter("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\Tc02_step4.txt"));
	  List<WebElement> Grid4=driver.findElements(By.xpath("//div[@class='product-grid']"));
	  System.out.println("Items list count is" +Grid4.size());
	  bw1.write("Items count is " +Grid4.size());
	  bw1.close();
	  
	  //---------------------------------------------
	  
	  //step5-Select Monitors
	  
	  WebElement search= driver.findElement(By.xpath(".//*[@id='content']/div[2]/p/select"));
	  search.sendKeys(Keys.ENTER);
	  System.out.println("searched  monitor in search box");
	  WebElement drop= driver.findElement(By.name("category_id"));
      Select E = new Select(drop);
E.selectByValue("28");
System.out.println("Selected Monitors from dropdown");

//Checkbox
driver.findElement(By.name("sub_category")).click();
System.out.println("Check box selected");
//Searchbutton
driver.findElement(By.id("button-search")).click();
System.out.println("Clicked on search button");

//---------------------------------------------

//Validation [Get the number of items in the grid and output to flat file]

try {
	  
	driver.findElement(By.xpath(pro.getProperty("search.gridpath.xpath"))).click();
	
} catch (Exception e) {
	
	// TODO: handle exception
}

// Validation Flat file

BufferedWriter bw2=new BufferedWriter(new FileWriter("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\Tc02_step5.txt"));
List<WebElement> Grid5=driver.findElements(By.xpath("//div[@class='product-grid']"));
System.out.println("Items list count is" +Grid5.size());
bw2.write("Items count is " +Grid5.size());
bw2.close();

//---------------------------------------------

	  //step6-Phones and PDA's tab
	  driver.findElement(By.xpath("//a[@href='http://10.207.182.108:81/opencart/index.php?route=product/category&path=24']")).click();
	  System.out.println("Clicked on Phones and PDA's tab");
	  
	//------------------------------------------------
	  
//Validation [Get the number of items in the grid and output to flat file]
	  
	  try {
		  
		driver.findElement(By.xpath(pro.getProperty("search.gridpath.xpath"))).click();
		
	} catch (Exception e) {
		
		// TODO: handle exception
	}

	// Validation Flat file

	BufferedWriter bw3=new BufferedWriter(new FileWriter("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\Tc02_step6.txt"));
	List<WebElement> Grid6=driver.findElements(By.xpath("//div[@class='product-grid']/div"));
	System.out.println("Items list count is" +Grid6.size());
	bw3.write("Items count is " +Grid6.size());
	bw3.close();

	//-------------------------------------------------
	
	  //step7- sort the price
	
	  WebElement drop1= driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[3]/select"));
	         Select F = new Select(drop1);
	  F.selectByVisibleText("Price (High > Low)");
	  System.out.println("Price (High > Low) is selected");
	  Thread.sleep(3000);
	  
	  //step8-Add to compare for first 3 phones
	  
	  driver.findElement(By.xpath("(//a[contains(.,'Add to Compare')])[1]")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("(//a[contains(.,'Add to Compare')])[2]")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("(//a[contains(.,'Add to Compare')])[3]")).click();
	  System.out.println("3 products added to compare");
	  Thread.sleep(3000);
	  
	  //-----------------------------------------------
	  
	  //validation Loop
	  BufferedWriter loop=new BufferedWriter(new FileWriter("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\Tc02_step8.txt"));
	  ArrayList<String> prodname=new ArrayList<String>();
	  
	  for(int i=1;i<=Grid6.size();i++)
	  {
			  String prod=driver.findElement(By.xpath(".//*[@id='content']/div[4]/div[" + i + "]/div[2]/a")).getText();
		  System.out.println("The product name is " +prod);
		  
		  prodname.add(driver.findElement(By.xpath(".//*[@id='content']/div[4]/div[" + i + "]/div[2]/a")).getText());
		  
		  loop.write("The product name is " + i + ":" +prod);
		  loop.newLine();
	  }
	      loop.close();
	      System.out.println(prodname.size());
	      System.out.println(prodname.get(0));
	      System.out.println(prodname.get(1));
	      System.out.println(prodname.get(2));
	    
	      
	   //------------------------------------------------
	  
	  //Click on close
	   driver.findElement(By.xpath(".//*[@id='notification']/div")).click();
	   System.out.println("Clicked on close");
	   Thread.sleep(3000);
	               
	 //step9-Click on Product compare
	     driver.findElement(By.xpath("//a[@id='compare-total']")).click();
	                   System.out.println(("clicked on product compare"));
	                   Thread.sleep(5000);
	                   
	    //----------------------------
	                   
	        //Validation	                   
	         String pname1=driver.findElement(By.linkText("Palm Treo Pro")).getText();
	         Assert.assertEquals(pname1, prodname.get(0));
	         
	         String pname2=driver.findElement(By.linkText("iPhone")).getText();
	         Assert.assertEquals(pname2, prodname.get(1));
	         
	         String pname3=driver.findElement(By.linkText("HTC Touch HD")).getText();
	         Assert.assertEquals(pname3, prodname.get(2));
	         
	         System.out.println("Validation is success");
	         
	    //----------------------------
	         
	              
	  //step10-click on 1st phone link
	                   driver.findElement(By.linkText("iPhone")).click();
	                    System.out.println("clicked on the 1st phone link");
	                    Thread.sleep(3000);
	                    
	  //step11-write the description to the flatfile
	                    
	                   /* List<WebElement> File=driver.findElements(By.xpath("//div[@id='tabs']"));
	                    for(int i=0;i<File.size();i++)
	                    {
	                    	String tab=File.get(i).getText();
	                    }*/
	                    
	          /*BufferedWriter flatfile=new BufferedWriter(new FileWriter("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\Tc02_step11.txt"));
	          String Description=driver.findElement(By.xpath(".//*[@id='tab-description']/ul/li[5]")).getText();
	          flatfile.write("Feature 5th line is " +Description);
	          flatfile.close();*/
	                    
	          
	   //step12-click on add to cart
	                  driver.findElement(By.xpath("//input[@type='button']")).click();
	                  System.out.println("Add to cart");
	                  Thread.sleep(3000);
	                   
	                  
	                  
	                   //step13-click on Shopping cart
	                  driver.findElement(By.xpath(".//*[@id='notification']/div/a[2]")).click();
	                  System.out.println("Shopping cart link");
	                  Thread.sleep(3000);
	                  
	                  //step14-Click on checkout
	                  driver.findElement(By.xpath(".//*[@id='content']/div[5]/div[1]/a")).click();
	                  System.out.println("Checkout done");
	                  Thread.sleep(3000);
	                  
	                  
	                //step15-Continue1
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
	               
	               //step16-Checkbox
	               driver.findElement(By.name("agree")).click();
	               System.out.println("checkbox selected");
	               Thread.sleep(3000);
	               
	               //contiune button
	               driver.findElement(By.id("button-payment-method")).click();
	               System.out.println("Continue button");
	               Thread.sleep(3000);
	               
	               //step17-confirm order
	               driver.findElement(By.id("button-confirm")).click();
	               System.out.println("confirm button");
	               Thread.sleep(3000);
	               
	               //step18-navigate back
	               driver.navigate().back();
	               System.out.println("Navigating back");
	               Thread.sleep(3000);

	               //step19-MyAccount-order history
	               driver.findElement(By.linkText("My Account")).click();
	               System.out.println("My Account");
	               Thread.sleep(3000);
	               
	               //Order History
	            driver.findElement(By.linkText("View your order history")).click();
	             System.out.println("Order History");
	             Thread.sleep(3000);
	             
	             //----------------------------
	             
	             //validation
	            /* 
	             BufferedWriter orderhistory=new BufferedWriter(new FileWriter("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\Tc02_step19.txt"));
		          String orderno=driver.findElement(By.xpath(".//*[@id='tab-description']/ul/li[5]")).getText();
		          flatfile.write("Feature 5th line is " +Description);
		          flatfile.close();
	             System.out.println("wrote to flatfile");*/
	             //----------------------------
		         
	             
	            //step20-Subscribe to Newsletter
	             driver.findElement(By.linkText("Newsletter")).click();
	             Thread.sleep(3000);
	             driver.findElement(By.xpath(".//*[@id='content']/form/div[2]/div[2]/input")).click();
	             Thread.sleep(3000);
	             driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
	               System.out.println("Subscribe / unsubscribe to newsletter");
	               Thread.sleep(3000);
	               
	            //step21-Specials footer
	                 driver.findElement(By.xpath("//a[@href='http://10.207.182.108:81/opencart/index.php?route=product/special']")).click();
	                 System.out.println("Specials footer");
	                Thread.sleep(3000);
	                
	                //step22-List/Grid
	                driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[1]/a")).click();
	                System.out.println("Grid");
	                Thread.sleep(3000);
	                
	                //step23-Logout
	          Thread.sleep(3000);
	          driver.findElement(By.linkText("Logout")).click();
	               
	             
	          String f= driver.findElement(By.xpath(".//*[@id='content']/h1")).getText();
	            Assert.assertEquals(f,"Account Logout");
	          System.out.println("Logout Successful");
	               
	        //ExtentReports line
			  logger.log(LogStatus.PASS, "AppleSearch is successfull" ,"Method \"Apple\" is passed"); 

	  }
	  


	  
	  
	  }

	 

