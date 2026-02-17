package testCases;

import org.testng.annotations.Test;

import org.testng.Assert;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginPageTest extends BaseClass{

	@Test(groups={"Sanity","Master"})
	public void Login() {
		
		
		logger.info("**** Starting TC_002_LoginTest  ****");
		logger.debug("capturing application debug logs....");
		
		try {
			
			//Home Page
			HomePage hp = new HomePage(driver);
			hp.ClickOnMyAccount();
			logger.info("clicked on myaccount link on the home page..");
			hp.ClickOnLoginBtn();
			logger.info("clicked on login link under myaccount..");
			
			
			//Login Page
			LoginPage lp = new LoginPage(driver);
			logger.info("Entering valid email and password..");
			lp.EnterEmailID(p.getProperty("email")); // Fetching details from config.properties
			lp.EnterPassword(p.getProperty("password")); //Fetching from config.properties
			lp.ClickOnLogin();
			logger.info("clicked on ligin button..");
			
			
			//My Account
			MyAccountPage map = new MyAccountPage(driver);
			boolean targetPage = map.isMyAccountPageExists();
			
			Assert.assertTrue(targetPage);
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("**** Finished TC_002_LoginTest  ****");
	}
}
