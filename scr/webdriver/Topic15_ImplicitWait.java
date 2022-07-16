package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_ImplicitWait {
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
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	public void TC_01_Not_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish")).getText(), "Hello World!");
		

	}

	@Test
	public void TC_02_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish")).getText(), "Hello World!");
		
	}

	@Test
	public void TC_03_More_Time() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish")).getText(), "Hello World!");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	@Test
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	
	}
}