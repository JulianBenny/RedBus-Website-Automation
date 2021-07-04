package in.redbus.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import in.redbus.pages.BusHirePage;
import in.redbus.pages.HomePage;

public class BusHire extends BaseTest {

//	@Test
	public void outstation() {

		HomePage hp = new HomePage(driver);
		hp.busHireButton.click();

		BusHirePage bh = new BusHirePage(driver);
		driver.switchTo().frame(bh.iFrame);
		bh.Outstation.click();

		driver.switchTo().defaultContent();
		driver.switchTo().frame(bh.iFrame);

//		bh.pickupLocation.sendKeys("Green Park");
//		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//		bh.pickupLocation.sendKeys(Keys.ARROW_DOWN);
//		bh.pickupLocation.sendKeys(Keys.ARROW_UP);
//		bh.pickupLocation.sendKeys(Keys.ENTER);
//		bh.pickupLocation.sendKeys(Keys.RETURN);
//		Select sel = new Select(bh.pickupLocation);
//		// select with text visible
//		sel.selectByVisibleText("Green Park");

//		bh.destinationLocation.sendKeys("Manali");
//		bh.destinationLocation.sendKeys(Keys.UP);
//		bh.destinationLocation.sendKeys(Keys.DOWN);
//		bh.destinationLocation.sendKeys(Keys.RETURN);

		bh.enterDateAndTimeOutstation("9-08-2021-10:05 PM", "12-07-2021-9:30 PM");

		bh.numPassengers.sendKeys("4");

//		bh.proceedButton.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
