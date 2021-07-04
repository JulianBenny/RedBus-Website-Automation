package in.redbus.tests;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import in.redbus.pages.BusHirePage;
import in.redbus.pages.HomePage;
import utilities.CommonUtils;

public class BusHire extends BaseTest {

	// Worksheet Name used by all tests
	private String sheetName = "BusHireTestData";

	@Test
	public void outstation() throws Throwable {

		String testName = "Outstation";

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

		BusHirePage bh = new BusHirePage(driver);
		log.info("Navigated to bus hire page");
		extentTest.log(LogStatus.INFO, "Navigated to bus hire page");
		driver.switchTo().frame(bh.iFrame);
		bh.Outstation.click();

		driver.switchTo().defaultContent();
		driver.switchTo().frame(bh.iFrame);

		bh.enterLocation(testData.get("Pickup"), testData.get("Destination"));
		log.info("Pickup and Destination location entered");
		extentTest.log(LogStatus.INFO, "Pickup and Destination location entered");

		bh.enterDateAndTimeOutstation(testData.get("From Date and Time"), testData.get("To Date and Time"));
		log.info("From, To Date and Time entered");
		extentTest.log(LogStatus.INFO, "From, To Date and Time entered");

		bh.numPassengers.sendKeys(testData.get("Num of passengers"));
		log.info("Number of passengers entered");
		extentTest.log(LogStatus.INFO, "Number of passengers entered");

		bh.proceedButton.click();

		softAssertion.assertEquals(true,
				driver.findElement(By.xpath(testData.get("Expected xpath of element"))).isDisplayed(),
				"Assering if reached the point where only personal details have to be filled");

	}

}
