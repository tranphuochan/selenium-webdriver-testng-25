package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Register_01_Empty_Data() {
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Click vào đăng ký Button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		// Kiểm tra message lỗi hiển thị cở các field bắt buộc
		driver.findElement(By.id("txtFirstname-error")).getText();
		
		// Kiểm tra điều kiện trả về là bằng với điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
		 		
	}

	@Test
	public void Register_02_Invalid_Email() {
		//mở app ra 
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("PHUOC HAN");
		driver.findElement(By.id("txtEmail")).sendKeys("TRANPHUOCHAN@GMAIL@.COM");
		driver.findElement(By.id("txtCEmail")).sendKeys("TRANPHUOCHAN@GMAIL@.COM");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345abc");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345abc");
		driver.findElement(By.id("txtPhone")).sendKeys("0932134231");
		
		//click vào đăng kí button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//kiểm tra điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtFirstname")).getText(),"");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	
		
	}

	@Test
	public void Register_03_Incorrect_Confirm_Email() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Phuoc Han");
		driver.findElement(By.id("txtEmail")).sendKeys("tranphuochan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tranphuoc@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345abc");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345abc");
		driver.findElement(By.id("txtPhone")).sendKeys("0932134231");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
				
	}

	@Test
	public void Register_04_Password_Less_Than_6_Chars() {
		
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Phuoc Han");
		driver.findElement(By.id("txtEmail")).sendKeys("tranphuochan@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345");
		driver.findElement(By.id("txtPhone")).sendKeys("0932134231");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		
		
	}
	@Test
	public void Register_05_Incorrect_Password() {
	
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Phuoc Han");
		driver.findElement(By.id("txtEmail")).sendKeys("tranphuochan@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456abc");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456ab");
		driver.findElement(By.id("txtPhone")).sendKeys("0932134231");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
		
		
	}
	
	
	@Test
	public void Register_06_Incorrect_Phone() {
		

		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Phuoc Han");
		driver.findElement(By.id("txtEmail")).sendKeys("tranphuochan@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456abc");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456ab");
		driver.findElement(By.id("txtPhone")).sendKeys("093213423");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}