package seleniumtests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DemoSiteTest {

	static ExtentReports report = new ExtentReports("C:/DevEnv/DemoSiteReport.html", true);
	static WebDriver driver;

	ExtentTest test;

	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "C:/DevEnv/webdriver/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:/DevEnv/webdriver/geckodriver.exe");
	}

	@Before
	public void getURL() {
		driver.get("http://www.thedemosite.co.uk/");
	}

	@After
	public void cleanUp() {
		report.endTest(test);
		driver.close();
	}

	@AfterClass
	public static void destroy() {
		report.flush();
		driver.quit();
	}

	@Test
	public void createUserChrome() throws InterruptedException {
		test = report.startTest("Chrome: Create new user and verify login");

		driver = new ChromeDriver();

		test.log(LogStatus.INFO, "Browser started.");
		Thread.sleep(500);

		driver.findElement(By.cssSelector(
				"body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(6)"))
				.click();
		Thread.sleep(500);
		WebElement createUser = driver.findElement(By.name("username"));
		createUser.sendKeys("Maverick");
		WebElement createPassword = driver.findElement(By.name("password"));
		createPassword.sendKeys("Secure");
		Thread.sleep(500);
		driver.findElement(By.name("FormsButton2")).click();
		Thread.sleep(500);
		test.log(LogStatus.INFO, "Created user.");

		driver.findElement(By.cssSelector(
				"body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(7)"))
				.click();
		Thread.sleep(500);
		WebElement inputUser = driver.findElement((By.name("username")));
		inputUser.sendKeys("Maverick");
		WebElement inputPassword = driver.findElement((By.name("password")));
		inputPassword.sendKeys("Secure");
		Thread.sleep(500);
		driver.findElement(By.name("FormsButton2")).click();
		test.log(LogStatus.INFO, "User login.");

		WebElement loginSuccess = driver.findElement(By.cssSelector(
				"body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b"));
		Thread.sleep(500);
		test.log(LogStatus.INFO, "Verifying login success.");

		String loginState = loginSuccess.getText();
		if (loginState.equals("**Successful Login**")) {
			test.log(LogStatus.PASS, "Login Success!");
			System.out.println("Login Success!");
		} else {
			test.log(LogStatus.FAIL, "Login failure.");
			System.out.println("Login failure.");
		}

		assertEquals(loginSuccess.getText(), "**Successful Login**");
	}

	@Test
	public void createUserFirefox() throws InterruptedException {
		test = report.startTest("Firefox: Create new user and verify login");

		driver = new FirefoxDriver();

		test.log(LogStatus.INFO, "Browser started.");
		Thread.sleep(500);

		driver.findElement(By.cssSelector(
				"body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(6)"))
				.click();
		Thread.sleep(500);
		WebElement createUser = driver.findElement(By.name("username"));
		createUser.sendKeys("Maverick");
		WebElement createPassword = driver.findElement(By.name("password"));
		createPassword.sendKeys("Secure");
		Thread.sleep(500);
		driver.findElement(By.name("FormsButton2")).click();
		Thread.sleep(500);
		test.log(LogStatus.INFO, "Created user.");

		driver.findElement(By.cssSelector(
				"body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(7)"))
				.click();
		Thread.sleep(500);
		WebElement inputUser = driver.findElement((By.name("username")));
		inputUser.sendKeys("Maverick");
		WebElement inputPassword = driver.findElement((By.name("password")));
		inputPassword.sendKeys("Secure");
		Thread.sleep(500);
		driver.findElement(By.name("FormsButton2")).click();
		test.log(LogStatus.INFO, "User login.");

		WebElement loginSuccess = driver.findElement(By.cssSelector(
				"body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b"));
		Thread.sleep(500);
		test.log(LogStatus.INFO, "Verifying login success.");

		String loginState = loginSuccess.getText();
		if (loginState.equals("**Successful Login**")) {
			test.log(LogStatus.PASS, "Login Success!");
			System.out.println("Login Success!");
		} else {
			test.log(LogStatus.FAIL, "Login failure.");
			System.out.println("Login failure.");
		}

		assertEquals(loginSuccess.getText(), "**Successful Login**");

	}
}
