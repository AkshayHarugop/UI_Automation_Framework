package DS.UI_Automation_Framework.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import DS.UI_Automation_Framework.TestComponents.BaseTest;
import DS.UI_Automation_Framework.pageobjects.CartPage;
import DS.UI_Automation_Framework.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	public ErrorValidationsTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void LoginErrorValidations() throws IOException {
		System.out.println("LoginErrorValidations test execution "+Thread.currentThread().getId());
		landingPage.loginApplication(prop.getProperty("email"), prop.getProperty("Wpassword"));
		String errorMsg = landingPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Incorrect email or password.");
	}
	
	@Test
	public void ProductErrorValidations() throws IOException {
		System.out.println("ProductErrorValidations test execution "+Thread.currentThread().getId());
		ProductCatalogue productCatalogue = landingPage.loginApplication(prop.getProperty("email"), prop.getProperty("password"));
		productCatalogue.addProductToCart(prop.getProperty("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(prop.getProperty("WproductName"));
		Assert.assertFalse(match);
	}

}