package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class ProductCatalogue extends AbstractComponent {

	private WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement animatingElement;

	By productLocator = By.className("mb-3");
	By addToCartLocator = By.cssSelector(".card-body button:last-of-type");
	By toastMessageLocator = By.className("toast-message");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productLocator);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement productSelected = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return productSelected;
	}

	public void addProductToCart(String productName) {
		WebElement product = getProductByName(productName);
		System.out.println("Inside addProductToCart: " + product.getText());
		product.findElement(addToCartLocator).click();
		waitForElementToAppear(toastMessageLocator);
		waitForElementToDisappear(animatingElement);
	}

}
