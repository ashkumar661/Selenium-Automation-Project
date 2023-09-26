package testComponenents;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.OrdersPage;
import pageObjects.ProductCatalogue;

public class EndToEndTest extends BaseTest {
	
	WebDriver driver;

	@Test(dataProvider = "getData")
	public void productSelection(HashMap<String,String> input) throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goTocart();
		Boolean match = cartPage.verifyProductInCart(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.checkout();
		checkoutPage.selectCountry(input.get("country"));
		OrdersPage ordersPage = checkoutPage.placeOrder();
		String orderConfirmationMessage = ordersPage.retreiveMessage();
		Assert.assertEquals(orderConfirmationMessage, "THANKYOU FOR THE ORDER.");
	}
	
	@DataProvider
	public Object[][] getData(){
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "ashok123@gmail.com");
		map1.put("password", "Ashok@123");
		map1.put("product", "ADIDAS ORIGINAL");
		map1.put("country", "India");
		
		HashMap<String,String> map2 = new HashMap<String,String>();
		map2.put("email", "amit123@gmail.com");
		map2.put("password", "Amit@123");
		map2.put("product", "IPHONE 13 PRO");
		map2.put("country", "India");
		return new Object[][] {{map1},{map2}};
	}
}
