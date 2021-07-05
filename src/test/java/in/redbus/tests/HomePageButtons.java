package in.redbus.tests;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import in.redbus.pages.HomePage;
import utilities.CommonUtils;

public class HomePageButtons extends BaseTest {

	// Worksheet Name used by all tests
	private String sheetName = "HomePageTestData";

	@Test
	public void redbusButtonFunctionality() {
		String testName = "RedBus Button Functionality";

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
		softAssertion.assertEquals(driver.getTitle(), testData.get("Expected Title"),
				"Assertion that we start on homepage");

		HomePage hp = new HomePage(driver);
		hp.busTickets.click();
		log.info("Navigating to 'Bus Tickets' page");
		extentTest.log(LogStatus.INFO, "Navigating to 'Bus Tickets' page");
		softAssertion.assertNotEquals(driver.getTitle(), testData.get("Expected Title"),
				"Assertion that we are not on homepage");

		hp.redbusButton.click();
		log.info("Clicking on redbus button");
		extentTest.log(LogStatus.INFO, "Clicking on redbus button");
		softAssertion.assertEquals(driver.getTitle(), testData.get("Expected Title"),
				"Assertion that we are back on homepage");

	}

	@Test
	public void helpButton() {
		String testName = "Help Button";

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
		hp.helpButton.click();
		log.info("Clicked help button on homepage");
		extentTest.log(LogStatus.INFO, "Clicked help button on homepage");

		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		driver.switchTo().window(newTab.get(0)); // change focus to new tab
		hp.closeSigninHelpPage.click();

		softAssertion.assertEquals(testData.get("Expected Title"), driver.getTitle(),
				"Assertion that new tab with redbus help opened");

	}

	@Test
	public void rPoolButton() {
		String testName = "rPool Button";

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
		hp.rPoolButton.click();
		log.info("Clicked rPool button on homepage");
		extentTest.log(LogStatus.INFO, "Clicked rPool button on homepage");

		softAssertion.assertEquals(driver.getTitle(), testData.get("Expected Title"),
				"Assertion that redirected to rPool Page");
	}

	@Test
	public void busHireButton() {
		String testName = "Bus Hire Button";

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
		hp.busHireButton.click();
		log.info("Clicked Bus Hire button on homepage");
		extentTest.log(LogStatus.INFO, "Clicked Bus Hire button on homepage");

		softAssertion.assertEquals(driver.getTitle(), testData.get("Expected Title"),
				"Assertion that redirected to Bus Hire Page");
	}
}
