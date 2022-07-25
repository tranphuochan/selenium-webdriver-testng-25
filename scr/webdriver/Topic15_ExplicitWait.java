package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic15_ExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("MAC OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		} else {
		}
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();	
		
		
		
	}

	@Test
	public void TC_01_Not_enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 3);
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
		
	
	}

	@Test
	public void TC_02_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
		
	}

	@Test
	public void TC_03_More_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 20);
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
		
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