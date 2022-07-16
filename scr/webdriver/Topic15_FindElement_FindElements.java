package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic15_FindElement_FindElements {
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
	public void TC_01_FindElement() {
		//-Tìm thấy duy nhất 1 element/ node
		//- Tìm thấy và thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên không cần chờ time out 15s
		driver.findElement(By.cssSelector("input#email"));
		
		//Tìm thấy nhiều hơn 1 element/node
		//Nó sẽ thao tác vs node đầu tiên
		//Ko quan tâm các node còn lại
		//Trong trường hợp bạn bắt sai locator thì nó sai
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("han@gmail.com");
		
		//Không tìm thấy element/ node nào
		//Có cơ chế tìm lại = 0,5s sẽ tìm lại 1 lần
		//Nếu trong thời gian tìm lại mà thấy element thì thỏa mãn đk -> pass
		//Nếu hết thời gian 15s vẫn ko thấy thì
		//+ Đánh fail testcase + ko chạy step tiếp theo.
		//+Throw ra 1 ngoại lệ : NoSuchElementException
		driver.findElement(By.cssSelector("input[type='check']"));
		
	}

	@Test
	public void TC_02_FindElements() {
		driver.get("https://www.facebook.com/");
		//Tìm thấy duy nhất 1 element/node
		//Tìm thấy và lưu nó vào list = 1 element
		//Vì nó tìm thấy nên ko cần phải chờ hết timeout 15s
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number = " + elements.size());;
		
		//Tìm thấy nhiều hơn 1 element/node
		//Tìm thấy và lưu nó vào list = element tương ứng
		elements = driver.findElements(By.cssSelector("input"));
		System.out.println("List element number = "+ elements.size());
		
		//Không tìm thấy element/ node nào
		//Có cơ chế tìm lại = 0,5 giây sẽ tìm lại 1 lần
		//Nếu trong thời gian đang tìm lại mà thấy element thì thỏa mãn đk - Pass
		//Nếu hết thời gian (15s) mà vẫn không thấy element
		//+ Ko đánh fail testcase + vẫn chạy step tiếp theo
		elements = driver.findElements(By.cssSelector("input[type='check']"));
		System.out.println("List element number = "+ elements.size());
		
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