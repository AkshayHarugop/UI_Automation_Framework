package UI_Automation_Framework.stepDefination;

import java.io.IOException;

import org.testng.Assert;

import DS.UI_Automation_Framework.TestComponents.BaseTest;
import DS.UI_Automation_Framework.pageobjects.CartPage;
import DS.UI_Automation_Framework.pageobjects.CheckoutPage;
import DS.UI_Automation_Framework.pageobjects.ConfirmationPage;
import DS.UI_Automation_Framework.pageobjects.LandingPage;
import DS.UI_Automation_Framework.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImplimentation extends BaseTest {

	public StepDefinationImplimentation() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public LandingPage LandingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage ConfirmationPage;
	
	@Given("I landed on the Ecommerce page")
	public void I_landed_on_the_Ecommerce_page() {
		try {
			LandingPage = launchApplication();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Given("^Logged in with the username (.+) and password (.+)$")
	public void Logged_in_username_and_password(String username, String password){
		productCatalogue = landingPage.loginApplication(username,	password);
	}
	
	@When("^I add the product (.+) from the cart$")
	public void I_add_Product_to_Cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage = checkoutPage.selectCountry(prop.getProperty("Country"));
	}
	
	@Then("{string} message is displayed on the cofirmationPage.")
	public void messageDisplayedConfirmationPage(String string) {
		String msg = ConfirmationPage.verifyMsg();
		Assert.assertEquals(msg, "THANKYOU FOR THE ORDER.");
		driver.close();
	}
	
	@Then("Verify the error msg")
	public void Verify_the_error_msg() {
		landingPage.getErrorMessage();
		driver.close();
	}
}
