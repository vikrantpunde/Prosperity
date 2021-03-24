package Tests;

import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Console;

import DriverInstance.BaseClass;

import Pages.LoginPage;
import Utility.Util;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
public class LoginTest extends BaseClass{
	
	public WebDriver driver;
	WebDriverWait wait;
	LoginPage obj;
  
	
	@BeforeClass
  public void setup() {
	obj=new LoginPage(BaseClass.driver);	
	this.driver=BaseClass.driver;
	obj.switchToIFrame();
  }	

  @Test(groups={"smoke"})
  public void invalidUsernamePasswordTest() throws Exception{
	  test = extent.createTest("invalidUsernamePasswordTest");
	  obj.enterUsername(Util.USER_NAME);
	  obj.enterPassword(Util.PASSWD);
	  obj.clickSubmit();
	  String text = obj.invalidTextPresent();
	  assertTrue(text.equals(Util.loginCredInvalidText)); 
  }
  
  @Test(groups={"smoke"})
  public void invalidEmailAddressTest() throws Exception{
		test = extent.createTest("invalidEmailAddressTest");
	  obj.enterUsername("ABC");
	  obj.enterPassword(Util.PASSWD);
	  String text = obj.invalidEmailText();
	  System.out.println(text);
	  System.out.println(Util.invalidEmailErrorMessage);
	  assertTrue(text.equals(Util.invalidEmailErrorMessage));
  }
  
}
