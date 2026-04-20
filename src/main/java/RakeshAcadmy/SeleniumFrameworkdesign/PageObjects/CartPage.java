package RakeshAcadmy.SeleniumFrameworkdesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import RakeshAcadmey.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartproducts;
	
	@FindBy(css = ".totalRow button")
	WebElement CheckoutEle;
	;
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver =driver;
	}
	public  Boolean verifyProductDisplay(String prodcutName ) {
		
		Boolean match= cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(prodcutName));
		return match;
		
	}
	public CheckoutPage goToCheckout() {
		CheckoutEle.click();
		return new CheckoutPage(driver);
	}

}
