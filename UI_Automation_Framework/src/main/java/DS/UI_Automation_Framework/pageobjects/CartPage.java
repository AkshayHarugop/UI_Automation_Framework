package DS.UI_Automation_Framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DS.UI_Automation_Framework.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="div.cartSection h3")
	List<WebElement> cartProducts;
	
	By MyCart = By.xpath("//h1[text()='My Cart']");
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public List<WebElement> listCartProducts() {
		waitForElementToAppear(MyCart);
		return cartProducts;
	}
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = listCartProducts().stream().anyMatch(s -> s.getText().equals(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkout.click();
		return new CheckoutPage(driver);
	}

}
