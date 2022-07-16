package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_Element_Condition_Status {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver,10);
		// tác dụng với các hàm find element cứ mỗi 0,5s lại tìm 1 lần cho đến khi hết 10s
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}

	@Test
	public void TC_01_Visible_Display_Visibility() {
		driver.get("https://www.facebook.com/");
		//1. Có trên UI (Bắt buộc)
		//2. Có trong HTML (Bắt buộc)
		// Wait cho cái email textbox hiển thị trong 10s	
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
	}

	@Test
	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		//2.  ko có trên UI 
		//1.  có trong HTML ( bắt buộc)
		driver.get("https://www.facebook.com/");
				
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
				
		//Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
					
	}

	@Test
	public void TC_02_Invisible_Undisplayed_Invisibility_II() {
		//2.  ko có trên UI 
		//2.  ko có trong HTML 
		driver.get("https://www.facebook.com/");
		
		//Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
		
		
	}

	@Test
	public void TC_03_Presence_I() {
		driver.get("https://www.facebook.com/");
		//1.  có trên UI 
		//1.  có trong HTML ( bắt buộc)
		
		//Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		
	}

	@Test
	public void TC_03_Presence_II() {
		driver.get("https://www.facebook.com/");
		//2. ko có trên UI 
		//1.  có trong HTML ( bắt buộc)
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
				

	}

	@Test
	public void TC_04_Staleness() {
		//2. ko có trên UI ( bắt buộc)
		//2. ko có trong HTML 
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//Phase 1 : Element có trong cây html
		WebElement ReEnteremailtextbox = driver.findElement(By.name("reg_email_confirmation__"));
		// Thao tác vs element khá làm cho element ReEnter ko còn trong DOM
		//...
		
		//Close Popup
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		
		//Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		
		explicitWait.until(ExpectedConditions.stalenessOf(ReEnteremailtextbox));
		
	
				
	
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