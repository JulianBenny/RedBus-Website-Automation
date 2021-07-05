package utilities;

import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HandlingCalendar {

	public static void selectDate(WebElement calendar, String year, String monthName, String day, WebDriver driver)
			throws ParseException {
		// Clicking on calendar to open calendar widget
		calendar.click();

		// Retrieving current year value
		String currentMonthYear = driver.findElement(By.xpath("//td[@class = 'monthTitle']")).getText();
		String[] splitStr = currentMonthYear.trim().split("\\s+");
		String currentYear = splitStr[1];

		// Click on Next arrow till we get desired year
		if (!currentYear.equals(year)) {
			do {
				driver.findElement(By.xpath("//td[@class = 'next']")).click();
			} while (!driver.findElement(By.xpath("//td[@class = 'monthTitle']")).getText().trim().split("\\s+")[1]
					.equals(year));

		}

		// Select desired month after selecting desired year

		String currentMonthYear2 = driver.findElement(By.xpath("//td[@class = 'monthTitle']")).getText();
		String[] splitStr2 = currentMonthYear2.trim().split("\\s+");
		String currentMonth = splitStr2[0];

		if (!currentMonth.equalsIgnoreCase(monthName)) {
			do {
				driver.findElement(By.xpath("//td[@class = 'next']")).click();
			} while (!driver.findElement(By.xpath("//td[@class = 'monthTitle']")).getText().trim().split("\\s+")[0]
					.equalsIgnoreCase(monthName));

		}

		// Find dates of desired month only

		List<WebElement> allDateOfDesiredMonth = driver.findElements(By.xpath("//td[@class = 'wd day']"));
		List<WebElement> allDateOfDesiredMonth2 = driver.findElements(By.xpath("//td[@class = 'we day']"));

		for (WebElement d : allDateOfDesiredMonth) {
			if (d.getText().trim().equals(day)) {
				d.click();
				break;
			}
		}

		for (WebElement d : allDateOfDesiredMonth2) {
			if (d.getText().trim().equals(day)) {
				d.click();
				break;
			}
		}

	}

}
