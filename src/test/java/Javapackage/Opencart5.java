package Javapackage;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Opencart5 {

  
	  FileInputStream File;
	  Properties pro;
	  
	  WebDriver driver;
	  String str1;
	  String url;
	 
	  
	  @BeforeClass
          public void f() throws Exception {
		  File=new FileInputStream(new File("D:\\Selenium Docs\\Selenium Eclipse\\Test1\\Repo.properties"));
		  pro=new Properties();
		  pro.load(File);
		  
		  //Grid
		  
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
	  
	  @Test(description="Login")
	  public void Login() throws Exception{
		  
		  Object extent;
		
		 
		  
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
		  
  }
}
