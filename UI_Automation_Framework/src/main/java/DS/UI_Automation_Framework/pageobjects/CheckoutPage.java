package DS.UI_Automation_Framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DS.UI_Automation_Framework.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By PaymentPage = By.xpath("//div[@class='payment__title'][contains(text(),'Payment Method')]");
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement CountryField;
	
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	List<WebElement> countries;
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeHolder;
	
	public void enterCountry(String Country) {
		waitForElementToAppear(PaymentPage);
		Actions a = new Actions(driver);
		a.sendKeys(CountryField,Country).build().perform();
	}
	
	public List<WebElement> listCountries(String Country) {
		enterCountry(Country);
		return countries;
	}
	
	public ConfirmationPage selectCountry(String Country) {
		WebElement country = listCountries(Country).stream().filter(s -> s.getText().equalsIgnoreCase(Country)).findAny().orElse(null);
		country.click();
		placeHolder.click();
		return new ConfirmationPage(driver);
	}
	
}
