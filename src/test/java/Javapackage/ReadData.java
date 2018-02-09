package Javapackage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class ReadData {
	WebDriver driver;
  @Test
  public void f() {
		
	
		  System.setProperty("webdriver.chrome.driver", "D:\\Selenium Docs\\Selenium Drivers\\chromedriver.exe");
		   driver=new ChromeDriver();
		 //Step1-Launch opencart
			  driver.get("http://10.207.182.108:81/opencart/");
				driver.manage().window().maximize();
		        driver.findElement(By.xpath("//a[@href='http://10.207.182.108:81/opencart/index.php?route=account/register']")).click();		  
	  }
	  /*------------------------------------------------------------------------*/
	   
	  @Test(dataProvider="Registration1",dependsOnMethods="f")
	  public void Createaccount(String fname,String lname,String email,String phone,String fax,String company,String companyid,String address1,String address2,String city,String postcode,String password,String confirm) throws Exception
	  {
		 
	//step2-Create an account
	       
	//step3-fill details
	        driver.findElement(By.name("firstname")).sendKeys(fname);
	        driver.findElement(By.name("lastname")).sendKeys(lname);
	        driver.findElement(By.name("email")).sendKeys(email);
	        driver.findElement(By.name("telephone")).sendKeys(phone);
	        driver.findElement(By.name("fax")).sendKeys(fax);
	        driver.findElement(By.name("company")).sendKeys(company);         
	        driver.findElement(By.name("company_id")).sendKeys(companyid);
	        driver.findElement(By.name("address_1")).sendKeys(address1);
	        driver.findElement(By.name("address_2")).sendKeys(address2);
	        driver.findElement(By.name("city")).sendKeys(city);
	        driver.findElement(By.name("postcode")).sendKeys(postcode);
	        Thread.sleep(3000);
	        WebElement drop = driver.findElement(By.name("country_id"));
	        Select A = new Select(drop);
	       A.selectByVisibleText("India");
	       Thread.sleep(3000);
	       WebElement drop1 = driver.findElement(By.name("zone_id"));
	       Select B = new Select(drop1);
	       B.selectByVisibleText("Andhra Pradesh");
	       
	        driver.findElement(By.name("password")).sendKeys(password);
	       driver.findElement(By.name("confirm")).sendKeys(confirm);
	       driver.findElement(By.xpath("//*[@id='content']/form/div[4]/table/tbody/tr/td[2]/input[1]")).click();
	       driver.findElement(By.xpath(".//*[@id='content']/form/div[5]/div/a/b")).click();
	       driver.findElement(By.xpath(".//*[@id='cboxClose']")).click();   
	       Thread.sleep(3000);
	       
	       //step4
	       driver.findElement(By.name("agree")).click();
	       driver.findElement(By.xpath("//input[@type='submit']")).click();
	       
	        Thread.sleep(5000);
	String e= driver.findElement(By.xpath(".//*[@id='content']/h1")).getText();
	Assert.assertEquals(e,"Your Account Has Been Created!");
	        System.out.println("Account created");                                                                  
	                        Thread.sleep(3000);
	                        
	           //step5             
	         //home
	         driver.findElement(By.xpath("//a[@href='http://10.207.182.108:81/opencart/index.php?route=common/home']")).click();
	          System.out.println("Home page");
	           Thread.sleep(2000);


	         //click on Samsung Galaxy tab
	           driver.findElement(By.xpath("//a[@href='index.php?route=product/product&path=57&product_id=49']")).click();
	            System.out.println("Samsung galaxy tab");
	            Thread.sleep(3000);
	  }
	 
	 
	  
	  @DataProvider(name="Registration1")
	  public static Object[][] Register() throws IOException{
		  Object[][] obj=ExcelLogic.readExcel("RegistrationSheet","D:\\Selenium Docs\\InputFiles of Testng\\EclipseInputSheet.xlsx");
		 // System.out.println(obj);
		  return obj;
	  }
	  
  }

