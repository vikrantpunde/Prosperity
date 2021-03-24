package Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Util {
	public static final String CHROME_PATH = System.getProperty("user.dir")+"\\chromedriver.exe";
	//public static final String CHROME_PATH = "D:\\chromedriver.exe";
	
	public static final String BASE_URL_guru99 = "https://prosperikey.invisionapp.com/";	
	
	public static String customerID;
	public static final int WAIT_TIME = 30; 
	

	// Valid account for login
	public static final String USER_NAME = "mngr150931@gmail.com";
	public static final String PASSWD = "UdEnYma";
	//Manager home page title
	public static final String Prosp_HP_title="InVision - Sign In";
	public static final String loginCredInvalidText= "You entered an incorrect email, password, or both.";
	public static final String invalidEmailErrorMessage= "That format doesn't look right. Make sure there aren't any typos.";
	public static final String notApprovedDmnErrorMessage= "is no longer an approved domain. Try a different email address,";
	//Invalid username or password after clicked on login button alert message
	public static final String Login_alert_msg="User or Password is not valid";

	public static void setCustomerId(String CustId){
		customerID=CustId;
	}
	
	public static String getScreenshot(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		String path=System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
		
		File destination=new File(path);
		
		try 
		{
			FileUtils.copyFile(src, destination);
		} catch (IOException e) 
		{
			System.out.println("Capture Failed "+e.getMessage());
		}
		
		return path;
	}
}
