package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_10_User_Interaction{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	JavascriptExecutor jsExecutor;
	Alert alert;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	
	}

	@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		
		WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
		action.moveToElement(ageTextbox).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath(" //div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
			
	}

	@Test
	public void TC_02_Hover_To_Element() {
		driver.get("https://www.myntra.com/");
		WebElement Kidslink = driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[@href='/shop/kids']"));
		action.moveToElement(Kidslink).perform();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(), "Kids Home Bath");	
	}

	@Test
	public void TC_03_Hover_To_Element() {
		driver.get("https://fptshop.com.vn/");
		WebElement Phonemenu = driver.findElement(By.xpath("//a[@title='ĐIỆN THOẠI']"));
		action.moveToElement(Phonemenu).perform();
		driver.findElement(By.xpath("//a[@title='Apple (iPhone)']")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://fptshop.com.vn/dien-thoai/apple-iphone");
		
	}
	
	@Test
	public void TC_04_Click_And_Hold_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// store all 12 element
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable li"));
		
		//12
		Assert.assertEquals(allNumbers.size(), 12);
		
		//Click và giữ chuột tại số 1
				action.clickAndHold(allNumbers.get(0))
				
				//Hover chuôt đến số 11
				.moveToElement(allNumbers.get(10))
				
				//Nhả chuột trái ra
				.release()
				
				//Thực thi
				.perform();	
		allNumbers = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(allNumbers.size(), 9);
	}
	
	@Test
	public void TC_05_Click_And_Select_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		// đè nút control
		action.keyDown(Keys.CONTROL).perform();
		// chọn số
		action.click(allNumbers.get(0)).click(allNumbers.get(2)).click(allNumbers.get(5)).click(allNumbers.get(10));
		//nhả control ra
		action.keyUp(Keys.CONTROL).perform();
		
		allNumbers = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(allNumbers.size(), 4);
		
	}
	@Test
	public void TC_06_Double_Click() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement doubleClickme = driver.findElement(By.xpath("//button[text()='Double click me']"));
		
		//Scroll to element
		//True : mép trên của element và kéo element lên trên cùng
		//False: kéo xuống mép dưới của element và kéo element xuống dưới cùng
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickme);
	
		action.doubleClick(doubleClickme).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
		
	}
	
	@Test
	public void TC_07_Click_And_Select_Element() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightclick = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
		action.contextClick(rightclick).perform();
		sleepInSecond(3);
		
		WebElement quitmenu = driver.findElement(By.cssSelector("li.context-menu-icon-quit"));
		action.moveToElement(quitmenu).perform();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		
		action.click(quitmenu).perform();
		sleepInSecond(2);
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"clicked: quit");
		alert.accept();
		sleepInSecond(2);
		Assert.assertFalse(quitmenu.isDisplayed());
		
		
	}
	
	@Test
	public void TC_08_Drag_and_drop_html4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement drag = driver.findElement(By.cssSelector("div#draggable"));
		WebElement drop = driver.findElement(By.cssSelector("div#droptarget"));
		action.dragAndDrop(drag, drop).perform();
		sleepInSecond(3);
		
		//TEXT
		Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");
		//Backgroundcolor
		String LogionButtonCorlorHexa = Color.fromString(drop.getCssValue("background-color")).asHex().toUpperCase();
		
		Assert.assertEquals(LogionButtonCorlorHexa, "#03A9F4");	
		
	}
	@Test
	public void TC_09_Drag_and_drop_html5() {
		
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	
	}
}