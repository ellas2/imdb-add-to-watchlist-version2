package preconditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class ChromeWebDriver implements AnyWebDriver {
	private WebDriver driver = null;
	
	public ChromeWebDriver(String driverExecPath) {
		System.setProperty("webdriver.chrome.driver", driverExecPath);
	}
	
	public WebDriver initializeAtUrl(String url) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	
	//Calling this will return null until initializeAtAdreess is called
	public WebDriver getWebDriver() {
		return driver;
	}
}

