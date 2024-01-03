package DS.UI_Automation_Framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DS.UI_Automation_Framework.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By loginpage = By.xpath("//h1[@class='login-title'][text()='Log in']");

	@FindBy(id = "userEmail")
	WebElement userName;

	@FindBy(id = "userPassword")
	WebElement passWord;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
	WebElement element;
	
	@FindBy(css="div.toast-message")
	WebElement errormsg;

	public ProductCatalogue loginApplication(String email, String password) {
		userName.sendKeys(email);
		passWord.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}

	public void goTo(String URL) {
		driver.get(URL);
		waitForElementToAppear(loginpage);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(loginpage));
	}
	
	public void getErrorMessage() {
		waitForElementToAppear(element);
//		return errormsg.getText();
	}

}
