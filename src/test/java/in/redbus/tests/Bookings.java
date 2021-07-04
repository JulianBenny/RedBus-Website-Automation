package in.redbus.tests;

import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import in.redbus.pages.HomePage;
import utilities.CommonUtils;

public class Bookings extends BaseTest{
	
	// Worksheet Name used by all tests
		private String sheetName = "BookingData";

		@Test
		public void cancellationFunctionality() {
			String testName = "Cancellation";

			extentTest = extentReports.startTest(testName); // extent reporting
			SoftAssert softAssertion = new SoftAssert(); // Assertions

			// Fetching all test data from excel file
			HashMap<String, String> testData = new HashMap<String, String>();
			testData = reader.getRowTestData(sheetName, testName);

			// checking if execution required field is no
			String executionRequired = testData.get("Execution Required").toLowerCase();
			CommonUtils.toCheckExecutionRequired(executionRequired);

			// TEST
			log.info(testName + " started...");
			
			HomePage hp = new HomePage(driver);
			hp.manageBooking.click();
			log.info("Clicked Manage Booking on homepage");
			extentTest.log(LogStatus.INFO, "Clicked Manage Booking on homepage");
			hp.cancel.click();
			log.info("Clicked cancel in the options");
			extentTest.log(LogStatus.INFO, "Clicked cancel in the options");
			
			softAssertion.assertEquals(driver.getTitle(), testData.get("Expected Title"));
			
		}
		
		@Test
		public void changeTravelDateFunctionality() {
			String testName = "Change Travel Date";

			extentTest = extentReports.startTest(testName); // extent reporting
			SoftAssert softAssertion = new SoftAssert(); // Assertions

			// Fetching all test data from excel file
			HashMap<String, String> testData = new HashMap<String, String>();
			testData = reader.getRowTestData(sheetName, testName);

			// checking if execution required field is no
			String executionRequired = testData.get("Execution Required").toLowerCase();
			CommonUtils.toCheckExecutionRequired(executionRequired);

			// TEST
			log.info(testName + " started...");
			
			HomePage hp = new HomePage(driver);
			hp.manageBooking.click();
			log.info("Clicked Manage Booking on homepage");
			extentTest.log(LogStatus.INFO, "Clicked Manage Booking on homepage");
			hp.changeTravelDate.click();
			log.info("Clicked change travel date in the options");
			extentTest.log(LogStatus.INFO, "Clicked change travel date in the options");
			
			softAssertion.assertEquals(driver.getTitle(), testData.get("Expected Title"));
			
		}
		
		
		@Test
		public void showTicketFunctionality() {
			String testName = "Show Ticket";

			extentTest = extentReports.startTest(testName); // extent reporting
			SoftAssert softAssertion = new SoftAssert(); // Assertions

			// Fetching all test data from excel file
			HashMap<String, String> testData = new HashMap<String, String>();
			testData = reader.getRowTestData(sheetName, testName);

			// checking if execution required field is no
			String executionRequired = testData.get("Execution Required").toLowerCase();
			CommonUtils.toCheckExecutionRequired(executionRequired);

			// TEST
			log.info(testName + " started...");
			
			HomePage hp = new HomePage(driver);
			hp.manageBooking.click();
			log.info("Clicked Manage Booking on homepage");
			extentTest.log(LogStatus.INFO, "Clicked Manage Booking on homepage");
			hp.showTicket.click();
			log.info("Clicked show ticket in the options");
			extentTest.log(LogStatus.INFO, "Clicked show ticket in the options");
			
			softAssertion.assertEquals(driver.getTitle(), testData.get("Expected Title"));
			
		}
		
		@Test
		public void emailSMSTicketFunctionality() {
			String testName = "EMAIL/SMS Ticket";

			extentTest = extentReports.startTest(testName); // extent reporting
			SoftAssert softAssertion = new SoftAssert(); // Assertions

			// Fetching all test data from excel file
			HashMap<String, String> testData = new HashMap<String, String>();
			testData = reader.getRowTestData(sheetName, testName);

			// checking if execution required field is no
			String executionRequired = testData.get("Execution Required").toLowerCase();
			CommonUtils.toCheckExecutionRequired(executionRequired);

			// TEST
			log.info(testName + " started...");
			
			HomePage hp = new HomePage(driver);
			hp.manageBooking.click();
			log.info("Clicked Manage Booking on homepage");
			extentTest.log(LogStatus.INFO, "Clicked Manage Booking on homepage");
			hp.emailSMSTicket.click();
			log.info("Clicked EMAIL/SMS in the options");
			extentTest.log(LogStatus.INFO, "Clicked EMAIL/SMS in the options");
			
			softAssertion.assertEquals(driver.getTitle(), testData.get("Expected Title"));
			
		}

}
