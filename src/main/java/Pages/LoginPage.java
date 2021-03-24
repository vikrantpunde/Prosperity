package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utility.Util;
public class LoginPage {
	
	
	@FindBy(id="signin_email")
	WebElement email;
	
	@FindBy(linkText="join the team")
	WebElement joinTeamLink;
	
	@FindBy(id="signin_password")
	WebElement pass;
	
	@FindBy(xpath="//button[contains (text(),'Sign in')]")
	WebElement signin;
	
	@FindBy(xpath="//span[1]")
	WebElement invalidText;
	
	@FindBy(xpath="//input[@id='signin_email']//following::div[1][contains(text(),'That format')]")
	WebElement invalidEmailText;
	
	@FindBy(tagName="iframe")
	WebElement frame;
	
	WebDriver driver;
	WebDriverWait wait;
	
	
	public LoginPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(this.driver,15);
	}
	
	public void enterUsername(String user) throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOf(email));	
		if(email.isDisplayed()){
		email.clear();
		email.sendKeys(user);
		}
	}
	
	
	public void enterPassword(String passwrd){
		wait.until(ExpectedConditions.visibilityOf(pass));
		if(pass.isDisplayed()){
		pass.clear();
		pass.sendKeys(passwrd);
		}
	}
	
	public void clickSubmit(){
		wait.until(ExpectedConditions.visibilityOf(signin));
		signin.click();
	}
	public String getTitle(){
		return driver.getTitle();
	}

	public void switchToIFrame(){
		WebElement el = (WebElement) ((JavascriptExecutor) driver).executeScript("return window.frameElement");
		if(el!=frame)
		{
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("feature-frame:auth-ui"));
		}
	}
	public void clickJoinATeamLink(){
		wait.until(ExpectedConditions.visibilityOf(joinTeamLink));	
		joinTeamLink.click();
	}
	public String invalidTextPresent(){
		wait.until(ExpectedConditions.visibilityOf(invalidText));
		return invalidText.getText();
	}
	
	public String invalidEmailText(){
		wait.until(ExpectedConditions.visibilityOf(invalidEmailText));
		return invalidEmailText.getText();
	}
	
	
}
