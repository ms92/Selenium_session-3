package variousConcepts;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaunchBrowser {
	WebDriver driver;

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://techfios.com/billing/?ng=admin/" + "");

	}

	@Test
	public void loginTest() {

		// type name = value;
		int a = 5;

		// storing web element
		WebElement USERNAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_ELEMENT = driver.findElement(By.xpath("/html/body/div/div/div/form/div[3]/button"));

		// storing using By class
		By USERNAME_FIELD_LOCATOR = By.xpath("//input[@id='username']");
		By PASSWORD_FIELD_LOCATOR = By.xpath("//input[@id='password']");
		By SIGNIN_FIELD_LOCATOR = By.xpath("/html/body/div/div/div/form/div[3]/button");

		USERNAME_ELEMENT.clear();
		driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys("demo@techfios.com");
		PASSWORD_ELEMENT.sendKeys("bbc123");
		SIGNIN_ELEMENT.click();

		By DASHBOARD_TITLE_FIELD_LOCATOR = By.xpath("// h2[text()=' Dashboard ']");
		boolean pageTitleDisplayStatus;
		try {
			WebElement DASHBOARD_TITLE_ELEMENT = driver.findElement(By.xpath("// h2[text()=' Dashboard ']"));
			pageTitleDisplayStatus = true;
		} catch (Exception e) {
			pageTitleDisplayStatus = false;
			e.printStackTrace();
		}

		WebDriverWait wait = new WebDriverWait(driver, 5); // fluent wait
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DASHBOARD_TITLE_FIELD_LOCATOR));
		//wait.until(ExpectedConditions.visibilityOf(SIGNIN_ELEMENT));

		Assert.assertTrue("Dashboard is not available!", pageTitleDisplayStatus);

	}

	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
