package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	private WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectedCountry;
	
	@FindBy(className="action__submit")
	WebElement submit;
	
	By CountryLocator = By.cssSelector("[placeholder='Select Country']");
	
	public void selectCountry(String countryName) {
		Actions act = new Actions(driver);
		act.sendKeys(Country, countryName).build().perform();
		waitForElementToAppear(CountryLocator);
		selectedCountry.click();
	}
	
	public OrdersPage placeOrder() {
		submit.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
}
