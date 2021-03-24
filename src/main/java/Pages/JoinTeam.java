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
import Pages.LoginPage;

public class JoinTeam {
	
	
	@FindBy(id="self_invite_email")
	WebElement email;
	
	
	@FindBy(xpath="//input[@id='self_invite_email']//following::div[1][contains(text(),'That format')]")
	WebElement invalidEmailText;
	
	@FindBy(xpath="//button[contains (text(),'Continue')]")
	WebElement continueBtn;
	
	@FindBy(xpath="//div[contains(text(),'approved domain')]")
	WebElement notAprDmnMsg;
	
	@FindBy(tagName="iframe")
	WebElement frame;
	
	WebDriver driver;
	WebDriverWait wait;
	LoginPage lp;
	
	public JoinTeam(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(this.driver,15);
		switchToIFrame();
		lp=new LoginPage(driver);
		lp.clickJoinATeamLink();
	}
	
	public void enterEmail(String user) throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOf(email));	
		if(email.isDisplayed()){
		email.clear();
		email.sendKeys(user);
		}
	}
	
	
	public void clickContinue(){
		wait.until(ExpectedConditions.visibilityOf(continueBtn));
		continueBtn.click();
	}
	

	public void switchToIFrame(){
		WebElement el = (WebElement) ((JavascriptExecutor) driver).executeScript("return window.frameElement");
		if(el!=frame)
		{
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("feature-frame:auth-ui"));
		}
		
	}
	
	public String invalidEmailText(){
		wait.until(ExpectedConditions.visibilityOf(invalidEmailText));
		String text = invalidEmailText.getText();
		return text;
	}
	
	public String notApprovedDomain(){
		wait.until(ExpectedConditions.visibilityOf(notAprDmnMsg));
		String text = notAprDmnMsg.getText();
		return text;
	}
}
