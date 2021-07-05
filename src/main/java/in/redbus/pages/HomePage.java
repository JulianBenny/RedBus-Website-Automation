package in.redbus.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	// Log4j logger
	public final static Logger log = Logger.getLogger(HomePage.class);

	WebDriver driver;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(id = "signin-block")
	public WebElement signIn;

	@FindBy(id = "signInLink")
	public WebElement signInLink;

	@FindBy(id = "src")
	public WebElement fromLoc;

	@FindBy(id = "dest")
	public WebElement destLoc;

	@FindBy(id = "onward_cal")
	public WebElement calendar;

	@FindBy(id = "search_btn")
	public WebElement searchBuses;

	@FindBy(id = "redBus")
	public WebElement busTickets;

	@FindBy(xpath = "//i[@class = 'icon icon-redBus_Logo D121_logo_main']")
	public WebElement redbusButton;

	@FindBy(xpath = "//a[contains(text(),'Help')]")
	public WebElement helpButton;

	@FindBy(xpath = "//i[@class = 'icon-close']")
	public WebElement closeSigninHelpPage;

	@FindBy(id = "cars")
	public WebElement rPoolButton;

	@FindBy(id = "redBus Bus Hire")
	public WebElement busHireButton;

	@FindBy(xpath = "//div[contains(text(),'Manage Booking')]")
	public WebElement manageBooking;

	@FindBy(xpath = "//span[contains(text(),'Cancel')]")
	public WebElement cancel;

	@FindBy(xpath = "//span[contains(text(),'Change Travel Date')]")
	public WebElement changeTravelDate;

	@FindBy(xpath = "//span[contains(text(),'Show My Ticket')]")
	public WebElement showTicket;

	@FindBy(xpath = "//span[contains(text(),'Email/SMS')]")
	public WebElement emailSMSTicket;

	@FindBy(xpath = "//span[@class='flagicon icon-SGP']")
	public WebElement flag;

	@FindBy(xpath = "//a[contains(text(),'APSRTC')]")
	public WebElement APSRTC;

	@FindBy(xpath = "//div[contains(text(),'Most Innovative Company')]")
	public WebElement awardLink;

	@FindBy(xpath = "//a[contains(text(),'KPN Travels')]")
	public WebElement operator;

	@FindBy(xpath = "//a[contains(text(),'Goibibo')]")
	public WebElement goibiboPartner;

	public void selectLocation(String fromLocation, String toLocation) {
		// capitalising first letter
		fromLocation = fromLocation.substring(0, 1).toUpperCase() + fromLocation.substring(1).toLowerCase();
		toLocation = toLocation.substring(0, 1).toUpperCase() + toLocation.substring(1).toLowerCase();

		fromLoc.sendKeys(fromLocation);
		String xpath = String.format("//li[text() = '%s']", fromLocation);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(xpath)).click();
		log.info(fromLocation + " was entered");

		destLoc.sendKeys(toLocation);
		String xpath2 = String.format("//li[text() = '%s']", toLocation);
		try {
			log.info(toLocation + " was entered");
			driver.findElement(By.xpath(xpath2)).click();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Invalid Location");
		}
		log.info(toLocation + " was entered");
	}

}
