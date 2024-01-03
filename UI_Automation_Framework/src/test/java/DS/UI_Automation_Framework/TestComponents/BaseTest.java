package DS.UI_Automation_Framework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import DS.UI_Automation_Framework.pageobjects.LandingPage;
import DS.UI_Automation_Framework.resources.ExtentReportNG;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends ExtentReportNG {

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

//		String BrowserName = prop.getProperty("browser");
		String BrowserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		String browserName = prop.getProperty("browser");

		if (BrowserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if (BrowserName.equalsIgnoreCase("chromeheadless")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			browserName.equalsIgnoreCase("chromeheadless");
			options.addArguments("headless");
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		} else if (BrowserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
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

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File Dest = new File(System.getProperty("user.dir") + "\\reports\\" + ExtentReportNG.TimeStamp + "\\"
				+ testCaseName + "_" + getCurrentDate() + "_" + getCurrentTime() + ".png");
		FileUtils.copyFile(source, Dest);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + "_" + getCurrentDate() + "_"
				+ getCurrentTime() + ".png";
	}

	private static String getCurrentDate() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM_dd_YYYY");
		Date now = new Date();
		return sdfDate.format(now);
	}

	private static String getCurrentTime() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("HH_mm_ss");
		Date now = new Date();
		return sdfDate.format(now);
	}

}