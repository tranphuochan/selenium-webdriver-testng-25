package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	// Khai báo 1 biến driver  đại diện cho selenium webdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		// set gecko driver giao tiếp giữa trình duyệt và code
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// bật trình duyệt fifox
		driver = new FirefoxDriver();
		
		// set thời gian đi tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Bật browser to lên 
		driver.manage().window().maximize();
		
		// Mở app ra
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() {
		
		driver.findElement(By.id("email")).sendkey("dam@gmail.com");
		
	
		
	}

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}