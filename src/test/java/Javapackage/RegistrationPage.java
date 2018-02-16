package Javapackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	
	 WebDriver driver;
     
	 
     public RegistrationPage(WebDriver driver)
     {
           this.driver=driver;
           PageFactory.initElements(driver, this);
     }
     
     
     @FindBy(linkText="create an account") public  WebElement Register;
     @FindBy(name="firstname") public  WebElement fnameR;
     @FindBy(name="lastname") public  WebElement LnameR;
     @FindBy(name="email") public  WebElement emailR;
     @FindBy(name="telephone") public  WebElement telephoneR;
     @FindBy(name="fax") public  WebElement faxR;
     @FindBy(name="company") public  WebElement companyR;
     @FindBy(name="company_id") public  WebElement companyidR;
     @FindBy(name="address_1") public  WebElement address1R;
     @FindBy(name="address_2") public  WebElement address2R;
     @FindBy(name="city") public  WebElement cityR;
     @FindBy(name="postcode") public  WebElement postcodeR;
     @FindBy(name="country_id") public  WebElement countryidR;
     @FindBy(name="zone_id") public  WebElement zoneidR;
     @FindBy(name="password") public  WebElement passwordR;
     @FindBy(name="confirm") public  WebElement confirmR;
     @FindBy(xpath="//*[@id='content']/form/div[4]/table/tbody/tr/td[2]/input[1]") public  WebElement SubscribeR;
     @FindBy(xpath=".//*[@id='content']/form/div[5]/div/a/b") public  WebElement checkboxR;
     @FindBy(xpath=".//*[@id='cboxClose']") public  WebElement continueR;
     
     
} 
