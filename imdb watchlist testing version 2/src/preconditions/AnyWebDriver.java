package preconditions;
import org.openqa.selenium.WebDriver;

public interface AnyWebDriver {
		
	public WebDriver initializeAtUrl(String url);
	
	//Calling this will return null until initializeAtAdreess is called
	public WebDriver getWebDriver();
}
