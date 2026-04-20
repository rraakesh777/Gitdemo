package RakeshAcadmy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RakeshAcadmy.SeleniumFrameworkdesign.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver intializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//RakeshAcadmy//resources//GalobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser"); 
		
		if (browserName.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();			
		} else if (browserName.equals("Firefox")) {

		} else if (browserName.equals("Edge")) {

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();		
		return driver;		

	}
	public  List<HashMap<String,String>> getJsonDatatoMap(String filePath) throws IOException {
		//read json to string
		String jsonContent= FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);		
		//Sting to Hashmap  jackson Databind
		ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {}
        );
        return data;
	}	
	
public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file =new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
	}
	
	@BeforeMethod
	public LandingPage LaunchApplicaton() throws IOException {
		
		driver=intializeDriver();
		landingPage = new LandingPage(driver);	
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.close();
		System.out.println("Test Case Executed Successfully");
		
		
	}
} 
