package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage
{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(how = How.XPATH, using ="//span[text()='My Account']")
	WebElement MyAccount;

	@FindBy(how = How.XPATH, using = "//a[text()='Register']")
	WebElement RegisterBtn;
	
	@FindBy(how = How.XPATH, using="//a[text()='Login']")
	WebElement LoginBtn;
	
	public void ClickOnMyAccount() {
		MyAccount.click();
	}
	
	public void ClickOnRegister() {
		RegisterBtn.click();
	}
	
	public void ClickOnLoginBtn() {
		LoginBtn.click();
	}
}
