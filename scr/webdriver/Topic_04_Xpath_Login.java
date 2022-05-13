package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullName, emailAddress, password;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Automation";
		lastName = "HAN";
		fullName = firstName + " " + lastName;
		emailAddress = "AFC" + generateRandomNumber() + "@hotmail.com";
		password= "12345678";	
	}
	
	@Test
	public void LOGIN_01_Empty_email_and_Password() {
		
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[(text()='Account')]")).click();
		
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
		
		
		driver.findElement(By.xpath("//div[@class='buttons-set']//button[@type='submit']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
			
	}

	@Test
	public void Login_02_Invalid_Email() {
		
		
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[(text()='Account')]")).click();
		
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("12341234@1234");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.xpath("//div[@class='buttons-set']//button[@type='submit']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}

	@Test
	public void Login_03_Password_less_than_6_character() {

		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[(text()='Account')]")).click();
		
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("tranphuoc@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12345");
		
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
		
		
	}
	
	@Test
	public void Login_04_Incorrect_Password_and_Email() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[(text()='Account')]")).click();
		
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		
		driver.findElement(By.xpath("//div[@class='buttons-set']//button[@type='submit']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']")).getText(), "Invalid login or password.");
	}
	
	
	@Test
	public void Login_05_Create_New_account() {
		
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//span[text()='Register']")).click();
		
		//Tuyệt đối
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");
		// Tương đối
		String contactInforText =  driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInforText.contains(fullName));
		Assert.assertTrue(contactInforText.contains(emailAddress));
		// Nếu dùng hàm tuyệt đối so sánh bằng thì sẽ chứa cả change password... -> không dùng.
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
			
	}
	
	@Test
	public void Login_06_Valid_Email_and_password() {
		
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		String contactInforText =  driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInforText.contains(fullName));
		Assert.assertTrue(contactInforText.contains(emailAddress));
			
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
		
	}	
