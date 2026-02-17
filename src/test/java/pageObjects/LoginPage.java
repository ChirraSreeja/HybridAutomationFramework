package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.XPATH, using="//input[@name='email']")
	WebElement EmailAddress;
	
	@FindBy(how = How.XPATH,using="//input[@name='password']")
	WebElement Password;
	
	@FindBy(how = How.XPATH,using="//input[@type='submit']")
	WebElement LoginBtn;
	
	public void EnterEmailID(String email) {
		EmailAddress.sendKeys(email);
	}
	
	public void EnterPassword(String PWD) {
		Password.sendKeys(PWD);
	}
	
	public void ClickOnLogin() {
		LoginBtn.click();
	}

}
