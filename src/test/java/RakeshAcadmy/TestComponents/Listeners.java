package RakeshAcadmy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import RakeshAcadmy.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReportsNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	 @Override
	    public void onTestStart(ITestResult result) {		 
		 test = extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);
	        System.out.println("Test Started");
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	    	extentTest.get().log(Status.PASS, "Test Case passed");
	        System.out.println("Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	    	extentTest.get().fail(result.getThrowable());
	    	try {
				driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	String filepath = null;
			try {
				filepath = getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	        System.out.println("Test Failed");
	        //screenshot
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("Test Skipped");
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        System.out.println("Execution Started");
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        System.out.println("Execution Finished");
	        extent.flush();
	    }
}
