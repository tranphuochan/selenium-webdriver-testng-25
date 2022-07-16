package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("MAC OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		} else {
		}
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		WebElement dangkypop = driver.findElement(By.id("modal-login-v1"));
		Assert.assertFalse(dangkypop.isDisplayed());
		driver.findElement(By.cssSelector("div#button-login-dialog")).click();
		sleepInSecond(3);
		Assert.assertTrue(dangkypop.isDisplayed());
		driver.findElement(By.id("account-input")).sendKeys("automationfc");
		driver.findElement(By.id("password-input")).sendKeys("automationfc");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");

	}

	@Test
	public void TC_02_() {
			
	}

	@Test
	public void TC_03() {
		
	}

	@Test
	public void TC_03() {
		
	}
	
	@Test
	public void TC_03() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	
	}
}