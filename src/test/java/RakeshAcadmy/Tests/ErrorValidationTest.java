package RakeshAcadmy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.CartPage;
import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.CheckoutPage;
import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.ConfirmationMessage;
import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.ProductCatalougue;
import RakeshAcadmy.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {
	
	

	@Test
	public void LoginErrorValidation() throws IOException, InterruptedException {
	String prodcutName = "ZARA COAT 3";
	ProductCatalougue productCatalouge= landingPage.loginApplication("rraakesh777@gmail.com","Java@43221");	
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
	
	}
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
	String prodcutName = "ZARA COAT 3";
	ProductCatalougue productCatalouge= landingPage.loginApplication("rraakesh777@gmail.com","Java@4321");	
	List<WebElement>products= productCatalouge.getProductsList();
	productCatalouge.addProductToCart(prodcutName);	
	CartPage cartPage= productCatalouge.goToCartPage();;
	Boolean match =cartPage.verifyProductDisplay("ZARA COAT 333");
	Assert.assertFalse(match);
	
	
	
	}
}
