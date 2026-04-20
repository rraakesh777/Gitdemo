package RakeshAcadmy.SeleniumFrameworkdesign.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RakeshAcadmey.AbstractComponents.AbstractComponents;

public class ConfirmationMessage  extends AbstractComponents {
	WebDriver driver;
	public ConfirmationMessage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = ".hero-primary")
	WebElement ConfirmationMessage;
	
	
	public String getConfirmationMessage() {
		// TODO Auto-generated method stub
		return ConfirmationMessage.getText();
	}
	
	
	

}
