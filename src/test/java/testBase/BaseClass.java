package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;


public class BaseClass {
	
	static public WebDriver driver;
	  public Properties p;
	  public Logger logger;
		

	@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os", "browser"})
	public void SetUp(String os, String Browser) throws IOException {
		
		//Loading config.properties file
		
	  FileReader file = new FileReader("./src//test//resources//config.properties");
		
		p = new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());//Log4j	
		
		String env = p.getProperty("execution_env");
		System.out.println("The environment is"+env);
		
		if(env.equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else {
				System.out.print("Invalid OS System");
				return;
			}
			
		//browser
			switch(Browser.toLowerCase()) {
			
			case "chrome": cap.setBrowserName("chrome");break;
			case "edge": cap.setBrowserName("MicrosoftEdge");break;
			case "firefox": cap.setBrowserName("FireFox");break;
			default: System.out.print("Invalid Browser Entered"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			
		}
		if(env.equalsIgnoreCase("local")) {
			
			switch(Browser.toLowerCase()) {
			
			          case "chrome": driver = new ChromeDriver(); break;
			          case "firefox": driver = new FirefoxDriver(); break;
			          case "edge": driver = new EdgeDriver();break;
			          default: System.out.print("Invalid Browser");return;
			}
		}
		else {
		    throw new RuntimeException("Invalid execution_env value"+env);
		}
		
		
		
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appURL")); // Reading URL from Config.properties file.
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void TearDown() {
		driver.quit();
	}
	
	public String GenerateRandomString() {
		RandomStringGenerator generator = new RandomStringGenerator.Builder() 
				.withinRange('0', '9')
	            .withinRange('a', 'z')
	            .withinRange('A', 'Z')
	            .build();
		
				  return generator.generate(5);
	}
	public String GenerateRandomNumber() {
		RandomStringGenerator generator = new RandomStringGenerator.Builder() 
				.withinRange('0', '9')
	            .build();
		
				  return generator.generate(10);
	}
	
	public String randomAlphaNumeric()
	{
		RandomStringGenerator generator1 = new RandomStringGenerator.Builder() 
				.withinRange('0', '9')
	            .withinRange('a', 'z')
	            .withinRange('A', 'Z')
	            .build();
		RandomStringGenerator generator2 = new RandomStringGenerator.Builder() 
				.withinRange('0', '9')
	            .build();
		
		return (generator1.generate(3)+"@"+generator2.generate(4));
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}
