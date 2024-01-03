package DS.UI_Automation_Framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DS.UI_Automation_Framework.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	By loginSuccessful = By.xpath("//div[@aria-label='Login Successfully']");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	By toastMsg = By.cssSelector("#toast-container");
	By animation = By.cssSelector(".ng-animating");
	By routerlink = By.cssSelector("[routerlink*='cart']");
	
	
	
	public List<WebElement> getProductList() {
//		waitForElementToAppear(loginSuccessful);
//		waitForElementToDisAppear(loginSuccessful);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		waitForElementToDisAppear(animation);
		waitForElementToAppear(routerlink);
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
