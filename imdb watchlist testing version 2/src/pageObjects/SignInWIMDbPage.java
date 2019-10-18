package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInWIMDbPage {
	private WebDriver driver = null;
	private By emailTextBox = By.name("email");
	private By passwordTextBox = By.name("password");
	private By submit = By.id("signInSubmit");
	
	public SignInWIMDbPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterEmailAddr(String addr) {
		driver.findElement(emailTextBox).sendKeys(addr);
	}
	
	public void enterPassword(String password) {
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys(password);
	}
	
	public void submitSignIn() {
		driver.findElement(submit).click();	
	}
	
	public void fullSignIn(String email, String password) {
		enterEmailAddr(email);
		enterPassword(password);
		submitSignIn();
	}
}
