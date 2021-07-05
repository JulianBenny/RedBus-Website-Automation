package in.redbus.tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.DockerClass;
import utilities.ExcelUtils;
import utilities.ReadPropertiesFile;
import utilities.Screenshot;

public class BaseTest {

	public static WebDriver driver;
	
	// Log4j Logger
	public final static Logger log = Logger.getLogger(BaseTest.class); 
	
	// extent reporting
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	// Excel File Test Data Reader
	public static ExcelUtils reader = null;
	
	static {
		try {

			reader = new ExcelUtils("./Resources/Test Data/TestDataRedBus.xlsx");
		} catch (Exception e) {

			log.error(e.getMessage());
		}
	}

	@BeforeSuite
	public static void setExtent() {
		extentReports = new ExtentReports(".\\Reports\\ExtentReport.html");
	}

	
	@BeforeMethod
	public static void intializeWebdriver() {
		boolean headlessMode = Boolean.parseBoolean(ReadPropertiesFile.prop.getProperty("headless"));
		boolean runOnDocker = Boolean.parseBoolean(ReadPropertiesFile.prop.getProperty("docker"));

		// to run on docker
		if(runOnDocker) {

			try {
				driver = DockerClass.toRunBrowserOnDocker(headlessMode);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		else {

			driver = DockerClass.toRunBrowserOnLocal(headlessMode);
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // Implicit wait
		
	}

	// Opening URL
	@BeforeMethod
	public static void openBrowser() {
		driver.get(ReadPropertiesFile.prop.getProperty("url"));
		driver.manage().window().maximize();
		log.info("URL opened...");
	}

	@AfterMethod
	public static void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			String screenshotPath = Screenshot.takeScreenShot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test case passed successfully");
			log.info("Test case passed");
		}
		extentReports.endTest(extentTest);
		
		// closing the driver
		driver.close();
		log.info("Browser closed...");
	}


	@AfterSuite
	public static void endReport() {
		driver.quit();
		extentReports.flush();
		extentReports.close();
		
	}

}
