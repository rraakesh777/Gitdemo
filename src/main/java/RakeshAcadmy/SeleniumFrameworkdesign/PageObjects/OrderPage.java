package RakeshAcadmy.SeleniumFrameworkdesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import RakeshAcadmey.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	WebDriver driver;
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productName;
	
	@FindBy(css = ".totalRow button")
	WebElement CheckoutEle;
	;
	
	
	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver =driver;
	}
	public  Boolean verifyOrderDisplay(String prodcutName ) {
		
		Boolean match= productName.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(prodcutName));
		return match;
		
	}
	public CheckoutPage goToCheckout() {
		CheckoutEle.click();
		return new CheckoutPage(driver);
	}
}
