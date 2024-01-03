package DS.UI_Automation_Framework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DS.UI_Automation_Framework.pageobjects.CartPage;
import DS.UI_Automation_Framework.pageobjects.OrderPage;

public class AbstractComponents {
	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement routerLink;

	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	@FindBy(xpath="//h1[@class='ng-star-inserted'][text()='Your Orders']")
	WebElement OrderPage;
	
	@FindBy(xpath="//div[@class='heading cf']/h1[text()='My Cart']")
	WebElement Mycart;

	public CartPage goToCartPage() {
		routerLink.click();
		waitForElementToAppear(Mycart);
		return new CartPage(driver);
	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
//		waitForElementToAppear(OrderPage);
		return new OrderPage(driver);
	}

	public void waitForElementToAppear(By ByElement) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ByElement));
	}

	public void waitForElementToDisAppear(By ByElement) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ByElement));
	}

	public void waitForElementToAppear(WebElement WebElement) {
		wait.until(ExpectedConditions.visibilityOf(WebElement));
	}
}
