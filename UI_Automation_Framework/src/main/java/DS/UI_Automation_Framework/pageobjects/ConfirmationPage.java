package DS.UI_Automation_Framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import DS.UI_Automation_Framework.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By orderPlacedSucc = By.xpath("//div[@aria-label='Order Placed Successfully']");
	
	@FindBy(xpath = "//h1[@class='hero-primary']")
	WebElement msg;
	
	public String verifyMsg() {
		waitForElementToAppear(orderPlacedSucc);
		waitForElementToDisAppear(orderPlacedSucc);
		return msg.getText();
	}
	
}
