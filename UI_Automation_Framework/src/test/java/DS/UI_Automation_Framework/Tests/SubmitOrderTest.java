package DS.UI_Automation_Framework.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import DS.UI_Automation_Framework.TestComponents.BaseTest;
import DS.UI_Automation_Framework.pageobjects.CartPage;
import DS.UI_Automation_Framework.pageobjects.CheckoutPage;
import DS.UI_Automation_Framework.pageobjects.ConfirmationPage;
import DS.UI_Automation_Framework.pageobjects.OrderPage;
import DS.UI_Automation_Framework.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	public SubmitOrderTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void submitOrder() throws IOException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(prop.getProperty("email"),
				prop.getProperty("password"));
		productCatalogue.addProductToCart(prop.getProperty("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(prop.getProperty("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage ConfirmationPage = checkoutPage.selectCountry(prop.getProperty("Country"));
		String msg = ConfirmationPage.verifyMsg();
		Assert.assertEquals(msg, "THANKYOU FOR THE ORDER.");
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication(prop.getProperty("email"),
				prop.getProperty("password"));
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Boolean match = orderPage.VerifyOrderDisplay(prop.getProperty("productName"));
		Assert.assertTrue(match);
	}

	@Test(dataProvider = "getData")
	public void submitOrderWithDataProvider(String Email, String Password, String productName, String Country)
			throws IOException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(Email, Password);
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage ConfirmationPage = checkoutPage.selectCountry(Country);
		String msg = ConfirmationPage.verifyMsg();
		Assert.assertEquals(msg, "THANKYOU FOR THE ORDER.");
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "test_all_leader_ds2@gmail.com", "Test@123", "IPHONE 13 PRO", "India" },
				{ "test_all_leader_ds3@gmail.com", "Test@123", "ADIDAS ORIGINAL", "Maldives" } };
	}

	@Test(dataProvider = "getDataHashmap")
	public void submitOrderWithHashmap(HashMap<String, String> input) throws IOException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("EmailId"), input.get("Password"));
		productCatalogue.addProductToCart(input.get("ProductName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("ProductName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage ConfirmationPage = checkoutPage.selectCountry(input.get("Country"));
		String msg = ConfirmationPage.verifyMsg();
		Assert.assertEquals(msg, "THANKYOU FOR THE ORDER.");
	}

	@DataProvider
	public Object[][] getDataHashmap() {
		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
		map1.put("EmailId", "test_all_leader_ds2@gmail.com");
		map1.put("Password", "Test@123");
		map1.put("ProductName", "IPHONE 13 PRO");
		map1.put("Country", "India");
		HashMap<Object, Object> map2 = new HashMap<Object, Object>();
		map2.put("EmailId", "test_all_leader_ds3@gmail.com");
		map2.put("Password", "Test@123");
		map2.put("ProductName", "ADIDAS ORIGINAL");
		map2.put("Country", "Maldives");
		return new Object[][] { { map1 }, { map2 } };
	}

	@Test(dataProvider = "getDataJson")
	public void submitOrderWithJson(HashMap<String, String> input) throws IOException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("EmailId"), input.get("Password"));
		productCatalogue.addProductToCart(input.get("ProductName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("ProductName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage ConfirmationPage = checkoutPage.selectCountry(input.get("Country"));
		String msg = ConfirmationPage.verifyMsg();
		Assert.assertEquals(msg, "THANKYOU FOR THE ORDER.");
	}

	@DataProvider
	public Object[][] getDataJson() throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir")
						+ "\\src\\test\\java\\DS\\UI_Automation_Framework\\Data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}

	public String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File Dest = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + "_" + getCurrentDate()+"_"+getCurrentTime() + ".png");
		FileUtils.copyFile(source, Dest);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + "_" + getCurrentDate()+"_"+getCurrentTime() + ".png";
	}

	private static String getCurrentDate() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd-YYYY");
		Date now = new Date();
		return sdfDate.format(now);
	}

	private static String getCurrentTime() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date();
		return sdfDate.format(now);
	}

}
