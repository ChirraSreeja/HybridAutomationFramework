package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.XPATH, using="//input[@name='firstname']")
	WebElement FirstName;
	
	@FindBy(how = How.XPATH, using = "//input[@name='lastname']")
	WebElement LastName;
	
	@FindBy(how = How.XPATH, using="//input[@name='email']")
	WebElement Email;
	
	@FindBy(how = How.XPATH, using="//input[@name='telephone']")
	WebElement TelePhoneNumber;
	
	@FindBy(how = How.XPATH, using="//input[@name='password']")
	WebElement Password;
	
	@FindBy(how=How.XPATH,using="//input[@name='confirm']")
	WebElement ConfirmPassword;
	
	@FindBy(how = How.XPATH, using = "//input[@name='agree']")
	WebElement PrivacyPolicy;
	
	@FindBy(how = How.XPATH,using="//input[@type='submit']")
	WebElement ContinueBtn;
	
	@FindBy(how= How.XPATH, using="//h1[text()='Your Account Has Been Created!']")
	WebElement ConfirmationMsg;
	
	
	public void AddFirstName(String FN) {
		FirstName.sendKeys(FN);
	}
	public void AddLastName(String LN) {
		LastName.sendKeys(LN);
	}
	public void AddEmail(String email) {
		Email.sendKeys(email);
	}
	public void AddTelephoneNumber(String Number) {
		TelePhoneNumber.sendKeys(Number);
	}
	public void AddPassword(String PWD) {
		Password.sendKeys(PWD);
	}
	public void ConfirmPassword(String PWD) {
		ConfirmPassword.sendKeys(PWD);
	}
	public void AcceptPrivacyPolicy() {
		PrivacyPolicy.click();
	}
	public void ClickOkBtn() {
		ContinueBtn.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return (ConfirmationMsg.getText());
		}
       catch(Exception e) {
    	   return (e.getMessage());
	   }
	}

}
