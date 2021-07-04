package utilities;

import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import in.redbus.tests.BaseTest;

public class CommonUtils extends BaseTest {

	// function toCheckExecutionRequired
	public static void toCheckExecutionRequired(String executionRequired) {

		// if execution required field is no
		if (executionRequired.equals("no")) {

			extentTest.log(LogStatus.WARNING, "Execution Required : " + executionRequired.toUpperCase());
			log.info("Execution required is no , skipping the test");
			throw new SkipException("Skipping this test case");
		}

		// if execution required field is empty
		if (executionRequired.equals("")) {

			extentTest.log(LogStatus.WARNING, "Execution Required : " + executionRequired.toUpperCase());
			log.info("Execution required is empty , skipping the test");
			throw new SkipException("Skipping this test case");
		}
	}

}
