package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_Mix_Implicit_Explicit {
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
	public void TC_01_Element_Found() {
		
		//Element có xuất hiện và không cần chờ hết timeout
		//Dù có set cả 2 loại wait thì ko ảnh hưởng
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		
		//Implicit Wait: Chỉ apply cho findelement/findelements
		//Explicit Wait: Cho các điều kiện của element
		
		driver.get("https://www.facebook.com/");
		
		//Explicit
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("Thời gian kết thúc của explicit:" + getTimeStamp());
		
		//Implicit
		System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());
		
		
	}

	@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");
		
		//Implicit
		System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());	
		}
		
		//output
		//Cơ chế tìm lại sau 0,5s
		//Khi hết timeout sẽ đánh fail testca
		//Throw ra 1 exception : Nosuch element

		
	}

	@Test
	public void TC_03_Element_Not_Found() {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
		
		//Explicit
		System.out.println("Thời gian bắt đầu của explicit :" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit :" + getTimeStamp());
		}	
	}

	@Test
	public void TC_04_Element_Not_Found_Explicit_By() {
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
		
		//Explicit - By là tham số nhận vào của hàm explicit - visibilityOfElementLocated (by)
		//Implicit = 0
		//Tổng thời gian 
		System.out.println("Thời gian bắt đầu của explicit :" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit :" + getTimeStamp());
		}	
	}
		
	
	@Test
	public void TC_05_Element_Not_Found_Mix_Findelement() {
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
		// Nhận thời gian của implicitwait
		
		System.out.println("Thời gian bắt đầu của explicit :" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit :" + getTimeStamp());
		}	
	}
	@Test
	public void TC_05_Element_Not_Found_Mix_() {
		
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
		// KẾT QUẢ chạy 0s báo lỗi do không tìm được element ( sử dụng hàm findelement với tham số là BY = 0)
		
		
		System.out.println("Thời gian bắt đầu của explicit :" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit :" + getTimeStamp());
		}		
	
	}
	@AfterClass
	public void afterClass() {
		
		
		driver.quit();
	}
	//Show ra time-stamp tai thời điểm gọi hàm này
			//Time-stamp = ngày - giờ - phút - giây
	public String getTimeStamp() {
			Date date = new Date();
			return date.toString();
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	
	}
}