package Tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import DriverInstance.BaseClass;
import Pages.JoinTeam;
import Utility.Util;

public class JoinPageTest extends BaseClass{
	
		public WebDriver driver;
		WebDriverWait wait;
		JoinTeam obj;
	  
		
		@BeforeClass
	  public void setup() {
		obj=new JoinTeam(BaseClass.driver);	
		this.driver=BaseClass.driver;
	  }	

	  @Test(groups={"smoke"})
	  public void invalidEmaildTest() throws Exception{
		  test = extent.createTest("invalidEmaildTest");
		  obj.enterEmail("ABA");
		  String text = obj.invalidEmailText();
		  assertTrue(text.equals(Util.invalidEmailErrorMessage)); 
	  }
	  
	  @Test(groups={"smoke"})
	  public void notApprovedDomainMessageTest() throws Exception{
		  test = extent.createTest("notApproveddomainMessageTest");
		  obj.enterEmail("ABA@abc.com");
		  obj.clickContinue();
		  String text = obj.notApprovedDomain(); 
		  assertTrue(text.contains(Util.notApprovedDmnErrorMessage)); 
	  }
}
