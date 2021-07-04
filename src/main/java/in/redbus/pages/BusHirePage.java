package in.redbus.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageUtils.DateAndTimeBusHire;

public class BusHirePage {

	// Log4j logger
	public final static Logger log = Logger.getLogger(HomePage.class);

	WebDriver driver;

	public BusHirePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(xpath = "//object[@class = '_2J2AiPaNvmZu-bu153D3kq']")
	public WebElement iFrame;
	
	@FindBy(xpath = "//div[contains(text(),'Outstation')]")
	public WebElement Outstation;
	
	@FindBy(xpath = "//input[@id='locationTextFieldLocal']")
	public WebElement pickupLocation;
	
	@FindBy(xpath = "//input[@id='local_dest_name']")
	public WebElement destinationLocation;
	
	@FindBy(xpath = "//*[@id='from_datepicker']/div/div/input")
	public WebElement fromDate;
	
	@FindBy(xpath = "//div[@id='to_datepicker']")
	public WebElement tillDate;
	
	@FindBy(xpath = "//input[@id='numberOfPax']")
	public WebElement numPassengers;
	
	@FindBy(xpath = "//button[@id='proceedButtonOutstation']")
	public WebElement proceedButton;
	
	
	public void enterDateAndTimeOutstation(String fromDateAndTime , String tillDateAndTime){

		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");
		 
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click()", fromDate);


		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// selecting from date and time
		try {
			DateAndTimeBusHire.selectDateAndTimeAtBusHirePage(driver, fromDateAndTime);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		tillDate.click();
		// selecting till date and time
		try {
			DateAndTimeBusHire.selectDateAndTimeAtBusHirePage(driver, tillDateAndTime);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}

