package RakeshAcadmy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.CartPage;
import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.CheckoutPage;
import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.ConfirmationMessage;
import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.LandingPage;
import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.ProductCatalougue;
import RakeshAcadmy.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl  extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalougue productCatalouge;
	public ConfirmationMessage confirmationMessage;
	
	@Given("I landed on Ecommerce Page")

	public void I_landed_on_Ecommerce_Page() throws IOException
	
	{
		landingPage = LaunchApplicaton();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password )
	{
		productCatalouge = landingPage.loginApplication(username, password);
	}
	
	@When("^i add product (.+) to cart$")
		public void i_add_product_to_cart(String productName) throws InterruptedException 
		{
		List<WebElement>products= productCatalouge.getProductsList();
		    productCatalouge.addProductToCart(productName);        
		}
	
	
	@When("^checkout (.+) and Submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage= productCatalouge.goToCartPage();;
		Boolean match =cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkOutPage =cartPage.goToCheckout();
		checkOutPage.selectCountry("india");
		 confirmationMessage = checkOutPage.submittOrder();			
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string){
		String textonweb =confirmationMessage.getConfirmationMessage();
		Assert.assertTrue(textonweb.equalsIgnoreCase(string));
	}
	
}
