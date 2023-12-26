package DS.UI_Automation_Framework.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		String URL = "https://rahulshettyacademy.com/client/";
		String email = "akshay@gmail.com";
		String password = "Test@123";
		String productName = "ZARA COAT 3";
		String Country = "India";

		// Browser setup
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		// Maximize the browser
		driver.manage().window().maximize();
		// Setting implicit wait for all the steps
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// Defining the object for the explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// Url hitting
		driver.get(URL);
		
		// Login page enter and check and add credentials to it ..
		// wait for login page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='login-title'][text()='Log in']")));
//		driver.findElement(By.xpath("//h1[@class='login-title'][text()='Log in']"));
		// enter Email
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(email);
		// enter Password
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys(password);
		// click on login button
		driver.findElement(By.xpath("//input[@id='login']")).click();

		// wait for page to load and get stable
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Login Successfully']")));
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@aria-label='Login Successfully']")));

		// make the list of elements
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		// grab the prod
		WebElement prod = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		// click add to cart
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		// Wait for page to load and settel
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart']")));
		// click on the cart button
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// My cart page check the availability of product and click on checkout
		// wait for cart page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='My Cart']")));
		// list the elements
		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div.cartSection h3"));
		// compare the product
		Boolean match = cartProducts.stream().anyMatch(s -> s.getText().equals(productName));
		Assert.assertTrue(match);
		// click on the checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();

		// wait for payment method page
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='payment__title'][contains(text(),'Payment Method')]")));

		// Enter the country name and select
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), Country).build().perform();
		List<WebElement> countryies = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		WebElement country = countryies.stream().filter(s -> s.getText().equalsIgnoreCase(Country)).findAny()
				.orElse(null);
		country.click();

		// click on the place order
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();

		// wait and vanish for the order placed successfully msg
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@aria-label='Order Placed Successfully']")));
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@aria-label='Order Placed Successfully']")));

		// thank you page verification ...
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText(),
				"THANKYOU FOR THE ORDER.");

		driver.close();
		System.out.println("Completed");
	}

}
