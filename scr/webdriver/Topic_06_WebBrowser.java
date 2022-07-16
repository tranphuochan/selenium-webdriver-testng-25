package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebBrowser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("");
	}

	@Test
	public void TC_01_Method() {
		//Close tab 
		//Nếu có 1 tab -> close browser
		//Nhiều tab -> close tab đang active
		driver.close();
		
		//Đóng lun browser
		driver.quit();
		
		//Mở 1 url ra 
		
		driver.get("http://live.techpanda.org/");
		
		//Dùng để tìm element
		driver.findElement(By.xpath(""));
		
		//Dùng để tìm nhiều element
		driver.findElements(By.xpath(""));
		
		//Lấy ra URL hiện tại
		driver.getCurrentUrl();
		
		//lấy ra source code (HTML;CSS) của hiện tại
		driver.getPageSource();
		
		//Lấy ra title của page hiện tại
		driver.getTitle();
		
		//Để xử lý window và tab
		//Lấy ra ID của tab đang active
		driver.getWindowHandle();
		
		//Lấy ra ID của tất cả các tab của window đang có
		driver.getWindowHandles();
		
		//Cookie: Lưu lại phiên đăng nhập/ session/ dữ liệu duyệt web/...
		//driver/manage().log
		
		//chờ cho find element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//Chờ 1 page load thành công
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		//****************Javascript Excutor*****
		//Chờ 1 đoạn code javascript được thực thi thành công
		//asynchronus script - bất đồng bộ
		//synchronous script - đồng bộ
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		
		driver.manage().window().fullscreen();
		driver.manage().window().maximize();
		
		//set vị trí của browser so với độ phân giải màn hình
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().getPosition();
		
		//Mở browser với kích thước là bao nhiêu
		driver.manage().window().setSize(new Dimension(100, 250));
		driver.manage().window().getSize();
		
		//tracking history tốt hơn
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("");
		driver.get("");
		
		
	
		
		
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