package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
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
	public void TC_01_Element() {
		//1. tìm element
		//2. với loại locator gì
		//3. Tương tác lên/ Kiểm tra nó
		
		//Thao tác trực tiếp thì không cần khai báo biến
		driver.findElement(By.id("")).click();
		
		//Thao tác từ 2 lần  trở lên thì cần khai báo biến tránh việc lặp lại.
		
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		emailTextbox.isDisplayed();
	
	}

	@Test
	public void TC_02_Element_Method() {
		WebElement element = driver.findElement(By.id(""));
		
		//Xóa dữ liệu trong những field cho phép nhập
		//Luôn luôn clear hêt dữ liệu trước khi thao tác lên field đó
		//Textbox / Text Area/ Editable Dropdown
		
		element.clear();
		element.sendKeys(Keys.ENTER);
		
		driver.findElement(By.className("footer")).findElement(By.cssSelector("a[tile='My Account']"));
		
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()='My Account']"));
		
		//Không khai báo biến = verify trực tiếp
		Assert.assertEquals(driver.findElement(By.className("search")).getAttribute("placeholder"), "Search entire store here...");
		
		//Khai báo biến để dùng nhiều lần
		String searchTextboxPlaceholderValue = driver.findElement(By.id("search")).getAttribute("placeholder");
		Assert.assertEquals(searchTextboxPlaceholderValue, "Search entire store here...");
		
		//GUI: Font/Size/Color/...
		
		element.getCssValue("background-color");
		//rgb(51, 153, 204)
		element.getCssValue("font-size");
		//13px
		
		//GUI: Position/ Location/ Size of element
		element.getLocation();
		element.getRect();
		element.getSize();
		
		//Framework: Attach screenshot to Report HTML
		element.getScreenshotAs(OutputType.FILE);
		
		element = driver.findElement(By.cssSelector("#advice-validate-email-email"));
		String emailTextboxTagname = element.getTagName();
	
		//Output của element này sẽ là input của element khác
		//Tuyền 1 biến vào trong chuỗi
		
		driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id=\"advice-validate-password-pass\"]"));
		
		// chuỗi thứ 1 + biến + chuỗi thứ 2
		element.getText();
		
		//Mong muốn 1 element hiền thị/ không hiển thị
		//Hiển thị: Người dùng nhìn thấy được/ có kích thước cụ thể ( chiều rộng/cao)
		//Áp ụng cho tất cả loại Element: textbox/textarea/dropdown/checkbox/radio/button...
		element.isDisplayed();
		
		//Mong muốn 1 element có thể thao tác được lên hoặc không
		//Ngược lại với disable
		//Thao tác được: Enable
		//Không thao tác được: Disable
		//Áp ụng cho tất cả loại Element: textbox/textarea/dropdown/checkbox/radio/button...
		element.isEnabled();
		
		//Mong muốn 1 element đã đc chọn hay chưa
		//Áp dụng: Checkbox/Radio Button/Dropdown
		element.isSelected();
		
		//Click vào 1 element 
		element.click();
		
		//Giống hành vi Enter ở các form
		//Chỉ dùng trong các form
		element.submit();
		
		
		
		
	}

	@Test
	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}