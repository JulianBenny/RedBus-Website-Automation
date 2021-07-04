package in.redbus.tests;

import java.text.ParseException;
import java.util.HashMap;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import in.redbus.pages.HomePage;
import utilities.CommonUtils;
import utilities.HandlingCalendar;

public class SearchBus extends BaseTest {
	
	// Worksheet Name used by all tests
	private String sheetName = "SearchBusTestData";

	@Test
	public void validSearchBus() {
		String testName = "Valid Search for a bus";

		extentTest = extentReports.startTest(testName);    // extent reporting
		SoftAssert softAssertion = new SoftAssert();	   // Assertions

		// Fetching all test data from excel file
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = reader.getRowTestData(sheetName, testName);
		
		// checking if execution required field is no
		String executionRequired = testData.get("Execution Required").toLowerCase();
		CommonUtils.toCheckExecutionRequired(executionRequired);

		
		//TEST
		log.info(testName + " test case started..." );
		HomePage hp = new HomePage(driver);

		hp.selectLocation(testData.get("From"), testData.get("To"));
		extentTest.log(LogStatus.INFO, "Both Locations entered");

		String[] dataStr = testData.get("Date").trim().split("\\s+");    // getting date from excel and splitting 
																		 // them by blank space
		
		try {
			HandlingCalendar.selectDate(hp.calendar, dataStr[2], dataStr[1], dataStr[0], driver);
			extentTest.log(LogStatus.INFO, "Date entered");
			log.info("Date entered");
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

		hp.searchBuses.click();
		extentTest.log(LogStatus.INFO, "Date entered");
		log.info("Search button clicked");
		
		String actualTitle = driver.getTitle().toLowerCase();
		String expectedTitle = testData.get("Expected Title");
		
		softAssertion.assertEquals(expectedTitle, actualTitle, "Assertion on actual and expected title of page.");

	}
	
	@Test
	public void invalidLocSearchBus() {
		String testName = "Invalid Location while searching for bus";

		extentTest = extentReports.startTest(testName);    // extent reporting
		SoftAssert softAssertion = new SoftAssert();	   // Assertions

		// Fetching all test data from excel file
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = reader.getRowTestData(sheetName, testName);
		
		// checking if execution required field is no
		String executionRequired = testData.get("Execution Required").toLowerCase();
		CommonUtils.toCheckExecutionRequired(executionRequired);

		
		//TEST
		String expectedTitle = testData.get("Expected Title");
		
		log.info(testName + " test case started..." );
		HomePage hp = new HomePage(driver);
		try {
			hp.selectLocation(testData.get("From"), testData.get("To"));
		}catch (Exception e) {
			String actualTitle = driver.getTitle().toLowerCase();
			softAssertion.assertEquals(expectedTitle, actualTitle, "Assertion on actual and expected title of page.");
		}
		
	}
	
//	@Test
	public void invalidDateSearchBus() {
		String testName = "Invalid Date while searching for bus";

		extentTest = extentReports.startTest(testName);    // extent reporting
		SoftAssert softAssertion = new SoftAssert();	   // Assertions

		// Fetching all test data from excel file
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = reader.getRowTestData(sheetName, testName);
		
		// checking if execution required field is no
		String executionRequired = testData.get("Execution Required").toLowerCase();
		CommonUtils.toCheckExecutionRequired(executionRequired);

		
		//TEST
		log.info(testName + " test case started..." );
		HomePage hp = new HomePage(driver);

		hp.selectLocation(testData.get("From"), testData.get("To"));
		extentTest.log(LogStatus.INFO, "Both Locations entered");

		String[] dataStr = testData.get("Date").trim().split("\\s+");    // getting date from excel and splitting 
																		 // them by blank space
		
		try {
			HandlingCalendar.selectDate(hp.calendar, dataStr[2], dataStr[1], dataStr[0], driver);
			extentTest.log(LogStatus.INFO, "Date entered");
			log.info("Date entered");
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

		hp.searchBuses.click();
		extentTest.log(LogStatus.INFO, "Date entered");
		log.info("Search button clicked");
		
		String actualTitle = driver.getTitle().toLowerCase();
		String expectedTitle = testData.get("Expected Title");
		
		softAssertion.assertEquals(expectedTitle, actualTitle, "Assertion on actual and expected title of page.");

	}
	
	

}
