package DriverInstance;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utility.Util;

public class BaseClass {
	 public static WebDriver driver;
	 public static ExtentHtmlReporter htmlReporter;
		public static ExtentReports extent;
		public static ExtentTest test;
		
	@BeforeSuite
	public void extentSetup() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ExtentTestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "Windows 10");
        extent.setSystemInfo("Host Name", "Vikrant");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Vikrant Punde");
        htmlReporter.config().setDocumentTitle("Prosperity Key Report");
        htmlReporter.config().setReportName("Prosperity Key Report");	
			
	}
	 
     @BeforeClass
     public  void runDriver() throws InterruptedException
     {
    	 System.setProperty("webdriver.chrome.driver",Util.CHROME_PATH);
    	 ChromeOptions chromeOptions= new ChromeOptions();
    	 chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(Util.BASE_URL_guru99);
      //  this.driver=driver;
    	
     }
    
     @AfterMethod(alwaysRun = true)
 	public void getResult(ITestResult result) throws IOException {
 		if (result.getStatus() == ITestResult.FAILURE) {
 			String screenShotPath = Util.getScreenshot(driver);
 			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
             test.fail(result.getThrowable());
 			test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
 		} else if (result.getStatus() == ITestResult.SUCCESS) {
 			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
 		} else {
 			test.log(Status.SKIP,
 					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
 			test.skip(result.getThrowable());
 		}
 		
 	}
     @AfterClass
     public void close()        
     {
        driver.close();
     }
     
     @AfterSuite
     public void flush()        
     {
    	 extent.flush();
     }
}
