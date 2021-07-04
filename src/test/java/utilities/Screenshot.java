package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	public static String takeScreenShot(WebDriver driver, String testCaseName) {

		String screenshotFileName =  System.getProperty("user.dir")+ "/Reports/" + testCaseName + ".png";

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		File finalDestination = new File(screenshotFileName);
				
		try {
			FileUtils.copyFile(scrFile, finalDestination);
			return screenshotFileName;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
