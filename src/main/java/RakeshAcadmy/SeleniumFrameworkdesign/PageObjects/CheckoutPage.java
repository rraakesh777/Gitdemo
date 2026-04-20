package RakeshAcadmy.SeleniumFrameworkdesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import RakeshAcadmey.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement SelectCountry;
	
	By 	results=By.cssSelector(".ta-results");
	
	public void selectCountry(String CountyName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country, CountyName).build().perform();
		waitForElementToAppear(results);
		SelectCountry.click();
		
	}
	public ConfirmationMessage submittOrder() {
		submit.click();
		return new ConfirmationMessage(driver);
	}
	
	
}
