package utilities;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import in.redbus.tests.BaseTest;

public class DockerClass extends BaseTest {

	public static RemoteWebDriver remoteDriver;
	public static WebDriver webDriver;
	public static DesiredCapabilities caps;
	private static URL url = null;
	private static String browserName = ReadPropertiesFile.prop.getProperty("browser").toLowerCase();

	public DockerClass() throws Exception {

		// check for browsername
		if (browserName.equals("chrome") || browserName.equals("firefox") || browserName.equals("edge")) {

			log.info("Browser supported");
		} else {

			System.out.println(browserName);
			log.error("Browser not supported");
			throw new Exception("Browser not supported");
		}
	}

	public static RemoteWebDriver toRunBrowserOnDocker(boolean headlessMode) throws Exception {

		url = new URL("http://localhost:4444/wd/hub");

		if (headlessMode) {

			remoteDriver = startBrowserInHeadlessInDocker();
			log.info(browserName + " in headless mode with docker initiated");
		} else {

			remoteDriver = startBrowserInNonHeadlessInDocker();
			log.info(browserName + " in non headless mode and with docker initiated");
		}

		return remoteDriver;
	}

	public static WebDriver toRunBrowserOnLocal(boolean headlessMode) throws Exception {

		if (headlessMode) {

			webDriver = startBrowserInHeadlessInLocal();
			log.info(browserName + " in headless mode without docker initiated");
		}

		else {

			webDriver = startBrowserInNonHeadlessInLocal();
			log.info(browserName + " in non headless mode and without docker initiated");
		}

		return webDriver;
	}

	// To start different browsers in docker
	public static RemoteWebDriver startBrowserInHeadlessInDocker() throws Exception {

		ChromeOptions chromeOptions = null;
		FirefoxOptions firefoxOptions = null;

		if (browserName.equals("chrome")) {

			caps = DesiredCapabilities.chrome();
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("headless");
			chromeOptions.addArguments("window-size=1920,1080");
			chromeOptions.merge(caps);
			remoteDriver = new RemoteWebDriver(url, chromeOptions);
		}

		else if (browserName.equals("firefox")) {

			caps = DesiredCapabilities.firefox();
			firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("headless");
			firefoxOptions.addArguments("window-size=1920,1080");
			firefoxOptions.merge(caps);
			remoteDriver = new RemoteWebDriver(url, firefoxOptions);
		}
		
		else {
			log.error("Browser not supported in docker");
			throw new Exception("Browser not supported in docker");
		}
			

		return remoteDriver;
	}

	// To start different browsers in nonheadless in docker
	public static RemoteWebDriver startBrowserInNonHeadlessInDocker() throws Exception {

		if (browserName.equals("chrome")) {

			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--no-sandbox");
			opt.addArguments("--disable-dev-shm-usage");
			remoteDriver = new RemoteWebDriver(url, opt);
		}

		else if (browserName.equals("firefox")) {

			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			remoteDriver = new RemoteWebDriver(url, options);
		}

		else {
			log.error("Browser not supported in docker");
			throw new Exception("Browser not supported in docker");
		}

		return remoteDriver;
	}

	// To start different browsers
	public static WebDriver startBrowserInHeadlessInLocal() throws Exception {

		if (browserName.equals("chrome")) {

			System.setProperty(ReadPropertiesFile.prop.getProperty("chromeDriver"),
					ReadPropertiesFile.prop.getProperty("chromeDriverAddress"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
					"--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
			options.addArguments(
					"user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36");
			webDriver = new ChromeDriver(options);
		}

		else if (browserName.equals("firefox")) {

			System.setProperty(ReadPropertiesFile.prop.getProperty("firefoxDriver"),
					ReadPropertiesFile.prop.getProperty("firefoxDriverAddress"));
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("-headless", "--disable-gpu", "--window-size=1920,1200",
					"--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary(firefoxBinary);
			webDriver = new FirefoxDriver(options);
		}
		
		else {
			log.error("Browser not supported in headless");
			throw new Exception("Browser not supported in docker");
		}

		return webDriver;
	}

	// To start different browsers in nonheadless
	public static WebDriver startBrowserInNonHeadlessInLocal() {

		if (browserName.equals("chrome")) {

			System.setProperty(ReadPropertiesFile.prop.getProperty("chromeDriver"),
					ReadPropertiesFile.prop.getProperty("chromeDriverAddress"));
			webDriver = new ChromeDriver();
			log.info("Chrome driver initiated in head mode");
		}

		else if (browserName.equals("firefox")) {

			System.setProperty(ReadPropertiesFile.prop.getProperty("firefoxDriver"),
					ReadPropertiesFile.prop.getProperty("firefoxDriverAddress"));
			webDriver = new FirefoxDriver();
			log.info("Firefox driver initiated in head mode");

		}
		
		else if(browserName.equals("edge")) {
			System.setProperty(ReadPropertiesFile.prop.getProperty("edgeDriver"),
					ReadPropertiesFile.prop.getProperty("edgeDriverAddress"));
			webDriver = new EdgeDriver();
			log.info("edge driver initiated in head mode");
		}

		return webDriver;
	}
}
