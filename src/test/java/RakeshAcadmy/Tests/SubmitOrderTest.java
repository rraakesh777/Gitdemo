package RakeshAcadmy.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.CartPage;
import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.CheckoutPage;
import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.ConfirmationMessage;
import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.OrderPage;
import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.ProductCatalougue;
import RakeshAcadmy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

	ProductCatalougue productCatalouge= landingPage.loginApplication(input.get("email"),input.get("password"));	
	List<WebElement>products= productCatalouge.getProductsList();
	productCatalouge.addProductToCart(input.get("productName"));	
	CartPage cartPage= productCatalouge.goToCartPage();;
	Boolean match =cartPage.verifyProductDisplay(input.get("productName"));
	Assert.assertTrue(match);
	CheckoutPage checkOutPage =cartPage.goToCheckout();
	checkOutPage.selectCountry("india");
	ConfirmationMessage confirmationMessage = checkOutPage.submittOrder();	
	String textonweb =confirmationMessage.getConfirmationMessage();
	Assert.assertTrue(textonweb.equalsIgnoreCase("Thankyou for the order."));
	
	
	
	}
	
	
	
	//@Test(dependsOnMethods = {"submitOrder"})
	public void orderHirstory() {
		ProductCatalougue productCatalouge= landingPage.loginApplication("rraakesh777@gmail.com","Java@4321");
		OrderPage orderPage =productCatalouge.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
		
	}
	
	
	@DataProvider 
	public Object[][] getData() throws IOException {
		List <HashMap<String,String>>data =getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//RakeshAcadmy//Data//PurchaseOrder.json");
		return new Object[] []{{data.get(0)},{data.get(1)}};
		
	}

}	
	
	
	// With the help of data provide
	
//	  @DataProvider public Object[][] getData() { return new Object[] []
//	  {{"rraakesh777@gmail.com","Java@4321","ZARA COAT 3"},{
//	 "rraakesh777+1@gmail.com","Java@4321","ADIDAS ORIGINAL"}};
//	 
//	  }
	
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "rraakesh777@gmail.com");
//	map.put("password", "Java@4321");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "rraakesh777+1@gmail.com");
//	map1.put("password", "Java@4321");
//	map1.put("productName", "ADIDAS ORIGINAL");	
	 
	
	


