package webdriver;

import java.io.File;
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

public class Topic15_ExplicitWait_TC06_07 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	//Image name
	String hanoi = "hanoi.jpg";
	String danang = "danang.jpg";
	String saigon = "saigon.jpg";
	
	//Upload file folder
	String uploadFileFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;
			
	//Image path
	String hanoiFilePath = uploadFileFolderPath + hanoi;
	String danangFilePath = uploadFileFolderPath + danang;
	String saigonFilePath = uploadFileFolderPath + saigon;
	
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("MAC OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		} else {
		}
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_06_Date_Time() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//Wait cho datepicker hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		//Verify cho select day là ko đc chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		//Wait cho ngày 26 có thể click đc
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='26']")));
		
		driver.findElement(By.xpath("//a[text()='26']")).click();
		//Wait cho Ajax biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='26']")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Tuesday, July 26, 2022");
		
	}

	@Test
	public void TC_07_Uploadfile() {
		
		driver.get("https://gofile.io/uploadFiles");
		
		explicitWait = new WebDriverWait(driver, 45);
		// ĐỢI CHO NÚT UPLOAD HIỂN THỊ
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-sm-auto>button.uploadButton")));
		
		// UP FILE LÊN
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hanoiFilePath + "\n" + danangFilePath + "\n" + saigonFilePath );
		
		//ĐỢI CHO PROGRESS BAR XUẤT HIỆN
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#rowUploadProgress div.progress-bar")));
		
		//ĐỢI CHO MESSAGE THÀNH CÔNG HIỂN THỊ
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		
		//VERIFY MESSAGE THÀNH CÔNG HIỂN THỊ
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		// Đợi cho nút show file hiển thị và click vào
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#rowUploadSuccess-showFiles"))).click();
		
		//Wait + Verify nút dowload có hiên thị trên 3 file mới upload
		
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + danang + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + hanoi + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + saigon +"']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
	
		
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