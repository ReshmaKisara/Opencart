package Javapackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class Dataprovider extends ExtendReportsClass{
	 
		RegistrationPage RegistrationPage;
		WebDriver driver;
		String url ;
		 
		
	  @BeforeClass
	  public void start() throws Exception {
		  
		  
		  
		  //Grid implementation
		  
		  url = "http://10.159.34.113:4444/wd/hub";
		          try {
		              DesiredCapabilities capabilities =new DesiredCapabilities();
		              capabilities.setBrowserName("chrome");
		              capabilities.setPlatform(Platform.WINDOWS);
		              driver = new RemoteWebDriver(new URL(url),capabilities);
		              
		          }catch(Exception e){
		              e.printStackTrace();
		          }
		  /*System.setProperty("webdriver.chrome.driver", "D:\\Selenium Docs\\Selenium Drivers\\chromedriver.exe");
		   driver=new ChromeDriver();*/
		   driver.manage().window().maximize();
		   
		   
			
		 //Step1-Launch opencart
			 
		   driver.get("http://10.207.182.108:81/opencart/");
		   
			Thread.sleep(3000);	
		//step2-click on Create an account
			
			RegistrationPage =new RegistrationPage(driver);	
			
			RegistrationPage.Register.click();
	  }
	  /*------------------------------------------------------------------------*/
	   
	  @Test(dataProvider="Registration1")
	  public void Login(String fname,String lname,String email,String phone,String fax,
			  String company,String company_id,String address1,String address2,String city,
			  String postcode,String password,String confirm) throws InterruptedException
	  {

		  logger = extent.startTest("Login"); 
		  
		  
		  RegistrationPage =new RegistrationPage(driver);
		  
	       
	//step3-fill details
		    RegistrationPage.fnameR.sendKeys(fname);
		    RegistrationPage.LnameR.sendKeys(lname);
		    RegistrationPage.emailR.sendKeys(System.nanoTime()+email);
		    RegistrationPage.telephoneR.sendKeys(phone);
		    RegistrationPage.faxR.sendKeys(fax);
		    RegistrationPage.companyR.sendKeys(company);
		    RegistrationPage.companyidR.sendKeys(company_id);
		    RegistrationPage.address1R.sendKeys(address1);
		    RegistrationPage.address2R.sendKeys(address2);
		    RegistrationPage.cityR.sendKeys(city);
		    RegistrationPage.postcodeR.sendKeys(postcode);
		    Thread.sleep(3000);
	        WebElement drop = RegistrationPage.countryidR;
	        Select A = new Select(drop);
	       A.selectByVisibleText("India");
	       Thread.sleep(3000);
	       WebElement drop1 = RegistrationPage.zoneidR;
	       Select B = new Select(drop1);
	       B.selectByVisibleText("Andhra Pradesh");
		    
	       RegistrationPage.passwordR.sendKeys(password);
	       RegistrationPage.confirmR.sendKeys(confirm);
	       
	       RegistrationPage.SubscribeR.click();
	       RegistrationPage.checkboxR.click();
	       RegistrationPage.continueR.click();
	       
	       
	       
	       //step4
	       Thread.sleep(3000);
	       driver.findElement(By.name("agree")).click();
	       driver.findElement(By.xpath("//input[@type='submit']")).click();
	       
	        Thread.sleep(5000);
	String e= driver.findElement(By.xpath(".//*[@id='content']/h1")).getText();
	Assert.assertEquals(e,"Your Account Has Been Created!");
	        System.out.println("Account created");                                                                  
	                        Thread.sleep(3000);
	                        
	          driver.findElement(By.linkText("Logout")).click();
	          Thread.sleep(2000);
	                        
	          RegistrationPage.Register.click();
	          Thread.sleep(1000);
	                        
	        /*   //step5               
	         //home
	         driver.findElement(By.xpath("//a[@href='http://10.207.182.108:81/opencart/index.php?route=common/home']")).click();
	          System.out.println("Home page");
	           Thread.sleep(2000);


	         //click on Samsung Galaxy tab
	           driver.findElement(By.xpath("//a[@href='index.php?route=product/product&path=57&product_id=49']")).click();
	            System.out.println("Samsung galaxy tab");
	            Thread.sleep(3000);
	            */
	            logger.log(LogStatus.PASS, "Login done Successfully", "Method \"Login\" is passed");
	  }
	 
	 /*-------------------------------------------------------------------------*/
	  
	  //DATAPROVIDER//
	  
	  @DataProvider(name="Registration1")
	  public static Object[][] Register() throws IOException{
		  Object[][] obj=ExcelLogic.readExcel("RegistrationSheet","D:\\Selenium Docs\\InputFiles of Testng\\EclipseInputSheet.xlsx");
		 // System.out.println(obj);
		  return obj;
	  }

	  @DataProvider(name="ReadTheData")
	  public static  Object[][] Review1() throws IOException{
		  Object[][] obj=ExcelLogic.readExcel("ReviewSheet","D:\\Selenium Docs\\InputFiles of Testng\\EclipseInputSheet.xlsx");
		  return obj;
	  }
	 
	 /*------------------------------------------------------------------------*/
		
	  @Test(dataProvider="ReadTheData")
	  
	  public void Review(String name,String review,String rating) throws Exception
	  {
		  
		  logger = extent.startTest("Review");
		  
	      //step6-Click on review tab
	      driver.findElement(By.linkText("Reviews (0)")).click();
	      System.out.println("Clicked on review");
	      
	      //step7&8
	      driver.findElement(By.name("name")).clear();
	      driver.findElement(By.name("name")).sendKeys(name);
	      driver.findElement(By.name("text")).sendKeys(review);
	      driver.findElement(By.xpath("//input[@value='"+rating+"']")).click();
	     
	      
	      /*System.out.println("Please enter the captcha:");
	      Scanner sc = new Scanner(System.in);
	      String Captcha = sc.nextLine();
	      driver.findElement(By.name("captcha")).clear();
	      driver.findElement(By.name("captcha")).sendKeys(Captcha);*/
	      
	      //captcha
	      Thread.sleep(9000);
	      
	      driver.findElement(By.id("button-review")).click();
	      System.out.println("Review done");
	      Thread.sleep(2000);
	      if(review.length()<25){
	    	  String rev=driver.findElement(By.className("warning")).getText();
	    	  Assert.assertTrue(rev.contains("Warning"));
	    	  System.out.println("Warning msg displayed"); 
	      }
	      
	     logger.log(LogStatus.PASS, "Review is successfull", "Method \"Review\" is passed");

	  }
	  
	  //------------------------------------------------------------------------

	  
	  
	@Test(description="Add to wishList")
	  
	  public void Wishlist() throws Exception{
		
		logger = extent.startTest("Wishlist");
		 
	     //step9-Add to WishList
	    Thread.sleep(10000);
	    driver.findElement(By.partialLinkText("Add to Wish List")).click();
	System.out.println("Added to WishList");
	    Thread.sleep(3000);
	    
	    //step10-close the ribbon
	    driver.findElement(By.xpath("//div[@id='notification']/div/img")).click();
	    System.out.println("close the success message of wishlist");
	    
	    //step11-whishlist link
	     Thread.sleep(1000);
	    driver.findElement(By.partialLinkText("Wish List")).click();
	    System.out.println("clicked on Wish list link");    
	    Thread.sleep(2000);
	    //checkpoint
	    String wishlist=driver.findElement(By.id("wishlist-total")).getText();
	    System.out.println("The text of the Wishlist is " + wishlist);
	    String wishlisttext=wishlist.substring(11,wishlist.length()-1);
	    //to convert string to integer
	    int wishlistint=Integer.parseInt(wishlisttext);
	   System.out.println("wish list items" +wishlistint);
	   
	  List<WebElement> Countwishlist=driver.findElements(By.xpath(".//*[@id='content']/div[2]/table/tbody"));
	   System.out.println("List count is " +Countwishlist.size());
	   
	   //comparing Countwishlist.size() and wishlistint
	   Assert.assertEquals("Wishlist Items are not matching", Countwishlist.size(), wishlistint);
	   
	    
	     //step12-Change the currency euro
	    driver.findElement(By.linkText("€")).click();
	    
	  //step13-Flatfile creation
	    File outputfile=new File("D:\\Selenium Docs\\Selenium Eclipse\\Output files\\Unitpricevalue.txt");
	    String value=driver.findElement(By.xpath(".//*[@id='wishlist-row49']/tr/td[5]/div")).getText();
	    System.out.println("Unit Price is" +value);
	    BufferedWriter bw=new BufferedWriter(new FileWriter(outputfile));
	    bw.write("The unit price value is" +value);
	    
	  //step14-Change the currency pound
	    driver.findElement(By.linkText("£")).click();
	    
	  //step15-Flatfile creation
	    String value1=driver.findElement(By.xpath(".//*[@id='wishlist-row49']/tr/td[5]/div")).getText();
	    System.out.println("Unit Price is" +value1);
	    //BufferedWriter bw=new BufferedWriter(new FileWriter(outputfile));
	    bw.newLine();
	    bw.append("The unit price value is" +value1);
	    
	  //step16-Change the currency dollar
	    driver.findElement(By.linkText("$")).click();
	    
	  //step17-Flatfile creation
	    String value2=driver.findElement(By.xpath(".//*[@id='wishlist-row49']/tr/td[5]/div")).getText();
	    System.out.println("Unit Price is" +value2);
	    //BufferedWriter bw=new BufferedWriter(new FileWriter(outputfile));
	    bw.newLine();
	    bw.append("The unit price value is" +value1);
	    bw.close();
	    
	   
	    Thread.sleep(2000);

	    
	      //step18-add to cart
	driver.findElement(By.xpath(".//*[@id='wishlist-row49']/tr/td[6]/img")).click();
	System.out.println("Added to cart");

	     //step19-close the success message of cart button
	    Thread.sleep(3000);
	driver.findElement(By.xpath("//div[@id='notification']/div/img")).click();
	    System.out.println("close the success message of cart");
	    
	     Thread.sleep(1000);
	    
	//step20-My wish list remove icon
	    driver.findElement(By.partialLinkText("Wish List")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//tbody[@id='wishlist-row49']/tr/td[6]/a/img")).click();
	    System.out.println("Removed icon in wishlist");
	    Thread.sleep(3000);
	    //step21-click on continue
	     driver.findElement(By.linkText("Continue")).click();
	    
	     
	     //step22-Logout
	    Thread.sleep(3000);
	    driver.findElement(By.linkText("Logout")).click();


	     String f=driver.findElement(By.xpath(".//*[@id='content']/h1")).getText();
	      Assert.assertEquals(f,"Account Logout");
	                    System.out.println("Logout Successful");
	    
	     
	                    logger.log(LogStatus.PASS, "Method \"Wishlist\" is passed");

		  
	         }
	   

	  }


