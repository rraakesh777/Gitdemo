package RakeshAcadmy.SeleniumFrameworkdesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import RakeshAcadmey.AbstractComponents.AbstractComponents;

public class ProductCatalougue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalougue(WebDriver driver) {
		// intiallization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// WebElement userEmail= driver.findElement(By.id("userEmail"));
	// List <WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
	// pagefactoorty

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement sppiner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");	
	By toastMessage =By.cssSelector("#toast-container");

	public List<WebElement> getProductsList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String prodcutName) {

		WebElement prod = getProductsList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(prodcutName)).findFirst().orElse(null);
		return prod;

	}
	
	public void addProductToCart(String prodcutName) throws InterruptedException {
		 
		WebElement prod = getProductByName(prodcutName);
		prod.findElement(addToCart).click();	
		waitForElementToAppear(toastMessage);
		waitForElementToDisppear(sppiner);
	}

}
