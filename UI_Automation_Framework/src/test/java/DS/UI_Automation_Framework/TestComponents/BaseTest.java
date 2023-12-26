package DS.UI_Automation_Framework.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import DS.UI_Automation_Framework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public Properties prop;
	public LandingPage landingPage;
	
	public BaseTest() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//DS//UI_Automation_Framework//resources//GlobalData.properties");
		prop.load(fis);
	
	}

	public WebDriver driver;
	
	public WebDriver initializeDriver() throws IOException {
		
		String BrowserName = prop.getProperty("browser");
		if (BrowserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(BrowserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		int impliWait = Integer.parseInt(prop.getProperty("implicitlyWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(impliWait));
		return driver;
	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		String URL = prop.getProperty("URL");
		landingPage.goTo(URL);
		return new LandingPage(driver);
	}
	
	@AfterMethod
	public void closeApplication() {
		driver.close();
		System.out.println("Completed");
	}

}
