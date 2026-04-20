package RakeshAcadmy.Tests;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	String prodcutName = "ZARA COAT 3";
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	driver.get("https://rahulshettyacademy.com/client");
	LandingPage landingPage = new LandingPage(driver);
	
	driver.findElement(By.id("userEmail")).sendKeys("rraakesh777@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Java@4321");
	driver.findElement(By.id("login")).click();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	List <WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
	
	WebElement prod = products.stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(prodcutName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();	
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	List<WebElement>cartproducts=driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match= cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(prodcutName));
	Assert.assertTrue(match);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains(@class,'ta-item')])[2]"))).click();
	driver.findElement(By.cssSelector(".btnn.action__submit")).click();
	String textonweb = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(textonweb.equalsIgnoreCase("Thankyou for the order."));
	driver.close();
	System.out.println("Test Case Executed Successfully");
	
	}
	
	

}
