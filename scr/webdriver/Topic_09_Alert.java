package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String autoITFirefox = projectPath + "\\selenium-webdriver-testng-25\\AutoIT\\authen_firefox.exe";
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_ACCEPT_ALERT() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		// Muốn thao tác trên alert phải switch vào nó
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		sleepInSecond(3);
		//Accept Alert này
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");
		;
		
	}

	@Test
	public void TC_02_CONFIRM_ALERT() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.cssSelector(" p#result")).getText(),"You clicked: Cancel");
		
	}

	@Test
	public void TC_03_PROMPT_ALERT() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		sleepInSecond(3);
		alert.sendKeys("daominhdam");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector(" p#result")).getText(), "You entered: daominhdam");
		
	}
	
	@Test
	public void TC_04_Authentication_Alert_Trick() {
		//Slenium cho mình pass cái user/pass trực tiếp vào Url trước khi open nó ra 
		//Format: http(s)://Username:Password@domain
		
		String username = "admin";
		String password = "admin";
		String url = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		
		driver.get(url);
		//Dùng verify ko tuyệt đối vì text có xuống dòng
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']//p")).getText().contains("Congratulations! You must have the proper credentials."));
			
	}	
	
	@Test
	public void TC_05_Authentication_Alert_Trick_Navigate_From_Other_Page() {
		String username = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com");
		
		//Ko nên click vào link để nó show Dialog ra
		//Nên get cái url của link đó
		
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		//https://the-internet.herokuapp.com/basic_auth
		
		//1 tách link thành nhiều chuỗi
		
		String[] authenlinkArray = basicAuthenLink.split("//");
		//http:
		//the-internet.herokuapp.com/basic_auth
		
		//2 lấy chuỗi cộng vào
		
		String NewAuthenlinkURL = authenlinkArray[0] + "//" + username + ":" + password + "@" + authenlinkArray[1];
		
		driver.get(NewAuthenlinkURL);
		
		String contentText = driver.findElement(By.xpath("//div[@id='content']//p")).getText();
		Assert.assertTrue(contentText.contains("Congratulations! You must have the proper credentials."));
			
			
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