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

	// Instantiating driver according to the browser and mode in accordance to the
	// input in config.properties file
//	@BeforeMethod
//	public static void intializeWebdriver() {
//
//		if ((ReadPropertiesFile.prop.getProperty("browser")).equalsIgnoreCase("chrome")) {
//
//			if (ReadPropertiesFile.prop.getProperty("headless").equalsIgnoreCase("true")) {
//
//				System.setProperty(ReadPropertiesFile.prop.getProperty("chromeDriver"), // Reading from
//																						// config.properties file
//						ReadPropertiesFile.prop.getProperty("chromeDriverAddress"));
//
//				ChromeOptions options = new ChromeOptions();
//				options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
//						"--ignore-certificate-errors", "--disable-extensions", "--no-sandbox",
//						"--disable-dev-shm-usage");
//				driver = new ChromeDriver(options);
//			}
//
//			else if (ReadPropertiesFile.prop.getProperty("headless").equalsIgnoreCase("false")) {
//
//				System.setProperty(ReadPropertiesFile.prop.getProperty("chromeDriver"), // Reading from
//																						// config.properties file
//						ReadPropertiesFile.prop.getProperty("chromeDriverAddress"));
//				driver = new ChromeDriver();
//			}
//		}
//
//		else if ((ReadPropertiesFile.prop.getProperty("browser")).equalsIgnoreCase("firefox")) {
//
//			if (ReadPropertiesFile.prop.getProperty("headless").equalsIgnoreCase("true")) {
//
//				System.setProperty(ReadPropertiesFile.prop.getProperty("firefoxDriver"), // Reading from
//																							// config.properties file
//						ReadPropertiesFile.prop.getProperty("firefoxDriverAddress"));
//				FirefoxBinary firefoxBinary = new FirefoxBinary();
//				firefoxBinary.addCommandLineOptions("-headless", "--disable-gpu", "--window-size=1920,1200",
//						"--ignore-certificate-errors", "--disable-extensions", "--no-sandbox",
//						"--disable-dev-shm-usage");
//				FirefoxOptions options = new FirefoxOptions();
//				options.setBinary(firefoxBinary);
//				driver = new FirefoxDriver(options);
//			}
//
//			else if (ReadPropertiesFile.prop.getProperty("headless").equalsIgnoreCase("false")) {
//
//				System.setProperty(ReadPropertiesFile.prop.getProperty("firefoxDriver"), // Reading from
//																							// config.properties file
//						ReadPropertiesFile.prop.getProperty("firefoxDriverAddress"));
//				driver = new FirefoxDriver();
//			}
//		}
//
//		else if ((ReadPropertiesFile.prop.getProperty("browser")).equalsIgnoreCase("edge")) {
//
//			System.setProperty(ReadPropertiesFile.prop.getProperty("edgeDriver"),
//					ReadPropertiesFile.prop.getProperty("edgeDriverAddress"));
//			driver = new EdgeDriver();
//		}
//
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Implicit wait
//	}
	
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
