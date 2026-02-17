package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass
{	
	@Test(groups ={"Regression","Master"})
	public void Register() {
		
		logger.info("***** Starting TC001_AccountRegistrationTest  ****");
		logger.debug("This is a debug log message");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.ClickOnMyAccount();
		logger.info("Clicked on MyAccount Link.. ");
		
		hp.ClickOnRegister();
		logger.info("Clicked on Register Link.. ");
		
		
		RegistrationPage rp = new RegistrationPage(driver);
		
		logger.info("Providing customer details...");
		rp.AddFirstName(GenerateRandomString().toUpperCase());
		rp.AddLastName(GenerateRandomString().toUpperCase());
		rp.AddEmail(GenerateRandomString()+"@gmail.com");
		rp.AddTelephoneNumber(GenerateRandomNumber());
		
		String pwd = randomAlphaNumeric();
		rp.AddPassword(pwd);
		rp.ConfirmPassword(pwd);
		
		rp.AcceptPrivacyPolicy();
		rp.ClickOkBtn();
		
		logger.info("Validating expected message..");
		
		String confmsg = rp.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");

		logger.info("Test passed");
		
		}
		catch(Exception e) {
			
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		finally {
			logger.info("***** Finished TC001_AccountRegistrationTest *****");
		}
		
	}
	
	

}
