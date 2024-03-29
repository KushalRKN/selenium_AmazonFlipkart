package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	
	public static WebDriver driver;
	
	public ResourceBundle rb = ResourceBundle.getBundle("config");;
	
	public Logger log = LogManager.getLogger(this.getClass());;
	
	@BeforeClass
	public void setup()
	{ 
		
		
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		//driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get(rb.getString("url1"));
		
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit(); // closes all tabs in browser
	}
	
	
	
public String captureScreen(String name) throws IOException {
		
		//date format to save screenshot name
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		// saving screenshot in location and name format
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + name + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;
	}
}
