package DS.UI_Automation_Framework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DS.UI_Automation_Framework.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> Names;
	
	public Boolean VerifyOrderDisplay(String productName) {
		boolean match = Names.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	

}
