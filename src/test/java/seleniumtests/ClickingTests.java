package seleniumtests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ClickingTests {

	static ExtentReports report = new ExtentReports("C:/DevEnv/DemoQAReport.html", true);
	static WebDriver driver;
	ExtentTest test;
	Actions builder = new Actions(driver);

	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "C:/DevEnv/webdriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Before
	public void getURL() {
		driver.get("http://demoqa.com");
	}

	@After
	public void endTest() {
		report.endTest(test);
	}

	@AfterClass
	public static void destroy() {
		report.flush();
		driver.quit();
	}

	@Test
	public void draggableTest() throws InterruptedException {
		test = report.startTest("Draggable Test");
		test.log(LogStatus.INFO, "Draggable Test started.");

		driver.findElement(By.cssSelector("#menu-item-140 > a")).click();
		if (driver.getTitle().equals("Draggable | Demoqa")) {
			test.log(LogStatus.PASS, "Navigate to 'draggable' page.");
		} else {
			test.log(LogStatus.FAIL, "Did not navigate to 'draggable' page.");
		}
		Thread.sleep(500);

		WebElement dragBox = driver.findElement(By.cssSelector("#draggable"));
		Point startPosition = dragBox.getLocation();
		int moveByX = 300;
		int moveByY = 100;
		builder.dragAndDropBy(dragBox, moveByX, moveByY).perform();
		Thread.sleep(500);

		Point endPosition = dragBox.getLocation();

		if (endPosition.getX() != startPosition.getX() && endPosition.getY() != startPosition.getY()) {
			test.log(LogStatus.PASS, "Box has been moved.");
		} else {
			test.log(LogStatus.FAIL, "Box has not been moved.");
		}

		if (endPosition.getX() == startPosition.getX() + moveByX
				&& endPosition.getY() == startPosition.getY() + moveByY) {
			test.log(LogStatus.PASS, "Box dragged correctly by (" + moveByX + ", " + moveByY + ")");
		} else {
			test.log(LogStatus.FAIL, "Box dragged incorrectly by (" + moveByX + ", " + moveByY + ")");
		}
	}

	@Test
	public void droppableTest() throws InterruptedException {
		test = report.startTest("Droppable Test");
		test.log(LogStatus.INFO, "Droppable Test started");

		driver.findElement(By.cssSelector("#menu-item-141 > a")).click();
		if (driver.getTitle().equals("Droppable | Demoqa")) {
			test.log(LogStatus.PASS, "Navigate to 'droppable' page.");
		} else {
			test.log(LogStatus.FAIL, "Did not navigate to 'droppable' page.");
		}
		Thread.sleep(500);

		WebElement dragBox = driver.findElement(By.cssSelector("#draggableview"));
		int moveByX = 150;
		int moveByY = 50;
		builder.dragAndDropBy(dragBox, moveByX, moveByY).perform();
		Thread.sleep(500);

		WebElement dropBox = driver.findElement(By.cssSelector("#droppableview > p"));
		String dropBoxText = dropBox.getText();
		if (dropBoxText.equals("Dropped!")) {
			test.log(LogStatus.PASS, "Drop box on other box");
		} else {
			test.log(LogStatus.FAIL, "Did not drop box on other box");
		}
	}

	@Test
	public void resizableTest() throws InterruptedException {
		test = report.startTest("Resizable Test");
		test.log(LogStatus.INFO, "Resizable Test Started");

		driver.findElement(By.cssSelector("#menu-item-143 > a")).click();
		if (driver.getTitle().equals("Resizable | Demoqa")) {
			test.log(LogStatus.PASS, "Navigate to 'resizable' page.");
		} else {
			test.log(LogStatus.FAIL, "Did not navigate to 'resizable' page.");
		}
		Thread.sleep(500);

		WebElement dragArrow = driver.findElement(By.cssSelector(
				"#resizable > div.ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se"));
		Point startPosition = dragArrow.getLocation();
		int moveByX = 300;
		int moveByY = 150;
		builder.dragAndDropBy(dragArrow, moveByX, moveByY).perform();
		Thread.sleep(500);

		Point endPosition = dragArrow.getLocation();
		if (endPosition.getX() == startPosition.getX() + (moveByX - 17)
				&& endPosition.getY() == startPosition.getY() + (moveByY - 17)) {
			test.log(LogStatus.PASS, "Increased box dimensions by (" + moveByX + ", " + moveByY + ")");
		} else {
			test.log(LogStatus.FAIL, "Increased box dimensions by (" + moveByX + ", " + moveByY + ")");
		}
	}

	@Test
	public void selectableTest() throws InterruptedException {
		test = report.startTest("Selectable Test");
		test.log(LogStatus.INFO, "Selectable Test Started");

		driver.findElement(By.cssSelector("#menu-item-142 > a")).click();
		if (driver.getTitle().equals("Selectable | Demoqa")) {
			test.log(LogStatus.PASS, "Navigate to 'selectable' page.");
		} else {
			test.log(LogStatus.FAIL, "Did not navigate to 'selectable' page.");
		}
		Thread.sleep(500);

		WebElement selectBox = driver.findElement(By.cssSelector("#selectable > li:nth-child(3)"));
		selectBox.click();
		Thread.sleep(500);

		try {
			WebElement boxSelected = driver.findElement(
					By.cssSelector("#selectable > li.ui-widget-content.ui-corner-left.ui-selectee.ui-selected"));
			test.log(LogStatus.INFO, "Something is selected");

			if (boxSelected.getText().equals("Item 3")) {
				test.log(LogStatus.PASS, "Selected Item 3");
			} else {
				test.log(LogStatus.FAIL, "Did not select Item 3");
			}
		} catch (NoSuchElementException e) {
			test.log(LogStatus.INFO, "Nothing is selected");
		}
	}

	@Test
	public void sortableTest() throws InterruptedException {
		test = report.startTest("Sortable Test");
		test.log(LogStatus.INFO, "Sortable Test Started");

		driver.findElement(By.cssSelector("#menu-item-151 > a")).click();
		if (driver.getTitle().equals("Sortable | Demoqa")) {
			test.log(LogStatus.PASS, "Navigate to 'sortable' page.");
		} else {
			test.log(LogStatus.FAIL, "Did not navigate to 'sortable' page.");
		}
		Thread.sleep(500);

		// This section is an absolute mess and I wish I knew a way to do it better
		WebElement selectBox1 = driver.findElement(By.cssSelector("#sortable > li:nth-child(1)"));
		WebElement selectBox2 = driver.findElement(By.cssSelector("#sortable > li:nth-child(2)"));
		WebElement selectBox3 = driver.findElement(By.cssSelector("#sortable > li:nth-child(3)"));
		WebElement selectBox4 = driver.findElement(By.cssSelector("#sortable > li:nth-child(4)"));
		WebElement selectBox5 = driver.findElement(By.cssSelector("#sortable > li:nth-child(5)"));
		WebElement selectBox6 = driver.findElement(By.cssSelector("#sortable > li:nth-child(6)"));
		WebElement selectBox7 = driver.findElement(By.cssSelector("#sortable > li:nth-child(7)"));

		Point selectBox1StartPosition = selectBox1.getLocation();
		Point selectBox2StartPosition = selectBox2.getLocation();
		Point selectBox3StartPosition = selectBox3.getLocation();
		Point selectBox4StartPosition = selectBox4.getLocation();
		Point selectBox5StartPosition = selectBox5.getLocation();
		Point selectBox6StartPosition = selectBox6.getLocation();
		Point selectBox7StartPosition = selectBox7.getLocation();

		builder.dragAndDrop(selectBox7, selectBox1).perform();
		builder.dragAndDrop(selectBox6, selectBox1).perform();
		builder.dragAndDrop(selectBox5, selectBox1).perform();
		builder.dragAndDrop(selectBox4, selectBox1).perform();
		builder.dragAndDrop(selectBox3, selectBox1).perform();
		builder.dragAndDrop(selectBox2, selectBox1).perform();
		test.log(LogStatus.INFO, "Boxes sorted.");
		Thread.sleep(1000);

		if (selectBox1StartPosition.equals(selectBox7.getLocation())
				&& selectBox2StartPosition.equals(selectBox6.getLocation())
				&& selectBox3StartPosition.equals(selectBox5.getLocation())
				&& selectBox4StartPosition.equals(selectBox4.getLocation())
				&& selectBox5StartPosition.equals(selectBox3.getLocation())
				&& selectBox6StartPosition.equals(selectBox2.getLocation())
				&& selectBox7StartPosition.equals(selectBox1.getLocation())) {
			test.log(LogStatus.PASS, "Boxes sorted in reverse order.");
		} else {
			test.log(LogStatus.FAIL, "Boxes not sorted in reverse order.");
		}
	}

}
