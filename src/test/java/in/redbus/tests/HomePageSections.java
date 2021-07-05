package in.redbus.tests;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import in.redbus.pages.HomePage;
import utilities.CommonUtils;

public class HomePageSections extends BaseTest{
	
	private String sheetName = "HomePageSectionTestData";

	@Test
	public void globalPresenceSection() {
		String testName = "Global Presence Section";

		extentTest = extentReports.startTest(testName); // extent reporting
		SoftAssert softAssertion = new SoftAssert(); // Assertions

		// Fetching all test data from excel file
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = reader.getRowTestData(sheetName, testName);

		// checking if execution required field is no
		String executionRequired = testData.get("Execution Required").toLowerCase();
		CommonUtils.toCheckExecutionRequired(executionRequired);

		// TEST
		log.info(testName + " test case started...");
		String oldTab = driver.getWindowHandle(); // getting window handle of current tab
		HomePage hp = new HomePage(driver);

		hp.flag.click();
		log.info("Clicking on Singapore flag");
		extentTest.log(LogStatus.INFO, "Clicking on Singapore flag");
		
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		driver.switchTo().window(newTab.get(0));     // change focus to new tab
		
		softAssertion.assertEquals(driver.getCurrentUrl(), testData.get("Expected URL"),
				"Assertion that we are redirected to correct url");
		
		driver.switchTo().window(oldTab);
		
	}
	
	
	@Test
	public void RTCSection() {
		String testName = "Different RTC's section";

		extentTest = extentReports.startTest(testName); // extent reporting
		SoftAssert softAssertion = new SoftAssert(); // Assertions

		// Fetching all test data from excel file
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = reader.getRowTestData(sheetName, testName);

		// checking if execution required field is no
		String executionRequired = testData.get("Execution Required").toLowerCase();
		CommonUtils.toCheckExecutionRequired(executionRequired);

		// TEST
		log.info(testName + " test case started...");
		String oldTab = driver.getWindowHandle(); // getting window handle of current tab
		HomePage hp = new HomePage(driver);
		
	    new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(hp.APSRTC));
		hp.APSRTC.click();
		log.info("Clicking on APSRTC option in Top RTC's Section");
		extentTest.log(LogStatus.INFO, "Clicking on APSRTC option in Top RTC's Section");
		
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		driver.switchTo().window(newTab.get(0));     // change focus to new tab
		
		softAssertion.assertEquals(driver.getCurrentUrl(), testData.get("Expected URL"),
				"Assertion that we are redirected to correct url");
		
		driver.switchTo().window(oldTab);
		
	}
	
	@Test
	public void awardSection() {
		String testName = "Award's Section";

		extentTest = extentReports.startTest(testName); // extent reporting
		SoftAssert softAssertion = new SoftAssert(); // Assertions

		// Fetching all test data from excel file
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = reader.getRowTestData(sheetName, testName);

		// checking if execution required field is no
		String executionRequired = testData.get("Execution Required").toLowerCase();
		CommonUtils.toCheckExecutionRequired(executionRequired);

		// TEST
		log.info(testName + " test case started...");
		String oldTab = driver.getWindowHandle(); // getting window handle of current tab
		HomePage hp = new HomePage(driver);
		
		hp.awardLink.click();
		log.info("Clicking on Award Link");
		extentTest.log(LogStatus.INFO, "Clicking on Award Link");
		
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		driver.switchTo().window(newTab.get(0));     // change focus to new tab
		
		softAssertion.assertEquals(driver.getCurrentUrl(), testData.get("Expected URL"),
				"Assertion that we are redirected to correct url");
		
		driver.switchTo().window(oldTab);
		
	}
	
	@Test
	public void operatorsSection() {
		String testName = "Operators Section";

		extentTest = extentReports.startTest(testName); // extent reporting
		SoftAssert softAssertion = new SoftAssert(); // Assertions

		// Fetching all test data from excel file
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = reader.getRowTestData(sheetName, testName);

		// checking if execution required field is no
		String executionRequired = testData.get("Execution Required").toLowerCase();
		CommonUtils.toCheckExecutionRequired(executionRequired);

		// TEST
		log.info(testName + " test case started...");
		HomePage hp = new HomePage(driver);
		
		hp.operator.click();
		log.info("Clicking on one of the operators");
		extentTest.log(LogStatus.INFO, "Clicking on one of the operators");
		
		softAssertion.assertEquals(driver.getCurrentUrl(), testData.get("Expected URL"),
				"Assertion that we are redirected to correct url");
		
	}
	
	@Test
	public void PartnersSection() {
		String testName = "Our Partners Section";

		extentTest = extentReports.startTest(testName); // extent reporting
		SoftAssert softAssertion = new SoftAssert(); // Assertions

		// Fetching all test data from excel file
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = reader.getRowTestData(sheetName, testName);

		// checking if execution required field is no
		String executionRequired = testData.get("Execution Required").toLowerCase();
		CommonUtils.toCheckExecutionRequired(executionRequired);

		// TEST
		log.info(testName + " test case started...");
		HomePage hp = new HomePage(driver);
		
		hp.goibiboPartner.click();
		log.info("Clicking on one of our partners-goibibo");
		extentTest.log(LogStatus.INFO, "Clicking on one of our partners-goibibo");
		
		softAssertion.assertEquals(driver.getCurrentUrl(), testData.get("Expected URL"),
				"Assertion that we are redirected to correct url");
		
	}
}
