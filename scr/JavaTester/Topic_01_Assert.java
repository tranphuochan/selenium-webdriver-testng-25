package JavaTester;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.tools.classfile.Annotation.element_value;

public class Topic_01_Assert {
	WebDriver driver;
	
	@Test
	public void TC_01() {
		
		//3 hàm assert hay dùng
		// Kiểm tra tính đúng đắn của dữ liệu
		
		//1 - Kiểm tra dữ liệu mình mong muốn là ĐÚNG
		//Email textbox hiển thị
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		
		//2- Kiểm tra dữ liệu mình mong muốn là SAI
		//Email textbox không hiển thị
		
		Assert.assertFalse(driver.findElement(By.id("email")).isDisplayed());
		
		//Kiểm tra dữ liệu mình mong muốn với dữ liệu thực tế là bằng nhau
		Assert.assertEquals(driver.findElement(By.className("search")).getAttribute("placeholder"), "Search entire store here...");
		
		//Tương đối.
		
		String benefitText = driver.findElement(By.cssSelector("ul.benefits")).getText();
		Assert.assertTrue(benefitText.contains("Faster checkout"));
		Assert.assertTrue(benefitText.contains("Save multiple shipping addresses"));
		Assert.assertTrue(benefitText.contains("View and track orders and more"));
		
		
	}
}